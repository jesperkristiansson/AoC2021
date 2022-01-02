package day09;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import helpers.Helpers;

public class Main {

	public static void main(String[] args) throws IOException {
		String[] text = Helpers.fileAsStrings("src/day09/input.txt");
		HeightMap map = new HeightMap(text);
		int sum = 0;
		for(int lowPoint : map.lowPoints()) {
			sum += lowPoint+1;
		}
		List<Integer> basins = map.findBasins();
		Collections.sort(basins);
		Collections.reverse(basins);
		System.out.println(basins.get(0)*basins.get(1)*basins.get(2));
	}
}
