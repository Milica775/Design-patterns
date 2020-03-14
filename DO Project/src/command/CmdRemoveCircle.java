package command;

import mvc.DrawingModel;
import shapes.Circle;

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
			DrawingModel.getInstanceLazy().log("Execute : Remove Circle" , circle.toString() + "\r\n");

		}

		@Override
		public void unexecute() {
			drawModel.add(circle);
			DrawingModel.getInstanceLazy().log("Unexecute : Remove Circle" , circle.toString() + "\r\n");

			
		}


		
}
