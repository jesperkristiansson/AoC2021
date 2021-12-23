package day15;

import java.awt.Point;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day15/example.txt");
		int height = input.length;
		int width = input[0].length();
		int[][] risk = new int[height][width];
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c ++) {
				risk[r][c] = Character.getNumericValue(input[r].charAt(c));
			}
		}
		Point start = new Point(0,0);
		Point end = new Point(width-1, height-1);
		int shortestPath = path(risk, new HashSet<Point>(), start, end);
		System.out.print(shortestPath);
	}
	
	private static int path(int[][] cave, Set<Point> path, Point current, Point end) {
		try {
			int i = cave[current.x][current.y];
		} catch(Exception e) {
			return 100000;
		}
		if(current.equals(end)) {
			return cave[current.x][current.y];
		} else if(!path.contains(current)){
			Set<Point> newPath = new HashSet<Point>(path);
			newPath.add(current);
			return min(path(cave, newPath, new Point(current.x+1, current.y), end),
					path(cave, newPath, new Point(current.x-1, current.y), end),
					path(cave, newPath, new Point(current.x, current.y+1), end),
					path(cave, newPath, new Point(current.x, current.y-1), end));
		} else {
			return 100000;
		}
	}
	
	private static int min(int i1, int i2, int i3, int i4) {
		return Math.min(Math.min(i1, i2), Math.min(i3, i4));
	}
}
