package importt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class OpenDrawFile implements Import {

	@Override
	public ArrayList<Object> importLogDraw(String path) {
		
	
		try {
			//citanje objekta iz fajla
			FileInputStream file=new FileInputStream(path);
			ObjectInputStream inputStream=new ObjectInputStream(file);
			return (ArrayList <Object>) inputStream.readObject();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
