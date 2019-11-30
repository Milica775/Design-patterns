package command;

import drawing.Line;

public class CmdModifyLine implements Command {
	
	private Line oldValue;
	private Line newValue;
	private Line originalValue = new Line();
	
	public CmdModifyLine(Line oldValue, Line newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void execute() {
		originalValue.getStartPoint().setX(oldValue.getStartPoint().getX());
		originalValue.getStartPoint().setY(oldValue.getStartPoint().getY());
		originalValue.getStartPoint().setOuterColor(oldValue.getStartPoint().getOuterColor());
		
		oldValue.getStartPoint().setX(newValue.getStartPoint().getX());
		oldValue.getStartPoint().setY(newValue.getStartPoint().getY());
		oldValue.getStartPoint().setOuterColor(newValue.getStartPoint().getOuterColor());
		
	}

	@Override
	public void unexecute() {
		oldValue.getStartPoint().setX(originalValue.getStartPoint().getX());
		oldValue.getStartPoint().setY(originalValue.getStartPoint().getY());
		oldValue.getStartPoint().setOuterColor(originalValue.getStartPoint().getOuterColor());
		
	}

}
