package command;


import mvc.DrawingModel;
import shapes.Shape;

public class CmdAdd implements Command{
	
	private Shape shape;
	private DrawingModel model;
	
	
	public CmdAdd(Shape shape, DrawingModel model)
	{
		this.shape=shape;
		this.model=model;
	}

	@Override
	public void execute() {
       model.add(shape);
	}

	@Override
	public void unexecute() {
	   model.remove(shape);

	}

	@Override
	public String commandToString() {
		return ( "Add" + " " + shape.getClass().getSimpleName() + " " + shape.toString() + "\r\n");
	}

}
