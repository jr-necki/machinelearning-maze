import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements MouseListener {
	JButton btn = new JButton("경로 찾기");
	JButton btn2 = new JButton("거인 발견");
	JButton btn3=new JButton("그만 하기");
	MazePanel mazePanel;
	
	boolean ok=false;
	int i=0; int j=0;
	int limit=0;
	int giant=0;
	
	Thread t;
	Clip clip;
	public ImagePanel(MazePanel mazePanel) {
		this.mazePanel = mazePanel;
		btn.setBackground(Color.gray);
		btn.addMouseListener(this);
		this.add(btn);
		btn2.setBackground(Color.gray);
		btn2.addMouseListener(this);
		this.add(btn2);
		btn3.setBackground(Color.gray);
		btn3.addMouseListener(this);
		this.add(btn3);
	

	}

	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		ImageIcon image = new ImageIcon("img/elbin2.jpg");
		g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.limit=mazePanel.SIZE;
		if (e.getSource().equals(btn2)) {
			while(!ok) {
				if(limit>giant) {
					this.i=(int) (Math.random()*mazePanel.SIZE-1);
					this.j=(int) (Math.random()*mazePanel.SIZE-1);
					if(mazePanel.grid[i][j].getBackground().equals(Color.white)) {
						mazePanel.grid[i][j].setBackground(Color.black);
						giant++;
						ok=true;
					}
				}else {
					JOptionPane.showMessageDialog(null, "더 이상 거인을 찾을 수 없다..!");
					ok=true;
				}			
			}
			ok=false;

		} else if (e.getSource().equals(btn)) {
			this.t = new Thread(mazePanel);
			this.t.start();
			File bgm;
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;

			bgm = new File("music/music2.wav"); 

			

			try {
				stream = AudioSystem.getAudioInputStream(bgm);
				format = stream.getFormat();
				info = new DataLine.Info(Clip.class, format);
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(stream);
				clip.start();

			} catch (Exception e1) {
				System.out.println("err : " + e);
			}

		}else if(e.getSource().equals(btn3)) {
			this.clip.stop();
			mazePanel.removeAll();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(btn)) {
			this.btn.setBackground(Color.red);
		} else if (e.getSource().equals(btn2)) {
			this.btn2.setBackground(Color.red);
		}else if(e.getSource().equals(btn3)) {
			this.btn3.setBackground(Color.red);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(btn)) {
			this.btn.setBackground(Color.gray);
		} else if (e.getSource().equals(btn2)) {
			this.btn2.setBackground(Color.gray);
		}else if(e.getSource().equals(btn3)) {
			this.btn3.setBackground(Color.gray);
		}

	}

}
