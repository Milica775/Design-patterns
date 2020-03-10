package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends AreaShape implements Movable,Cloneable {
	
	private Point center;
	private int radius;
	
	
	//konstruktori
	public Circle() {

	}

	public Circle(Point center, int radius) throws Exception  {
		setCenter(center);
		setRadius(radius);
	}

	public Circle(Point center, int radius, boolean selected) throws Exception  {
		this(center, radius);
		setSelected(selected);
	}
	public Circle(Point center, int radius, Color innerCol,Color outerCol) throws Exception  {
		this(center, radius);
		setInnerColor(innerCol);
		setOuterColor(outerCol);
	}
	@Override
	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}
	
	@Override
	public void fill(Graphics g) {
		if(getInnerColor()!=null)
			g.setColor(getInnerColor());
		else
			g.setColor(Color.RED);
		
		g.fillOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius()*2, this.getRadius()*2);
		
		
	}


	@Override
	public void draw(Graphics g) {
		
		fill(g);
		if(getOuterColor()!=null)
		    g.setColor(getOuterColor());
		
		
		g.drawOval(this.getCenter().getX() - this.radius, getCenter().getY() - getRadius(), this.getRadius()*2, this.getRadius()*2);
		
		
		
		if (isSelected()) {
			g.setColor(Color.RED);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
			
		}
		
	}

	
	//metode pristupa
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		if(radius>=0)
		this.radius = radius;
		else
			throw new Exception();
	}

	@Override
	public void moveOn(int x, int y) {
		center.setX(x);
		center.setY(y);
		
	}

	@Override
	public void moveBy(int x, int y) {
		center.setX(center.getX() + x);
		center.setY(center.getY() + y);
		
	}
	public String toString() {
		return  "(" + "CenterX=" + center.getX() + "," + "CenterY=" + center.getY()
		+ "," + "Radius=" + radius + "," 
				+ "OuterColor="+Integer.toString(getOuterColor().getRGB()) + "," + "InnerColor="+Integer.toString(getInnerColor().getRGB()) + ")";
	}


	public Shape clone() {			
		Circle circle= null;
		try {
			circle = new Circle(this.center, radius, getInnerColor(),getOuterColor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return circle;
	}
	public boolean equals(Object obj){
	if(obj instanceof Circle){
		Circle c=(Circle) obj;
		if(center.getX()==c.getCenter().getX() && c.getCenter().getY()==c.getCenter().getY() && radius==c.getRadius()
			 && getOuterColor().equals(c.getOuterColor()) && getInnerColor().equals(c.getInnerColor()))
			return true;
		else
			return false;
	}
	else 
		return false;
	}

	

	
	
	
	

}
