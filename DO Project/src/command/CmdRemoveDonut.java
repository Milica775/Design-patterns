package command;

import drawing.Donut;
import drawing.DrawingModel;

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

}
