package command;

import java.awt.Color;
import java.util.Iterator;

import drawing.DrawingModel;
import drawing.FrmDrawing;
import drawing.Point;
import drawing.Shape;

public class CmdSelection implements Command {

	private DrawingModel drawModel;
	private Shape selectShape;
	private FrmDrawing frame;
	public CmdSelection(Shape shape,DrawingModel model,FrmDrawing frame)
	{
	    drawModel=model;
	    this.selectShape=shape;
	    this.frame=frame;
	    
	}

	@Override
	public void execute() {
		
		drawModel.setSelection(selectShape,true);
		drawModel.log("Execute : Selection" + " " + selectShape.getClass().getSimpleName(), selectShape.toString()+ "\r\n");
		
	}

	@Override
	public void unexecute() {
		
		drawModel.setSelection(selectShape, false);
		drawModel.log("Execute : Selection" + " " + selectShape.getClass().getSimpleName(), selectShape.toString()+ "\r\n");

		
	}

}
