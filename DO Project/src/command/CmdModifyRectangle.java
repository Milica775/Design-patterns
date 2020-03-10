package command;

import drawing.DrawingModel;
import drawing.Point;
import drawing.Rectangle;

public class CmdModifyRectangle implements Command {
	
	private Rectangle oldValue;
	private Rectangle newValue;
	private Rectangle originalValue = new Rectangle();
	
	
	public CmdModifyRectangle(Rectangle oldValue, Rectangle newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}

	@Override
	public void execute() { 
		
		originalValue=(Rectangle) oldValue.clone();
		
		
		oldValue.getUpperLeftPoint().setX(newValue.getUpperLeftPoint().getX());
		oldValue.getUpperLeftPoint().setY(newValue.getUpperLeftPoint().getY());
		try {
			oldValue.setHeight(newValue.getHeight());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldValue.setWidth(newValue.getWidth());
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
		
		oldValue.getUpperLeftPoint().setX(originalValue.getUpperLeftPoint().getX());
		oldValue.getUpperLeftPoint().setY(originalValue.getUpperLeftPoint().getY());
		try {
			oldValue.setHeight(originalValue.getHeight());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oldValue.setWidth(originalValue.getWidth());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldValue.setOuterColor(originalValue.getOuterColor());
		oldValue.setInnerColor(originalValue.getInnerColor());
		DrawingModel.getInstanceLazy().log("Unexecute : Modify" + " " + originalValue.getClass().getSimpleName(), originalValue+"->"+newValue+ "\r\n");

	}

	


}
