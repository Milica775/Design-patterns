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
		indexBefore=drawModel.getSelectedShapeIndex();
       drawModel.getShapes().remove(selectedShape);
       drawModel.getShapes().add(selectedShape);
    
       DrawingModel.getInstanceLazy().log("Execute : Bring To Front" +" " + selectedShape.getClass().getSimpleName(),selectedShape.toString()+ "\r\n");
	}

	@Override
	public void unexecute() {

	   drawModel.getShapes().remove(selectedShape);
	   drawModel.getShapes().add(indexBefore, selectedShape);
	   DrawingModel.getInstanceLazy().log("Unexecute : Bring To Front" +" " + selectedShape.getClass().getSimpleName(),selectedShape.toString()+ "\r\n");

	}

}
