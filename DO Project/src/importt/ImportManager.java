package importt;

import java.util.ArrayList;

import export.Export;

public class ImportManager implements Import{
	
    private Import importt;
	
	public ImportManager(Import importt) {
		this.importt=importt;
	}

	@Override
	public ArrayList<Object> importLogDraw(String path) {
	 return importt.importLogDraw(path);
	}

}
