package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable,Movable,Cloneable{
	
	private boolean selected;
	private Color outerColor;
	
	//konstruktori
    public Shape() {
		
	}
	
	public Shape(boolean selected) {
		this.selected = selected;
	}
    
	//metode pristupa koje nasledjuju sve klase
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
 
		this.selected = selected;
	}
	
	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}

	
	//metode koje su zajednicke za sve klase
	public abstract boolean contains(Point p);
	public abstract void draw(Graphics g);

}
