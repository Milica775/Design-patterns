package command;

import drawing.DrawingModel;
import drawing.Line;
import drawing.Shape;

public class CmdBringToFront implements Command{
	
	
	private DrawingModel drawModel;
	private Shape selectedShape;
	

	public CmdBringToFront(DrawingModel drawModel,Shape selectedShape) {
		
		this.drawModel=drawModel;
	    this.selectedShape=selectedShape;	
	}

	@Override
	public void execute() {
       drawModel.getShapes().remove(selectedShape);
       drawModel.getShapes().add(selectedShape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
