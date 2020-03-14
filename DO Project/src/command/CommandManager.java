package command;
import java.awt.Color;
import java.util.HashMap;

import mvc.DrawingModel;
import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.HexagonAdapter;

public class CommandManager {

	public Command parse(String command, DrawingModel model) {
   
		String type=parseCommand(command);
		
		if(type.contains("Add")) {
			
			return createAddCommand(buildShape(command),model);
		}
		else if(type.contains("Remove")) {
			
			return createRemoveCommand(buildShape(command),model);
		}
		
		else if(type.contains("Bring To Front")) {
			
			
			return new CmdBringToFront(model,buildShape(command));
			
		}
		else if(type.contains("Bring To Back")) {
			
			
			return new CmdBringToBack(model,buildShape(command));
			
		}
		else if(type.contains("To Back")) {
        	
        	return new CmdToBack(model,buildShape(command));
			
		}
		else if(type.contains("To Front")) {
			
        	return new CmdToFront(model,buildShape(command));
		}
        else if(type.contains("Modify")) {
            String s= command.split("->")[0];		
    		String s1=command.split("->")[1]; 		
    		Shape old=buildShape(s);
    		int i=model.getIndexOfShape(old);
    		System.out.println(i);
    		Shape oldShape=model.get(i);
			return createModifyCommand(oldShape,s1);
		}
		return null;
	}
		
	private Command createModifyCommand(Shape oldShape, String s1) {
		Shape newShape;
		
		if(oldShape instanceof Point) {
    		
    		newShape=buildPoint(s1);
    		return new CmdModifyPoint((Point)oldShape,(Point)newShape);
    	}
    	if(oldShape instanceof Line) {
    	
    		newShape=buildLine(s1);
    		return new CmdModifyLine((Line)oldShape,(Line)newShape);
    	}
    	if(oldShape instanceof Rectangle) {
    	
    		newShape=buildRectangle(s1);

    		return new CmdModifyRectangle((Rectangle)oldShape,(Rectangle)newShape);
    	}
        if(oldShape instanceof Donut) {
    		
    		newShape=buildDonut(s1);

    		return new CmdModifyDonut((Donut)oldShape,(Donut)newShape);
    	}
    	if(oldShape instanceof Circle) {
    		
    		newShape=buildCircle(s1);

    		return new CmdModifyCircle((Circle)oldShape,(Circle)newShape);
    	}
    	if(oldShape instanceof HexagonAdapter) {
    		
    		newShape=buildHexagon(s1);
    		return new CmdModifyHexagon((HexagonAdapter)oldShape,(HexagonAdapter)newShape);
    	}
		return null;
	}

	private Command createRemoveCommand(Shape shape, DrawingModel model) {

		if(shape instanceof Point) {
			
			return new CmdRemovePoint((Point) shape,model);
						
		}
        if(shape instanceof Line) {
			
			
			return new CmdRemoveLine((Line) shape,model);
							
		}	
        if(shape instanceof Rectangle) {
			
		
			return new CmdRemoveRectangle((Rectangle) shape,model);		
		}
        if(shape instanceof Donut) {
    		
			return new CmdRemoveDonut((Donut) shape,model);
							
		}
        if(shape instanceof Circle) {
			
			
			return new CmdRemoveCircle((Circle) shape,model);
						
		}
        
         if(shape instanceof HexagonAdapter) {
				
				
				return new CmdRemoveHexagon((HexagonAdapter) shape,model);
								
			}
         return null;
				
	}



	private Command createAddCommand(Shape shape, DrawingModel model) {
		
		if(shape instanceof Point) {
			
			return new CmdAddPoint((Point) shape,model);
						
		}
        if(shape instanceof Line) {
			
			
			return new CmdAddLine((Line) shape,model);
							
		}	
        if(shape instanceof Rectangle) {
			
		
			return new CmdAddRectangle((Rectangle) shape,model);		
		}
        if(shape instanceof Donut) {
    		
			return new CmdAddDonut((Donut) shape,model);
							
		}
        if(shape instanceof Circle) {
			
			
			return new CmdAddCircle((Circle) shape,model);
						
		}
        
         if(shape instanceof HexagonAdapter) {
				
				
				return new CmdAddHexagon((HexagonAdapter) shape,model);
								
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
