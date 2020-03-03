package command;

import drawing.DrawingModel;
import drawing.Point;

public class CmdModifyPoint implements Command {
	
	private Point oldValue;
	private Point newValue;
	private Point originalValue = new Point();
	private DrawingModel model;

	
	public CmdModifyPoint(Point oldValue, Point newValue,DrawingModel model) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.model=model;
		
	}

	@Override
	public void execute() {
		
		originalValue=(Point) oldValue.clone();
		oldValue.setX(newValue.getX());
		oldValue.setY(newValue.getY());
		oldValue.setOuterColor(newValue.getOuterColor());
		model.log("Execute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue);

	}

	@Override
	public void unexecute() {
		oldValue.setX(originalValue.getX());
		oldValue.setY(originalValue.getY());
		oldValue.setOuterColor(originalValue.getOuterColor());
		model.log("Unexecute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue);

	}

	

}
