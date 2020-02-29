package export;

import java.util.ArrayList;

public class ExportManager implements Export {
	
	private Export export;
	
	public ExportManager(Export export) {
		this.export=export;
	}

	@Override
	public void export(ArrayList<Object> data, String path) {
		export.export(data, path);
		
	}

}
