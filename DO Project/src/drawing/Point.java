package drawing;

import java.awt.Color;

import java.awt.Graphics;

public class Point extends Shape {
	
	private int x;
	private int y;
	
	
	//konstruktori
    public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		setY(y);
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}
	
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}
	

	@Override
	public boolean contains(Point p) {
		return this.distance(p.getX(), p.getY()) <=3;
	}
	

	@Override
	public void draw(Graphics g) {
		if(getOuterColor()!=null)
			g.setColor(getOuterColor());
		else
			g.setColor(Color.GREEN);
		
		g.drawLine(this.x-2, this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
	
		
		if (isSelected()) {
			g.setColor(Color.RED);
			g.drawRect(this.x-3, this.y-3, 6, 6);
			
		}
		
	}
	
	
	//metode pristupa
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	

}
