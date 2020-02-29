package export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveToDrawFile implements Export{

	@Override
	public void export(ArrayList<Object> data, String path) {
		try {
			
			//cuvanje objekta u fajl
			FileOutputStream file=new FileOutputStream(path);
			ObjectOutputStream objectStream=new ObjectOutputStream(file);
			
			//metoda za serijalizaciju objekta
			objectStream.writeObject(data);
			
			objectStream.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
