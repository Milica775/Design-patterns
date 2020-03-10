package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;





import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmDrawing extends JFrame  {
	
	private DrawingController controller;
	private DrawingView view=new DrawingView();
	//da li moze da bude ovdje??
	private static DefaultListModel<String> dlm=new DefaultListModel<>();

	private JPanel contentPane;
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnDonut;
	JToggleButton tglbtnSelection;
	JButton tglbtnModify;
	JButton tglbtnDelete;
    JToggleButton tglbtnHexagon;
    JButton tglbtnUndo;
    JToggleButton tglbtnRedo;
    private JToolBar toolBar_1;
     JToggleButton tglbtnTofront;
	 JToggleButton tglbtnToback;
     JToggleButton tglbtnBringToBack;
    JToggleButton tglbtnBringToFront;
    private JScrollPane scrollPane;
    JToggleButton tglbtnExportToLog;
    JToggleButton tglbtnImportFromLog;
    JToggleButton tglbtnExportToDrawFile;
    JToggleButton tglbtnImportFromDraw;
    JToggleButton tglbtnExecuteLog;
     JButton tglbtnInnerColor;
     JButton tglbtnOuterColor;
 


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
		
		
		toolBar_1 = new JToolBar(JToolBar.VERTICAL);
		contentPane.add(toolBar_1, BorderLayout.WEST);
		
		tglbtnUndo = new JButton("Undo");
		tglbtnUndo.setEnabled(false);
		tglbtnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.undo();
			}
		});
	
		
		toolBar_1.add(tglbtnUndo);
		buttonGroup.add(tglbtnUndo);
		
		
		
		
		tglbtnRedo = new JToggleButton("Redo");
		tglbtnRedo.setEnabled(false);
		tglbtnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		
		toolBar_1.add(tglbtnRedo);
		
		tglbtnToback = new JToggleButton("To Back");
		tglbtnToback.setEnabled(false);
		tglbtnToback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.toBack();
			}
		});
		
		toolBar_1.add(tglbtnToback);
		
		tglbtnTofront = new JToggleButton("To Front");
		tglbtnTofront.setEnabled(false);
		tglbtnTofront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.toFront();
			}
		});
		
		toolBar_1.add(tglbtnTofront);
		
		tglbtnBringToFront = new JToggleButton("Bring To Front");
		tglbtnBringToFront.setEnabled(false);
		tglbtnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.bringToFront();
			}
		});
	
		toolBar_1.add(tglbtnBringToFront);
		
		tglbtnBringToBack = new JToggleButton("Bring To Back");
		tglbtnBringToBack.setEnabled(false);
		tglbtnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.bringToBack();
			}
		});
	
		toolBar_1.add(tglbtnBringToBack);
		
		tglbtnExportToLog = new JToggleButton("Export To Log");
		tglbtnExportToLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.exportToLog();
			}
		});
		toolBar_1.add(tglbtnExportToLog);
		
		tglbtnImportFromLog = new JToggleButton("Import From Log");
		tglbtnImportFromLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.importFromLog();
			}
		});
		toolBar_1.add(tglbtnImportFromLog);
		
		tglbtnExportToDrawFile = new JToggleButton("Export To Draw File");
		tglbtnExportToDrawFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.exportToDraw();
			}
		});
		toolBar_1.add(tglbtnExportToDrawFile);
		
		tglbtnImportFromDraw = new JToggleButton("Import From Draw ");
		tglbtnImportFromDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.importFromDraw();
			}
		});
		toolBar_1.add(tglbtnImportFromDraw);
	
		
		tglbtnExecuteLog = new JToggleButton("Execute log");
		tglbtnExecuteLog.setEnabled(false);
		tglbtnExecuteLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.executeLog();
			}
		});
		toolBar_1.add(tglbtnExecuteLog);
		
		tglbtnInnerColor = new JButton("Inner Color");
		tglbtnInnerColor.setBackground(Color.MAGENTA);
		tglbtnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.addInnerColor();
			}
		});
		toolBar_1.add(tglbtnInnerColor);
		
		tglbtnOuterColor = new JButton("Outer Color");
		tglbtnOuterColor.setBackground(Color.BLUE);
		tglbtnOuterColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.addOuterColor();
			}
		});
		toolBar_1.add(tglbtnOuterColor);
		
		
		
		
		
		
		
		contentPane.add(view, BorderLayout.CENTER);
		
		tglbtnPoint=new JToggleButton("Point");
		
		
		toolBar.add(tglbtnPoint);
		buttonGroup.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		toolBar.add(tglbtnLine);
		buttonGroup.add(tglbtnLine);
		tglbtnRectangle = new JToggleButton("Rectangle");
	toolBar.add(tglbtnRectangle);
		buttonGroup.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		toolBar.add(tglbtnCircle);
		buttonGroup.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		toolBar.add(tglbtnDonut);
		buttonGroup.add(tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		toolBar.add(tglbtnHexagon);
		buttonGroup.add(tglbtnHexagon);
		
		tglbtnSelection = new JToggleButton("Selection");
		tglbtnSelection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				controller.clickSelect(e);
			}
		});
	
		toolBar.add(tglbtnSelection);
		buttonGroup.add(tglbtnSelection);
		
		tglbtnModify = new JButton("Modify");
		tglbtnModify.setEnabled(false);
		tglbtnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
             controller.mouseClickedModify(e);
				
		};
		});
	
	    
		toolBar.add(tglbtnModify);
		buttonGroup.add(tglbtnModify);
		
		tglbtnDelete = new JButton("Delete");	
		tglbtnDelete.setEnabled(false);
		tglbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClickedDelete(e);
			
				
		};
		});
		toolBar.add(tglbtnDelete);
		buttonGroup.add(tglbtnDelete);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.EAST);
		JList list = new JList();
		
		list.setModel(dlm);
		scrollPane.setViewportView(list);
		
		
		scrollPane.setBounds(586, 452, 784, 461);
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

	public boolean getTglbtnHexagon() {
		return tglbtnHexagon.isSelected();
	}
	
	public boolean getTglbtnUndo() {
		return tglbtnUndo.isSelected();
	}
	public boolean getTglbtnTofront() {
			return tglbtnTofront.isSelected();
	}

	public boolean getTglbtnToback() {
			return tglbtnToback.isSelected();
	}

	public boolean getTglbtnBringToBack() {
			return tglbtnBringToBack.isSelected();
	}

	public boolean getTglbtnBringToFront() {
			return tglbtnBringToFront.isSelected();
	}
	
	public boolean getTglbtnRedo() {
		return tglbtnRedo.isSelected();
	}
	
	
	public static DefaultListModel<String> getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel<String> dlm) {
		this.dlm = dlm;
	}

	

}
