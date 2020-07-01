package command;

import mvc.DrawingModel;
import shapes.Rectangle;

public class CmdAddRectangle implements Command {
	
	private Rectangle rectangle;
	private DrawingModel drawModel;
	
	public CmdAddRectangle(Rectangle rectangle, DrawingModel drawModel)
	{
		this.rectangle=rectangle;
		this.drawModel=drawModel;
	}


	@Override
	public void execute() {
		drawModel.add(rectangle);
		
	}

	@Override
	public void unexecute() {
		drawModel.remove(rectangle);

	}


	@Override
	public String commandToString() {
		return ( "Add Rectangle" + rectangle.toString() + "\r\n");

	}


}
