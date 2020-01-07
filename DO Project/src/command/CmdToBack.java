package command;

import java.util.Collections;

import drawing.DrawingModel;
import drawing.Line;

public class CmdToBack implements Command {
	
	
	private DrawingModel drawModel;
	private int index;
	
    public CmdToBack(DrawingModel drawModel,int index) {
    	
    	this.drawModel=drawModel;
    	this.index=index;
		
	}

	@Override
	public void execute() {
		Collections.swap(drawModel.getShapes(), drawModel.getSelectedShapeIndex()-1, drawModel.getSelectedShapeIndex());
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
