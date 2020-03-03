package command;

import drawing.DrawingModel;
import drawing.Rectangle;

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
			drawModel.log("Execute : Remove Rectangle" , rectangle.toString() + "\r\n");

			
		}

		@Override
		public void unexecute() {
			drawModel.add(rectangle);
			drawModel.log("Unexecute : Remove Rectangle" , rectangle.toString() + "\r\n");

			
		}



}
