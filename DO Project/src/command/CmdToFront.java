package command;

import java.util.Collections;

import drawing.DrawingModel;
import drawing.Line;

public class CmdToFront implements Command {
	

	private DrawingModel drawModel;
	private int index;
	
	public CmdToFront(DrawingModel drawModel,int index) {
		this.index=index;
		this.drawModel=drawModel;
		
		
	}

	@Override
	public void execute() {
		Collections.swap(drawModel.getShapes(), drawModel.getSelectedShapeIndex()+1, drawModel.getSelectedShapeIndex());
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
