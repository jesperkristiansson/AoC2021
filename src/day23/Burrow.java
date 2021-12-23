package day23;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Burrow {
	private static int debug = 0;
	private Stack<Amphipod>[] rooms = new Stack[4];
	private Amphipod[] hallway = new Amphipod[11];
	private List<Integer> roomCol = Arrays.asList(2, 4, 6, 8);
	private final int roomSize = 2;
	private Map<Integer, Character> roomChar = Map.of(0, 'A', 1, 'B', 2, 'C', 3, 'D');
	private Map<Character, Integer> amphipodCost = Map.of('A', 1, 'B', 10, 'C', 100, 'D', 1000);

	public Burrow(String[] map) {
		for (int i = 0; i < 4; i++) {
			rooms[i] = new Stack<Amphipod>();
		}
		for (int room = 0; room < 4; room++) {
			for (int i = 0; i < roomSize; i++) {
				String row = map[1 + roomSize - i];
				char c = row.charAt(roomCol.get(room) + 1);
				rooms[room].push(new Amphipod(c, amphipodCost.get(c)));
			}
		}
	}

	private boolean reachedEnd(Stack<Amphipod>[] rooms) {
		for (int room = 0; room < 4; room++) {
			if (rooms[room].size() != roomSize) {
				return false;
			}
			for (Amphipod a : rooms[room]) {
				if (a.type != roomChar.get(room)) {
					return false;
				}
			}
		}
		return true;
	}

	public long minPath() {
		return minSort(0, hallway, rooms);
	}

	private long minSort(long cost, Amphipod[] hallway, Stack<Amphipod>[] rooms) {
		System.out.println(debug++);
		if (reachedEnd(rooms)) {
			return cost;
		} else {
			Set<Long> costs = new HashSet<Long>();
			for (int i = 0; i < hallway.length; i++) {
				if (hallway[i] != null) {
					Amphipod[] newHallway = copyHallway(hallway, i, null);
					for (int roomNo = 0; roomNo < rooms.length; roomNo++) {
						if (hallway[i].canEnter(rooms[roomNo], roomChar.get(roomNo))) {
							int steps = hallway[i].move(Math.abs(i - (roomNo + 1) * 2) + 2 - rooms[roomNo].size());
							costs.add(minSort(steps, newHallway, copyRooms(rooms, roomNo, true, hallway[i])));
						}
					}
				}
			}
			for (int roomNo = 0; roomNo < 4; roomNo++) {
				if (!topShouldStay(rooms, roomNo)) {
					Stack<Amphipod> room = rooms[roomNo];
					if (!correctRoom(room, roomNo)) {
						if (room.size() > 0) {
							if (room.peek().moves == 2) {
								for (int i = 0; i < hallway.length; i++) {
									if (!roomCol.contains(i)) {
										if (possibleMove((roomNo + 1) * 2, i, hallway)) {
											int steps = room.peek()
													.move(Math.abs(i - (roomNo + 1) * 2) + 3 - rooms[roomNo].size());
											costs.add(minSort(steps, copyHallway(hallway, i, room.peek()),
													copyRooms(rooms, roomNo, false, null)));
										}
									}
								}
							}
						}
					}
				}
			}
			if (costs.isEmpty()) {
				return Integer.MAX_VALUE;
			} else {
				long minCost = Integer.MAX_VALUE;
				for (Long c : costs) {
					if (c < minCost) {
						minCost = c;
					}
				}
				return minCost + cost;
			}
		}
	}

	private boolean topShouldStay(Stack<Amphipod>[] rooms, int roomNo) {
		for (Amphipod a : rooms[roomNo]) {
			if (a.type != roomChar.get(roomNo)) {
				return false;
			}
		}
		return true;
	}

	private boolean correctRoom(Stack<Amphipod> room, int roomNo) {
		for (Amphipod a : room) {
			if (a.type != roomChar.get(roomNo)) {
				return false;
			}
		}
		return true;
	}

	private Stack<Amphipod>[] copyRooms(Stack<Amphipod>[] rooms, int except, boolean push, Amphipod add) {
		Stack<Amphipod>[] newRooms = rooms.clone();
		newRooms[except] = (Stack<Amphipod>) rooms[except].clone();
		if (push) {
			Amphipod newAdd = new Amphipod(add);
			newAdd.moves = 0;
			newRooms[except].push(newAdd);
		} else {
			newRooms[except].pop();
		}
		return newRooms;
	}

	private Amphipod[] copyHallway(Amphipod[] hallway, int except, Amphipod instead) {
		Amphipod[] newHallway = hallway.clone();
		newHallway[except] = instead;
		return newHallway;
	}

	private boolean possibleMove(int from, int to, Amphipod[] hallway) {
		for (int i = Math.min(from, to); i <= Math.max(from, to); i++) {
			if (!(hallway[i] == null)) {
				return false;
			}
		}
		return true;
	}

	private class Amphipod {
		private char type;
		private int cost;
		private int moves;

		private Amphipod(char type, int cost) {
			this.type = type;
			this.cost = cost;
			moves = 2;
		}

		private Amphipod(Amphipod other) {
			this.type = other.type;
			this.cost = other.cost;
			this.moves = other.moves;
		}

		private int move(int steps) {
			if (moves <= 0) {
				return -1;
			} else {
				return cost * steps;
			}
		}

		private boolean canEnter(Stack<Amphipod> room, char roomType) {
			if (room.size() == 2 || type != roomType) {
				return false;
			} else if (room.size() == 1) {
				return room.peek().type == type;
			} else {
				return true;
			}
		}

		public String toString() {
			return Character.toString(type);
		}
	}
}