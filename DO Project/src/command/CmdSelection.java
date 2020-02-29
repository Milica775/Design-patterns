package command;

import drawing.DrawingModel;
import drawing.Point;
import drawing.Shape;

public class CmdSelection implements Command {

	private DrawingModel drawModel;
	private Shape selectShape;
	public CmdSelection(DrawingModel model,Shape shape)
	{
	    drawModel=model;
	    this.selectShape=shape;
	}

	@Override
	public void execute() {

		drawModel.setSelection(selectShape,true);
		drawModel.log("Execute : Selection shape", selectShape.toString());
		
	}

	@Override
	public void unexecute() {
		
		drawModel.setSelection(selectShape, false);
		drawModel.log("Unexecute : Selection shape", selectShape.toString());
		
		
	}

}
