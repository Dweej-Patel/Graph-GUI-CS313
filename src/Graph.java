import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class Graph {
	
	private Map<Vertex, HashSet<Vertex>> graph;
	private HashSet<Edge> edge;
	private HashMap<Integer, HashSet<Vertex>> connected;
	private HashSet<Vertex> apVertex;
	private int count = 1;
	
	public Graph() {
		graph = new HashMap<Vertex, HashSet<Vertex>>();
		edge = new HashSet<Edge>();
	}
	
	private void initConnected() {
		connected = new HashMap<Integer, HashSet<Vertex>>();
	}
	
	private void addNewConnected(Integer x) {
		HashSet<Vertex> set = new HashSet<Vertex>();
		connected.put(x, set);
	}
	
	public void addVertex(MouseEvent e) {
		Vertex vert = new Vertex(e.getX(),e.getY(), count++);
		HashSet<Vertex> set = new HashSet<Vertex>();
		graph.put(vert, set);
	}
	
	public void removeVertex(Vertex rem) {
		if(graph.containsKey(rem)){
			Iterator<Edge> itr = edge.iterator();
			HashSet<Edge> tempEdgeRM = new HashSet<Edge>();
			while(itr.hasNext()) {
				Edge ed = itr.next();
				if(ed.getVertex1().equals(rem) || ed.getVertex2().equals(rem))
					tempEdgeRM.add(ed);
			}
			for(Edge cont : tempEdgeRM) edge.remove(cont);
			for(Vertex v : graph.get(rem)) graph.get(v).remove(rem);
			graph.remove(rem);
		}
	}
	
	
	public Vertex vertexContained(MouseEvent e) {
		Vertex temp = new Vertex();
		Iterator<Map.Entry<Vertex, HashSet<Vertex>>> itr = graph.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry<Vertex, HashSet<Vertex>> entry = itr.next();
			if(entry.getKey().contains(e.getX(), e.getY())) {
				temp = entry.getKey();
				return temp;
			}
		}
		return temp;
	}
	
	public void moveVertex(Vertex vert, int x, int y) {
		vert.setXCoo(x);
		vert.setYCoo(y);
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		if(v1 != v2 && !(containsEdge(new Edge(v1, v2)))) {
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
			Edge edge = new Edge(v1,v2);
			this.edge.add(edge);
		}
	}
	
	public boolean checkVertex(Vertex v) {
		return graph.containsKey(v);
	}
	
	public boolean checkEdge(Edge e) {
		return edge.contains(e);
	}
	
	public void removeEdge(Edge e) {
		if(edge.contains(e)) {
			graph.get(e.getVertex1()).remove(e.getVertex2());
			graph.get(e.getVertex2()).remove(e.getVertex1());
			edge.remove(e);
		}
	}
	
	public Edge edgeContained(MouseEvent e) {
		Edge edB = new Edge();
		Iterator<Edge> itr = edge.iterator();
		while(itr.hasNext()) {
			Edge ed = itr.next();
			if(ed.contains(e.getX(), e.getY())) return ed;
		}
		return edB;
	}
	
	public boolean containsEdge(Edge e) {
		Iterator<Edge> itr = edge.iterator();
		while(itr.hasNext()) {
			Edge ed = itr.next();
			if(ed.equals(e)) return true;
		}
		return false;
	}
	
	public void addAllEdges() {
		int count1 = 0, count2 = 0;
		Iterator<Map.Entry<Vertex, HashSet<Vertex>>> itr = graph.entrySet().iterator();
		while(itr.hasNext()) {
			count1++;
			Map.Entry<Vertex, HashSet<Vertex>> entry1 = itr.next();
			Iterator<Map.Entry<Vertex, HashSet<Vertex>>> itr2 = graph.entrySet().iterator();
			while(itr2.hasNext()) {
				count2++;
				Map.Entry<Vertex, HashSet<Vertex>> entry2 = itr2.next();
				if(count1 < count2) addEdge(entry1.getKey(), entry2.getKey());
			}
		}
	}
	
	public void printEdge(Graphics g) {
		Iterator<Edge> itr = edge.iterator();
		while(itr.hasNext()) {
			itr.next().drawEdge(g);
		}
	}
	
	public void printVertex(Graphics g) {
		Iterator<Map.Entry<Vertex, HashSet<Vertex>>> itr = graph.entrySet().iterator();
		while(itr.hasNext()) {
			itr.next().getKey().drawVertex(g);
		}
	}
	
	public Color randColor() {
		Random rand = new Random();
		float r = rand.nextFloat(), g = rand.nextFloat(), b = rand.nextFloat();
		//Color[] color = {new Color(22,23,44)};
		return new Color(r,g,b);
	}
	
	private void connectedDFSUtil(Vertex v, HashMap<Vertex, Boolean> visited, HashMap<Vertex, HashSet<Vertex>> tempGraph, Color color, Integer count) {
		visited.put(v , true);
		v.setColor(color);
		connected.get(count).add(v);
		Iterator<Vertex> itr = tempGraph.get(v).iterator();
		while(itr.hasNext()) {
			Vertex vert = itr.next();
			if(!visited.get(vert)) {
				connectedDFSUtil(vert, visited, tempGraph, color, count);
			}
		}
	}
	
	private HashMap<Vertex, HashSet<Vertex>> DFS() {
		HashMap<Vertex, HashSet<Vertex>> tempGraph = new HashMap<Vertex, HashSet<Vertex>>(graph);
		HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		initConnected();
		int counter = 0;
		Iterator<HashMap.Entry<Vertex, HashSet<Vertex>>> itr = tempGraph.entrySet().iterator();
		while(itr.hasNext()) {
			Vertex v = itr.next().getKey();
			visited.put(v, false);
		}
		Iterator<HashMap.Entry<Vertex, Boolean>> itr2 = visited.entrySet().iterator();
		while(itr2.hasNext()) {
			Entry<Vertex, Boolean> entry = itr2.next();
			Boolean bo = entry.getValue();
			Vertex vert = entry.getKey();
			if(bo == false){
				Color color = randColor();
				addNewConnected(counter);
				connectedDFSUtil(vert, visited, tempGraph, color, counter);
				counter++;
			}
		}
		return tempGraph;
	}
	
	public void printDFS(Graphics g) {
		HashMap<Vertex, HashSet<Vertex>> tempGraph = DFS();
		int contained = connected.size();
		for(int i = 0; i < contained; i++) {
			for(Vertex v1 : connected.get(i)) {
				v1.drawVertex(g);
				Color color = v1.getColor();
				for(Vertex v2 : tempGraph.get(v1)) {
					Edge edge = new Edge(v1, v2);
					edge.setColor(color);
					edge.drawEdge(g);
				}
			}
		}
	}
	
	private void APUtil(Vertex vert, HashMap<Vertex, Boolean> visited, HashMap<Vertex, Integer> disc,
								HashMap<Vertex, Integer> low, HashMap<Vertex, Vertex> parent,
								HashMap<Vertex, Boolean> ap, int time) {
		int children = 0;
		visited.put(vert, true);
		disc.put(vert, ++time);
		low.put(vert, time);
		Iterator<Vertex> itr = graph.get(vert).iterator();
		while(itr.hasNext()) {
			Vertex v = itr.next();
			if (!visited.get(v)) {
				children++;
				parent.put(v, vert);
				APUtil(v, visited, disc, low, parent, ap, time);
				low.put(vert, Math.min(low.get(vert), low.get(v)));
				if(parent.get(vert) == null && children > 1) ap.put(vert, true);
				if(parent.get(vert) != null && low.get(v) >= disc.get(vert)) ap.put(vert, true); 
			}
			else if(!v.equals(parent.get(vert))) low.put(vert, Math.min(low.get(vert), disc.get(v)));
		}
	}
	
	private void AP() {
		HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		HashMap<Vertex, Integer> disc = new HashMap<Vertex, Integer>();
		HashMap<Vertex, Integer> low = new HashMap<Vertex, Integer>();
		HashMap<Vertex, Vertex> parent = new HashMap<Vertex, Vertex>();
		HashMap<Vertex, Boolean> ap = new HashMap<Vertex, Boolean>();
		apVertex = new HashSet<Vertex>();
		int time = 0;
		Iterator<HashMap.Entry<Vertex, HashSet<Vertex>>> itr = graph.entrySet().iterator();
		while(itr.hasNext()) {
			Vertex vert = itr.next().getKey();
			parent.put(vert, null);
			ap.put(vert, false);
			visited.put(vert, false);
		}
		Iterator<HashMap.Entry<Vertex, Boolean>> itr2 = visited.entrySet().iterator();
		while(itr2.hasNext()) {
			Entry<Vertex, Boolean> entry = itr2.next();
			Boolean bo = entry.getValue();
			Vertex vert = entry.getKey();
			if(bo == false) {
				APUtil(vert, visited, disc, low, parent, ap, time);
			}
		}
		Iterator<HashMap.Entry<Vertex, Boolean>> itr3 = ap.entrySet().iterator();
		while(itr3.hasNext()) {
			Entry<Vertex, Boolean> entry = itr3.next();
			if(entry.getValue() == true) apVertex.add(entry.getKey());
		}
	}
	
	public void printAP(Graphics g) {
		AP();
		for(Vertex v : apVertex) {
			Vertex tempV = new Vertex(v.getXCoo(), v.getYCoo(), 0);
			Color c = v.getColor();
			tempV.setColor(new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue()));
			tempV.setDiameter(v.getDiameter()*3);
			tempV.drawVertex(g);
		}
	}
	
	public void reVertex() {
		Iterator<HashMap.Entry<Vertex, HashSet<Vertex>>> itr = graph.entrySet().iterator();
		while(itr.hasNext()) {
			Vertex vert = itr.next().getKey();
			vert.setColor(Color.RED);
		}
	}
	
}

