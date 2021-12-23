package day8;

import java.io.IOException;
import helpers.Helpers;

public class Digits {

	public static void main(String[] args) throws IOException {
		String[] lines = Helpers.fileAsStrings("src/day8/input.txt");
		int sum = 0;
		for(String line : lines){
			String[] split = line.split("\\|");
			String[] input = split[0].trim().split(" ");
			String[] output = split[1].trim().split(" ");
			Display d = new Display(input);
			sum += d.output(output);
		}
		System.out.println(sum);
	}
}
