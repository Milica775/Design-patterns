package mvc;

import java.awt.Graphics;

import java.util.ListIterator;

import javax.swing.JPanel;

import shapes.Shape;

public class DrawingView extends JPanel {
	
	private DrawingModel model=new DrawingModel();

	public void setModel(DrawingModel model2) {
		model=model2;		
	} 
	public void paint(Graphics g) {
		ListIterator<Shape> it=model.getShapes().listIterator();
		while(it.hasNext()) {
			it.next().draw(g);
		
		}
		
	}

		
	
}
