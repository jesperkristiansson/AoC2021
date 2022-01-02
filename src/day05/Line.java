package day05;

import java.awt.Point;
import java.util.ArrayList;

public class Line {
	private Point start;
	private Point end;
	private ArrayList<Point> points = new ArrayList<>();

	public Line(Point p0, Point p1) {
		points.addAll(pointsBet(p0, p1));
		start = p0;
		end = p1;
	}

	private ArrayList<Point> pointsBet(Point p0, Point p1) {
		ArrayList<Point> ret = new ArrayList<>();
		int x0 = p0.x;
		int x1 = p1.x;
		int y0 = p0.y;
		int y1 = p1.y;
		if (y0 == y1) {
			for (int i = Math.min(x0, x1); i <= Math.max(x0, x1); i++) {
				ret.add(new Point(i,y0));
			}
		} else if (x0 == x1) {
			for (int i = Math.min(y0, y1); i <= Math.max(y0, y1); i++) {
				ret.add(new Point(x0, i));
			}
		} else {
			int stepX = 1;
			int stepY = 1;
			if(x1 < x0) {
				stepX = -1;
			}
			if(y1 < y0) {
				stepY = -1;
			}
			for (int i = 0; i <= Math.abs(x1-x0); i++) {
				int x = x0+stepX*i;
				int y = y0+stepY*i;
				ret.add(new Point(x, y));
			}
		}
		return ret;
	}
	
	public boolean empty() {
		return points.isEmpty();
	}
	
	public ArrayList<Point> points(){
		return points;
	}
	
	public String toString() {
		return start + " " + end;
	}

}
