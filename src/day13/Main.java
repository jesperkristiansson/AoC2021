package day13;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Point;
import helpers.Helpers;

public class Main{
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day13/input.txt");
		Set<Point> points = new HashSet<Point>();
		List<String> folds = new ArrayList<String>();
		for (String s : input) {
			if (!s.isEmpty()) {
				if (Character.isDigit(s.charAt(0))) {
					String[] coords = s.split(",");
					int x = Integer.parseInt(coords[0]);
					int y = Integer.parseInt(coords[1]);
					points.add(new Point(x, y));
				} else {
					String[] relevant = s.split(" ")[2].split("=");
					String axis = relevant[0];
					String coord = relevant[1];
					folds.add(axis + coord);
				}
			}
		}
		Set<Point> foldedPoints = points;
		for(String fold : folds) {
			foldedPoints = fold(foldedPoints, fold);
		}
		createWindow(foldedPoints);
		System.out.println(foldedPoints.size());
	}
	
	private static void createWindow(Set<Point> points) {
		JFrame frame = new JFrame("Drawing");
		Canvas canvas = new drawPoints(points);
		canvas.setSize(100, 100);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}

	private static Set<Point> fold(Set<Point> points, String fold) {
		Set<Point> foldedPoints = new HashSet<Point>();
		char axis = fold.charAt(0);
		int coord = Integer.parseInt(fold.substring(1));
		for (Point p : points) {
			int newX = p.x;
			int newY = p.y;
			if (axis == 'x') {
				if (p.x > coord) {
					newX = coord - (p.x - coord);
				}
			} else {
				if(p.y > coord) {
					newY = coord - (p.y - coord);
				}
			}
			foldedPoints.add(new Point(newX, newY));
		}
		return foldedPoints;
	}
}
