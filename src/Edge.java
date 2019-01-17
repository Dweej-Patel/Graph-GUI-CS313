import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Edge extends Line2D.Double {
	
	private static final long serialVersionUID = 2L;
	
	private Vertex vertex1;
	private Vertex vertex2;
	private Color color = Color.BLUE;
	
	public Edge(Vertex vert1, Vertex vert2) {
		vertex1 = vert1;
		vertex2 = vert2;
	}
	
	public Edge() {
		vertex1 = new Vertex();
		vertex2 = new Vertex();	
	}
	
	@Override
	public boolean contains(double x, double y) {
		return (this.ptLineDist(x, y) < 3);
	}

	public Vertex getVertex1() {
		return vertex1;
	}
	
	public Vertex getVertex2() {
		return vertex2;
	}
	
	public void setVertex1(Vertex v) {
		vertex1 = v;
	}
	
	public void setVertex2(Vertex v) {
		vertex2 = v;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void drawEdge(Graphics g) {
		g.setColor(color);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		this.setLine(vertex1.getXCoo(), vertex1.getYCoo(), vertex2.getXCoo(), vertex2.getYCoo());
		g2.draw(this);
	}
	
	@Override
	public boolean equals(Object ob) {
		if(this == ob) return true;
		if(ob == null || ob.getClass() != getClass()) return false;
		Edge v = (Edge) ob;
		if ((vertex1.equals(v.vertex1) && vertex2.equals(v.vertex2)) || (vertex1.equals(v.vertex2) && vertex2.equals(v.vertex1))) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int hc = vertex1.hashCode() + vertex2.hashCode();
		int result = 1;
		result *= 31 + hc;
		return result;
	}	
	
}

