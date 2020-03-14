package command;

import mvc.DrawingModel;
import shapes.Circle;
import shapes.Donut;

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
		DrawingModel.getInstanceLazy().log("Execute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue+ "\r\n");

		
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
		DrawingModel.getInstanceLazy().log("Unexecute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue+ "\r\n");

		
	}




}
