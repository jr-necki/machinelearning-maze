import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class MazePanel extends JPanel implements Runnable {
	Reinforcement rf = new Reinforcement();
	ImagePanel ip = new ImagePanel(this);

	boolean goal = false;
	boolean complete = false;
	boolean finish = false;
	boolean isSame = false;

	int SIZE = 15;
	int r = 0;
	int c = 0;
	JLabel[][] grid;
	ArrayList<Integer> newPoint = new ArrayList<Integer>();
	JTextArea ta = new JTextArea();
	ArrayList<Integer> pointList = new ArrayList<Integer>();

	ImageIcon maria = new ImageIcon("img/wallmaria.jpg");
	ImageIcon rose = new ImageIcon("img/wallrose.jpg");
	String point = "";
	int moving = 0;

	Route route;
	ArrayList<Route> rlist = new ArrayList<Route>();
	private Route shortestRoute;
	Route minmum=new Route();
	

	public MazePanel() {// 세팅
		this.setLayout(new GridLayout(1, 2));
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();

		panel.setLayout(new GridLayout(SIZE, SIZE));
		this.grid = new JLabel[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				grid[i][j] = new JLabel();
				if (i == 0 && j == 0) {
					grid[i][j].setIcon(maria);
					grid[i][j].setBackground(Color.yellow);

				} else if (i == SIZE - 1 && j == SIZE - 1) {
					grid[i][j].setIcon(rose);
					grid[i][j].setBackground(Color.yellow);
				} else {
					grid[i][j].setBackground(Color.white);
				}

				LineBorder lb = new LineBorder(Color.black, 5);
				grid[i][j].setBorder(lb);

				grid[i][j].setOpaque(true);
				panel.add(grid[i][j]);
			}
		}
		panel2.setLayout(new GridLayout(2, 1));
		panel2.setBackground(Color.black);
		ta.setBackground(Color.black);
		ta.setForeground(Color.green);
		panel2.add(ta);
		panel2.add(ip);
		this.add(panel);
		this.add(panel2);
		
		minmum.setMoving(10000);

		rf.setSize(SIZE);
	}

	@Override
	public void run() {
		while (!finish) {// 같은 경로가 나올때 까지
			pointList.clear();
			while (!goal) {
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				newPoint = rf.move(grid, r, c);// 새포인트 받음
				rf.giveList(pointList);

				if (pointList.size() >= 4) {
					if (newPoint.get(0) == pointList.get(pointList.size() - 4)
							&& newPoint.get(1) == pointList.get(pointList.size() - 3)) {// 전전포
						grid[r][c].setBackground(Color.gray);
					}
				}
				if (newPoint.get(0) != r || newPoint.get(1) != c) {
					grid[newPoint.get(0)][newPoint.get(1)].setBackground(Color.green);
					moving++;
					point += "(" + r + ", " + c + ") >>>";
					ta.setText(point + "                                [움직인 횟수: " + moving + "]");

					ta.setLineWrap(true);

					pointList.add(newPoint.get(0));
					pointList.add(newPoint.get(1));
				}
				r = newPoint.get(0);
				c = newPoint.get(1);
				if (r == SIZE - 1 && c == SIZE - 1) {
					goal = true;
					grid[r][c].setBackground(Color.yellow);
				}

			}
			route = new Route();
			route.setMoving(moving);
			route.setPath(pointList);
			this.rlist.add(route);
				
			if (rlist.size() > SIZE * 2) {
				finish = isfinished(route);
		
			}
			reset();
		}
		
		// 최소경로 그리기...!
		shortestRoute=findShortestPath();
		for(int i=0;i<shortestRoute.path.size();i+=2) {
			r=shortestRoute.path.get(i);
			c=shortestRoute.path.get(i+1);
			grid[r][c].setBackground(Color.blue);
			point += "(" + r + ", " + c + ") >>>";
		}
		ta.setText("최적 코스 :::"+point + " [움직인 횟수: " +shortestRoute.moving + "]");
		ta.setForeground(Color.blue);
		ta.setLineWrap(true);
		
	}

	private Route findShortestPath() {
		for(Route r: rlist) {
			if(minmum.moving>r.moving) {
				minmum=r;
			}
		}
		return minmum;
	}

	private boolean isfinished(Route newRoute) {

		for (Route r : rlist) {
			if (r.path.size() == newRoute.getPath().size()) {
				for (int i = 0; i < r.path.size(); i++) {
					if (r.path.get(i) == newRoute.getPath().get(i)) {
						isSame = true;
					} else {
						isSame = false;
					}
				}
			}
			finish = isSame;
		}
		return finish;

	}

	private void reset() {

		this.r = 0;
		this.c = 0;
		this.goal = false;
		this.point = "";
		this.moving = 0;
		//this.pointList.clear();
		this.rf.reward = 50;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (grid[i][j].getBackground().equals(Color.green) || grid[i][j].getBackground().equals(Color.gray)) {
					grid[i][j].setBackground(Color.white);
				}

			}
		}

	}
}