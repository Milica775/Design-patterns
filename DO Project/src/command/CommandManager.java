package command;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import drawing.Circle;
import drawing.Donut;
import drawing.DrawingModel;
import drawing.FrmDrawing;
import drawing.Line;
import drawing.Point;
import drawing.Rectangle;
import drawing.Shape;
import hexagon.HexagonAdapter;

public class CommandManager {

	public Command parse(String command, DrawingModel model,FrmDrawing frame) {
		
		String type=parseCommand(command);
		
		if(!type.contains("Modify"))
		{
		Shape shape=buildShape(command);
	
		int index=model.getIndexOfShape(shape);
	
		
		
		
		
		if(type.contains("Add")) {
			
			
		
			
			if(type.contains("Point")) {
				
				return new CmdAddPoint((Point) shape,model);
							
			}
            if(type.contains("Line")) {
				
				
				return new CmdAddLine((Line) shape,model);
								
			}	
            if(type.contains("Rectangle")) {
				
			
				return new CmdAddRectangle((Rectangle) shape,model);		
			}
            if(type.contains("Circle")) {
				
				
				return new CmdAddCircle((Circle) shape,model);
							
			}
             if(type.contains("Donut")) {
			
				return new CmdAddDonut((Donut) shape,model);
								
			}
             if(type.contains("Hexagon")) {
 				
 				
 				return new CmdAddHexagon((HexagonAdapter) shape,model);
 								
 			}
		}
		if(type.contains("Selection")) {
			
			
			//samo sa ovim radi ali nema smisla
			//model.add(shape);
			return new CmdSelection(shape,model,frame);
			
		}
		if(type.contains("Bring To Front")) {
			
			
			return new CmdBringToFront(model,shape,index);
			
		}
		if(type.contains("Bring To Back")) {
			
			
			return new CmdBringToBack(model,shape,index);
			
		}
        if(type.contains("To Back")) {
        	
        	return new CmdToBack(model,shape,index);
			
		}
        if(type.contains("To Front")) {
			
        	return new CmdToFront(model,shape,index);
		}
        
		if(type.contains("Remove")) {
		
			
			if(type.contains("Point")) {
				
				
				
				return new CmdRemovePoint((Point) shape,model);
							
			}
            if(type.contains("Line")) {
				
				
				return new CmdRemoveLine((Line) shape,model);
								
			}	
            if(type.contains("Rectangle")) {
				
				
				return new CmdRemoveRectangle((Rectangle) shape,model);		
			}
            if(type.contains("Circle")) {
				
			
				return new CmdRemoveCircle((Circle) shape,model);
							
			}
             if(type.contains("Donut")) {
				
			
				return new CmdRemoveDonut((Donut) shape,model);
								
			}
             if(type.contains("Hexagon")) {
 				
     			
 				return new CmdRemoveHexagon((HexagonAdapter) shape,model);
 								
 			}
            
		}
		}
		if(type.contains("Modify")) {
			String s= command.split("->")[0];
    		String s1=command.split("->")[1];
    		
    		Shape oldShape=buildShape(s);
    		Shape newShape;
    		
        	if(type.contains("Point")) {
        		newShape=buildPoint(s1);
        		
        		//model.add(newShape);
        		return new CmdModifyPoint((Point)oldShape,(Point)newShape,model);
        	}
        	if(type.contains("Line")) {
        		newShape=buildLine(s1);
        	
        		return new CmdModifyLine((Line)oldShape,(Line)newShape,model);
        	}
        	if(type.contains("Rectangle")) {
        		newShape=buildRectangle(s1);
        	
        		return new CmdModifyRectangle((Rectangle)oldShape,(Rectangle)newShape,model);
        	}
        	if(type.contains("Circle")) {
        		newShape=buildCircle(s1);
        		
        		return new CmdModifyCircle((Circle)oldShape,(Circle)newShape,model);
        	}
        	if(type.contains("Donut")) {
        		newShape=buildDonut(s1);
        		
        		return new CmdModifyDonut((Donut)oldShape,(Donut)newShape,model);
        	}
        	if(type.contains("Hexagon")) {
        		newShape=buildHexagon(s1);
        	
        		return new CmdModifyHexagon((HexagonAdapter)oldShape,(HexagonAdapter)newShape,model);
        	}
        }
		return null;
	}
	
	





	private String parseCommand(String command) {
		return command.split("\\(")[0];
		
	}







