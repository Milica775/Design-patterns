package command;

import drawing.Circle;
import drawing.Donut;

public class CmdModifyDonut implements Command {
	
	private Donut oldValue;
	private Donut newValue;
	private Donut originalValue = new Donut();
	
	public CmdModifyDonut(Donut oldValue, Donut newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void execute() { 
		try {
			originalValue.setInnerRadius(oldValue.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		originalValue.setSecondOuterColor(oldValue.getSecondOuterColor());
	
		
	}

	@Override
	public void unexecute() {
		
		
		try {
			oldValue.setInnerRadius(originalValue.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oldValue.setSecondOuterColor(originalValue.getSecondOuterColor());
		
		
	}




}
