package day13;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;

public class drawPoints extends Canvas{
	Set<Point> points;
	int pixelSize = 10;
	
	public drawPoints(Set<Point> points) {
		this.points = points;
	}
	
	@Override
	public void paint(Graphics g) {
		for(Point p : points) {
			g.fillRect(p.x*pixelSize+1, p.y*pixelSize+1, pixelSize, pixelSize);
		}
	}
}
