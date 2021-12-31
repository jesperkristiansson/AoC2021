package day23;

import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import helpers.Helpers;

public class Main {

	public static void main(String[] dargs) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day23/input.txt");
		long time = System.currentTimeMillis();
		PriorityQueue<BurrowNode> unvisited = new PriorityQueue<BurrowNode>();
		unvisited.add(new BurrowNode(new Burrow(input), 0));
		Set<BurrowNode> visited = new HashSet<BurrowNode>();
		while (!unvisited.isEmpty()) {
			BurrowNode currentNode = unvisited.poll();
			if (currentNode.finished()) {
				System.out.println(currentNode.cost());
				System.out.println("Time: " + (System.currentTimeMillis() - time));
				break;
			}
			for (BurrowNode node : currentNode.neighbors()) {
				if (!visited.contains(node)) {
					if (unvisited.contains(node)) {
						BurrowNode oldNode = get(unvisited, node);
						if (currentNode.cost() + node.cost() < oldNode.cost()) {
							oldNode.updateCost(currentNode.cost() + node.cost());
						}
					} else {
						node.updateCost(currentNode.cost() + node.cost());
						unvisited.add(node);
					}
				}
			}
			visited.add(currentNode);
		}
	}

	static BurrowNode get(PriorityQueue<BurrowNode> queue, BurrowNode item) {
		for (BurrowNode node : queue) {
			if (node.equals(item)) {
				return node;
			}
		}
		return null;
	}
}