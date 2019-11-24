package drawing;

import javax.swing.JFrame;

public class DrawingApp {

	public static void main(String[] args) {
		
		DrawingModel model=new DrawingModel();
		FrmDrawing frame=new FrmDrawing();
		
		DrawingController controller=new DrawingController(model,frame);
		
		frame.getView().setModel(model);
		frame.setController(controller);
		
	
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
