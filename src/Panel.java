import java.awt.GridLayout;

import javax.swing.JPanel;

public class Panel extends JPanel {
	ImagePanel ip;
	DataPanel dp;
	public Panel() {
		this.dp=new DataPanel();
		//this.ip=new ImagePanel();
		GridLayout g=new GridLayout(2,1);
		this.setLayout(g);
		this.add(dp);
		this.add(ip);
	}

}
