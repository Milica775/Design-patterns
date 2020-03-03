package command;




import drawing.DrawingModel;
import drawing.Point;

public class CmdAddPoint implements Command {
	
	private Point point;
	private DrawingModel drawModel;

	public CmdAddPoint(Point point, DrawingModel model)
	{
		this.point=point;
	    drawModel=model;
	}

	@Override
	public void execute() {
		drawModel.add(point);
		drawModel.log("Execute : Add Point" , point.toString() + "\r\n");
	}

	@Override
	public void unexecute() {
		drawModel.remove(point);
		drawModel.log("Unexecute : Add Point" , point.toString() + "\r\n");
		
	}



}
