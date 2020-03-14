package command;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdBringToBack implements Command{
	
	private DrawingModel drawModel;
	private Shape selectedShape;
	private int index;

	public CmdBringToBack(DrawingModel drawModel,Shape selectedShape) {
		this.selectedShape=selectedShape;
		this.drawModel=drawModel;
	
	}
	
	@Override
	public void execute() {
		index=drawModel.getSelectedShapeIndex();
	  drawModel.getShapes().remove(selectedShape);
	  drawModel.getShapes().add(0, selectedShape);
	  DrawingModel.getInstanceLazy().log("Execute : Bring To Back"+" " + selectedShape.getClass().getSimpleName(),selectedShape.toString()+"\r\n");

	}

	@Override
	public void unexecute() {
		drawModel.getShapes().remove(0);
		drawModel.getShapes().add(index, selectedShape);
		DrawingModel.getInstanceLazy().log("Unexecute : Bring To Back"+" " + selectedShape.getClass().getSimpleName(),selectedShape.toString()+"\r\n");

		
	}

}
