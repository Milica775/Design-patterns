package command;
import mvc.DrawingModel;
import shapes.Point;

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
		
		
	}

	@Override
	public void unexecute() {
		drawModel.remove(point);
		
	}
	
	@Override
	public String commandToString() {
		return ( "Add Point" + point.toString() + "\r\n");
	
	}



}
