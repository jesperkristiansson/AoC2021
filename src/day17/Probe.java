package day17;

import java.awt.Point;

public class Probe {
	Point pos = new Point(0,0);
	int dx;
	int dy;
	
	public Probe(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public Point step() {
		pos = new Point(pos.x + dx, pos.y + dy);
		if(dx > 0) {
			dx--;
		} else if(dx < 0) {
			dx++;
		}
		dy--;
		return pos;
	}
}
