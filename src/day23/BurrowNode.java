package day23;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BurrowNode implements Comparable<BurrowNode> {
	private Burrow burrow;
	private int cost;

	public BurrowNode(Burrow burrow, int cost) {
		this.burrow = burrow;
		this.cost = cost;
	}

	public int cost() {
		return cost;
	}

	public void updateCost(int newCost) {
		cost = newCost;
	}

	public List<BurrowNode> neighbors() {
		List<BurrowNode> neighbors = new ArrayList<BurrowNode>();
		for (Map.Entry<Burrow, Integer> entry : burrow.neighbors().entrySet()) {
			BurrowNode newNode = new BurrowNode(entry.getKey(), entry.getValue());
			neighbors.add(newNode);
		}
		return neighbors;
	}

	public boolean finished() {
		return burrow.finished();
	}

	@Override
	public String toString() {
		return burrow.toString() + "\ncost: " + cost;
	}

	@Override
	public boolean equals(Object other) {
		return burrow.equals(((BurrowNode) other).burrow);
	}

	@Override
	public int compareTo(BurrowNode arg0) {
		return cost - arg0.cost;
	}
}