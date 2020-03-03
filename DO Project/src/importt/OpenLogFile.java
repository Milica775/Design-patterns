package importt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class OpenLogFile implements Import {

	@Override
	public ArrayList<Object> importLogDraw(String path) {
		
		
		File file = new File(path);
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String line;
		try {
			 ArrayList<Object> logLine=new ArrayList<Object>();
			while((line = br.readLine()) != null){
			    
			   
			    logLine.add(line);
			    
			}
			return logLine;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	
	}

}
