import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DataPanel extends JPanel {
	JTextArea ta=new JTextArea(50, 50);

	public DataPanel() {
		this.setBackground(Color.black);
		this.add(ta);
		ta.setBackground(Color.black);
		ta.setForeground(Color.green);
		ta.setText("start!!");
		
	}

}
