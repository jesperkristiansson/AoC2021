package day15;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import helpers.Helpers;

public class Alt {

	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day15/input.txt");
		System.out.println(part2(input));
	}

	static int part2(String[] input) {
		int height = input.length;
		int width = input[0].length();
		Set<Position> unvisited = new HashSet<Position>();
		Position start = new Position(0, 0, 0, 0);
		unvisited.add(start);
		Position end = new Position(width * 5 - 1, height * 5 - 1, 0, 0);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int baseCost = Character.getNumericValue(input[y].charAt(x));
				for (int r = 0; r < 5; r++) {
					for (int c = 0; c < 5; c++) {
						int newX = x + width*c;
						int newY = y + height*r;
						int newCost = newCost(baseCost, r, c);
						unvisited.add(new Position(newX, newY, newCost, Integer.MAX_VALUE));
					}
				}
			}
		}
		return costToEnd(unvisited, end);
	}
	
	static int newCost(int baseCost, int r, int c) {
		int newCost = (baseCost + r + c) % 9;
		if(newCost == 0) {
			newCost = 9;
		}
		return newCost;
	}

	static int part1(String[] input) {
		int height = input.length;
		int width = input[0].length();
		Set<Position> unvisited = new HashSet<Position>();
		Position start = new Position(0, 0, 0, 0);
		unvisited.add(start);
		Position end = new Position(width - 1, height - 1, 0, 0);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int cost = Character.getNumericValue(input[y].charAt(x));
				unvisited.add(new Position(x, y, cost, Integer.MAX_VALUE));
			}
		}
		return costToEnd(unvisited, end);
	}

	static int costToEnd(Set<Position> unvisited, Position end) {
		while (!unvisited.isEmpty()) {
			Position current = cheapest(unvisited);
			System.out.println(current);
			if (current.equals(end)) {
				return current.totalCost();
			}
			for (Position pos : current.neighbors(unvisited)) {
				int newCost = current.totalCost() + pos.cost();
				if (newCost < pos.totalCost()) {
					pos.setTotalCost(newCost);
				}
			}
			unvisited.remove(current);
		}
		return -1;
	}

	static Position cheapest(Iterable<Position> positions) {
		int lowestCost = Integer.MAX_VALUE;
		Position ret = null;
		for (Position pos : positions) {
			if (pos.totalCost() < lowestCost) {
				lowestCost = pos.totalCost();
				ret = pos;
			}
		}
		return ret;
	}
}