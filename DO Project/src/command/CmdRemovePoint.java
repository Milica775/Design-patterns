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
			drawModel.remove(point);
           
			
		}

		@Override
		public void unexecute() {
			drawModel.add(point);

		}

		@Override
		public String commandToString() {
			return ("Remove Point" + point.toString()+"\r\n");
			
		}

	

}
