package day15;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
		Map<Point, Node> unvisited = new LinkedHashMap<Point, Node>();
		Node end = null;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int baseCost = Character.getNumericValue(input[y].charAt(x));
				for (int r = 0; r < 5; r++) {
					for (int c = 0; c < 5; c++) {
						int newX = x + width * c;
						int newY = y + height * r;
						int newCost = newCost(baseCost, r, c);
						Node node = new Node(newCost, Integer.MAX_VALUE);
						if(newX == width * 5 - 1 && newY == height * 5 - 1) {
							end = node;
						}
						unvisited.put(new Point(newX, newY), node);
					}
				}
			}
		}
		unvisited.replace(new Point(0, 0), new Node(0, 0));
		return costToEnd(unvisited, end);
	}

	static int newCost(int baseCost, int r, int c) {
		int newCost = (baseCost + r + c) % 9;
		if (newCost == 0) {
			newCost = 9;
		}
		return newCost;
	}

	/*
	 * static int part1(String[] input) { int height = input.length; int width =
	 * input[0].length(); Set<Position> unvisited = new HashSet<Position>();
	 * Position start = new Position(0, 0, 0, 0); unvisited.add(start); Position end
	 * = new Position(width - 1, height - 1, 0, 0); for (int y = 0; y < height; y++)
	 * { for (int x = 0; x < width; x++) { int cost =
	 * Character.getNumericValue(input[y].charAt(x)); unvisited.add(new Position(x,
	 * y, cost, Integer.MAX_VALUE)); } } return costToEnd(unvisited, end); }
	 */

	static int costToEnd(Map<Point, Node> map, Node end) {
		Map<Node, Collection<Node>> neighbors = new HashMap<Node, Collection<Node>>();
		Set<Node> unvisited = new HashSet<Node>();
		for (Map.Entry<Point, Node> entry : map.entrySet()) {
			unvisited.add(entry.getValue());
			int x = entry.getKey().x;
			int y = entry.getKey().y;
			List<Node> neighs = new ArrayList<Node>();
			neighs.add(map.get(new Point(x+1, y)));
			neighs.add(map.get(new Point(x, y+1)));
			neighs.add(map.get(new Point(x-1, y)));
			neighs.add(map.get(new Point(x, y-1)));
			List<Node> realNeighs = new ArrayList<Node>();
			for(Node node : neighs) {
				if(node != null) {
					realNeighs.add(node);
				}
			}
			neighbors.put(entry.getValue(), realNeighs);
		}
		while (!unvisited.isEmpty()) {
			Node current = cheapest(unvisited);
			if (current.equals(end)) {
				return current.totalCost();
			}
			for (Node pos : neighbors.get(current)) {
				int newCost = current.totalCost() + pos.cost();
				if (newCost < pos.totalCost()) {
					pos.setTotalCost(newCost);
				}
			}
			unvisited.remove(current);
		}
		return -1;
	}

	static Node cheapest(Iterable<Node> unvisited) {
		int lowestCost = Integer.MAX_VALUE;
		Node ret = null;
		for (Node node : unvisited) {
			if (node.totalCost() < lowestCost) {
				lowestCost = node.totalCost();
				ret = node;
			}
		}
		return ret;
	}
}