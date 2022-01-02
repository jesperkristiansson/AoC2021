package day03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] nums = Helpers.fileAsStrings("src/day03/input.txt");
		int oxy = rating(nums, 1, 0);
		int co2 = rating(nums, 0, 1);
		System.out.println(oxy * co2);
	}

	private static int rating(String[] arr, int most, int least) {
		ArrayList<String> l = new ArrayList<>();
		Collections.addAll(l, arr);
		for (int i = 0; i < 12; i++) {
			if (l.size() == 1) {
				break;
			}
			int count = 0;
			for (String num : l) {
				count += Character.getNumericValue(num.charAt(i));
			}
			int j = most;
			if (count < l.size() / 2) {
				j = least;
			}
			Integer val = Integer.valueOf(j);
			Integer index = Integer.valueOf(i);
			l.removeIf(s -> s.charAt(index) != Character.forDigit(val, 10));
		}
		return Integer.parseInt(l.get(0), 2);
	}

	private static void removeIf(List<String> l, int i, char con) {
		l.forEach(s -> {
			if (s.charAt(i) == con) {
				l.remove(s);
			}
		});
	}
}
