package command;
import shapes.Point;

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
		//zbog redo
		//oldValue.setSelected(true);

	}

	@Override
	public void unexecute() {
		oldValue.setX(originalValue.getX());
		oldValue.setY(originalValue.getY());
		oldValue.setOuterColor(originalValue.getOuterColor());
		//ako treba da se ukloni selekcija
	    //oldValue.setSelected(false);

	}

	@Override
	public String commandToString() {
		return ("Modify" + " " + originalValue.getClass().getSimpleName() + originalValue+"->"+newValue+ "\r\n");		
	}

	

}
