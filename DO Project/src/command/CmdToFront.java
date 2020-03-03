package command;

import java.util.Collections;

import drawing.DrawingModel;
import drawing.Line;
import drawing.Shape;

public class CmdToFront implements Command {
	

	private DrawingModel drawModel;
	private int index;
	private Shape s;
	
	public CmdToFront(DrawingModel drawModel,Shape s,int index) {
		this.index=index;
		this.drawModel=drawModel;
		this.s=s;
		
	}

	@Override
	public void execute() {
		if(drawModel.getSelectedShapeIndex()!=drawModel.getShapes().size()-1)
		{
		Collections.swap(drawModel.getShapes(), index+1, index);
		}
	    drawModel.log("Execute : To Front"+" " + s.getClass().getSimpleName(),s.toString() + "\r\n");

		
	}

	@Override
	public void unexecute() {
		Collections.swap(drawModel.getShapes(), drawModel.getSelectedShapeIndex()-1, drawModel.getSelectedShapeIndex());
	    drawModel.log("Unexecute : To Front",drawModel.getSelectedShape().toString()+ "\r\n");

	}

}
