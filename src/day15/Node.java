package day15;

import java.util.Collection;
import java.util.LinkedList;

public class Node implements Comparable<Node>{
	private int cost;
	private int totalCost;
	private Collection<Node> neighbors = new LinkedList<Node>();
	
	public Node(int cost, int totalCost){
		this.cost = cost;
		this.totalCost = totalCost;
	}
	
	public void addNeighbor(Node other) {
		neighbors.add(other);
	}
	
	public Collection<Node> neighbors(){
		return neighbors;
	}
	
	public int cost() {
		return cost;
	}
	
	public int totalCost() {
		return totalCost;
	}
	
	public void setTotalCost(int newCost) {
		totalCost = newCost;
	}
	
	@Override
	public String toString() {
		return String.format("cost: %d totalCost: %d", cost, totalCost);
	}

	@Override
	public int compareTo(Node o) {
		return totalCost - o.totalCost;
	}
}
