package command;

import drawing.DrawingModel;
import drawing.Shape;

public class CmdBringToBack implements Command{
	
	private DrawingModel drawModel;
	private Shape selectedShape;

	public CmdBringToBack(DrawingModel drawModel,Shape selectedShape) {
		this.selectedShape=selectedShape;
		this.drawModel=drawModel;
	}
	
	@Override
	public void execute() {
	  drawModel.getShapes().remove(selectedShape);
	  drawModel.getShapes().add(0, selectedShape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
