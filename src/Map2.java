import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Map2 extends JFrame{
	private MapPanel panel = new MapPanel();
	private BackgroundMap mapPanel;
	private Car_hor Car1;
	private Car_hor sub1Car;
	private Car_hor sub2Car;
	private Car_ver sub3Car;
	private Car_ver sub4Car;
	private Image backgroundImg = new ImageIcon("image/sky.png").getImage();
	private Image goal = new ImageIcon("image/goal.png").getImage();
	private Image sign = new ImageIcon("image/sign.png").getImage();
	private int count=0;
	private long startTime=0;
	private long finishTime=0;
	private long playTime=0;
	private double score=0;
	private boolean finish=false;
	private Timer timer;
	
	public Map2() {
		initListener();
		
		goal = new ImageIcon("image/goal.png").getImage();
		sign = new ImageIcon("image/sign.png").getImage();
		backgroundImg = new ImageIcon("image/sky.png").getImage();
		
		startTime = System.currentTimeMillis();
		
		timer = new Timer(4000, new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new Map3();
	    		dispose();
	    	}
	    });
	    timer.setRepeats(false); 
	    
	    setTitle("RUSH HOUR GAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); //자유롭게 그림을 그릴 수 있게 해준다.)
 		setSize(1000, 800);
 		
 		mapPanel = new BackgroundMap();
 		panel.setBounds(0, 0, 1000, 800);
 		add(panel);	 
 		
 		Car1 = new Car_hor(Color.RED, 200, 300, 2, 1);
 		Car1.setBounds(0,0,1000,800);
	    panel.add(Car1);
	    
	    sub1Car = new Car_hor(Color.ORANGE, 300, 500, 3, 2);
	    sub1Car.setBounds(0,0,1000,800);
	    panel.add(sub1Car);
	   
	    sub2Car = new Car_hor(Color.CYAN, 100, 200, 2, 3);
	    sub2Car.setBounds(0,0,1000,800);
	    panel.add(sub2Car);
	    
	    sub3Car = new Car_ver(Color.YELLOW, 400, 500, 3, 4);
	    sub3Car.setBounds(0,0,1000,800);
	    panel.add(sub3Car);
	    
	    sub4Car = new Car_ver(Color.WHITE, 100, 600, 3, 5);
	    sub4Car.setBounds(0,0,1000,800);
	    panel.add(sub4Car);
	    	    
	    setVisible(true);
	}
	
	private class MapPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(backgroundImg, 0, 0, 1000, 800, this);
			mapPanel.draw(g);
			
			g.setColor(Color.GRAY);
			g.setFont(new Font("Arial", Font.ITALIC, 70));
			g.drawString("LEVEL 2", 303, 83);
			g.setColor(Color.WHITE);
			g.drawString("LEVEL 2", 300, 80);
			
			g.setFont(new Font("굴림", Font.BOLD, 40));
			g.setColor(Color.GRAY);
			g.drawString(Integer.toString(count), 781 , 232);
			g.drawString("번", 826 , 232);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(count), 780 , 230);
			g.drawString("번", 825 , 230);
			
			sub2Car.draw(g);
			Car1.draw(g);
			sub1Car.draw(g);
			sub3Car.draw(g);
			sub4Car.draw(g);
			
			if(Car1.x >= 600) {
				finishTime = System.currentTimeMillis(); 
				playTime = (finishTime - startTime)/1000;
				score = 100 - playTime*count/10;
				finish = true;
				timer.start();
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
	
    public boolean checkCollision() {
        Rectangle Rcar1 = new Rectangle(Car1.x, Car1.y-50, Car1.width, Car1.height);
        Rectangle Rsub1Car = new Rectangle(sub1Car.x, sub1Car.y-50, sub1Car.width, sub1Car.height);
        Rectangle Rsub2Car = new Rectangle(sub2Car.x, sub2Car.y-50, sub2Car.width, sub2Car.height);
        Rectangle Rsub3Car = new Rectangle(sub3Car.x, sub3Car.y-sub3Car.height, sub3Car.width, sub3Car.height);
        Rectangle Rsub4Car = new Rectangle(sub4Car.x, sub4Car.y-sub4Car.height, sub4Car.width, sub4Car.height);
        
        if (Rcar1.intersects(Rsub1Car)) {
            return true;
        }

        if (Rcar1.intersects(Rsub2Car)) {
            return true;
        }
        
        if (Rcar1.intersects(Rsub3Car)) {
            return true;
        }
        
        if (Rcar1.intersects(Rsub4Car)) {
            return true;
        }

        if (Rsub1Car.intersects(Rsub2Car)) {
            return true;
        }
        
        if (Rsub1Car.intersects(Rsub3Car)) {
            return true;
        }
        
        if (Rsub1Car.intersects(Rsub4Car)) {
            return true;
        }
        
        if (Rsub2Car.intersects(Rsub3Car)) {
            return true;
        }
        
        if (Rsub2Car.intersects(Rsub4Car)) {
            return true;
        }

        if (Rsub3Car.intersects(Rsub4Car)) {
            return true;
        }
        
        return false;
    }

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
						sub4Car.stop();
						break;
						
					case KeyEvent.VK_2:
						Car1.stop();
						sub1Car.move();
						sub2Car.stop();
						sub3Car.stop();
						sub4Car.stop();
						break;
						
					case KeyEvent.VK_3:
						Car1.stop();
						sub1Car.stop();
						sub2Car.move();
						sub3Car.stop();
						sub4Car.stop();
						break;
						
					case KeyEvent.VK_4:
						Car1.stop();
						sub1Car.stop();
						sub2Car.stop();
						sub3Car.move();
						sub4Car.stop();
						break;
						
					case KeyEvent.VK_5:
						Car1.stop();
						sub1Car.stop();
						sub2Car.stop();
						sub3Car.stop();
						sub4Car.move();
						break;
						
					case KeyEvent.VK_UP:
						if(sub3Car.Moving()) {
							sub3Car.up();
							if(checkCollision()) {
								sub3Car.down();
							}
							else
								count += sub3Car.c;
						}
						else if(sub4Car.Moving()) {
							sub4Car.up();
							if(checkCollision()) {
								sub4Car.down();
							}
							else
								count += sub4Car.c;
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
						else if(sub4Car.Moving()) {
							sub4Car.down();
							if(checkCollision()) {
								sub4Car.up();
							}
							else
								count += sub4Car.c;
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
				if(!finish)
					panel.repaint();
			}
		});
	}
}
