package hexagon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import drawing.AreaShape;
import drawing.Circle;
import drawing.Point;
import drawing.Shape;

public class HexagonAdapter extends AreaShape implements Cloneable{
	
	private Hexagon hexagon;
	
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(int xx,int yy,int rr) {
	    	hexagon=new Hexagon(xx,yy,rr);	    
	    }
	public HexagonAdapter(int xx,int yy,int rr,Color areaColor, Color borderColor) {
    	hexagon=new Hexagon(xx,yy,rr);
    	hexagon.setAreaColor(areaColor);
    	hexagon.setBorderColor(borderColor);
    }
	@Override
	public boolean contains(Point p) {
		return this.hexagon.doesContain(p.getX(), p.getY());
	}

	@Override
	public void draw(Graphics g) {
		if(hexagon.getAreaColor()==null)
			hexagon.setAreaColor(Color.BLUE);
		if(hexagon.getBorderColor()==null)
			hexagon.setBorderColor(Color.BLACK);
		this.hexagon.paint(g);
		
	}
	
	public Color getInterColor() {
		return hexagon.getAreaColor();
	}
	public Color getOuterColor() {
		return hexagon.getBorderColor();
	}
	public void setInterColor(Color interColor) {
		this.hexagon.setAreaColor(interColor);
		super.setInnerColor(interColor);
	}
	public void setOuterColor(Color outerColor) {
		this.hexagon.setBorderColor(outerColor);
		super.setOuterColor(outerColor);
	}
    public void setSelected(boolean selected)
    {
    	this.hexagon.setSelected(selected);
    	super.setSelected(selected);
	}
    
    public boolean isSelected(boolean selected)
    {
    	return this.hexagon.isSelected();
    }
    
    public int getX() {
        return this.hexagon.getX();
    }

    public int getY() {
        return this.hexagon.getY();
    }

    public int getRadius() {
        return this.hexagon.getR();
    }
    public void setCenterX(int x) {
       this.hexagon.setX(x);
    }
    public void setCenterY(int y) {
        this.hexagon.setY(y);
     }
    public void setRadius(int r) {
        this.hexagon.setR(r);
     }
	public String toString() {
		return  "(" + "x=" + hexagon.getX() + "," + "y=" + hexagon.getY()
		+ "," + "radius=" + hexagon.getR() + "," 
				+ "OuterColor="+Integer.toString(hexagon.getBorderColor().getRGB()) + "," + "InnerColor="+Integer.toString(hexagon.getAreaColor().getRGB()) + ")";
	}
	public Shape clone() {			
		HexagonAdapter hex= null;
		try {
			hex = new HexagonAdapter(this.getX(), this.getY(), this.getRadius(),this.getInterColor(),this.getOuterColor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return hex;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	

}
