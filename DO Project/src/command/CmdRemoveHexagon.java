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
		DrawingModel.getInstanceLazy().log("Execute : Remove Hexagon" , hex.toString() + "\r\n");		
	}

	@Override
	public void unexecute() {
		drawModel.add(hex);
		DrawingModel.getInstanceLazy().log("Unexecute : Remove Hexagon" , hex.toString() + "\r\n");		

	}

}
