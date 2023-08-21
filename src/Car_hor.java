import java.awt.*;
import javax.swing.*;

class Car_hor extends JPanel{
    private Color color;
    public int x;
    public int y;
    private int size;
    private int id;
    public int width;
    public int height;
    public int c;
    private boolean move;
    
    public Car_hor(Color color, int x, int y, int size, int id) {
        this.color = color;
        this.x = x;
        this.y = y+50;
        this.size = size * 100 - 20;
        this.id = id;
        this.move = false;
        this.width = size * 100;
        this.height = 100;
        this.c = 0;
        setLocation(this.x, this.y);
    }
    
    public void draw(Graphics g) {
    	//자동차 부품 좌표
    	int []main_x = {x, x, x+size, x+size, x+size*4/5, x+size*3/5, x+size*3/10, x+size/5};
    	int []main_y = {y, y+40, y+40, y, y, y-40, y-40, y};
    	int []bonnet_x = {x, x+size, x+size+20, x+20};
    	int []bonnet_y = {y, y, y-20, y-20};
    	int []window_x = {x+size*3/5, x+size*4/5, x+size*4/5+20, x+size*3/5+20};
    	int []window_y = {y-40, y, y-20, y-60};
    	int []front_x = {x+size, x+size, x+size+20, x+size+20};
    	int []front_y = {y, y+40, y+20, y-20};
    	int []top_x = {x+size*3/10+20, x+size*3/10, x+size*3/5, x+size*3/5+20};
    	int []top_y = {y-60, y-40, y-40, y-60};
    	
    	//자동차 모양
    	g.setColor(color);
        g.fillPolygon(bonnet_x, bonnet_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(bonnet_x, bonnet_y, 4);
        
        g.setColor(new Color(135,206,250));
        g.fillPolygon(window_x, window_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(window_x, window_y, 4);
        
        g.setColor(color);
        g.fillPolygon(front_x, front_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(front_x, front_y, 4);
        
        g.setColor(color);
        g.fillPolygon(top_x, top_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(top_x, top_y, 4);
        
        g.setColor(color);
        g.fillPolygon(main_x, main_y, 8);
        g.setColor(Color.BLACK);
        g.drawPolygon(main_x, main_y, 8);
        
        //자동차 바퀴
        g.fillOval(x+size/4, y+40-size/16, size/8, size/8);
        g.fillOval(x+size*5/8, y+40-size/16, size/8, size/8);
        
        //자동차 번호
        g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.ITALIC, 26));
		g.drawString(Integer.toString(id), x+size/2 , y+10);
    }
    
    //자동차가 움직이는지 boolean으로 return
    public boolean Moving() {
        return move;
    }

    public void move() {
    	move = true;
    }
    
    public void stop() {
    	move = false;
    }
    
    public void left() {
    	if (move) {
    		if(x > 100 && x < 600 ) {
    			x = x - 100;
    			//움직였으면 1
    			c = 1;
    		}
    		else
    			//안움직였으면 0
    			c = 0;
    		setLocation(x, y);
    	}
    }

    public void	right() {
    	if (move) {
    		//1번 차는 골인지점의 x좌표가 100만큼 더 크기 때문에 따로 설정
    		if(id == 1) {
	    		if(x < 700 - size) {
	    			x = x + 100;
	    			c = 1;
	    		}
	    		else
	    			c = 0;
    		}
    		else {
    			if(x < 600 - size) {
    				x = x + 100;
    				c = 1;
    			}
    			else
    				c = 0;
    		}
    	setLocation(x, y);
    	}
    }
}