package command;

import drawing.DrawingModel;
import drawing.Point;

public class CmdAddPoint implements Command {
	
	private Point point;
	private DrawingModel drawModel;
	
	public CmdAddPoint(Point point, DrawingModel model)
	{
		point=point;
	    drawModel=model;
	}

	@Override
	public void execute() {
		drawModel.add(point);
		
	}

	@Override
	public void unexecute() {
		drawModel.remove(point);
		
	}

}
