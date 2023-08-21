import java.awt.*;
import javax.swing.*;
class Car_ver extends JPanel{
    private Color color;
    public int x;
    public int y;
    private int size;
    private int id;
    public int width;
    public int height;
    public int c;
    private boolean move;

    public Car_ver(Color color, int x, int y, int size, int id) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size * 100;
        this.id = id;
        this.move = false;
        this.width = 100;
        this.height = size * 100;
        this.c = 0;
        setLocation(this.x, this.y);
    }
    
    public void draw(Graphics g) {
    	int []maint_x = {x, x+80, x+100, x+20};
    	int []maint_y = {y-size+80, y-size+80, y-size+60, y-size+60};
    	int []mainr_x = {x+80, x+100, x+100, x+80};
    	int []mainr_y = {y, y-20, y-size+60, y-size+80};
    	int []window_x = {x, x+80, x+100, x+20};
    	int []window_y = {y-size+20, y-size+20, y-size, y-size};
    	int []headt_x = {x+10, x+90, x+100, x+20};
    	int []headt_y = {y-size+10, y-size+10, y-size, y-size};
    	int []headr_x = {x+80, x+100, x+100, x+80};
    	int []headr_y = {y-size+75, y-size+55, y-size, y-size+20};
    	
    	
    	//자동차 모양
    	g.setColor(color);
        g.fillPolygon(maint_x, maint_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(maint_x, maint_y, 4);
        
        g.setColor(color);
        g.fillRect(x, y-size+80, 80, size-80);
        g.setColor(Color.BLACK);
        g.drawRect(x, y-size+80, 80, size-80);
   
        g.setColor(color);
        g.fillPolygon(mainr_x, mainr_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(mainr_x, mainr_y, 4);
        
        g.setColor(color);
        g.fillRect(x, y-size+20, 80, 55);
        g.setColor(Color.BLACK);
        g.drawRect(x, y-size+20, 80, 55);
        
        g.setColor(color);
        g.fillPolygon(headr_x, headr_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(headr_x, headr_y, 4);
        
        g.setColor(new Color(135,206,250));
        g.fillPolygon(window_x, window_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(window_x, window_y, 4); 
        
        g.setColor(color);
        g.fillPolygon(headt_x, headt_y, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(headt_x, headt_y, 4);
        
        //자동차 바퀴
        g.fillOval(x+100-size/18, y-size+15, size/12, size/10);
        g.fillOval(x+100-size/18, y-size/3, size/12, size/10);
        
        //자동차 번호
        g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.ITALIC, 26));
		g.drawString(Integer.toString(id), x+35 , y-size/2);
    }
    
    //자동차가 움직이는지 boolean오로 return해준다.
    public boolean Moving() {
        return move;
    }


    public void move() {
    	move = true;
    }
    
    public void stop() {
    	move = false;
    }    
    
	public void up() {
		if (move) {
    		if(y > 100 + size) {
    			y = y - 100;
    			//자동차가 움직였으면 1
    			c = 1;
    		}
    		else
    			//자동차가 안움직였으면 0
    			c = 0;
    	setLocation(x, y);
    	}
	}

	public void down() {
		if (move) {
    		if(y < 700) {
    			y = y + 100;
    			c = 1;
    		}
    		else
    			c = 0;
    	setLocation(x, y);
    	}
	}
}
