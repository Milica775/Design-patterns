package command;

import java.util.Collections;


import mvc.DrawingModel;
import shapes.Line;
import shapes.Shape;

public class CmdToBack implements Command {
	
	
	private DrawingModel drawModel;
	private Shape s;
	private int index;

	
    public CmdToBack(DrawingModel drawModel,Shape s) {
    	
    	this.drawModel=drawModel;
    	
    	this.s=s;
		
	}

	@Override
	public void execute() {
		index=drawModel.getIndexOfShape(s);
		if(index!=0)
		{
		Collections.swap(drawModel.getShapes(), index-1, index);

		}

	}

	@Override
	public void unexecute() {
		if(index!=drawModel.getShapes().size()-1)
		{
		Collections.swap(drawModel.getShapes(), index, index-1);

		}
		
	}

	@Override
	public String commandToString() {
		return ("To Back"+" " + s.getClass().getSimpleName() + s.toString()+"\r\n");		
		
	}

}
