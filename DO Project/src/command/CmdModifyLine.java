package command;

import drawing.DrawingModel;
import drawing.Line;
import drawing.Point;

public class CmdModifyLine implements Command {
	
	private Line oldValue;
	private Line newValue;
	private Line originalValue = new Line();
	private DrawingModel model;
	
	public CmdModifyLine(Line oldValue, Line newValue, DrawingModel model) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.model=model;
	}

	@Override
	public void execute() {
		
		originalValue=(Line) oldValue.clone();
		
		oldValue.setStartPoint(newValue.getStartPoint());
		oldValue.setEndPoint(newValue.getEndPoint());
		oldValue.setOuterColor(newValue.getOuterColor());
		model.log("Execute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue);

		
	}

	@Override
	public void unexecute() {
		oldValue.setStartPoint(originalValue.getStartPoint());
		oldValue.setEndPoint(originalValue.getEndPoint());
		oldValue.setOuterColor(originalValue.getOuterColor());
		model.log("Unexecute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue);

	}

	

}
