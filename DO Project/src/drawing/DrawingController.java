package drawing;

import java.awt.Color;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hexagon.DlgHexagon;
import hexagon.Hexagon;
import hexagon.HexagonAdapter;

public class DrawingController {
	
	
	private DrawingModel model; 
	private FrmDrawing mainFrame;
	//javlja null exception kad ga stavim u model
	private Point startPoint; 

	public DrawingController(DrawingModel model2, FrmDrawing frame2) {
		model=model2;
		mainFrame=frame2;
	}

	public void mouseClicked(MouseEvent e) {
		
		if(mainFrame.getTglbtnSelection()) {
			//model.setSelectedShapes(null); //ponistava se trenutna selekcija
			Point p=new Point(e.getX(),e.getY()); //koordinate klika
			Iterator <Shape> it=model.getShapes().iterator();
			while(it.hasNext()) {
				Shape shape=it.next();
				//shape.setSelected(false);
				if((shape.contains(p)) && shape.isSelected()==false)
				{
					model.setSelectedShapes(shape);
					
				}
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
			    model.add(p);
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
				model.add(l);
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
			    model.add(r);
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
				model.add(c);
			
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
				d.setSecondOuterColor(dlgD.getExterCol());
				model.add(d);
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
			    h.setInterColor(dlgH.getInterCol());
				model.add(h);
		
		}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greška", JOptionPane.WARNING_MESSAGE);
				
				
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnik mora biti pozitivan!", "Greška", JOptionPane.WARNING_MESSAGE);
			}

		}
		else if(model.getSelectedShapes()!=null)
		{
			model.getSelectedShapes().setSelected(true);
		}
		
		//kad stavim getView prikazuje i dijaloge
		if(model.getShapes()!=null) 
			mainFrame.repaint();
			
}			



	public void mouseClickedModify(MouseEvent e) {
		int index=model.getShapes().indexOf(model.getSelectedShapes());
		Shape modifyShape=model.get(index);
		
		if(modifyShape!=null) {	
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
				          ((Point) modifyShape).setX(Integer.parseInt(dp.getTxtX()));
				          ((Point) modifyShape).setY(Integer.parseInt(dp.getTxtY()));
				          ((Point) modifyShape).setOuterColor(dp.getCol());
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
						((Line) modifyShape).setStartPoint(new Point((Integer.parseInt(dl.getTxtStartPointX())),(Integer.parseInt(dl.getTxtStartPointY()))));
						((Line) modifyShape).setEndPoint(new Point((Integer.parseInt(dl.getTxtEndPointX())),(Integer.parseInt(dl.getTxtEndPointY()))));
						((Line) modifyShape).setOuterColor(dl.getCol());
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
						((Rectangle)modifyShape).setUpperLeftPoint(new Point(Integer.parseInt(dr.getTxtUpperLeftPointX()),Integer.parseInt(dr.getTxtUpperLeftPointY())));
						((Rectangle)modifyShape).setHeight(Integer.parseInt(dr.getTxtHeight()));
						((Rectangle)modifyShape).setWidth(Integer.parseInt(dr.getTxtWidth()));
						((Rectangle)modifyShape).setOuterColor(dr.getExterCol());
						((Rectangle)modifyShape).setInnerColor(dr.getInterCol());
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

						((Circle)modifyShape).setCenter(new Point(Integer.parseInt(dc.getTxtCenterX()),Integer.parseInt(dc.getTxtCenterY())));
						((Circle)modifyShape).setRadius(Integer.parseInt(dc.getTxtRadius()));
						((Circle)modifyShape).setOuterColor(dc.getExterCol());
						((Circle)modifyShape).setInnerColor(dc.getInterCol());
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
				dd.setTxtInnerRadius(Integer.toString(((Donut)modifyShape).getInnerRadius()));
				dd.setTxtRadius(Integer.toString(((Donut)modifyShape).getRadius()));
				dd.setExterCol((((Donut)modifyShape).getOuterColor()));
				dd.setInterCol((((Donut)modifyShape).getInnerColor()));
				dd.setVisible(true);
				try
				{
					if(dd.isOk())
					{

						((Donut)modifyShape).setCenter(new Point(Integer.parseInt(dd.getTxtCenterX()),Integer.parseInt(dd.getTxtCenterY())));
						((Donut)modifyShape).setInnerRadius(Integer.parseInt(dd.getTxtInnerRadius()));
						((Donut)modifyShape).setRadius(Integer.parseInt(dd.getTxtRadius()));
						((Donut)modifyShape).setOuterColor(dd.getExterCol());
						((Donut)modifyShape).setSecondOuterColor(dd.getExterCol());
						((Donut)modifyShape).setInnerColor(dd.getInterCol());
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

			DlgHexagon dc=new DlgHexagon();
			dc.setTxtCenterX(Integer.toString((((HexagonAdapter)modifyShape).getX())));
			dc.setTxtCenterY(Integer.toString((((HexagonAdapter)modifyShape).getY())));
			dc.setTxtRadius(Integer.toString((((HexagonAdapter)modifyShape).getRadius())));
			dc.setInterCol((((HexagonAdapter)modifyShape).getInterColor()));
			dc.setExterCol((((HexagonAdapter)modifyShape).getOuterColor()));
			dc.setVisible(true);
			try
			{
				if(dc.isOk())
				{

					((HexagonAdapter)modifyShape).setCenterX(Integer.parseInt(dc.getTxtCenterX()));
					((HexagonAdapter)modifyShape).setCenterY(Integer.parseInt(dc.getTxtCenterY()));
					((HexagonAdapter)modifyShape).setRadius(Integer.parseInt(dc.getTxtRadius()));
					((HexagonAdapter)modifyShape).setOuterColor(dc.getExterCol());
					((HexagonAdapter)modifyShape).setInterColor(dc.getInterCol());
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
		}
		else
		{
			startPoint=null;
			//panel.setStartPoint(null);
			JOptionPane.showMessageDialog(new JFrame(), "Nijedan oblik nije selektovan.", "Greska!", JOptionPane.WARNING_MESSAGE);
		}
		
	}

	public void mouseClickedDelete(MouseEvent e) {
		
		
		
		if(model.getSelectedShapes()!=null)
		{
			int index1=model.getShapes().indexOf(model.getSelectedShapes());
			Shape deleteShape=model.get(index1);
		int answer=JOptionPane.showConfirmDialog(new JFrame(), "Da li ste sigurni da zelite da obrisete oblik?", "Brisanje oblika", JOptionPane.YES_NO_OPTION);
		if( answer==JOptionPane.YES_OPTION)
		{
			model.remove(deleteShape);
			model.setSelectedShapes(null);
			mainFrame.repaint();
		}
		}
		else
		{
		    startPoint=null;
			JOptionPane.showMessageDialog(new JFrame(), "Nijedan oblik nije selektovan!", "Greska", JOptionPane.ERROR_MESSAGE);
		}
		

		
  }

	
		
	
		
	
}
