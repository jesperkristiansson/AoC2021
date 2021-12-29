package day24;

import java.util.Map;

public class Value implements Operand {
	private int value;

	public Value(int value) {
		this.value = value;
	}

	@Override
	public int value(Map<Character, Integer> memory) {
		return value;
	}
}