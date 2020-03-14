package shapes;
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
		super(center,radius,innerCol,outerCol);
		setInnerRadius(innerRadius);
	}
	
	
	
	
	public void draw(Graphics g) {
	  
		Graphics2D gr = (Graphics2D)g;
	    g.setColor(getOuterColor());	 
		Ellipse2D e1 = new Ellipse2D.Double((this.getCenter().getX() - this.getInnerRadius()), (this.getCenter().getY() - this.getInnerRadius()), this.getInnerRadius()*2,this.innerRadius*2);		
		Area inner=new Area(e1);
		Ellipse2D e2=new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), this.getRadius()*2, this.getRadius()*2);
		Area outer=new Area(e2);
		outer.subtract(inner);
		gr.draw(outer);	
		g.setColor(getInnerColor());
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
	
	
	
	
	public String toString() {
		
		return super.toString() + "InnerRadius=" + innerRadius +  ")";
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
	public boolean equals(Object obj){
		if(obj instanceof Donut){
			Donut d=(Donut) obj;
			if(getCenter().getX()==d.getCenter().getX() && getCenter().getY()==d.getCenter().getY() && getRadius()==d.getRadius()
				&& getInnerRadius()==d.getInnerRadius() && getOuterColor().equals(d.getOuterColor()) && getInnerColor().equals(d.getInnerColor()))
				return true;
			else
				return false;
		}
		else 
			return false;
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
		

}

