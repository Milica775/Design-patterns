package drawing;


import java.awt.Color;

import java.awt.Graphics;
import java.awt.Shape;


import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;


public class Donut extends Circle  {
	
	private int innerRadius;
	
	
	
	

	//konstruktori
    public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) throws Exception {
		 super(center,radius);
		   setInnerRadius(innerRadius);	  
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) throws Exception {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	public Donut(Point center, int radius,int innerRadius,Color innerCol,Color outerCol) throws Exception  {
		this(center, radius,innerRadius);
		setInnerColor(innerCol);
		setOuterColor(outerCol);
	}
	
	public void draw(Graphics g) {
	
		 Graphics2D gr = (Graphics2D)g;
		 if(getOuterColor()!=null)
			    g.setColor(getOuterColor());
			else
				g.setColor(Color.BLACK);
		 
		Ellipse2D e1 = new Ellipse2D.Double((this.getCenter().getX() - this.getInnerRadius()), (this.getCenter().getY() - this.getInnerRadius()), this.getInnerRadius()*2,this.innerRadius*2);		
		Area inner=new Area(e1);
		 Ellipse2D e2=new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), this.getRadius()*2, this.getRadius()*2);
		Area outer=new Area(e2);
		outer.subtract(inner);
		gr.draw(outer);
		if(getInnerColor()!=null)
			g.setColor(getInnerColor());
		else
			g.setColor(Color.RED);
		gr.fill(outer);
		
		if (isSelected()) {
			g.setColor(Color.RED);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
			
		}
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return dFromCenter > innerRadius &&
				super.contains(p);
	}
	
	
	//metode pristupa
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) throws Exception {
		if(innerRadius>=0 && innerRadius<this.getRadius())
		this.innerRadius = innerRadius;
		else 
			throw new Exception();
	}
	
	
	public String toString() {
		return  "(" + "CenterX=" + super.getCenter().getX() + "," + "CenterY=" + super.getCenter().getY()
		+ "," + "Radius=" + super.getRadius() + "," + "InnerRadius=" +innerRadius + ","
				+ "OuterColor="+Integer.toString(getOuterColor().getRGB()) + "," + "InnerColor="+Integer.toString(getInnerColor().getRGB()) + ")";		
	}

	public Donut clone() {			
		Donut donut= null;
		try {
			donut = new Donut(this.getCenter(), this.getRadius(),this.getInnerRadius(),this.getInnerColor(),this.getOuterColor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return donut;
	}

}
