package drawing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DrawingApp {

	public static void main(String[] args) {
		
		DrawingModel model=new DrawingModel();
		FrmDrawing frame=new FrmDrawing();
		
		DrawingController controller=new DrawingController(model,frame);
		
		model.addPropertyChangeListener(controller);
		frame.getView().setModel(model);
		frame.setController(controller);
		//frame.getDlm().addElement(model.peek());

	
		frame.setBounds(500, 500, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
