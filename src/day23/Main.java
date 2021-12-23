package day23;

import java.io.IOException;

import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day23/input.txt");
		Burrow burrow = new Burrow(input);
		System.out.println(burrow.minPath());
		System.out.println();
	}
}
