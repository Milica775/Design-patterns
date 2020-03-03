package command;

import drawing.DrawingModel;
import drawing.Line;
import drawing.Shape;

public class CmdBringToFront implements Command{
	
	
	private DrawingModel drawModel;
	private Shape selectedShape;
	private int indexBefore;


	public CmdBringToFront(DrawingModel drawModel,Shape selectedShape,int selectedIndex) {
		
		this.drawModel=drawModel;
	    this.selectedShape=selectedShape;	
	    indexBefore=selectedIndex;
	}

	@Override
	public void execute() {
       drawModel.getShapes().remove(selectedShape);
       drawModel.getShapes().add(selectedShape);
    
       drawModel.log("Execute : Bring To Front" +" " + selectedShape.getClass().getSimpleName(),selectedShape.toString());
	}

	@Override
	public void unexecute() {

	   drawModel.getShapes().remove(selectedShape);
	   drawModel.getShapes().add(indexBefore, selectedShape);
       drawModel.log("Unexecute : Bring To Front" +" " + selectedShape.getClass().getSimpleName(),selectedShape.toString());

	}

}
