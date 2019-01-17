import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = -7394373021780357533L;
	
	public static GraphPanel graphPanel;
	
	public GUI(String title) {
		graphPanel = new GraphPanel();
		setTitle(title);
		setSize(1200,800);
		
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException| 
				IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
		GUI frame = new GUI("CS313 Graph");
		frame.initialize();
	}
	
	public void initialize() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ButtonGroup radioGroup = new ButtonGroup();
		RadioButtonHandler rh = new RadioButtonHandler(this);
		JRadioButton addVertex = new JRadioButton("Add Vertex");
		addVertex.setSelected(true);
		addVertex.addActionListener(rh);
		JRadioButton addEdge = new JRadioButton("Add Edge");
		addEdge.addActionListener(rh);
		JRadioButton remVertex = new JRadioButton("Remove Vertex");
		remVertex.addActionListener(rh);
		JRadioButton remEdge = new JRadioButton("Remove Edge");
		remEdge.addActionListener(rh);
		JRadioButton moveVertex = new JRadioButton("Move Vertex");
		moveVertex.addActionListener(rh);
		
		radioGroup.add(addVertex);
		radioGroup.add(addEdge);
		radioGroup.add(remVertex);
		radioGroup.add(remEdge);
		radioGroup.add(moveVertex);
		
		ButtonHandler bh = new ButtonHandler(this);
		JButton allEdge = new JButton("Add All Edges");
		allEdge.addActionListener(bh);
		JButton connectedComp = new JButton("Connected Components");
		connectedComp.addActionListener(bh);
		JButton showCut = new JButton("Show Cut Vertices");
		showCut.addActionListener(bh);
		JButton help = new JButton("Help");
		help.addActionListener(bh);
		
		JPanel westPanel = new JPanel(new GridLayout(0,1));
		westPanel.add(addVertex);
		westPanel.add(addEdge);
		westPanel.add(remVertex);
		westPanel.add(remEdge);
		westPanel.add(moveVertex);
		westPanel.add(allEdge);
		westPanel.add(connectedComp);
		westPanel.add(showCut);
		westPanel.add(help);
		
		add(westPanel, BorderLayout.WEST);
		add(graphPanel, BorderLayout.EAST);
		
		setVisible(true);
		
	}

}

