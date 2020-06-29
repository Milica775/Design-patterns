package command;

import java.util.Collections;


import mvc.DrawingModel;
import shapes.Line;
import shapes.Shape;

public class CmdToFront implements Command {
	

	private DrawingModel drawModel;
	private Shape s;
	private int index;
	
	public CmdToFront(DrawingModel drawModel,Shape s) {
		
		this.drawModel=drawModel;
		this.s=s;
		
	}
	

	@Override
	public void execute() {
		index=drawModel.getIndexOfShape(s);
		
		if(index!=drawModel.getShapes().size()-1)
		{
		Collections.swap(drawModel.getShapes(), index+1, index);
		System.out.println("exec:to front"+drawModel.getShapes());
		}
		DrawingModel.getInstanceLazy().log("Execute : To Front"+" " + s.getClass().getSimpleName(),s.toString() + "\r\n");

		
	}

	@Override
	public void unexecute() {
		if(index!=0)
		{
		Collections.swap(drawModel.getShapes(), index, index+1);
		System.out.println("unexec:to front"+drawModel.getShapes());

		}
		DrawingModel.getInstanceLazy().log("Unexecute : To Front"+" " + s.getClass().getSimpleName(),s.toString()+ "\r\n");

	}

}
