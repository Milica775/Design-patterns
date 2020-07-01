package command;


import mvc.DrawingModel;
import shapes.HexagonAdapter;

public class CmdModifyHexagon implements Command{
	
	private HexagonAdapter oldValue;
	private HexagonAdapter newValue;
	private HexagonAdapter originalValue = new HexagonAdapter();

	
	public CmdModifyHexagon(HexagonAdapter oldValue, HexagonAdapter newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}


	@Override
	public void execute() {
		originalValue=(HexagonAdapter) oldValue.clone();
		oldValue.setCenterX(newValue.getX());
		oldValue.setCenterY(newValue.getY());
		try {
			oldValue.setRadius(newValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldValue.setOuterColor(newValue.getOuterColor());
		oldValue.setInterColor(newValue.getInterColor());

	}

	@Override
	public void unexecute() {
		oldValue.setCenterX(originalValue.getX());
		oldValue.setCenterY(originalValue.getY());
		try {
			oldValue.setRadius(originalValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oldValue.setOuterColor(originalValue.getOuterColor());
		oldValue.setInterColor(originalValue.getInterColor());

	}


	@Override
	public String commandToString() {
		return ("Modify" + " " + originalValue.getClass().getSimpleName() + originalValue+"->"+newValue+ "\r\n");		

	}

}
