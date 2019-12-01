package drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JColorChooser;

public class Donut extends Circle {
	
	private int innerRadius;
	private Color secondOuterColor;
	
	

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
	
	public void draw(Graphics g) {
		super.draw(g);
		//PnlDrawing pn=new PnlDrawing(new FrmDrawing());
		//g.setColor(pn.getBackground());
	
		
		
        
		g.fillOval(this.getCenter().getX() - this.innerRadius, this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius()*2, this.innerRadius*2);
		if(secondOuterColor!=null)
		{
			g.setColor(secondOuterColor);
		}
		else
			g.setColor(Color.GREEN);
		g.drawOval(this.getCenter().getX() - this.innerRadius, this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius()*2, this.innerRadius*2);
		
		
		if (isSelected()) {
			g.setColor(Color.RED);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + getInnerRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - getInnerRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() + getInnerRadius() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - getInnerRadius() - 3, 6, 6);
			
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
	
	public Color getSecondOuterColor() {
		return secondOuterColor;
	}

	public void setSecondOuterColor(Color secondOuterColor) {
		this.secondOuterColor = secondOuterColor;
	}
	
	

}
