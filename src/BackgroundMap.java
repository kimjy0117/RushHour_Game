import java.awt.*;
import javax.swing.*;

class BackgroundMap extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//도로 생성
			for(int row = 1; row < 7; row++) {
				for(int col = 1; col < 7; col++) {
					g.setColor(Color.DARK_GRAY);
					g.fillRoundRect(row*100+3, col*100+3, 94, 94, 10, 10);
					g.setColor(new Color(192,192,192));
					g.fillRoundRect(row*100, col*100, 94, 94, 10, 10);
				}
			}
			//골인 지점 생성
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect(7*100+3, 3*100+3, 94, 94, 10, 10);
			g.setColor(Color.ORANGE);
			g.fillRoundRect(7*100, 3*100, 94, 94, 10, 10);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Jokerman", Font.ITALIC, 26));
			g.drawString("GOAL!", 700, 340);
			
			//이동 횟수
			g.setFont(new Font("굴림", Font.ITALIC, 50));
			g.setColor(Color.GRAY);
			g.drawString("<이동횟수>", 711, 177);
			g.setColor(Color.WHITE);
			g.drawString("<이동횟수>", 710, 175);
		}
		public void draw(Graphics g) {
			paintComponent(g);
		}
	}