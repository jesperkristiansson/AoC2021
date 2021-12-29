package day25;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import helpers.Helpers;

public class Main {

	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day25/input.txt");
		char[][] cucumbers = new char[input.length][input[0].length()];
		for (int y = 0; y < input.length; y++) {
			String row = input[y];
			for (int x = 0; x < row.length(); x++) {
				cucumbers[y][x] = row.charAt(x);
			}
		}
		int steps = 0;
		boolean move = true;
		while (move) {
			move = false;
			if(moveEast(cucumbers)) {
				move = true;
			}
			if(moveSouth(cucumbers)) {
				move = true;
			}
		//	drawCucumbers(cucumbers);
			steps++;
		}
		System.out.println(steps);
	}
	
	static void drawCucumbers(char[][] cucumbers) {
		System.out.println("-----------");
		for (int y = 0; y < cucumbers.length; y++) {
			for (int x = 0; x < cucumbers[0].length; x++) {
				System.out.print(cucumbers[y][x]);
			}
			System.out.println();
		}
	}

	static boolean moveEast(char[][] cucumbers) {
		boolean changedState = false;
		Map<Point, Point> moves = new HashMap<Point, Point>();
		for (int y = 0; y < cucumbers.length; y++) {
			for (int x = 0; x < cucumbers[0].length; x++) {
				if (cucumbers[y][x] == '>') {
					int nextX = x + 1 < cucumbers[0].length ? x + 1 : 0;
					if (cucumbers[y][nextX] == '.') {
						moves.put(new Point(x,y), new Point(nextX,y));
					}
				}
			}
		}
		for(Map.Entry<Point, Point> entry : moves.entrySet()) {
			cucumbers[entry.getValue().y][entry.getValue().x] = '>';
			cucumbers[entry.getKey().y][entry.getKey().x] = '.';
			changedState = true;
		}
		return changedState;
	}

	static boolean moveSouth(char[][] cucumbers) {
		boolean changedState = false;
		Map<Point, Point> moves = new HashMap<Point, Point>();
		for (int y = 0; y < cucumbers.length; y++) {
			for (int x = 0; x < cucumbers[0].length; x++) {
				if (cucumbers[y][x] == 'v') {
					int nextY = y + 1 < cucumbers.length ? y + 1 : 0;
					if (cucumbers[nextY][x] == '.') {
						moves.put(new Point(x,y), new Point(x,nextY));
					}
				}
			}
		}
		for(Map.Entry<Point, Point> entry : moves.entrySet()) {
			cucumbers[entry.getValue().y][entry.getValue().x] = 'v';
			cucumbers[entry.getKey().y][entry.getKey().x] = '.';
			changedState = true;
		}
		return changedState;
	}
}