	private Shape buildShape(String command) {
		
		Shape shape = null;
		String type=parseCommand(command);
		if(type.contains("Point")) {
			
			shape=buildPoint(command);
			
						
		}
        if(type.contains("Line")) {
			
        	shape=buildLine(command);
			
							
		}	
        if(type.contains("Rectangle")) {
			
        	shape=buildRectangle(command);
					
		}
        if(type.contains("Circle")) {
			
        	shape=buildCircle(command);
		
						
		}
         if(type.contains("Donut")) {
			
        	 shape=buildDonut(command);
			
							
		}
         if(type.contains("Hexagon")) {
				
        	 shape=buildHexagon(command);
				
								
			}
         return shape;
	}







	public Point buildPoint(String command){
		HashMap<String,String> parsePoint=parseShape(command);
		int x=Integer.parseInt(parsePoint.get("x"));
		int y=Integer.parseInt(parsePoint.get("y"));
		Color col=new Color(Integer.parseInt(parsePoint.get("Color")));
		return new Point(x,y,col);
		
		
	}
	private Line buildLine(String command) {
		HashMap<String,String> parseLine=parseShape(command);
		int startPointX=Integer.parseInt(parseLine.get("StartPointX"));
		int startPointY=Integer.parseInt(parseLine.get("StartPointY"));
		int endPointX=Integer.parseInt(parseLine.get("EndPointX"));
		int endPointY=Integer.parseInt(parseLine.get("EndPointY"));
		Color col=new Color(Integer.parseInt(parseLine.get("Color")));
		return new Line(new Point(startPointX,startPointY),new Point(endPointX,endPointY),col);
		
	}
	
	private Rectangle buildRectangle(String command) {
		HashMap<String,String> parseRectangle=parseShape(command);
		int upperLeftPointX=Integer.parseInt(parseRectangle.get("UpperLeftPointX"));
		int upperLeftPointY=Integer.parseInt(parseRectangle.get("UpperLeftPointY"));
		int height=Integer.parseInt(parseRectangle.get("Height"));
		int width=Integer.parseInt(parseRectangle.get("Width"));
		Color outerCol=new Color(Integer.parseInt(parseRectangle.get("OuterColor")));
		Color innerCol=new Color(Integer.parseInt(parseRectangle.get("InnerColor")));
		try {
			return new Rectangle(new Point(upperLeftPointX,upperLeftPointY),height,width,innerCol,outerCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private Circle buildCircle(String command) {
		HashMap<String,String> parseCircle=parseShape(command);
		int centerX=Integer.parseInt(parseCircle.get("CenterX"));
		int centerY=Integer.parseInt(parseCircle.get("CenterY"));
		int radius=Integer.parseInt(parseCircle.get("Radius"));
		Color outerCol=new Color(Integer.parseInt(parseCircle.get("OuterColor")));
		Color innerCol=new Color(Integer.parseInt(parseCircle.get("InnerColor")));
		try {
			return new Circle(new Point(centerX,centerY),radius,innerCol,outerCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private Donut buildDonut(String command) {
		HashMap<String,String> parseDonut=parseShape(command);
		int centerX=Integer.parseInt(parseDonut.get("CenterX"));
		int centerY=Integer.parseInt(parseDonut.get("CenterY"));
		int radius=Integer.parseInt(parseDonut.get("Radius"));
		int innerRadius=Integer.parseInt(parseDonut.get("InnerRadius"));
		Color outerCol=new Color(Integer.parseInt(parseDonut.get("OuterColor")));
		Color innerCol=new Color(Integer.parseInt(parseDonut.get("InnerColor")));
		try {
			return new Donut(new Point(centerX,centerY),radius,innerRadius,innerCol,outerCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private HexagonAdapter buildHexagon(String command) {
		HashMap<String,String> parseHex=parseShape(command);
		int X=Integer.parseInt(parseHex.get("x"));
		int Y=Integer.parseInt(parseHex.get("y"));
		int radius=Integer.parseInt(parseHex.get("radius"));
		Color outerCol=new Color(Integer.parseInt(parseHex.get("OuterColor")));	
		Color innerCol=new Color(Integer.parseInt(parseHex.get("InnerColor")));	
		try {
			return new HexagonAdapter(X,Y,radius,innerCol,outerCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public HashMap<String,String> parseShape(String command){
		HashMap<String,String> helpMap=new HashMap<String,String>();
		String cast=command.split("\\(")[1];
		
		
		cast=cast.substring(0, cast.length()-1);
		
			
		for(String s: cast.split(",")) {
		
		    String [] ss=s.split("=");	
		
			helpMap.put(ss[0],ss[1]);
		
		}
		return helpMap;
	
		
	}
	

}
