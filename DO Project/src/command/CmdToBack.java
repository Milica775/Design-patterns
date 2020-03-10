package command;

import java.util.Collections;

import drawing.DrawingModel;
import drawing.Line;
import drawing.Shape;

public class CmdToBack implements Command {
	
	
	private DrawingModel drawModel;
	private Shape s;
	private int index;

	
    public CmdToBack(DrawingModel drawModel,Shape s) {
    	
    	this.drawModel=drawModel;
    	
    	this.s=s;
		
	}

	@Override
	public void execute() {
		index=drawModel.getIndexOfShape(s);
		if(index!=0)
		{
		Collections.swap(drawModel.getShapes(), index-1, index);
		}
		DrawingModel.getInstanceLazy().log("Execute : To Back"+" " + s.getClass().getSimpleName(),s.toString() + "\r\n");

	}

	@Override
	public void unexecute() {
		Collections.swap(drawModel.getShapes(), index+1, index);
		DrawingModel.getInstanceLazy().log("Unexecute : To Back"+" " + s.getClass().getSimpleName(),s.toString() + "\r\n");

	}

}
