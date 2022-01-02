package day18;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day18/input.txt");
		List<Integer> magnitudes = new ArrayList<Integer>();
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				String result = "[" + input[i] + "," + input[j] + "]";
				String newResult = reduce(result);
				while (!result.equals(newResult)) {
					result = newResult;
					newResult = reduce(result);
				}
				magnitudes.add(magnitude(result));
			}
		}
		Collections.sort(magnitudes);
		Collections.reverse(magnitudes);
		System.out.println(magnitudes.get(0));
	}

	static int magnitude(String s) {
		if (s.length() == 1) {
			return Integer.parseInt(s);
		} else {
			int nests = 0;
			int commaIndex = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '[') {
					nests++;
				} else if (c == ']') {
					nests--;
				} else if (c == ',' && nests == 1) {
					commaIndex = i;
				}
			}
			String left = s.substring(1, commaIndex);
			String right = s.substring(commaIndex + 1, s.length() - 1);
			return 3 * magnitude(left) + 2 * magnitude(right);
		}
	}

	static String reduce(String s) {
		int nests = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '[') {
				if (nests >= 4) {
					String pair = "";
					int j = 1;
					while (true) {
						char nextChar = s.charAt(i + j);
						if (nextChar == ']') {
							break;
						} else {
							pair += nextChar;
							j++;
						}
					}
					int pairLength = pair.length() + 2;
					int[] numbers = Arrays.stream(pair.split(",")).mapToInt(Integer::parseInt).toArray();

					String leftNum = "";
					int leftNumPos = 0;
					for (int h = i; h >= 0; h--) {
						char newChar = s.charAt(h);
						if (Character.isDigit(newChar)) {
							while (true) {
								leftNumPos = h;
								leftNum = newChar + leftNum;
								h--;
								newChar = s.charAt(h);
								if (!Character.isDigit(newChar)) {
									break;
								}
							}
							break;
						}
					}

					String rightNum = "";
					int rightNumPos = 0;
					for (int h = i + pairLength; h < s.length(); h++) {
						char newChar = s.charAt(h);
						if (Character.isDigit(newChar)) {
							rightNumPos = h - i - pairLength;
							while (true) {
								rightNum += newChar;
								h++;
								newChar = s.charAt(h);
								if (!Character.isDigit(newChar)) {
									break;
								}
							}
							break;
						}
					}

					String leftHalf = s.substring(0, i);
					if (!leftNum.isEmpty()) {
						int newLeftNum = Integer.parseInt(leftNum) + numbers[0];
						leftHalf = leftHalf.substring(0, leftNumPos) + newLeftNum
								+ leftHalf.substring(leftNumPos + leftNum.length());
					}

					String rightHalf = s.substring(i + pairLength);
					if (!rightNum.isEmpty()) {
						int newRightNum = Integer.parseInt(rightNum) + numbers[1];
						rightHalf = rightHalf.substring(0, rightNumPos) + newRightNum
								+ rightHalf.substring(rightNumPos + rightNum.length());
					}

					return leftHalf + "0" + rightHalf;
				} else {
					nests++;
				}
			} else if (c == ']') {
				nests--;
			}
		}
		String currentNum = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				currentNum += c;
			} else {
				if (currentNum.length() > 1) {
					String leftHalf = s.substring(0, i - currentNum.length());
					String rightHalf = s.substring(i);
					int number = Integer.parseInt(currentNum);
					int leftNumber = number / 2;
					int rightNumber = number / 2 + number % 2;
					return leftHalf + "[" + leftNumber + "," + rightNumber + "]" + rightHalf;
				} else {
					currentNum = "";
				}
			}
		}
		return s;
	}
}
