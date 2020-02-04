package export;

import java.awt.Component;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileHelp {
	
	public static String showFileDialogSave(String extension) {
		
		
		JFrame frame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();  
		
		//FileSystemView.getFileSystemView().getHomeDirectory() da prikaze Desktop
		 
		int userClick = fileChooser.showSaveDialog(frame); //prikazuje dijalog
		 
		if (userClick == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    return fileToSave.getAbsolutePath()+ "." + extension;
		}
		
		return null;
		
		
	}
	public static String showFileDialogOpen(String extension) {
		JFrame frame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();  
		
		//FileSystemView.getFileSystemView().getHomeDirectory() da prikaze Desktop
		 
		int userClick = fileChooser.showOpenDialog(frame); //prikazuje dijalog
		 
		if (userClick == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    return fileToSave.getAbsolutePath();
		}
		
		return null;
		
		
	}
	


}
