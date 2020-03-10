package command;

import drawing.DrawingModel;

import drawing.Rectangle;

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
		DrawingModel.getInstanceLazy().log("Execute : Add Rectangle" , rectangle.toString() + "\r\n");		
		
	}

	@Override
	public void unexecute() {
		drawModel.remove(rectangle);
		DrawingModel.getInstanceLazy().log("Unexecute : Add Rectangle" , rectangle.toString() + "\r\n");		

	}


}
