package command;

import drawing.DrawingModel;
import drawing.Line;
import hexagon.HexagonAdapter;

public class CmdAddHexagon implements Command {

	private HexagonAdapter hex;
	private DrawingModel drawModel;
	
	public CmdAddHexagon(HexagonAdapter hex, DrawingModel drawModel)
	{
		this.hex=hex;
		this.drawModel=drawModel;
	}

	@Override
	public void execute() {
		drawModel.add(hex);
		drawModel.log("Execute : Add Hexagon" , hex.toString() + "\r\n");		
	}

	@Override
	public void unexecute() {
		drawModel.remove(hex);
		drawModel.log("Unexecute : Add Hexagon" , hex.toString() + "\r\n");		

	}

}
