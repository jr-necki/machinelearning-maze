import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainPanel extends JFrame {
	private Panel p;
	private MazePanel mp;
	public MainPanel() {
		this.mp=new MazePanel();
		//this.p=new Panel();
		this.setLocation(100,0);
		this.setSize(1200,800);
		this.getContentPane().setBackground(Color.white);
		this.add(mp);
		
			
	}

}
