package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Movable,Cloneable {
	
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
	public Line(Point startPoint, Point endPoint, Color col) {
		this(startPoint, endPoint);
		setOuterColor(col);
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

	@Override
	public void moveOn(int x, int y) {
		int middleByX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
		int middleByY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
		int dx=x-middleByX;
		int dy=y-middleByY;
		this.startPoint.moveBy(dx, dy);
		this.endPoint.moveBy(dx, dy);
		
		
	}

	@Override
	public void moveBy(int x, int y) {
		this.startPoint.moveOn(this.startPoint.getX()+x, this.startPoint.getY()+y);
		this.endPoint.moveOn(this.endPoint.getX()+x, this.endPoint.getY()+y);
	}
	
	public String toString() {
		return  "(" + "StartPointX=" + startPoint.getX() + "," + "StartPointY=" + startPoint.getY()
		+ "," +"EndPointX=" + endPoint.getX() + "," + "EndPointY=" + endPoint.getY() + "," + "Color="+Integer.toString(getOuterColor().getRGB()) + ")";
	}
	public Shape clone() {			
		Line line = new Line(this.startPoint, endPoint, getOuterColor());		
		return line;
	}
	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line l=(Line) obj;
			if(startPoint.getX()==l.getStartPoint().getX() && startPoint.getY()==l.getStartPoint().getY() && endPoint.getX()==l.getEndPoint().getX() && endPoint.getY()==l.getEndPoint().getY() && getOuterColor().equals(l.getOuterColor()))
				return true;
			else
				return false;
		}
		else 
			return false;
	}
	
	

}
