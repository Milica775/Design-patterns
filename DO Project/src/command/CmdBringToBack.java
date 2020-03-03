package command;

import drawing.DrawingModel;
import drawing.Shape;

public class CmdBringToBack implements Command{
	
	private DrawingModel drawModel;
	private Shape selectedShape;
	private int indexBefore;

	public CmdBringToBack(DrawingModel drawModel,Shape selectedShape,int selectedIndex) {
		this.selectedShape=selectedShape;
		this.drawModel=drawModel;
		indexBefore=selectedIndex;
	}
	
	@Override
	public void execute() {
	  drawModel.getShapes().remove(selectedShape);
	  drawModel.getShapes().add(0, selectedShape);
      drawModel.log("Execute : Bring To Back"+" " + selectedShape.getClass().getSimpleName(),selectedShape.toString());

	}

	@Override
	public void unexecute() {
		drawModel.getShapes().remove(0);
		drawModel.getShapes().add(indexBefore, selectedShape);
	    drawModel.log("Unexecute : Bring To Back"+" " + selectedShape.getClass().getSimpleName(),selectedShape.toString());

		
	}

}
