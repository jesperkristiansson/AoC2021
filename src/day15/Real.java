package day15;

import java.io.IOException;

import helpers.Helpers;

public class Real {

	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day15/input.txt");
		int height = input.length;
		int width = input[0].length();
		int fullHeight = height * 5;
		int fullWidth = width * 5;
		Graph graph = new Graph();

		Node[][] nodes = new Node[fullHeight][fullWidth];
		int[][] risk = new int[fullHeight][fullWidth];
		for (int r = 0; r < fullHeight; r++) {
			for (int c = 0; c < fullWidth; c++) {
				Node node = new Node("");
				if (r == 0 && c == 0) {
					node = new Node("start");
				} else if (r == fullHeight - 1 && c == fullWidth - 1) {
					node = new Node("end");
				}
				risk[r][c] = (Character.getNumericValue(input[r % height].charAt(c % width)) + r / height + c / width)%9;
				if(risk[r][c] == 0) {
					risk[r][c] = 9;
				}
				nodes[r][c] = node;
			}
		}

		/*
		 * Node[][] nodes = new Node[height][width]; int[][] risk = new
		 * int[height][width]; for (int r = 0; r < height; r++) { for (int c = 0; c <
		 * width; c++) { Node node = new Node(""); if (r == 0 && c == 0) { node = new
		 * Node("start"); } else if (r == height - 1 && c == width - 1) { node = new
		 * Node("end"); } risk[r][c] = Character.getNumericValue(input[r].charAt(c));
		 * nodes[r][c] = node; } }
		 */
		for (int r = 0; r < fullHeight; r++) {
			for (int c = 0; c < fullWidth; c++) {
				Node node = nodes[r][c];
				if (r == 0) {
					if (c == 0) {
						node.addDestination(nodes[r + 1][c], risk[r + 1][c]);
						node.addDestination(nodes[r][c + 1], risk[r][c + 1]);
					} else if (c == fullWidth - 1) {
						node.addDestination(nodes[r + 1][c], risk[r + 1][c]);
						node.addDestination(nodes[r][c - 1], risk[r][c - 1]);
					} else {
						node.addDestination(nodes[r + 1][c], risk[r + 1][c]);
						node.addDestination(nodes[r][c + 1], risk[r][c + 1]);
						node.addDestination(nodes[r][c - 1], risk[r][c - 1]);
					}
				} else if (r == fullHeight - 1) {
					if (c == 0) {
						node.addDestination(nodes[r - 1][c], risk[r - 1][c]);
						node.addDestination(nodes[r][c + 1], risk[r][c + 1]);
					} else if (c == fullWidth - 1) {
						node.addDestination(nodes[r - 1][c], risk[r - 1][c]);
						node.addDestination(nodes[r][c - 1], risk[r][c - 1]);
					} else {
						node.addDestination(nodes[r - 1][c], risk[r - 1][c]);
						node.addDestination(nodes[r][c + 1], risk[r][c + 1]);
						node.addDestination(nodes[r][c - 1], risk[r][c - 1]);
					}
				} else if (c == 0) {
					node.addDestination(nodes[r + 1][c], risk[r + 1][c]);
					node.addDestination(nodes[r - 1][c], risk[r - 1][c]);
					node.addDestination(nodes[r][c + 1], risk[r][c + 1]);
				} else if (c == fullWidth - 1) {
					node.addDestination(nodes[r + 1][c], risk[r + 1][c]);
					node.addDestination(nodes[r - 1][c], risk[r - 1][c]);
					node.addDestination(nodes[r][c - 1], risk[r][c - 1]);
				} else {
					node.addDestination(nodes[r + 1][c], risk[r + 1][c]);
					node.addDestination(nodes[r - 1][c], risk[r - 1][c]);
					node.addDestination(nodes[r][c + 1], risk[r][c + 1]);
					node.addDestination(nodes[r][c - 1], risk[r][c - 1]);
				}
				graph.addNode(node);
			}
		}
		graph = Dijkstra.calculateShortestPathFromSource(graph, nodes[0][0]);
		for (Node node : graph.nodes) {
			if (node.name.equals("end")) {
				System.out.println(node.getDistance());
			}
		}
	}

}
