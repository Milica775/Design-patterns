package command;

import mvc.DrawingModel;
import shapes.Line;
import shapes.Shape;

public class CmdBringToFront implements Command{
	
	
	private DrawingModel drawModel;
	private Shape selectedShape;
	private int indexBefore;
	

	public CmdBringToFront(DrawingModel drawModel,Shape selectedShape) {
		
		this.drawModel=drawModel;
	    this.selectedShape=selectedShape;	
	   
	}

	@Override
	public void execute() {
		indexBefore=drawModel.getIndexOfShape(selectedShape);
       drawModel.getShapes().remove(selectedShape);
       drawModel.getShapes().add(selectedShape);
		System.out.println("exec:bring to front"+drawModel.getShapes());

       DrawingModel.getInstanceLazy().log("Execute : Bring To Front" +" " + selectedShape.getClass().getSimpleName(),selectedShape.toString()+ "\r\n");
	}

	@Override
	public void unexecute() {
		//indexBefore=drawModel.getSelectedShapeIndex();
	   drawModel.getShapes().remove(selectedShape);
	   drawModel.getShapes().add(indexBefore, selectedShape);
		System.out.println("unexec:bring to front"+drawModel.getShapes());

	   DrawingModel.getInstanceLazy().log("Unexecute : Bring To Front" +" " + selectedShape.getClass().getSimpleName(),selectedShape.toString()+ "\r\n");

	}

}
