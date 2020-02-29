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
import hexagon.HexagonAdapter;

public class CommandManager {

	public Command parse(String command, DrawingModel model) {
		
		String type=command.split("\\(")[0];
		System.out.println(type);
			
		
		if(type.contains("Add")) {
			if(type.contains("Point")) {
				
				Point p=buildPoint(command);
				return new CmdAddPoint(p,model);
							
			}
            if(type.contains("Line")) {
				
				Line l=buildLine(command);
				return new CmdAddLine(l,model);
								
			}	
            if(type.contains("Rectangle")) {
				
				Rectangle r=buildRectangle(command);
				return new CmdAddRectangle(r,model);		
			}
            if(type.contains("Circle")) {
				
				Circle c=buildCircle(command);
				return new CmdAddCircle(c,model);
							
			}
             if(type.contains("Donut")) {
				
				Donut d=buildDonut(command);
				return new CmdAddDonut(d,model);
								
			}
             if(type.contains("Hexagon")) {
 				
 				HexagonAdapter h=buildHexagon(command);
 				return new CmdAddHexagon(h,model);
 								
 			}
		}
		//ne radi
		if(type.contains("Remove")) {
			System.out.println("Remove");
			if(type.contains("Point")) {
				System.out.println("Point");
				
				Point p=buildPoint(command);
				return new CmdRemovePoint(p,model);
							
			}
            if(type.contains("Line")) {
				
				Line l=buildLine(command);
				return new CmdRemoveLine(l,model);
								
			}	
            if(type.contains("Rectangle")) {
				
				Rectangle r=buildRectangle(command);
				return new CmdRemoveRectangle(r,model);		
			}
            if(type.contains("Circle")) {
				
				Circle c=buildCircle(command);
				return new CmdRemoveCircle(c,model);
							
			}
             if(type.contains("Donut")) {
				
				Donut d=buildDonut(command);
				return new CmdRemoveDonut(d,model);
								
			}
		}
		return null;
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
		Color outerCol=new Color(Integer.parseInt(parseHex.get("BorderColor")));
		Color innerCol=new Color(Integer.parseInt(parseHex.get("AreaColor")));
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
			System.out.println(s);
		    String [] ss=s.split("=");	
		    System.out.println(ss);
			helpMap.put(ss[0],ss[1]);
		
		}
		return helpMap;
	
		
	}
	

}
