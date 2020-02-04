package export;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class SaveToLogFile implements Export {

	@Override
	public void export(ArrayList<Object> data, String path) {
		
		File newFile=new File(path);
		try {
			newFile.createNewFile(); //kreiran prazan fajl
		} catch (IOException e) { //javlja gresku bez exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
			byte [] toBytes = null;
			for(String logs:(ArrayList<String>) data.get(0)) {
			toBytes=logs.getBytes();
			
			try {
				Path convertPath=Paths.get(path); //jer mi je path string, sljedeca metoda prima path
		   Files.write(convertPath, toBytes, StandardOpenOption.APPEND);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}

	}
	


}
