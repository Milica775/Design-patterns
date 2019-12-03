package drawing;

import java.util.List;
import java.util.Stack;

import command.Command;

import java.util.ArrayList;

public class DrawingModel {
	
	private List<Shape> shapes=new ArrayList<Shape>();
	private Shape selectedShapes;
	private Stack<Command> commandStack = new Stack<>();
	private int undoRedoPointer = -1;
	
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
	}
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
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
