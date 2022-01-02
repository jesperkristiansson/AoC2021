package day15;

import java.util.ArrayList;
import java.util.List;

public class Position implements Comparable<Position>{
	private int x;
	private int y;
	private int cost;
	private int totalCost;
	
	public Position(int x, int y, int cost, int totalCost){
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.totalCost = totalCost;
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
	
	public List<Position> neighbors(Iterable<Position> allPositions){
		List<Position> neighbors =  new ArrayList<Position>();
		for(Position pos : allPositions) {
			if(isNeighbor(pos)) {
				neighbors.add(pos);
			}
		}
		return neighbors;
	}
	
	private boolean isNeighbor(Position otherPos) {
		boolean nextTo = Math.abs(x-otherPos.x) == 1 && y == otherPos.y;
		boolean aboveOrBelow = x == otherPos.x && Math.abs(y - otherPos.y) == 1;
		return nextTo || aboveOrBelow;
	}
	
	@Override
	public String toString() {
		return String.format("x: %d y: %d cost: %d totalCost: %d", x, y, cost, totalCost);
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Position) {
			Position otherPos = (Position) other;
			return x == otherPos.x && y == otherPos.y;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Position o) {
		return totalCost - o.totalCost;
	}
}
