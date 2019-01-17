import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class HelpGUI extends JFrame {
	
	private static final long serialVersionUID = 3907160952518337528L;

	public HelpGUI(String title) {
		setTitle(title);
		setSize(500,500);
		setResizable(false);
		JTextArea text = new JTextArea(helpString());
		text.setEditable(false);
		Font f = new Font("Helvetica",Font.PLAIN,18);
		text.setFont(f);
		this.add(text);
	}
	
	public void initialize() {
		setVisible(true);
	}
	
	public String helpString() {
		String text;
		text = "This is the help section. \n\n"
				+ "ADD VERTEX: Click anywhere on the outlined area to add a \n"
				+ "vertex. \n"
				+ "ADD EDGE: First click a vertex you want your edge to start, \n"
				+ "then click on the next vertex you want your edge to end. \n"
				+ "REMOVE VERTEX: Click on the vertex you want removed. \n"
				+ "REMOVE EDGE: Click on the edge you want removed. \n"
				+ "MOVE VERTEX: Press and drag the vertex you want to move. \n\n"
				+ "ADD ALL EDGE: Adds all edges to every vertex. \n"
				+ "CONNECTED COMPONENTS: Color codes all connected \n"
				+ "graphs. \n"
				+ "SHOW CUT VERTICES: Shows vertices that can be removed \n "
				+ "to disconnect the graph.";
		return text;
	}
}
