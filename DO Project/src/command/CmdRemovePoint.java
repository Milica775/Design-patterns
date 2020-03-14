package command;

import mvc.DrawingModel;
import shapes.Point;

public class CmdRemovePoint implements Command {
		
		private Point point;
		private DrawingModel drawModel;
		
		public CmdRemovePoint(Point point, DrawingModel model)
		{
			this.point=point;
		    drawModel=model;
		}

		@Override
		public void execute() {
			DrawingModel.getInstanceLazy().log("Execute : Remove Point" , point.toString() + "\r\n");
			drawModel.remove(point);
           
			
		}

		@Override
		public void unexecute() {
			drawModel.add(point);
			DrawingModel.getInstanceLazy().log("Unexecute : Remove Point" , point.toString() + "\r\n");

		}

	

}
