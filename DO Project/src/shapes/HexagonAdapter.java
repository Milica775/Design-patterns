package shapes;
import java.awt.Color;
import java.awt.Graphics;
import hexagon.Hexagon;
import shapes.AreaShape;
import shapes.Point;
import shapes.Shape;

public class HexagonAdapter extends AreaShape{
	
	private Hexagon hexagon;

	
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(int xx,int yy,int rr) throws Exception{
		hexagon=new Hexagon(xx,yy,rr);
	    }
	
	public HexagonAdapter(int xx,int yy,int rr,Color areaColor, Color borderColor) throws Exception {
    	this(xx,yy,rr);
    	setInterColor(areaColor);
    	setOuterColor(borderColor);
  
    	
    }
	@Override
	public boolean contains(Point p) {
		return this.hexagon.doesContain(p.getX(), p.getY());
	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
		
	}
	
	public String toString() {
		return  "(" + "x=" + hexagon.getX() + "," + "y=" + hexagon.getY()
		+ "," + "radius=" + hexagon.getR() + "," 
				+ "OuterColor="+Integer.toString(hexagon.getBorderColor().getRGB()) + "," + "InnerColor="+Integer.toString(hexagon.getAreaColor().getRGB()) + ")";
	}
	public Shape clone() {			
		HexagonAdapter hex= null;
		try {
			hex = new HexagonAdapter(this.getX(), this.getY(), this.getRadius(),this.getInterColor(),this.getOuterColor());
			hex.setSelected(this.isSelected());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return hex;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	public boolean equals(Object obj){
	if(obj instanceof HexagonAdapter){
		HexagonAdapter h=(HexagonAdapter) obj;
		if(this.getX()==h.getX() && this.getY()==h.getY() && this.getRadius()==h.getRadius())
			return true;
		else
			return false;
	}
	else 
		return false;
	}

	public Color getInterColor() {
		return hexagon.getAreaColor();
	}
	public Color getOuterColor() {
		return hexagon.getBorderColor();
	}
	
	public void setInterColor(Color interColor) {
		this.hexagon.setAreaColor(interColor);
	}
	public void setOuterColor(Color outerColor) {
		this.hexagon.setBorderColor(outerColor);
	}
    public void setSelected(boolean selected)
    {
    	this.hexagon.setSelected(selected);
    	super.setSelected(selected);
	}
    
    public boolean isSelected(boolean selected)
    {
    	return this.hexagon.isSelected();
    }
    
    public int getX() {
        return this.hexagon.getX();
    }

    public int getY() {
        return this.hexagon.getY();
    }

    public int getRadius() {
        return this.hexagon.getR();
    }
    public void setCenterX(int x) {
       this.hexagon.setX(x);
       
    }
    public void setCenterY(int y) {
        this.hexagon.setY(y);
     }
    public void setRadius(int r) {
        this.hexagon.setR(r);
     }

	@Override
	public void moveOn(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
