package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	
	private Point startPoint;
	private Point endPoint;
	
	
	//konstruktori
    public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}
	
	
	@Override
	public boolean contains(Point p) {
		if((startPoint.distance(p.getX(), p.getY()) + endPoint.distance(p.getX(), p.getY())) - length() <= 0.05)
			return true;
		return false;
	}
	

	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	@Override
	public void draw(Graphics g) {
		
		if(getOuterColor()!=null)
			g.setColor(getOuterColor());
		else
			g.setColor(Color.GREEN);
		
		g.drawLine(this.getStartPoint().getX(), getStartPoint().getY(), this.getEndPoint().getX(), this.getEndPoint().getY());
		
		
		if (isSelected()) {
			g.setColor(Color.RED);
			g.drawRect(getStartPoint().getX()  - 3, getStartPoint().getY() - 3, 6, 6);
			g.drawRect(getEndPoint().getX() - 3, getEndPoint().getY() - 3, 6, 6);
			g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		
		}
		
	}
	
	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
		int middleByY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}
	
	//metode pristupa
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	

}
