package drawing;

import java.util.List;
import java.util.ArrayList;

public class DrawingModel {
	
	private List<Shape> shapes=new ArrayList<Shape>();
	private Shape selectedShapes;
	public void add(Shape s) {
		shapes.add(s);
	}
	public Shape getSelectedShapes() {
		return selectedShapes;
	}
	public void setSelectedShapes(Shape selectedShapes) {
		this.selectedShapes = selectedShapes;
	}
	public List<Shape> getShapes() {
		return shapes;
	}
	public Shape get(int i) {
		return shapes.get(i);
	}
	public void remove(Shape s) {
		shapes.remove(s);
	}
	
	

	

}
