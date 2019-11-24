package drawing;

import java.awt.Color;
import java.awt.Graphics;

public abstract class AreaShape extends Shape {
	
	private Color innerColor;
	
	public Color getInnerColor() {
		return innerColor;
	}
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
	public abstract void fill(Graphics g);

}
