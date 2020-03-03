package drawing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;

import command.CmdAddCircle;
import command.CmdAddDonut;
import command.CmdAddHexagon;
import command.CmdAddLine;
import command.CmdAddPoint;
import command.CmdAddRectangle;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdModifyCircle;
import command.CmdModifyDonut;
import command.CmdModifyHexagon;
import command.CmdModifyLine;
import command.CmdModifyPoint;
import command.CmdModifyRectangle;
import command.CmdRemoveCircle;
import command.CmdRemoveDonut;
import command.CmdRemoveHexagon;
import command.CmdRemoveLine;
import command.CmdRemovePoint;
import command.CmdRemoveRectangle;
import command.CmdSelection;
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import command.CommandManager;
import export.ExportManager;
import export.FileHelp;
import export.SaveToDrawFile;
import export.SaveToLogFile;
import hexagon.DlgHexagon;
import hexagon.Hexagon;
import hexagon.HexagonAdapter;
import importt.ImportManager;
import importt.OpenDrawFile;
import importt.OpenLogFile;

public class DrawingController implements PropertyChangeListener{
	
	
	private DrawingModel model;
	private FrmDrawing mainFrame;
	//javlja null exception kad ga stavim u model
	private Point startPoint;
	private ArrayList<Object> log;
	private int numberOfLine;
	private LineNumberReader lineNumberReader;

	
	public DrawingController(DrawingModel model2, FrmDrawing frame2) {
		model=model2;
		mainFrame=frame2;
		//model.addPropertyChangeListener(mainFrame);
	}

