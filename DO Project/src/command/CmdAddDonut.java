package command;


import mvc.DrawingModel;
import shapes.Donut;

public class CmdAddDonut implements Command {

	private Donut donut;
	private DrawingModel drawModel;
	
	public CmdAddDonut(Donut donut, DrawingModel drawModel)
	{
		this.donut=donut;
		this.drawModel=drawModel;
	}
	
	@Override
	public void execute() {
		drawModel.add(donut);
		
	}

	@Override
	public void unexecute() {
		drawModel.remove(donut);

	}

	@Override
	public String commandToString() {
		return ( "Add Donut" + donut.toString() + "\r\n");
		
	}

	

}
