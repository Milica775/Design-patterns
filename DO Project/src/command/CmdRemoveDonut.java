package command;

import mvc.DrawingModel;
import shapes.Donut;

public class CmdRemoveDonut implements Command {

		private Donut donut;
		private DrawingModel drawModel;
		
		public CmdRemoveDonut(Donut donut, DrawingModel drawModel)
		{
			this.donut=donut;
			this.drawModel=drawModel;
		}
		
		@Override
		public void execute() {
			drawModel.remove(donut);

			
		}

		@Override
		public void unexecute() {
			drawModel.add(donut);

		}

		@Override
		public String commandToString() {
			return ("Remove Donut" + donut.toString() + "\r\n");
			
		}

		

}