	public void mouseClicked(MouseEvent e) {
		 
		Command cmdAdd = null;
		
		

		
		if(mainFrame.getTglbtnSelection()) {
			
				boolean notFoundShape=true;
				CmdSelection cmdSelection;
				Point p=new Point(e.getX(),e.getY()); //koordinate klika
				Iterator <Shape> it=model.getShapes().iterator();
				while(it.hasNext()) {
					Shape shape=it.next();
					if(shape.contains(p))
					{
						notFoundShape=false;
					if( !shape.isSelected())
					{
						cmdSelection=new CmdSelection(shape,model,mainFrame);
						execute(cmdSelection);	
					
					}
					else 
					{
						model.setSelection(shape,false);	
					}
					}			
				}
				if(notFoundShape) {
				
					model.removeAllSelection();
				}
			
			
			
			
		
		

		}
		if(mainFrame.getTglbtnPoint()) {
			Point p=new Point(e.getX(),e.getY()); //klik
			
			
			DlgPoint dlgP=new DlgPoint();
			dlgP.setTxtXEditable(false); //onemogucavam izmjenu
			dlgP.setTxtYEditable(false);
			dlgP.setTxtX(Integer.toString(p.getX()));
			dlgP.setTxtY(Integer.toString(p.getY()));
			dlgP.setVisible(true); //prikaz dijaloga
			if(dlgP.isOk())
			{
			    p.setOuterColor(dlgP.getCol());
			    cmdAdd=new CmdAddPoint(p,model);
			
			    execute(cmdAdd);
			    
			   
			}
		}
		else if(mainFrame.getTglbtnLine()) {
			if(startPoint==null) {
				startPoint=new Point(e.getX(),e.getY());
				
			}
			else {
				Line l=new Line(startPoint,new Point(e.getX(),e.getY()));
			    DlgLine dlgL=new DlgLine();
				dlgL.setTxtStartPointXEditable(false);
				dlgL.setTxtStartPointYEditable(false);
				dlgL.setTxtEndPointXEditable(false);
				dlgL.setTxtEndPointYEditable(false);		
				dlgL.setTxtStartPointX(Integer.toString(l.getStartPoint().getX()));
				dlgL.setTxtStartPointY(Integer.toString(l.getStartPoint().getY()));
				dlgL.setTxtEndPointX(Integer.toString(l.getEndPoint().getX()));
				dlgL.setTxtEndPointY(Integer.toString(l.getEndPoint().getY()));
				dlgL.setVisible(true);
			if(dlgL.isOk())
			{
				l.setOuterColor(dlgL.getCol());
				 cmdAdd=new CmdAddLine(l,model);
				 execute(cmdAdd);
				   
			    startPoint=null;
			}
			    }
		}
		else if(mainFrame.getTglbtnRectangle()) {
			Point p=new Point(e.getX(),e.getY());
			DlgRectangle dlgR=new DlgRectangle();
			dlgR.setTxtUpperLeftPointXEditable(false);
			dlgR.setTxtUpperLeftPointYEditable(false);
			dlgR.setTxtUpperLeftPointX(Integer.toString(p.getX()));
			dlgR.setTxtUpperLeftPointY(Integer.toString(p.getY()));
			dlgR.setVisible(true);
			try
			{
			if(dlgR.isOk()) {	
			    int h=Integer.parseInt(dlgR.getTxtHeight());
			    int w=Integer.parseInt(dlgR.getTxtWidth());
			    Rectangle r=new Rectangle(p,h,w);
			    r.setOuterColor(dlgR.getExterCol());
			    r.setInnerColor(dlgR.getInterCol());
			    cmdAdd=new CmdAddRectangle(r,model);
			    execute(cmdAdd);
			  
			   
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(),"Popunite sva polja ili provjerite tip podataka koji ste unijeli!" , "Greska", JOptionPane.WARNING_MESSAGE);
				
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju biti pozitivne!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(mainFrame.getTglbtnCircle()) {
			Point center=new Point(e.getX(),e.getY());
			DlgCircle dlgC=new DlgCircle();
			dlgC.setTxtCenterXEditable(false);
			dlgC.setTxtCenterYEditable(false);
			dlgC.setTxtCenterX(Integer.toString(center.getX()));
			dlgC.setTxtCenterY(Integer.toString(center.getY()));
			dlgC.setVisible(true);
			try
			{
			if(dlgC.isOk())
			{
				int radius=Integer.parseInt(dlgC.getTxtRadius());
				Circle c=new Circle(center,radius);
				c.setOuterColor(dlgC.getExterCol());
				c.setInnerColor(dlgC.getInterCol());
				 cmdAdd=new CmdAddCircle(c,model);
				 execute(cmdAdd);
			
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
				
				
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnik mora biti pozitivan!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(mainFrame.getTglbtnDonut()) {
			Point center=new Point(e.getX(),e.getY());
			DlgDonut dlgD =new DlgDonut();
			dlgD.setTxtCenterXEditable(false);
			dlgD.setTxtCenterYEditable(false);
			dlgD.setTxtCenterX(Integer.toString(center.getX()));
			dlgD.setTxtCenterY(Integer.toString(center.getY()));
			dlgD.setVisible(true);
			try
			{
			if(dlgD.isOk())
			{
				int innerRadius=Integer.parseInt(dlgD.getTxtInnerRadius());
				int radius=Integer.parseInt(dlgD.getTxtRadius());
				Donut d=new Donut(center,radius,innerRadius);
				d.setOuterColor(dlgD.getExterCol());
				d.setInnerColor(dlgD.getInterCol());
				cmdAdd=new CmdAddDonut(d,model);
				execute(cmdAdd);
				   
			}
			}
			
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greska", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnici moraju biti pozitivni i unutrasnji poluprecnik mora biti manji od spoljasnjeg!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			
			
		}
		else if(mainFrame.getTglbtnHexagon()) {		
			Point center=new Point(e.getX(),e.getY());
			DlgHexagon dlgH=new DlgHexagon();
			dlgH.setTxtCenterXEditable(false);
			dlgH.setTxtCenterYEditable(false);
			dlgH.setTxtCenterX(Integer.toString(center.getX()));
			dlgH.setTxtCenterY(Integer.toString(center.getY()));
			dlgH.setVisible(true);
			try
		{
				

			if(dlgH.isOk())
		{

				int radius=Integer.parseInt(dlgH.getTxtRadius());
				HexagonAdapter h=new HexagonAdapter(center.getX(),center.getY(),radius);
				h.setOuterColor(dlgH.getExterCol());
				if(h.getOuterColor()==null) {
					h.setOuterColor(Color.BLACK);
				}
			    h.setInterColor(dlgH.getInterCol());	
				if(h.getInterColor()==null) {
					h.setInterColor(Color.BLACK);
				}
				cmdAdd=new CmdAddHexagon(h,model);
				execute(cmdAdd);
		
		}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
				
				
			} catch (Exception e1) {
			
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnik mora biti pozitivan!", "Greška", JOptionPane.WARNING_MESSAGE);
			}

		}/*
		else if(model.getSelectedShapes()!=null)
		{
			for (Shape s : model.getSelectedShapes()) {
			 s.setSelected(true);
			}
		}*/
		
		
		//kad stavim getView prikazuje i dijaloge
		if(model.getShapes()!=null) 
			mainFrame.repaint();
			
}			



	

	public void mouseClickedModify(MouseEvent e) {
		Shape newState;
		Command cmdModify = null;
		Shape modifyShape=model.getSelectedShape();
		if (model.getSelectedShapes().size() != 1)
			return;
	
			if(modifyShape instanceof Point)
			{
	
				DlgPoint dp=new DlgPoint();
				dp.setTxtX(Integer.toString(((Point) modifyShape).getX()));
				dp.setTxtY(Integer.toString(((Point) modifyShape).getY()));
				dp.setCol(((Point) modifyShape).getOuterColor());
				dp.setVisible(true);
				try 
				{	
				     if(dp.isOk())
				     {
				    	  newState=new Point();
				          ((Point) newState).setX(Integer.parseInt(dp.getTxtX()));
				          ((Point) newState).setY(Integer.parseInt(dp.getTxtY()));
				          ((Point) newState).setOuterColor(dp.getCol());
				          cmdModify=new CmdModifyPoint((Point)modifyShape,(Point)newState,model);
				          
				          mainFrame.repaint();          
				}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
				}
				
	     		
			}
			else if(modifyShape instanceof Line)
			{
				DlgLine dl=new DlgLine();
				dl.setTxtStartPointX(Integer.toString(((Line)modifyShape).getStartPoint().getX()));
				dl.setTxtStartPointY(Integer.toString(((Line)modifyShape).getStartPoint().getY()));
				dl.setTxtEndPointX(Integer.toString(((Line)modifyShape).getEndPoint().getX()));
				dl.setTxtEndPointY(Integer.toString(((Line)modifyShape).getEndPoint().getY()));
				dl.setCol(((Line)modifyShape).getOuterColor());
				dl.setVisible(true);
				//u ovom dijelu otvara dijalog i prikazuje vrijednosti koje kupi sa panela
				try
				{
					if(dl.isOk())
					{
						newState=new Line();
					

						((Line) newState).setStartPoint(new Point((Integer.parseInt(dl.getTxtStartPointX())),(Integer.parseInt(dl.getTxtStartPointY()))));
						((Line) newState).setEndPoint(new Point((Integer.parseInt(dl.getTxtEndPointX())),(Integer.parseInt(dl.getTxtEndPointY()))));
						((Line) newState).setOuterColor(dl.getCol());
				          cmdModify=new CmdModifyLine((Line)modifyShape,(Line)newState,model);
				         
				          mainFrame.repaint();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(modifyShape instanceof Rectangle)
			{
				DlgRectangle dr=new DlgRectangle();
				dr.setTxtUpperLeftPointX(Integer.toString(((Rectangle)modifyShape).getUpperLeftPoint().getX()));
				dr.setTxtUpperLeftPointY(Integer.toString(((Rectangle)modifyShape).getUpperLeftPoint().getY()));
				dr.setTxtWidth(Integer.toString(((Rectangle)modifyShape).getWidth()));
				dr.setTxtHeight(Integer.toString(((Rectangle)modifyShape).getHeight()));
				dr.setInterCol(((Rectangle)modifyShape).getInnerColor());
				dr.setExterCol(((Rectangle)modifyShape).getOuterColor());
				dr.setVisible(true);
				try
				{
					if(dr.isOk())
					{
						newState=new Rectangle();
						((Rectangle)newState).setUpperLeftPoint(new Point(Integer.parseInt(dr.getTxtUpperLeftPointX()),Integer.parseInt(dr.getTxtUpperLeftPointY())));
						((Rectangle)newState).setHeight(Integer.parseInt(dr.getTxtHeight()));
						((Rectangle)newState).setWidth(Integer.parseInt(dr.getTxtWidth()));
						((Rectangle)newState).setOuterColor(dr.getExterCol());
						((Rectangle)newState).setInnerColor(dr.getInterCol());				          
			          cmdModify=new CmdModifyRectangle((Rectangle)modifyShape,(Rectangle)newState,model);

				          mainFrame.repaint();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju biti pozitivne!", "Greška", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(modifyShape instanceof Donut)
			{
				
				DlgDonut dd=new DlgDonut();
				dd.setTxtCenterX(Integer.toString(((Donut)modifyShape).getCenter().getX()));
				dd.setTxtCenterY(Integer.toString(((Donut)modifyShape).getCenter().getY()));
				dd.setTxtRadius(Integer.toString(((Donut)modifyShape).getRadius()));
				dd.setTxtInnerRadius(Integer.toString(((Donut)modifyShape).getInnerRadius()));
				dd.setInterCol((((Donut)modifyShape).getInnerColor()));
				dd.setExterCol((((Donut)modifyShape).getOuterColor()));
				dd.setVisible(true);
			
				try
				{  
					if(dd.isOk())
					{
                        newState=new Donut();
						((Donut)newState).setCenter(new Point(Integer.parseInt(dd.getTxtCenterX()),Integer.parseInt(dd.getTxtCenterY())));
						
						((Donut)newState).setRadius(Integer.parseInt(dd.getTxtRadius()));
						
						((Donut)newState).setInnerRadius(Integer.parseInt(dd.getTxtInnerRadius()));
						
						((Donut)newState).setInnerColor(dd.getInterCol());
					
						((Donut)newState).setOuterColor(dd.getExterCol());
				
				
				          cmdModify=new CmdModifyDonut((Donut)modifyShape,(Donut)newState,model);

				          mainFrame.repaint();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception ex)
				{
					
					JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju biti pozitivne!", "Greška", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(modifyShape instanceof Circle)
			{

				DlgCircle dc=new DlgCircle();
				dc.setTxtCenterX(Integer.toString(((Circle)modifyShape).getCenter().getX()));
				dc.setTxtCenterY(Integer.toString(((Circle)modifyShape).getCenter().getY()));
				dc.setTxtRadius(Integer.toString(((Circle)modifyShape).getRadius()));
				dc.setInterCol(((Circle)modifyShape).getInnerColor());
				dc.setExterCol(((Circle)modifyShape).getOuterColor());
				dc.setVisible(true);
				try
				{
					if(dc.isOk())
					{
                         newState=new Circle();
						((Circle)newState).setCenter(new Point(Integer.parseInt(dc.getTxtCenterX()),Integer.parseInt(dc.getTxtCenterY())));
						((Circle)newState).setRadius(Integer.parseInt(dc.getTxtRadius()));
						((Circle)newState).setOuterColor(dc.getExterCol());
						((Circle)newState).setInnerColor(dc.getInterCol());
				          cmdModify=new CmdModifyCircle((Circle)modifyShape,(Circle)newState,model);

				          mainFrame.repaint();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju biti pozitivne!", "Greška", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		
		else if(modifyShape instanceof HexagonAdapter)
		{
			
           
			DlgHexagon dh=new DlgHexagon();
			dh.setTxtCenterX(Integer.toString((((HexagonAdapter)modifyShape).getX())));
			dh.setTxtCenterY(Integer.toString((((HexagonAdapter)modifyShape).getY())));
			dh.setTxtRadius(Integer.toString((((HexagonAdapter)modifyShape).getRadius())));
			dh.setInterCol((((HexagonAdapter)modifyShape).getInterColor()));
			dh.setExterCol((((HexagonAdapter)modifyShape).getOuterColor()));
		
			dh.setVisible(true);
			try
			{
				
				
				if(dh.isOk())
				{
					
					  newState=new HexagonAdapter();
					 
					  int x=Integer.parseInt(dh.getTxtCenterX());
					  int y=Integer.parseInt(dh.getTxtCenterY());
					  int radius=Integer.parseInt(dh.getTxtRadius());
					  Color cO=dh.getExterCol();
					  Color cI=dh.getInterCol();
					  newState=new HexagonAdapter(x,y,radius,cI,cO);
					  
				          cmdModify=new CmdModifyHexagon((HexagonAdapter)modifyShape,(HexagonAdapter)newState,model);
				          
				          mainFrame.repaint();
				}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju biti pozitivne!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		}
			  execute(cmdModify);
			
	 
	

		
	}

	public void mouseClickedDelete(MouseEvent e) {			
			Command cmdRmv = null;
			if (model.getSelectedShapes().size() == 0)
				return;
			int answer=JOptionPane.showConfirmDialog(new JFrame(), "Da li ste sigurni da zelite da obrisete oblik?", "Brisanje oblika", JOptionPane.YES_NO_OPTION);
		if( answer==JOptionPane.YES_OPTION)
		{
			for (Shape deleteShape : model.getSelectedShapes()) {

			if(deleteShape instanceof Point)
			{
				cmdRmv=new CmdRemovePoint((Point)deleteShape,model);
			}
			else if(deleteShape instanceof Line)
			{
				cmdRmv=new CmdRemoveLine((Line)deleteShape,model);
				
			}
			else if(deleteShape instanceof Rectangle)
			{
				 cmdRmv=new CmdRemoveRectangle((Rectangle)deleteShape,model);
				
			}
			else if(deleteShape instanceof Donut)
			{
				cmdRmv=new CmdRemoveDonut((Donut)deleteShape,model);
	
			}
			else if(deleteShape instanceof Circle)
			{
				cmdRmv=new CmdRemoveCircle((Circle)deleteShape,model);
				
			}
			
			else if(deleteShape instanceof HexagonAdapter)
			{
				cmdRmv=new CmdRemoveHexagon((HexagonAdapter)deleteShape,model);
	
			}
			
			execute(cmdRmv);
					
			}
			
			mainFrame.repaint();
			//da obrise indekse
			model.getSelectedShapes().clear();
			
		
            

		}
		
		
		
		

		
  }
	public void execute(Command command) {
		 deleteElementsAfterPointer(model.getUndoRedoPointer());
		    command.execute();
		    model.getCommandStack().push(command);
		    model.setUndoRedoPointer(model.getUndoRedoPointer()+1);
		    
	}
	public void deleteElementsAfterPointer(int undoRedoPointer)
	{
	    if(model.getCommandStack().size()<1)
	    	return;
	    for(int i = model.getCommandStack().size()-1; i > undoRedoPointer; i--)
	    {
	    	model.getCommandStack().remove(i);
	    }
	}

	
	public void undo() {

		if(model.getUndoRedoPointer()<=-1)
	        return;
	
		Command command = model.getCommandStack().get(model.getUndoRedoPointer());		
	    command.unexecute();
	    model.setUndoRedoPointer(model.getUndoRedoPointer()-1);
		mainFrame.repaint();
	
	}

	public void redo() {
		
		    if(model.getUndoRedoPointer() == model.getCommandStack().size() - 1)
		        return;
		    model.setUndoRedoPointer(model.getUndoRedoPointer()+1);
		    Command command = model.getCommandStack().get(model.getUndoRedoPointer());
		    command.execute();
		    mainFrame.repaint();
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	
		if(evt.getPropertyName().equals("selectedShapes"))
		{
			mainFrame.tglbtnModify.setEnabled(model.getSelectedShapes().size()==1);				
			mainFrame.tglbtnDelete.setEnabled(model.getSelectedShapes().size()>0);
			mainFrame.tglbtnBringToBack.setEnabled(model.getSelectedShapes().size()==1);	
			mainFrame.tglbtnBringToFront.setEnabled(model.getSelectedShapes().size()==1);				
			mainFrame.tglbtnToback.setEnabled(model.getSelectedShapes().size()==1);				
			mainFrame.tglbtnTofront.setEnabled(model.getSelectedShapes().size()==1);				

		}
		if(evt.getPropertyName().equals("undoRedo")) {
			
			mainFrame.tglbtnUndo.setEnabled(model.getUndoRedoPointer()>-1);
			mainFrame.tglbtnRedo.setEnabled(model.getUndoRedoPointer()<model.getCommandStack().size()-1);
		}
		
			
			
		
	
	}

	public void bringToFront() {
		if (model.getSelectedShapes().size() != 1)
			return;
		Shape s=model.getSelectedShape();
		int selectedIndex=model.getSelectedShapeIndex();

		System.out.println(model.getSelectedShapeIndex());
		CmdBringToFront cmdBToF=new CmdBringToFront(model,s,selectedIndex);
		execute(cmdBToF);
		System.out.println(model.getSelectedShapeIndex());
		
	}

	public void bringToBack() {
		if (model.getSelectedShapes().size() != 1)
			return;
		Shape s=model.getSelectedShape();
		int selectedIndex=model.getSelectedShapeIndex();

		System.out.println(model.getSelectedShapeIndex());
		CmdBringToBack cmdBToB=new CmdBringToBack(model,s,selectedIndex);
		execute(cmdBToB);
		System.out.println(model.getSelectedShapeIndex());
		
	}

	public void toBack() {
		if (model.getSelectedShapes().size() != 1)
			return;
		Shape s=model.getSelectedShape();
		int index=model.getSelectedShapeIndex();
		System.out.println(model.getSelectedShapeIndex());
		CmdToBack cmdToB=new CmdToBack(model,s,index);
		execute(cmdToB);
		System.out.println(model.getSelectedShapeIndex());		
	}

	public void toFront() {
		if (model.getSelectedShapes().size() != 1)
			return;
		Shape s=model.getSelectedShape();
		int index=model.getSelectedShapeIndex();
		System.out.println(model.getSelectedShapeIndex());
		CmdToFront cmdToF=new CmdToFront(model,s,index);
		execute(cmdToF);
		System.out.println(model.getSelectedShapeIndex());
		
	}

	public void exportToLog() {
		ArrayList<Object> helpList=new ArrayList<Object>();
		helpList.add(model.getLogs());
		ExportManager exportManager=new ExportManager(new SaveToLogFile());
		String path=FileHelp.showFileDialogSave("log");
		if(path!=null) {
			exportManager.export(helpList, path);
		}
		
	}

	public void importFromLog() {
		
		ImportManager importManager=new ImportManager(new OpenLogFile());
		String path=FileHelp.showFileDialogOpen("log");
		if(path!=null) {
			ArrayList<Object> log=importManager.importLogDraw(path);
			mainFrame.tglbtnExecuteLog.setEnabled(log!=null);
			try {
				lineNumberReader=new LineNumberReader(new FileReader(path));
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Object l: log)
			{
			mainFrame.getDlm().addElement((String) l);
			

			}						
		}
		
	}

	public void exportToDraw() {
		ArrayList<Object> objectFromDraw=new ArrayList<Object>();
		objectFromDraw.add(model.getShapes());
		ExportManager exportManager=new ExportManager(new SaveToDrawFile());
		String path=FileHelp.showFileDialogSave("drwg");
		if(path!=null) {
			exportManager.export(objectFromDraw, path);
		}
	}

	public void importFromDraw() {
		
		ImportManager importManager=new ImportManager(new OpenDrawFile());
		String path=FileHelp.showFileDialogOpen("drwg");
		if(path!=null) {
			ArrayList<Object> log=importManager.importLogDraw(path);
			for (Shape s : (ArrayList<Shape>) log.get(0)) {
				
				model.add(s);
			}
			
			mainFrame.repaint();
			
	}
		
	}

	public void executeLog() {
		
		
		try {
			/*
			if(model.getLogs().isEmpty())
				return;*/
			String line=lineNumberReader.readLine();
		
		
			CommandManager commandManager=new CommandManager();
			Command command=commandManager.parse(line,model,mainFrame);
		
			if(line.contains("Execute"))
			{	
			execute(command);
			
			
			}
			if(line.contains("Unexecute"))
			{
			undo();
			}
			mainFrame.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
			
			
	
		
		
	}

	public void addOuterColor() {
		Color colorForButton=JColorChooser.showDialog(null, "Odaberite boju!", mainFrame.tglbtnOuterColor.getBackground());
		if(colorForButton!=null) {
		mainFrame.tglbtnOuterColor.setBackground(colorForButton);
		}
		else
		{
			mainFrame.tglbtnOuterColor.setBackground(mainFrame.tglbtnOuterColor.getBackground());
		}
	
	}
	public void addInnerColor() {
		Color colorForButton=JColorChooser.showDialog(null, "Odaberite boju!", mainFrame.tglbtnInnerColor.getBackground());
		if(colorForButton!=null) {
			mainFrame.tglbtnInnerColor.setBackground(colorForButton);
			}
			else
			{
				mainFrame.tglbtnInnerColor.setBackground(mainFrame.tglbtnInnerColor.getBackground());
			}
	}

	public void clickSelect(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.DESELECTED) {
		
			model.removeAllSelection();
			mainFrame.repaint();
			
		}
	}

	
}
	
	
	
	
		
	
		
	

