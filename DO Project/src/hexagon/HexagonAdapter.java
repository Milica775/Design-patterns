package hexagon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import drawing.Point;
import drawing.Shape;

public class HexagonAdapter extends Shape{
	
	private Hexagon hexagon;	
	
	public HexagonAdapter(int xx,int yy,int rr) {
	    	hexagon=new Hexagon(xx,yy,rr);	    
	    }
	@Override
	public boolean contains(Point p) {
		return this.hexagon.doesContain(p.getX(), p.getY());
	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);	
	}
	
	public Color getInterColor() {
		return this.hexagon.getAreaColor();
	}
	public void setInterColor(Color interColor) {
		this.hexagon.setAreaColor(interColor);
	}
	public void setOuterColor(Color outerColor) {
		this.hexagon.setBorderColor(outerColor);
	}
    public void setSelected(boolean selected)
    {
    	this.hexagon.setSelected(selected);
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
	
	

}
