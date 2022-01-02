package day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import helpers.Helpers;

public class Main {

	public static void main(String[] args) throws IOException {
		String[] lines = Helpers.fileAsStrings("src/day10/input.txt");
		Map<Character, Character> pairs = pairs();
		Map<Character, Integer> values = valueMap();
		Stack<Character> stack;
		ArrayList<Long> scores = new ArrayList<Long>();
		for (String line : lines) {
			stack = new Stack<Character>();
			boolean corrupted = false;
			for (char c : line.toCharArray()) {
				if (pairs.containsKey(c)) {
					stack.push(c);
				} else if (pairs.get(stack.pop()) != c) {
					corrupted = true;
					break;
				}
			}
			if (!corrupted) {
				long sum = 0;
				while (!stack.empty()) {
					sum = sum * 5;
					sum += values.get(pairs.get(stack.pop()));
				}
				scores.add(sum);
			}
		}
		Collections.sort(scores);
		System.out.println(scores.get(scores.size() / 2));
	}

	private static Map<Character, Character> pairs() {
		Map<Character, Character> starts = new HashMap<Character, Character>();
		starts.put('(', ')');
		starts.put('[', ']');
		starts.put('{', '}');
		starts.put('<', '>');
		return starts;
	}

	private static Map<Character, Integer> valueMap() {
		Map<Character, Integer> value = new HashMap<Character, Integer>();
		value.put(')', 1);
		value.put(']', 2);
		value.put('}', 3);
		value.put('>', 4);
		return value;
	}
}
