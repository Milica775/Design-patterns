package mvc;

import java.awt.Color;

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
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import command.CommandManager;
import export.ExportManager;
import export.FileHelp;
import export.SaveToDrawFile;
import export.SaveToLogFile;
import shapes.DlgHexagon;
import shapes.HexagonAdapter;
import importt.ImportManager;
import importt.OpenDrawFile;
import importt.OpenLogFile;
import shapes.Circle;
import shapes.DlgCircle;
import shapes.DlgDonut;
import shapes.DlgLine;
import shapes.DlgPoint;
import shapes.DlgRectangle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class DrawingController implements PropertyChangeListener{
	
	
	private DrawingModel model;
	private FrmDrawing mainFrame;	
	private LineNumberReader lineNumberReader;
	private Point startPoint;

	
	public DrawingController(DrawingModel model2, FrmDrawing frame2) {
		model=model2;
		mainFrame=frame2;
		
	}

	public void mouseClicked(MouseEvent e) {
		 
		Command cmdAdd = null;
				
		if(mainFrame.getTglbtnSelection()) {
			
				boolean notFoundShape=true;
				Point p=new Point(e.getX(),e.getY()); //koordinate klika
				Iterator <Shape> it=model.getShapes().iterator();
				while(it.hasNext()) {
					Shape shape=it.next();
					if(shape.contains(p))
					{
						notFoundShape=false;
						
					if( !shape.isSelected())
					{
						model.setSelection(shape,true);
						DrawingModel.getInstanceLazy().log("Selection", shape.toString()+ "\r\n");

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
			dlgP.getBtnColor().setVisible(false);
			dlgP.setTxtXEditable(false); //onemogucavam izmjenu
			dlgP.setTxtYEditable(false);
			dlgP.setTxtX(Integer.toString(p.getX()));
			dlgP.setTxtY(Integer.toString(p.getY()));
			dlgP.setVisible(true);
			if(dlgP.isOk())
			{
			p.setOuterColor(mainFrame.getBtnOuterColor().getBackground());
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
				dlgL.getBtnColor().setVisible(false);
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
				l.setOuterColor(mainFrame.getBtnOuterColor().getBackground());
				 cmdAdd=new CmdAddLine(l,model);
				 execute(cmdAdd);
				   
			    startPoint=null;
			}
			    }
		}
		else if(mainFrame.getTglbtnRectangle()) {
			Point pp=new Point(e.getX(),e.getY());
			DlgRectangle dlgR=new DlgRectangle();
			dlgR.getBtnExteriorColor().setVisible(false);
			dlgR.getBtnInteriorColor().setVisible(false);
			dlgR.setTxtUpperLeftPointXEditable(false);
			dlgR.setTxtUpperLeftPointYEditable(false);
			dlgR.setTxtUpperLeftPointX(Integer.toString(pp.getX()));
			dlgR.setTxtUpperLeftPointY(Integer.toString(pp.getY()));
			dlgR.setVisible(true);
			try
			{
			if(dlgR.isOk()) {	
			    int h=Integer.parseInt(dlgR.getTxtHeight());
			    int w=Integer.parseInt(dlgR.getTxtWidth());
			    Rectangle r=new Rectangle(pp,h,w);
				r.setOuterColor(mainFrame.getBtnOuterColor().getBackground());
				r.setInnerColor(mainFrame.getBtnInnerColor().getBackground());

			    cmdAdd=new CmdAddRectangle(r,model);
			    execute(cmdAdd);
			  
			   
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(),"Fill in all the fields or check data type you have entered!" , "Error", JOptionPane.WARNING_MESSAGE);
				
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Height and width must be positive!", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(mainFrame.getTglbtnCircle()) {
			Point center=new Point(e.getX(),e.getY());
			DlgCircle dlgC=new DlgCircle();
			dlgC.getBtnExteriorColor().setVisible(false);
			dlgC.getBtnInteriorColor().setVisible(false);
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
				c.setOuterColor(mainFrame.getBtnOuterColor().getBackground());
				c.setInnerColor(mainFrame.getBtnInnerColor().getBackground());
				 cmdAdd=new CmdAddCircle(c,model);
				 execute(cmdAdd);
			
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
				
				
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive!", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(mainFrame.getTglbtnDonut()) {
			Point center=new Point(e.getX(),e.getY());
			DlgDonut dlgD =new DlgDonut();
			dlgD.getBtnExteriorColor().setVisible(false);
			dlgD.getBtnInteriorColor().setVisible(false);
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
				d.setOuterColor(mainFrame.getBtnOuterColor().getBackground());
				d.setInnerColor(mainFrame.getBtnInnerColor().getBackground());
				cmdAdd=new CmdAddDonut(d,model);
				execute(cmdAdd);
				   
			}
			}
			
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive and inner radius and the inner radius must be smaller than the outer radius!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			
		}
		 else if (mainFrame.getTglbtnHexagon()) {		
			Point center=new Point(e.getX(),e.getY());
			DlgHexagon dlgH=new DlgHexagon();
			dlgH.getBtnInteriorColor().setVisible(false);
			dlgH.getBtnExteriorColor().setVisible(false);
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
				HexagonAdapter h=new HexagonAdapter(center.getX(),center.getY(),radius,mainFrame.getBtnOuterColor().getBackground(),mainFrame.getBtnInnerColor().getBackground());			
				cmdAdd=new CmdAddHexagon(h,model);
				execute(cmdAdd);
		
		}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
				
				
			} catch (Exception e1) {
			
				JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive!", "Error", JOptionPane.WARNING_MESSAGE);
			}

		
		}
		
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
				          cmdModify=new CmdModifyPoint((Point)modifyShape,(Point)newState);
				          
				          mainFrame.repaint();          
				}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
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
				          cmdModify=new CmdModifyLine((Line)modifyShape,(Line)newState);
				         
				          mainFrame.repaint();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
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
			          cmdModify=new CmdModifyRectangle((Rectangle)modifyShape,(Rectangle)newState);

				          mainFrame.repaint();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Height and width must be positive!", "Error", JOptionPane.WARNING_MESSAGE);
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
				
				
				          cmdModify=new CmdModifyDonut((Donut)modifyShape,(Donut)newState);

				          mainFrame.repaint();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception ex)
				{
					
					JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive and inner radius and the inner radius must be smaller than the outer radius!!", "Error", JOptionPane.WARNING_MESSAGE);
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
				          cmdModify=new CmdModifyCircle((Circle)modifyShape,(Circle)newState);

				          mainFrame.repaint();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive!", "Error", JOptionPane.WARNING_MESSAGE);
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
					  
				          cmdModify=new CmdModifyHexagon((HexagonAdapter)modifyShape,(HexagonAdapter)newState);
				          
				          mainFrame.repaint();
				}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive!", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
			  execute(cmdModify);
			
	 
	

		
	}

	public void mouseClickedDelete(MouseEvent e) {	
		if (model.getSelectedShapes().size() == 0)
			return;
		ArrayList<Shape> shapesForDelete=new ArrayList<Shape>();
		for(Shape s: model.getSelectedShapes()) {
			shapesForDelete.add(s);
			
		}
			Command cmdRmv = null;
			
		int answer=JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete shapes?", "Delete", JOptionPane.YES_NO_OPTION);
		if( answer==JOptionPane.YES_OPTION)
		{
			for (Shape deleteShape : shapesForDelete) {
				

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
			model.setSelection(deleteShape, false);
					
			}
			
			mainFrame.repaint();
			
			
		
            

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
		    DrawingModel.getInstanceLazy().log("Redo:","");

		    command.execute();

		    mainFrame.repaint();
			
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	
		if(evt.getPropertyName().equals("selectedShapes"))
		{
			mainFrame.getBtnModify().setEnabled(model.getSelectedShapes().size()==1);				
			mainFrame.getBtnDelete().setEnabled(model.getSelectedShapes().size()>0);
			mainFrame.getBtnBringToBack().setEnabled(model.getSelectedShapes().size()==1);	
			mainFrame.getBtnBringToFront().setEnabled(model.getSelectedShapes().size()==1);				
			mainFrame.getBtnToback().setEnabled(model.getSelectedShapes().size()==1);				
			mainFrame.getBtnTofront().setEnabled(model.getSelectedShapes().size()==1);				

		}
		if(evt.getPropertyName().equals("undoRedo")) {
			
			mainFrame.getBtnUndo().setEnabled(model.getUndoRedoPointer()>-1);
			mainFrame.getBtnRedo().setEnabled(model.getUndoRedoPointer()<model.getCommandStack().size()-1);
		}
		
			
			
		
	
	}

	public void bringToFront() {
		if (model.getSelectedShapes().size() != 1)
			return;
		Shape s=model.getSelectedShape();
	

		System.out.println(model.getSelectedShapeIndex());
		CmdBringToFront cmdBToF=new CmdBringToFront(model,s);
		execute(cmdBToF);
		System.out.println(model.getSelectedShapeIndex());
		
	}

	public void bringToBack() {
		if (model.getSelectedShapes().size() != 1)
			return;
		Shape s=model.getSelectedShape();
		

		System.out.println(model.getSelectedShapeIndex());
		CmdBringToBack cmdBToB=new CmdBringToBack(model,s);
		execute(cmdBToB);
		System.out.println(model.getSelectedShapeIndex());
		
	}

	public void toBack() {
		if (model.getSelectedShapes().size() != 1)
			return;
		Shape s=model.getSelectedShape();
		
		System.out.println(model.getSelectedShapeIndex());
		CmdToBack cmdToB=new CmdToBack(model,s);
		execute(cmdToB);
		System.out.println(model.getSelectedShapeIndex());		
	}

	public void toFront() {
		if (model.getSelectedShapes().size() != 1)
			return;
		Shape s=model.getSelectedShape();
		
		CmdToFront cmdToF=new CmdToFront(model,s);
		execute(cmdToF);
		System.out.println(model.getSelectedShapeIndex());
		
	}

	public void exportToLog() {
		
		ArrayList<Object> helpList=new ArrayList<Object>();
		helpList.add(DrawingModel.getInstanceLazy().getLogs());
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
			
			try {
				lineNumberReader=new LineNumberReader(new FileReader(path));
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.log("Imported log file from:", path);
			mainFrame.getBtnExecuteLog().setEnabled(true);
				
		}
		
	}

	public void exportToDraw() {
		if(model.getShapes().isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "The drawing is empty!", "Error", JOptionPane.WARNING_MESSAGE);

		}
		else
		{
		ArrayList<Object> objectFromDraw=new ArrayList<Object>();
		objectFromDraw.add(model.getShapes());
		ExportManager exportManager=new ExportManager(new SaveToDrawFile());
		String path=FileHelp.showFileDialogSave("drwg");
		if(path!=null) {
			exportManager.export(objectFromDraw, path);
		}
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
			
			String line=lineNumberReader.readLine();
			CommandManager commandManager=new CommandManager();
			Command command=commandManager.parse(line,model);
			
             if(line.contains("Redo:Execute")) {
				
				redo();
			}
             else if(line.contains("Execute"))
			{	
			   execute(command);
			System.out.println(model.getSelectedShapes());
			System.out.println(model.getSelectedShape());
			System.out.println(model.getShapes());
			
			}
			else if(line.contains("Unexecute"))
			{
			    undo();
			}
			
			else
			{
				DrawingModel.getInstanceLazy().log("Info:",line);
			}
			mainFrame.repaint();
			if(lineNumberReader.getLineNumber()+1>model.getLogs().size()-1)
			{
				
				mainFrame.getBtnExecuteLog().setEnabled(false);
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
			
			
	
		
		
	}

	public void addOuterColor() {
		Color colorForButton=JColorChooser.showDialog(null, "Odaberite boju!", mainFrame.getBtnOuterColor().getBackground());
		if(colorForButton!=null) {
		mainFrame.getBtnOuterColor().setBackground(colorForButton);
		}
		else
		{
			mainFrame.getBtnOuterColor().setBackground(mainFrame.getBtnOuterColor().getBackground());
		}
	
	}
	public void addInnerColor() {
		Color colorForButton=JColorChooser.showDialog(null, "Odaberite boju!", mainFrame.getBtnInnerColor().getBackground());
		if(colorForButton!=null) {
			mainFrame.getBtnInnerColor().setBackground(colorForButton);
			}
			else
			{
				mainFrame.getBtnInnerColor().setBackground(mainFrame.getBtnInnerColor().getBackground());
			}
	}

	public void clickSelect(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.DESELECTED) {
		
			model.removeAllSelection();
			mainFrame.repaint();
			
		}
	}

	
}
	
	
	
	
		
	
		
	

