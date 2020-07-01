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

			
		}

		@Override
		public void unexecute() {
			drawModel.add(rectangle);

			
		}


		@Override
		public String commandToString() {
			return ("Remove Rectangle" + rectangle.toString() + "\r\n");

		}



}
