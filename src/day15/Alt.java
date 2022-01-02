package day15;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import helpers.Helpers;

public class Alt {

	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day15/input.txt");
		// int leastCost = part1(input);
		int leastCost = part2(input);
		System.out.println(leastCost);
	}

	static int part1(String[] input) {
		int height = input.length;
		int width = input[0].length();
		Node[][] nodes = new Node[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int cost = Character.getNumericValue(input[y].charAt(x));
				Node node = new Node(cost, Integer.MAX_VALUE);
				nodes[x][y] = node;
			}
		}
		Node start = new Node(0, 0);
		nodes[0][0] = start;
		Node end = nodes[width - 1][height - 1];
		addNeighbors(nodes);
		return costToEnd(start, end);
	}

	static int part2(String[] input) {
		int height = input.length;
		int width = input[0].length();
		int fullHeight = height * 5;
		int fullWidth = width * 5;
		Node[][] nodes = new Node[fullWidth][fullHeight];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int baseCost = Character.getNumericValue(input[y].charAt(x));
				for (int r = 0; r < 5; r++) {
					for (int c = 0; c < 5; c++) {
						int newX = x + width * c;
						int newY = y + height * r;
						int newCost = newCost(baseCost, r, c);
						Node node = new Node(newCost, Integer.MAX_VALUE);
						nodes[newX][newY] = node;
					}
				}
			}
		}
		Node start = new Node(0, 0);
		nodes[0][0] = start;
		Node end = nodes[fullWidth - 1][fullHeight - 1];
		addNeighbors(nodes);
		return costToEnd(start, end);
	}

	static int newCost(int baseCost, int r, int c) {
		int newCost = (baseCost + r + c) % 9;
		if (newCost == 0) {
			newCost = 9;
		}
		return newCost;
	}

	static void addNeighbors(Node[][] nodes) {
		for (int y = 0; y < nodes.length; y++) {
			for (int x = 0; x < nodes[0].length; x++) {
				tryAddNeighbor(nodes, nodes[x][y], x, y + 1);
				tryAddNeighbor(nodes, nodes[x][y], x, y - 1);
				tryAddNeighbor(nodes, nodes[x][y], x + 1, y);
				tryAddNeighbor(nodes, nodes[x][y], x - 1, y);
			}
		}
	}

	static void tryAddNeighbor(Node[][] nodes, Node node, int x, int y) {
		try {
			node.addNeighbor(nodes[x][y]);
		} catch (Exception e) {
		}
	}

	static int costToEnd(Node start, Node end) { // Dijkstra's algorithm
		Set<Node> unvisited = new HashSet<Node>();
		Set<Node> visited = new HashSet<Node>();
		unvisited.add(start);
		while (!unvisited.isEmpty()) {
			Node current = cheapestNode(unvisited);
			if (current.equals(end)) {
				return current.totalCost();
			}
			for (Node pos : current.neighbors()) {
				if (!visited.contains(pos)) {
					unvisited.add(pos);
					int newCost = current.totalCost() + pos.cost();
					if (newCost < pos.totalCost()) {
						pos.setTotalCost(newCost);
					}
				}
			}
			unvisited.remove(current);
			visited.add(current);
		}
		return -1;
	}

	static Node cheapestNode(Collection<Node> unvisited) {
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