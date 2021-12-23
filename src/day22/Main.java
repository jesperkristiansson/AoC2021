package day22;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import helpers.Helpers;

public class Main {
	static final int lowBound = -50;
	static final int highBound = 50;
	
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day22/input.txt");
		List<Cuboid> processed = new ArrayList<Cuboid>();
		for (String line : input) {
			String[] split = line.split(" ");
			boolean turnOn = split[0].equals("on");
			String filtered = line.replaceAll("[^-0-9]", " ");
			String[] coords = filtered.trim().split(" +");
			int xMin = Integer.parseInt(coords[0]);
			int xMax = Integer.parseInt(coords[1]);
			int yMin = Integer.parseInt(coords[2]);
			int yMax = Integer.parseInt(coords[3]);
			int zMin = Integer.parseInt(coords[4]);
			int zMax = Integer.parseInt(coords[5]);
			Cuboid newCuboid = new Cuboid(xMin, xMax, yMin, yMax, zMin, zMax, turnOn);
			List<Cuboid> newCubes = new ArrayList<Cuboid>();
			for(Cuboid otherCube : processed) {
				if(newCuboid.intersects(otherCube)) {
				newCubes.add(newCuboid.intersection(otherCube));
				}
			}
			processed.addAll(newCubes);
			if(turnOn) {
				processed.add(newCuboid);
			}
		}
		long cubesOn = 0;
		for(Cuboid c : processed) {
			cubesOn += c.volume();
		}
		System.out.println(cubesOn);
	}
}
