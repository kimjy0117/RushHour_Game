import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Map1 extends JFrame{
	private MapPanel panel = new MapPanel();
	private BackgroundMap mapPanel;
	private Car_hor Car1;
	private Car_hor sub1Car;
	private Car_hor sub2Car;
	private Car_ver sub3Car;
	private Image backgroundImg = new ImageIcon("image/grass.jpg").getImage();
	private Image goal = new ImageIcon("image/goal.png").getImage();
	private Image sign = new ImageIcon("image/sign.png").getImage();
	private int count=0;
	private long startTime=0;
	private long finishTime=0;
	private long playTime=0;
	private double score=0;
	private boolean finish=false;
	private Timer timer;

	public Map1() {
		//배경 음악
		new BGM();
		
		//키보드 작동
		initListener();
		
		//이미지 가져옴
		goal = new ImageIcon("image/goal.png").getImage();
	    sign = new ImageIcon("image/sign.png").getImage();
	    backgroundImg = new ImageIcon("image/grass.png").getImage();
	    
		//현재 시간
		startTime = System.currentTimeMillis();	 
		
		//4초 후에 Map2를 실행, Map1화면 종료
	    timer = new Timer(4000, new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new Map2();
	    		dispose();
	    	}
	    });
	    
	    //타이머를 한 번만 실행
	    timer.setRepeats(false);
	    
	    //프레임 설정
		setTitle("RUSH HOUR GAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); //자유롭게 그림을 그릴 수 있게 해준다.)
 		setSize(1000, 800);
 		
 		//맵 생성
 		mapPanel = new BackgroundMap();
 		panel.setBounds(0, 0, 1000, 800);
 		add(panel);
 		
 		//자동차 생성
 		Car1 = new Car_hor(Color.RED, 100, 300, 2, 1);
 		Car1.setBounds(0,0,1000,800);
	    panel.add(Car1);
	    
	    sub1Car = new Car_hor(Color.GREEN, 300, 500, 3, 2);
	    sub1Car.setBounds(0,0,1000,800);
	    panel.add(sub1Car);
	    
	    sub2Car = new Car_hor(Color.BLUE, 400, 600, 2, 3);
	    sub2Car.setBounds(0,0,1000,800);
	    panel.add(sub2Car);
	    
	    sub3Car = new Car_ver(Color.WHITE, 400, 500, 3, 4);
	    sub3Car.setBounds(0,0,1000,800);
	    panel.add(sub3Car);
	    
	    //그림을 보이게 설정
	    setVisible(true);
	    
		}
	
	private class MapPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//배경화면 그리기
			g.drawImage(backgroundImg, 0, 0, 1000, 800, this);
			mapPanel.draw(g);
			
			//타이틀
			g.setColor(Color.GRAY);
			g.setFont(new Font("Arial", Font.ITALIC, 70));
			g.drawString("LEVEL 1", 303, 83);
			g.setColor(Color.WHITE);
			g.drawString("LEVEL 1", 300, 80);
			
			//이동횟수
			g.setFont(new Font("굴림", Font.BOLD, 40));
			g.setColor(Color.GRAY);
			g.drawString(Integer.toString(count), 781 , 232);
			g.drawString("번", 826 , 232);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(count), 780 , 230);
			g.drawString("번", 825 , 230);
			
			//자동차를 그림
			Car1.draw(g);
			sub1Car.draw(g);
			sub2Car.draw(g);
			sub3Car.draw(g);
			
			//완주 시 알림창
			if(Car1.x >= 600) {
				
				//현재 시간 가져옴
				finishTime = System.currentTimeMillis(); 
				//소요시간 계산
				playTime = (finishTime - startTime)/1000;
				//소요시간과 이동횟수로 점수를 계산
				score = 100 - playTime*count/10;
				//완주시 finish를 참으로 변경
				finish=true;
				//타이머 실행
				timer.start();
				
				//알림창 구현
				g.drawImage(goal, 0, 50, 800, 600, this);
				g.drawImage(sign, 320, 420, 400, 400, this);
				g.setFont(new Font("굴림", Font.BOLD, 50));
				g.setColor(Color.BLACK);
				g.drawString(Long.toString(playTime), 540 , 540);
				g.drawString("시간:    초", 410 , 540);
				g.drawString(Integer.toString((int)score), 540 , 600);
				g.drawString("점수:    점", 410 , 600);
				
			}	
		}
	}
	
	//충돌감지
    public boolean checkCollision() {
        Rectangle Rcar1 = new Rectangle(Car1.x, Car1.y-50, Car1.width, Car1.height);
        Rectangle Rsub1Car = new Rectangle(sub1Car.x, sub1Car.y-50, sub1Car.width, sub1Car.height);
        Rectangle Rsub2Car = new Rectangle(sub2Car.x, sub2Car.y-50, sub2Car.width, sub2Car.height);
        Rectangle Rsub3Car = new Rectangle(sub3Car.x, sub3Car.y-sub3Car.height, sub3Car.width, sub3Car.height);

        if (Rcar1.intersects(Rsub1Car)) {
            return true;
        }

        if (Rcar1.intersects(Rsub2Car)) {
            return true;
        }
        
        if (Rcar1.intersects(Rsub3Car)) {
            return true;
        }

        if (Rsub1Car.intersects(Rsub2Car)) {
            return true;
        }
        
        if (Rsub1Car.intersects(Rsub3Car)) {
            return true;
        }
        
        if (Rsub2Car.intersects(Rsub3Car)) {
            return true;
        }

        return false;
    }

    //키보드 리스너
	private void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				
					case KeyEvent.VK_1:
						Car1.move();
						sub1Car.stop();
						sub2Car.stop();
						sub3Car.stop();
						break;
						
					case KeyEvent.VK_2:
						Car1.stop();
						sub1Car.move();
						sub2Car.stop();
						sub3Car.stop();
						break;
						
					case KeyEvent.VK_3:
						Car1.stop();
						sub1Car.stop();
						sub2Car.move();
						sub3Car.stop();
						break;
						
					case KeyEvent.VK_4:
						Car1.stop();
						sub1Car.stop();
						sub2Car.stop();
						sub3Car.move();
						break;
						
					case KeyEvent.VK_UP:
						if(sub3Car.Moving()) {
							sub3Car.up();
							//자동차가 충돌시 다시 원위치
							if(checkCollision()) {
								sub3Car.down();
							}
							else
								//자동차가 움직였을 경우 count증가
								count += sub3Car.c;
						}
						break;
						
					case KeyEvent.VK_DOWN:
						if(sub3Car.Moving()) {
							sub3Car.down();
							if(checkCollision()) {
								sub3Car.up();
							}
							else
								count += sub3Car.c;
						}
						break;
						
					case KeyEvent.VK_LEFT:
							if(Car1.Moving()) {
								Car1.left();
								if(checkCollision()) {
									Car1.right();
								}
								else
									count += Car1.c;
							}
							else if(sub1Car.Moving()) {
								sub1Car.left();
								if(checkCollision()) {
									sub1Car.right();
								}
								else
									count += sub1Car.c;
							}
							else if(sub2Car.Moving()) {
								sub2Car.left();
								if(checkCollision()) {
									sub2Car.right();
								}
								else
									count += sub2Car.c;
							}
						break;
						
					case KeyEvent.VK_RIGHT:
						
							if(Car1.Moving()) {
								Car1.right();
								if(checkCollision()) {
									Car1.left();
								}
								else
									count += Car1.c;
							}
							else if(sub1Car.Moving()) {
								sub1Car.right();
								if(checkCollision()) {
									sub1Car.left();
								}
								else
									count += sub1Car.c;
							}
							else if(sub2Car.Moving()) {
								sub2Car.right();
								if(checkCollision()) {
									sub2Car.left();
								}
								else
									count += sub2Car.c;
							}
						break;
					
					default:
						return;
				}
				//1번 자동차가 완주할 경우 더 이상 repaint하지 않음
				if(!finish)
					panel.repaint();
			}
		});
	}
}

