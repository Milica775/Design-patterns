package command;

import mvc.DrawingModel;
import shapes.HexagonAdapter;

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
	}

	@Override
	public void unexecute() {
		drawModel.remove(hex);

	}

	@Override
	public String commandToString() {
		return ( "Add Hexagon" + hex.toString() + "\r\n");
		
	}

}
