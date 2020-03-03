package command;

import drawing.Circle;
import drawing.DrawingModel;
import hexagon.HexagonAdapter;

public class CmdModifyHexagon implements Command{
	
	private HexagonAdapter oldValue;
	private HexagonAdapter newValue;
	private HexagonAdapter originalValue = new HexagonAdapter();
	private DrawingModel model;
	
	public CmdModifyHexagon(HexagonAdapter oldValue, HexagonAdapter newValue,DrawingModel model) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.model=model;
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
		model.log("Execute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue);

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
		model.log("Unexecute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue);

	}

}
