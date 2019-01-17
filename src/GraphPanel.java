import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {

	private static final long serialVersionUID = 3L;
	
	private Graph graph = new Graph();
    private Vertex tempVert, vert1 = new Vertex(), vert2;
    private Edge tempEdge;
    private int countEdgeV = 0;
    private Color color;

    public GraphPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));
        
        addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
//        		repaint();
//        		if(RadioButtonHandler.getRadioSelected() == 0) {
//        			graph.addVertex(e);
//        			repaint();
//        		}
//        		if(RadioButtonHandler.getRadioSelected() == 1) {
//        			if(countEdgeV == 0) {
//        				tempVert = graph.vertexContained(e);
//        				if(graph.checkVertex(tempVert)) {
//        					vert1 = tempVert;
//        					color = vert1.getColor();
//        					vert1.setColor(new Color(255-color.getRed(), 255-color.getGreen(),255-color.getBlue()));
//        					countEdgeV++;
//        					repaint();
//        				}
//        			}
//        			if(countEdgeV == 1) {
//        				tempVert = graph.vertexContained(e);
//        				if(graph.checkVertex(tempVert) && tempVert != vert1) {
//        					vert2 = tempVert;
//        					graph.addEdge(vert1, vert2);
//        					vert1.setColor(color);
//        					countEdgeV = 0;
//        					repaint();
//        				}
//        			}
//        		}
//
//        		if(RadioButtonHandler.getRadioSelected() == 3) {
//        			tempEdge = graph.edgeContained(e);
//        			graph.removeEdge(tempEdge);
//        			repaint();
//        		}
        	}
        });
        addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e) {
        		
        		repaint();
        		if(RadioButtonHandler.getRadioSelected() == 0) {
        			graph.addVertex(e);
        			repaint();
        		}
        		if(RadioButtonHandler.getRadioSelected() == 1) {
        			if(countEdgeV == 0) {
        				tempVert = graph.vertexContained(e);
        				if(graph.checkVertex(tempVert)) {
        					vert1 = tempVert;
        					color = vert1.getColor();
        					vert1.setColor(new Color(255-color.getRed(), 255-color.getGreen(),255-color.getBlue()));
        					countEdgeV++;
        					repaint();
        				}
        			}
        			if(countEdgeV == 1) {
        				tempVert = graph.vertexContained(e);
        				if(graph.checkVertex(tempVert) && tempVert != vert1) {
        					vert2 = tempVert;
        					graph.addEdge(vert1, vert2);
        					vert1.setColor(color);
        					countEdgeV = 0;
        					repaint();
        				}
        			}
        		}

        		if(RadioButtonHandler.getRadioSelected() == 3) {
        			tempEdge = graph.edgeContained(e);
        			graph.removeEdge(tempEdge);
        			repaint();
        		}
        		
        		////////////////////////////////////////////////
        		
        		tempVert = graph.vertexContained(e);
        		if(RadioButtonHandler.getRadioSelected() == 4) {
        			tempVert = graph.vertexContained(e);
        		}
        		if(RadioButtonHandler.getRadioSelected() == 2) {
        			graph.removeVertex(tempVert);
        			repaint();
        		}
        	}
        });
        addMouseMotionListener(new MouseAdapter() {
        	public void mouseDragged(MouseEvent e) {
        		if(RadioButtonHandler.getRadioSelected() == 4) {
        			if(graph.checkVertex(tempVert)) {
        				graph.moveVertex(tempVert, e.getX(), e.getY());
        				repaint();
        			}
        		}
        	}
        });
    }
    
    public void updateVertex() {
    	vert1 = new Vertex();
    }
    
    public void changeOrig() {
    	vert1.setColor(color);
    	countEdgeV = 0;
    	repaint();
    }
    
    public Graph getGraph() {
    	return graph;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(1022,800);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        graph.printEdge(g);
        graph.printVertex(g);
        
    }
    
}
