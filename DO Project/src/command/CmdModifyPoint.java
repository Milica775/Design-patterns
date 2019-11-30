package command;

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
		originalValue.setX(oldValue.getX());
		originalValue.setY(oldValue.getY());
		originalValue.setOuterColor(oldValue.getOuterColor());
		
		
		oldValue.setX(newValue.getX());
		oldValue.setY(newValue.getY());
		oldValue.setOuterColor(newValue.getOuterColor());
		
	}

	@Override
	public void unexecute() {
		oldValue.setX(originalValue.getX());
		oldValue.setY(originalValue.getY());
		oldValue.setOuterColor(originalValue.getOuterColor());
		
	}

}
