package command;

import drawing.Circle;
import drawing.Donut;
import drawing.DrawingModel;

public class CmdModifyDonut implements Command {
	
	private Donut oldValue;
	private Donut newValue;
	private Donut originalValue = new Donut();
	private DrawingModel model;
	
	public CmdModifyDonut(Donut oldValue, Donut newValue,DrawingModel model) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.model=model;
	}

	@Override
	public void execute() { 
	
			originalValue=(Donut) oldValue.clone();

		
		oldValue.getCenter().setX(newValue.getCenter().getX());
		oldValue.getCenter().setY(newValue.getCenter().getY());
		try {
			oldValue.setRadius(newValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldValue.setInnerRadius(newValue.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldValue.setOuterColor(newValue.getOuterColor());
		oldValue.setInnerColor(newValue.getInnerColor());
		model.log("Execute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue);

		
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
		try {
			oldValue.setInnerRadius(originalValue.getInnerRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oldValue.setOuterColor(originalValue.getOuterColor());
		oldValue.setInnerColor(originalValue.getInnerColor());
		model.log("Unexecute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue);

		
	}




}
