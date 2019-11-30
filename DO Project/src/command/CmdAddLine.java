package command;

import drawing.DrawingModel;
import drawing.Line;

public class CmdAddLine implements Command{
	
	private Line line;
	private DrawingModel drawModel;
	
	public CmdAddLine(Line line, DrawingModel drawModel)
	{
		this.line=line;
		this.drawModel=drawModel;
	}

	@Override
	public void execute() {
		drawModel.add(line);
		
	}

	@Override
	public void unexecute() {
		drawModel.remove(line);
		
	}

}
