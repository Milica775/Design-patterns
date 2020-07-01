package command;

import mvc.DrawingModel;
import shapes.Line;

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

		}

		@Override
		public void unexecute() {
			drawModel.add(line);

			
		}

		@Override
		public String commandToString() {
			return ("Remove Line" + line.toString() + "\r\n");
			
		}

		
}
