import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

class RadioButtonHandler implements ActionListener {
	
	JFrame jf;
	private static int selectedB = 0;
	
	public RadioButtonHandler(JFrame jf) {
		this.jf = jf;
	}
	
	public void actionPerformed(ActionEvent e) {
		String title = e.getActionCommand();
		if(title.equals("Add Vertex")) {
			selectedB = 0;
		}else if(title.equals("Add Edge")) {
			selectedB = 1;
		}else if(title.equals("Remove Vertex")) {
			selectedB = 2;
		}else if(title.equals("Remove Edge")) {
			selectedB = 3;
		}else if(title.equals("Move Vertex")) {
			selectedB = 4;
		}
		if(!title.equals("Add Edge")) {
			GUI.graphPanel.changeOrig();
		}
	}
	
	public static int getRadioSelected() {
		return selectedB;
	}
	
}
