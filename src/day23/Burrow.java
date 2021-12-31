package day23;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Burrow {
	private static final int roomSize = 4;
	private static final List<Integer> roomCol = Arrays.asList(2, 4, 6, 8);
	private static final Map<Integer, Character> roomChar = Map.of(0, 'A', 1, 'B', 2, 'C', 3, 'D');
	private static final Map<Character, Integer> amphipodCost = Map.of('A', 1, 'B', 10, 'C', 100, 'D', 1000);
	private Stack<Amphipod>[] rooms;
	private Amphipod[] hallway;

	public Burrow(String[] map) {
		rooms = new Stack[4];
		hallway = new Amphipod[11];
		for (int i = 0; i < 4; i++) {
			rooms[i] = new Stack<Amphipod>();
		}
		for (int room = 0; room < 4; room++) {
			for (int i = 0; i < roomSize; i++) {
				String row = map[1 + roomSize - i];
				char c = row.charAt(roomCol.get(room) + 1);
				rooms[room].push(new Amphipod(c));
			}
		}
	}

	public Burrow(Stack<Amphipod>[] rooms, Amphipod[] hallway) {
		this.rooms = rooms;
		this.hallway = hallway;
	}

	public boolean finished() {
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

	public Map<Burrow, Integer> neighbors() {
		Map<Burrow, Integer> neighbors = new HashMap<Burrow, Integer>();
		for (int i = 0; i < hallway.length; i++) {
			if (hallway[i] != null) {
				Amphipod[] newHallway = copyHallway(hallway, i, null);
				for (int roomNo = 0; roomNo < rooms.length; roomNo++) {
					if (possibleMove(i, (roomNo + 1) * 2)) {
						if (hallway[i].canEnter(rooms[roomNo], roomChar.get(roomNo))) {
							int steps = hallway[i]
									.move(Math.abs(i - (roomNo + 1) * 2) + roomSize - rooms[roomNo].size());
							Stack<Amphipod>[] newRooms = copyRooms(rooms, roomNo, true, hallway[i]);
							neighbors.put(new Burrow(newRooms, newHallway), steps);
						}
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
									if (possibleMove((roomNo + 1) * 2, i)) {
										int steps = room.peek().move(
												Math.abs(i - (roomNo + 1) * 2) + roomSize + 1 - rooms[roomNo].size());
										Amphipod[] newHallway = copyHallway(hallway, i, room.peek());
										Stack<Amphipod>[] newRooms = copyRooms(rooms, roomNo, false, null);
										neighbors.put(new Burrow(newRooms, newHallway), steps);
									}
								}
							}
						}
					}
				}
			}
		}
		return neighbors;
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

	private boolean possibleMove(int from, int to) {
		if (from < to) {
			for (int i = from + 1; i <= to; i++) {
				if (!(hallway[i] == null)) {
					return false;
				}
			}
		} else {
			for (int i = to; i < from; i++) {
				if (!(hallway[i] == null)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Amphipod a : hallway) {
			sb.append(a == null ? "[ ]" : "[" + a + "]");
		}
		sb.append('\n');
		for (Stack<Amphipod> s : rooms) {
			sb.append(s);
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Burrow) {
			Burrow o = (Burrow) other;
			boolean hallwayEquals = Arrays.equals(hallway, o.hallway);
			boolean roomsEquals = Arrays.equals(rooms, o.rooms);
			return hallwayEquals && roomsEquals;
		} else {
			return false;
		}
	}

	private static class Amphipod {
		private char type;
		private int cost;
		private int moves;

		private Amphipod(char type) {
			this.type = type;
			cost = amphipodCost.get(type);
			moves = 2;
		}

		private Amphipod(char type, int moves) {
			this.type = type;
			cost = amphipodCost.get(type);
			this.moves = moves;
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
			if (room.size() == roomSize || type != roomType) {
				return false;
			} else {
				for (Amphipod a : room) {
					if (a.type != type) {
						return false;
					}
				}
				return true;
			}
		}

		public String toString() {
			return Character.toString(type);
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Amphipod) {
				Amphipod o = (Amphipod) other;
				return type == o.type && moves == o.moves;
			} else {
				return false;
			}
		}
	}
}