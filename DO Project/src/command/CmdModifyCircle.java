package command;

import drawing.Circle;

public class CmdModifyCircle implements Command {
	
	private Circle oldValue;
	private Circle newValue;
	private Circle originalValue = new Circle();
	
	public CmdModifyCircle(Circle oldValue, Circle newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void execute() { 
		originalValue.getCenter().setX(oldValue.getCenter().getX());
		originalValue.getCenter().setY(oldValue.getCenter().getY());
		try {
			originalValue.setRadius(oldValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		originalValue.setOuterColor(oldValue.getOuterColor());
		originalValue.setInnerColor(oldValue.getInnerColor());
		
		oldValue.getCenter().setX(newValue.getCenter().getX());
		oldValue.getCenter().setY(newValue.getCenter().getY());
		try {
			oldValue.setRadius(newValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldValue.setOuterColor(newValue.getOuterColor());
		oldValue.setInnerColor(newValue.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		
		oldValue.getCenter().setX(originalValue.getCenter().getX());
		oldValue.getCenter().setY(originalValue.getCenter().getY());
		try {
			oldValue.setRadius(originalValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oldValue.setOuterColor(originalValue.getOuterColor());
		oldValue.setInnerColor(originalValue.getInnerColor());
		
	}

	

}
