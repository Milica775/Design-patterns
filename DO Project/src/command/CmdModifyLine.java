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
		
		originalValue.setStartPoint(oldValue.getStartPoint());
		originalValue.setEndPoint(oldValue.getEndPoint());
		originalValue.setOuterColor(oldValue.getOuterColor());
		
		oldValue.setStartPoint(newValue.getStartPoint());
		oldValue.setEndPoint(newValue.getEndPoint());
		oldValue.setOuterColor(newValue.getOuterColor());
		/*
		originalValue.getStartPoint().setX(oldValue.getStartPoint().getX());
		originalValue.getStartPoint().setY(oldValue.getStartPoint().getY());
		originalValue.getStartPoint().setOuterColor(oldValue.getStartPoint().getOuterColor());
		
		oldValue.getStartPoint().setX(newValue.getStartPoint().getX());
		oldValue.getStartPoint().setY(newValue.getStartPoint().getY());
		oldValue.getStartPoint().setOuterColor(newValue.getStartPoint().getOuterColor());
		*/
	}

	@Override
	public void unexecute() {
		oldValue.setStartPoint(originalValue.getStartPoint());
		oldValue.setEndPoint(originalValue.getEndPoint());
		oldValue.setOuterColor(originalValue.getOuterColor());
		/*
		oldValue.getStartPoint().setX(originalValue.getStartPoint().getX());
		oldValue.getStartPoint().setY(originalValue.getStartPoint().getY());
		oldValue.getStartPoint().setOuterColor(originalValue.getStartPoint().getOuterColor());
		*/
	}

	

}
