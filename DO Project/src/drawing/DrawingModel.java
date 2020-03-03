package drawing;

import java.util.List;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import command.Command;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DrawingModel {
	
	private List<Shape> shapes=new ArrayList<Shape>();
	private List<Shape> selectedShapes=new ArrayList<Shape>();
	private List<String> logs=new ArrayList<String>();
	
	
	
	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
	}

	//private Shape selectedShapes;
	private Stack<Command> commandStack = new Stack<>();
	private int undoRedoPointer = -1;
	private PropertyChangeSupport propertyChangeSupport;
	
	
	public DrawingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}
	

	
	public Stack<Command> getCommandStack() {
		return commandStack;
	}
	public void setCommandStack(Stack<Command> commandStack) {
		this.commandStack = commandStack;
	}
	public int getUndoRedoPointer() {
		return undoRedoPointer;
	}
	public void setUndoRedoPointer(int undoRedoPointer) {
		this.undoRedoPointer = undoRedoPointer;
		propertyChangeSupport.firePropertyChange("undoRedo",-1,undoRedoPointer);
      
	}
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	public void add(Shape s) {
		shapes.add(s);
	}
	public void log(String s,String ss) {
		FrmDrawing.getDlm().addElement(s+ss);
		logs.add(s+ss);
	}
	public List<Shape> getSelectedShapes() {
		return selectedShapes;

	}
	public void setSelectedShapes(List<Shape> selectedShapes) {
		

		this.selectedShapes=selectedShapes;
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
	public Shape getSelectedShape() {
		for (Shape s : shapes) {
			
			if (s != null && s.isSelected()) {
			
				return s;
				
			}
		}
		return null;
	}

	public String peek() {
		return logs.get(logs.size()-1);
	}

	public void setSelection(Shape shape,boolean select) {		
		      shape.setSelected(select);		
		if(select) {		
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
