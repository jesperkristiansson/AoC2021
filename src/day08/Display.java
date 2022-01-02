package day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Display {
	private HashMap<Integer, Set<Character>> nums = new HashMap<Integer, Set<Character>>();

	public Display(String[] input) {
		ArrayList<String> list = new ArrayList<String>();
		for (String s : input) {
			list.add(s);
		}
		initialNums(list);
		addRest(list);
	}

	public int output(String[] output) {
		String sum = "";
		for (String s : output) {
			for (Map.Entry<Integer, Set<Character>> entry : nums.entrySet()) {
				if (entry.getValue().equals(charSet(s))) {
					sum += entry.getKey();
				}
			}
		}
		return Integer.parseInt(sum);
	}

	private void initialNums(List<String> list) {
		ArrayList<String> removeList = new ArrayList<>();
		for (String s : list) {
			boolean remove = true;
			switch (s.length()) {
			case 2:
				nums.put(1, charSet(s));
				break;
			case 3:
				nums.put(7, charSet(s));
				break;
			case 4:
				nums.put(4, charSet(s));
				break;
			case 7:
				nums.put(8, charSet(s));
				break;
			default:
				remove = false;
			}
			if (remove) {
				removeList.add(s);
			}
		}
		list.removeAll(removeList);
	}

	private void addRest(List<String> list) {
		String rem = "";
		for (String s : list) { // 9
			rem = s;
			if (charSet(s).containsAll(nums.get(7)) && charSet(s).containsAll(nums.get(4))) {
				nums.put(9, charSet(s));
				break;
			}
		}
		list.remove(rem);

		for (String s : list) { // 0
			rem = s;
			if (s.length() == 6 && charSet(s).containsAll(nums.get(1))) {
				nums.put(0, charSet(s));
				break;
			}
		}
		list.remove(rem);

		for (String s : list) { // 6
			rem = s;
			if (s.length() == 6) {
				nums.put(6, charSet(s));
				break;
			}
		}
		list.remove(rem);

		for (String s : list) { // 5
			rem = s;
			if (nums.get(6).containsAll(charSet(s))) {
				nums.put(5, charSet(s));
				break;
			}
		}
		list.remove(rem);

		for (String s : list) { // 3
			rem = s;
			if (charSet(s).containsAll(nums.get(1))) {
				nums.put(3, charSet(s));
				break;
			}
		}
		list.remove(rem);

		for (String s : list) { // 2
			rem = s;
			if (nums.get(8).containsAll(charSet(s))) {
				nums.put(2, charSet(s));
				break;
			}
		}
		list.remove(rem);
	}

	private Set<Character> charSet(String s) {
		Set<Character> ret = new HashSet<>();
		for (char c : s.toCharArray()) {
			ret.add(c);
		}
		return ret;
	}
}
