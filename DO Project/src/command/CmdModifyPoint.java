package command;

import drawing.DrawingModel;
import drawing.Point;

public class CmdModifyPoint implements Command {
	
	private Point oldValue;
	private Point newValue;
	private Point originalValue = new Point();
	

	
	public CmdModifyPoint(Point oldValue, Point newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
       
		
	}

	@Override
	public void execute() {
		
		originalValue=(Point) oldValue.clone();
		oldValue.setX(newValue.getX());
		oldValue.setY(newValue.getY());
		oldValue.setOuterColor(newValue.getOuterColor());
		//oldValue.setSelected(newValue.isSelected());
		DrawingModel.getInstanceLazy().log("Execute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue+ "\r\n");

	}

	@Override
	public void unexecute() {
		oldValue.setX(originalValue.getX());
		oldValue.setY(originalValue.getY());
		oldValue.setOuterColor(originalValue.getOuterColor());
		DrawingModel.getInstanceLazy().log("Unexecute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue+ "\r\n");

	}

	

}
