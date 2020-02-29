package command;

import drawing.Circle;
import drawing.DrawingModel;

public class CmdAddCircle implements Command{
	
	private Circle circle;
	private DrawingModel drawModel;
	
	public CmdAddCircle(Circle circle, DrawingModel drawModel)
	{
		this.circle=circle;
		this.drawModel=drawModel;
	}


	@Override
	public void execute() {
		drawModel.add(circle);
		
	}

	@Override
	public void unexecute() {
		drawModel.remove(circle);
		
	}


	

}