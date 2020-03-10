package command;

import drawing.DrawingModel;
import drawing.Line;

public class CmdRemoveLine implements Command{
		
		private Line line;
		private DrawingModel drawModel;
		
		public CmdRemoveLine(Line line, DrawingModel drawModel)
		{
			this.line=line;
			this.drawModel=drawModel;
		}

		@Override
		public void execute() {
			drawModel.remove(line);
			DrawingModel.getInstanceLazy().log("Execute : Remove Line" , line.toString() + "\r\n");

		}

		@Override
		public void unexecute() {
			drawModel.add(line);
			DrawingModel.getInstanceLazy().log("Unexecute : Remove Line" , line.toString() + "\r\n");

			
		}

		
}
