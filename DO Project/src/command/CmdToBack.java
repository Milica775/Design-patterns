package command;

import java.util.Collections;

import drawing.DrawingModel;
import drawing.Line;
import drawing.Shape;

public class CmdToBack implements Command {
	
	
	private DrawingModel drawModel;
	private int index;
	private Shape s;
	
    public CmdToBack(DrawingModel drawModel,Shape s,int index) {
    	
    	this.drawModel=drawModel;
    	this.index=index;
    	this.s=s;
		
	}

	@Override
	public void execute() {
		if(drawModel.getSelectedShapeIndex()!=0)
		{
		Collections.swap(drawModel.getShapes(), index-1, index);
		}
	    drawModel.log("Execute : To Back"+" " + drawModel.getSelectedShape().getClass().getSimpleName(),drawModel.getSelectedShape().toString() + "\r\n");

	}

	@Override
	public void unexecute() {
		Collections.swap(drawModel.getShapes(), drawModel.getSelectedShapeIndex()+1, drawModel.getSelectedShapeIndex());
	    drawModel.log("Unexecute : To Back"+" " + drawModel.getSelectedShape().getClass().getSimpleName(),drawModel.getSelectedShape().toString() + "\r\n");

	}

}
