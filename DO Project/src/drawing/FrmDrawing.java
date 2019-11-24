package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {
	
	private DrawingController controller;
	private DrawingView view=new DrawingView();


	private JPanel contentPane;
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnDonut;
	JToggleButton tglbtnSelection;
	JToggleButton tglbtnModify;
	JToggleButton tglbtnDelete;


	public FrmDrawing() {
		setTitle("Milica Despotovic IT 08-2017");		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.gray);

		ButtonGroup buttonGroup=new ButtonGroup();
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		contentPane.add(view, BorderLayout.CENTER);
		tglbtnPoint = new JToggleButton("Point");
		toolBar.add(tglbtnPoint);
		buttonGroup.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		toolBar.add(tglbtnLine);
		buttonGroup.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//view.setStartPoint(null);
			}
		});
		toolBar.add(tglbtnRectangle);
		buttonGroup.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		toolBar.add(tglbtnCircle);
		buttonGroup.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		toolBar.add(tglbtnDonut);
		buttonGroup.add(tglbtnDonut);
		
		tglbtnSelection = new JToggleButton("Selection");
		toolBar.add(tglbtnSelection);
		buttonGroup.add(tglbtnSelection);
		
		tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClickedModify(e);
				
		};
		});
	
	    
		toolBar.add(tglbtnModify);
		buttonGroup.add(tglbtnModify);
		
		tglbtnDelete = new JToggleButton("Delete");	
		tglbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClickedDelete(e);
				
		};
		});
		toolBar.add(tglbtnDelete);
		buttonGroup.add(tglbtnDelete);
	}

	public boolean getTglbtnPoint() {
		return tglbtnPoint.isSelected();
	}

	public boolean getTglbtnLine() {
		return tglbtnLine.isSelected();
	}

	public boolean getTglbtnRectangle() {
		return tglbtnRectangle.isSelected();
	}

	public boolean getTglbtnCircle() {
		return tglbtnCircle.isSelected();
	}

	public boolean getTglbtnDonut() {
		return tglbtnDonut.isSelected();
	}

	public boolean getTglbtnSelection() {
		return tglbtnSelection.isSelected();
	}

	public boolean getTglbtnModify() {
		return tglbtnModify.isSelected();
	}

	public boolean getTglbtnDelete() {
		return tglbtnDelete.isSelected();
	}
	
	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public void setController(DrawingController controller2) {
		controller=controller2;
		
	}

}
