package day5;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import helpers.Helpers;
import java.awt.Point;
import java.util.HashMap;

public class Vents {
	public static void main(String[] args) throws IOException{
		String[] vents = Helpers.fileAsStrings("vents.txt");
		List<Line> lines = Arrays.stream(vents)
					.map(s -> s.split(" "))
					.map(arr -> dropMiddle(arr))
					.map(arr -> toPoints(arr))
					.map(points -> new Line(points[0], points[1]))
					.filter(l -> !l.empty())
					.collect(Collectors.toList());
		HashMap<Point, Integer> map = new HashMap<>();
		lines.forEach(l -> l.points().forEach(p -> map.put(p, map.getOrDefault(p, 0)+1)));
		int overlaps = 0;
		for(Map.Entry<Point, Integer> e : map.entrySet()) {
			if(e.getValue() >= 2){
				overlaps++;
			}
		}
		System.out.println(overlaps);
	}
	
	private static String[] dropMiddle(String[] arr) {
		String[] ret = {arr[0], arr[2]};
		return ret;
	}
	
	private static Point[] toPoints(String[] arr) {
		Point[] ret = new Point[2];
		for(int i = 0; i < 2; i++) {
			String[] arr2 = arr[i].split(",");
			ret[i] = new Point(Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1]));
		}
		return ret;
	}
}
