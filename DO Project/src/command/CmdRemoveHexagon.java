package command;

import mvc.DrawingModel;
import shapes.HexagonAdapter;

public class CmdRemoveHexagon implements Command{

	private HexagonAdapter hex;
	private DrawingModel drawModel;
	
	public CmdRemoveHexagon(HexagonAdapter hex, DrawingModel drawModel)
	{
		this.hex=hex;
		this.drawModel=drawModel;
	}

	@Override
	public void execute() {
		drawModel.remove(hex);
	}

	@Override
	public void unexecute() {
		drawModel.add(hex);

	}

	@Override
	public String commandToString() {
		return ("Remove Hexagon" + hex.toString() + "\r\n");
		
	}

}
