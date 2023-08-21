import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameIntro extends JFrame{
	private IntroPanel panel = new IntroPanel();
	private Image introImg = new ImageIcon("image/gameIntro.png").getImage();
	private Image startImg = new ImageIcon("image/start.png").getImage();
	private Image exitImg = new ImageIcon("image/exit.png").getImage();
	
	public GameIntro() {
		//배경 음악 재생
		new BGM();
		
		introImg = new ImageIcon("image/gameIntro.png").getImage();
		startImg = new ImageIcon("image/start.png").getImage();
		exitImg = new ImageIcon("image/exit.png").getImage();
		
		//마우스 리스너
		initListener();
		
		//프레임 설정
		setTitle("RUSH HOUR GAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setSize(1000, 800);
		setVisible(true);
	}
	
	private class IntroPanel extends JPanel {
		public void paintComponent(Graphics g) {
		super.paintComponent(g);
			
		//배경화면 그리기
		g.drawImage(introImg, 0, 0, 1000, 800, this);
		
		//타이틀
		g.setColor(Color.GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		g.drawString("RUSH HOUR", 194, 254);
		g.setColor(Color.WHITE);
		g.drawString("RUSH HOUR", 190, 250);
		
		//버튼 그리기
		g.setColor(Color.GRAY);
        g.fillRect(343, 398, 294, 97);
		g.drawImage(startImg, 335, 390, 300, 100, this);
		
		g.setColor(Color.GRAY);
        g.fillRect(343, 548, 296, 97);
		g.drawImage(exitImg, 335, 540, 300, 100, this);
		}
	}
	
	private void initListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				if(x>=335 && x<=635) {
					if(y>=390 && y<=490) {
						new Map1();
						dispose();
					}
				}
				if (x>=335 && x<=635) {
					if(y>=540 && y<=640) {
						System.exit(0);
					}
				}
				
			}
		});
	}
}
