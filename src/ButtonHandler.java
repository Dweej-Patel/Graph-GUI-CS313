import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ButtonHandler implements ActionListener {
	
	JFrame jf;
	
	public ButtonHandler(JFrame jf) {
		this.jf = jf;
	}
	
	public void actionPerformed(ActionEvent e) {
		String title = e.getActionCommand();
		if(title.equals("Add All Edges")) {
			GUI.graphPanel.getGraph().addAllEdges();
			GUI.graphPanel.repaint();
		}
		if(title.equals("Connected Components")) {
			GUI.graphPanel.updateVertex();
			GUI.graphPanel.getGraph().printDFS(GUI.graphPanel.getGraphics());
			GUI.graphPanel.getGraph().reVertex();
		}
		if(title.equals("Show Cut Vertices")) {
			GUI.graphPanel.getGraph().printAP(GUI.graphPanel.getGraphics());
		}
		if(title.equals("Help")) {
			HelpGUI help = new HelpGUI("Help");
			help.initialize();
		}
	}
}
