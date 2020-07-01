package mvc;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DrawingApp {

	public static void main(String[] args) {
		
		DrawingModel model=new DrawingModel();
		FrmDrawing frame=new FrmDrawing();
		frame.getView().setModel(model);
	
		
		DrawingController controller=new DrawingController(model,frame);
		frame.setController(controller);
		

		
		frame.setBounds(70, 70, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		model.addPropertyChangeListener(controller);


	
	}

}
