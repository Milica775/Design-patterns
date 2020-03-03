package command;

import drawing.Circle;
import drawing.DrawingModel;

public class CmdRemoveCircle implements Command{
		
		private Circle circle;
		private DrawingModel drawModel;
		
		public CmdRemoveCircle(Circle circle, DrawingModel drawModel)
		{
			this.circle=circle;
			this.drawModel=drawModel;
		}


		@Override
		public void execute() {
			drawModel.remove(circle);
			drawModel.log("Execute : Remove Circle" , circle.toString() + "\r\n");

		}

		@Override
		public void unexecute() {
			drawModel.add(circle);
			drawModel.log("Unexecute : Remove Circle" , circle.toString() + "\r\n");

			
		}


		
}
