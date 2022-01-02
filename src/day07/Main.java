package day07;

import java.io.IOException;
import java.util.Arrays;

import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException{
		String[] input = Helpers.fileAsStrings("src/day07/input.txt");
		int[] crabs = Arrays.stream(input[0].split(","))
								.mapToInt(s -> Integer.parseInt(s))
								.toArray();
		int minFuel = Integer.MAX_VALUE;
		for(int pos = 0; pos < max(crabs); pos++) {
			int fuel = 0;
			for(int crab : crabs) {
				fuel += Math.abs(increasingSum(Math.abs(crab-pos)));
			}
			if(fuel < minFuel) {
				minFuel = fuel;
			}
		}
		System.out.println(minFuel);
	}
	
	public static int increasingSum(int n) {
		return n*(n+1)/2;
	}
	
	public static int max(int[] arr) {
		int max = 0;
		for(int i : arr) {
			if(i > max) {
				max = i;
			}
		}
		return max;
	}
}
