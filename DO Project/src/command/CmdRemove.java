package command;


import java.util.ArrayList;
import java.util.List;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdRemove implements Command {
	
	private List<Shape> shapes;
	private DrawingModel model;
	
	
	public CmdRemove(List<Shape> shapes, DrawingModel model)
	{
		this.shapes=new ArrayList<>(shapes);
		this.model=model;
	}
	

	@Override
	public void execute() {
		for(Shape s : shapes)
		{
			model.remove(s);
		}
		
	}

	@Override
	public void unexecute() {
		for(Shape s : shapes)
		{
		model.add(s);
		}
	}
		
	

	@Override
	public String commandToString() {
		
			return ("Remove shapes" + shapes.toString() + "\r\n");
		


	}

}
