package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends AreaShape implements Movable,Cloneable{
	
	private Point upperLeftPoint;
	private int width;
	private int height;
	
	
	//konstruktori
	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int height, int width) throws Exception {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) throws Exception {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, Color innerCol, Color outerCol) throws Exception {
		this(upperLeftPoint, height, width);
		setInnerColor(innerCol);
		setOuterColor(outerCol);
	}
	
	@Override
	public boolean contains(Point p) {
		
		if (this.getUpperLeftPoint().getX() <= p.getX() 
				&& p.getX()<= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= p.getY()
				&& p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void fill(Graphics g) {
		if(getInnerColor()!=null)
		    g.setColor(getInnerColor());
		else
			g.setColor(Color.blue);
		
		g.fillRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.height);
		
	}
	
	@Override
	public void draw(Graphics g) {
         fill(g);
		if(getOuterColor()!=null)
		    g.setColor(getOuterColor());
		else
			g.setColor(Color.GREEN);
		
		
		g.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.getWidth(), this.height);
	
		
		if (isSelected()) {
			g.setColor(Color.RED);
			g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3 + getWidth(), this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3 + getHeight(), 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() + getWidth() - 3, this.getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}
		
	}
	
	
	
	//metode pristupa
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) throws Exception {
		if(width>=0)
		this.width = width;
		else
			throw new Exception();
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) throws Exception {
		if(height>=0)
		this.height = height;
		else
			throw new Exception();
	}

	@Override
	public void moveOn(int x, int y) {
		upperLeftPoint.setX(x);
		upperLeftPoint.setY(y);
		
	}

	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.setX(upperLeftPoint.getX() + x);
		upperLeftPoint.setY(upperLeftPoint.getY() + y);
	}
	public String toString() {
		return  "(" + "UpperLeftPointX=" + upperLeftPoint.getX() + "," + "UpperLeftPointY=" + upperLeftPoint.getY()
		+ "," + "Height=" + height + "," + "Width=" +width + "," 
				+ "OuterColor="+Integer.toString(getOuterColor().getRGB()) + "," + "InnerColor="+Integer.toString(getInnerColor().getRGB()) + ")";
	}

	public Shape clone() {			
		Rectangle rectangle = null;
		try {
			rectangle = new Rectangle(this.upperLeftPoint, height,width, getInnerColor(),getOuterColor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rectangle;
	}

	public boolean equals(Object obj){
		if(obj instanceof Rectangle){
			Rectangle r=(Rectangle) obj;
			if(upperLeftPoint.getX()==r.getUpperLeftPoint().getX() && upperLeftPoint.getY()==r.getUpperLeftPoint().getY() && height==r.getHeight()
				&&	width==r.getWidth() && getOuterColor().equals(r.getOuterColor()) && getInnerColor().equals(r.getInnerColor()))
				return true;
			else
				return false;
		}
		else 
			return false;
	}
	

	


	

}
