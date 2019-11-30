package command;

import java.util.Stack;

public class UndoRedoManager {

	//stack na koji cu da smjestam komande koje mogu da izvrse undo ili redo
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();
	
	private class OldState {
		private String undoName = getUndoName();
		private String redoName = getUndoName();
		private boolean undoAvailable = isUndoAvailable();
		private boolean redoAvailable = isRedoAvailable();
	}
	
	public void execute(Command command) {
		try {
			OldState oldState = new OldState();
			//izvrsava komandu
			command.execute();
			//brise sve sa Redo stacka jer cim izvrsimo neku novu komandu nemamo pravo vise na redo
			redoStack.clear();
			addChanges(oldState);
		} catch (IllegalStateException e) {
			// report and log
		}
	}

	public void undo() {
		if (!undoStack.isEmpty()) {
			try {
				OldState oldState = new OldState();
				//uzima komandu sa stacka
				Command command = undoStack.pop();
				//izvrsava undo
				command.unexecute();
				//kad izvrsimo undo moguce je obaviti i redo
				redoStack.push(command);
				addChanges(oldState);
			} catch (IllegalStateException e) {
				// report and log
			}
		}
	}
	private void addChanges(OldState oldState) {
		
	}
	
	/*public void redo() {
		
		if (!redoStack.isEmpty()) {
			try {
				OldState oldState = new OldState();
				Command command = redoStack.pop();
				//izvrsava redo
				command.redo();
				//moguce je izvrsiti undo
				undoStack.push(command);
				addChanges(oldState);
			} catch (IllegalStateException e) {
				// report and log
			}
		}
	}*/
	public boolean isUndoAvailable() {
		return !undoStack.isEmpty();
	}
	public boolean isRedoAvailable() {
		return !redoStack.isEmpty();
	}
	
	public String getUndoName() {
		if (isUndoAvailable()) {
			//return undoStack.peek().getName();
		}
		return "";
	}
	public String getRedoName() {
		if (isRedoAvailable()) {
			//return redoStack.peek().getName();
		}
		return "";
	}
}
