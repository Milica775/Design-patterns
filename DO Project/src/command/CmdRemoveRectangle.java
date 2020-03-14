package command;

import mvc.DrawingModel;
import shapes.Rectangle;

public class CmdRemoveRectangle implements Command {
		
		private Rectangle rectangle;
		private DrawingModel drawModel;
		
		public CmdRemoveRectangle(Rectangle rectangle, DrawingModel drawModel)
		{
			this.rectangle=rectangle;
			this.drawModel=drawModel;
		}


		@Override
		public void execute() {
			drawModel.remove(rectangle);
			DrawingModel.getInstanceLazy().log("Execute : Remove Rectangle" , rectangle.toString() + "\r\n");

			
		}

		@Override
		public void unexecute() {
			drawModel.add(rectangle);
			DrawingModel.getInstanceLazy().log("Unexecute : Remove Rectangle" , rectangle.toString() + "\r\n");

			
		}



}
