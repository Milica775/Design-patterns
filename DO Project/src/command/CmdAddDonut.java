package command;


import drawing.Donut;
import drawing.DrawingModel;

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

	

}
