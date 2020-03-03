package command;

import drawing.DrawingModel;
import drawing.Point;

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
			drawModel.remove(point);
			drawModel.log("Execute : Remove Point" , point.toString() + "\r\n");

			
		}

		@Override
		public void unexecute() {
			drawModel.add(point);
			drawModel.log("Unexecute : Remove Point" , point.toString() + "\r\n");

		}

	

}
