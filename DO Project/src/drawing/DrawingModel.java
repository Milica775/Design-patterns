package drawing;
import java.util.List;
import java.util.Stack;
import command.Command;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DrawingModel {
	
	private List<Shape> shapes=new ArrayList<Shape>();
	private List<Shape> selectedShapes=new ArrayList<Shape>();
	private List<String> logs=new ArrayList<String>();
	private Shape selectedShape;
	private Stack<Command> commandStack = new Stack<>();
	private int undoRedoPointer = -1;
	private PropertyChangeSupport propertyChangeSupport;
	private static DrawingModel instanceLazy;
	
	public DrawingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	//PropertyChangeListener
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}
	
	//Add/remove Shape
	public void add(Shape s) {
		shapes.add(s);
	}
	public void remove(Shape s) {
		shapes.remove(s);
	}
	
	//Get/set shapes
	public List<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	
	//get/set selectedShape
	public Shape getSelectedShape() {
    for (Shape s : shapes) {
			
			if (s != null && s.isSelected()) {
			
				return s;
				
			}
		}
		
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
      for (Shape s : shapes) {
			
			if (s.equals(selectedShape)) {
			
				selectedShape=s;
				
			}
		}
		
		
	}

	//Get/set logs
	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	
	//get/set CommandStack
	public Stack<Command> getCommandStack() {
		return commandStack;
	}
	public void setCommandStack(Stack<Command> commandStack) {
		this.commandStack = commandStack;
	}
	
	//get/set UndoRedoPointer
	public int getUndoRedoPointer() {
		return undoRedoPointer;
	}
	public void setUndoRedoPointer(int undoRedoPointer) {
		this.undoRedoPointer = undoRedoPointer;
		propertyChangeSupport.firePropertyChange("undoRedo",-1,undoRedoPointer);
      
	}
	
	//get/set selectedShapes
	public List<Shape> getSelectedShapes() {
		return selectedShapes;

	}
	public void setSelectedShapes(List<Shape> selectedShapes) {
		

		this.selectedShapes=selectedShapes;
	}
	
    //getIndex
	public int getSelectedShapeIndex() {
		int listSize = shapes.size() - 1;
		for (int i = 0; i <= listSize; i++) {
			if (shapes.get(i) != null && shapes.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}
	public int getIndexOfShape(Shape s) {
		
		int listSize = shapes.size() - 1;
		for (int i = 0; i <= listSize; i++) {
			if (shapes.get(i).equals(s)) {
				
				return i;
			}
		}
		return -1;
	}
	
	public Shape get(int i) {
		return shapes.get(i);
	}
	

	
	public static DrawingModel getInstanceLazy() {
		if (instanceLazy == null) {
			synchronized(DrawingModel.class) {
				if (instanceLazy == null) {
					instanceLazy = new DrawingModel();
				}
			}
		}
		return instanceLazy;
	}

	
	//hm
	public void log(String s,String ss) {
		FrmDrawing.getDlm().addElement(s+ss);
		logs.add(s+ss);
	}

	
	public String peek() {
		return logs.get(logs.size()-1);
	}

	public void setSelection(Shape shape,boolean select) {		
		      shape.setSelected(select);

		if(select) {	
			setSelectedShape(shape);
		selectedShapes.add(shape);
		}
		if(!select) {
		selectedShapes.remove(shape);
		}
		propertyChangeSupport.firePropertyChange("selectedShapes", selectedShapes,shape);
        
	}

	

	public void removeAllSelection() {
		for(Shape s:shapes) {
			s.setSelected(false);
			selectedShapes.remove(s);
			propertyChangeSupport.firePropertyChange("selectedShapes", selectedShapes,s);

		}
		
	}

	

	
   
	

	

}
