import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Vertex extends Ellipse2D.Double {
	
	private static final long serialVersionUID = 1L;
	
	private int xCoo;
    private int yCoo;
    private int diameter;
    private int count = 0;
    private Color color = Color.RED;
    
    public Vertex() {
    }
    
    public Vertex(int x, int y, int count) {
    	this.count = count;
    	xCoo = x;
    	yCoo = y;
    	diameter = 10;
    }

    public int getXCoo() {
        return xCoo;
    }

    public void setXCoo(int x) {
        xCoo = x;
    }

    public int getYCoo() {
        return yCoo;
    }

    public void setYCoo(int y) {

        yCoo = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int d) {
        diameter = d;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
    }

    public void drawVertex(Graphics g) {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g; 
        this.setFrame((xCoo - diameter / 2), (yCoo - diameter / 2), diameter,diameter);
        g2.fill(this);
        g2.draw(this);
    }
    
    @Override
	public boolean equals(Object ob) {
		if(this == ob) return true;
		if(ob == null || ob.getClass() != getClass()) return false;
		Vertex v = (Vertex) ob;
		if (xCoo != v.xCoo || yCoo != v.yCoo || diameter != v.diameter || count != v.count) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		int cValue = 1997*count;
		int result = 1;
		result *= 31 + cValue;
		return result;
	}
	
}