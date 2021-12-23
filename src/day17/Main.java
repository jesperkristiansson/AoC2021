package day17;

import java.awt.Point;
import java.io.IOException;

import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day17/input.txt");
		int xMin = Integer.parseInt(input[0]);
		int xMax = Integer.parseInt(input[1]);
		int yMin = Integer.parseInt(input[2]);
		int yMax = Integer.parseInt(input[3]);
		int possibleVelocities = 0;
		for (int dy = yMin-1; dy < 1000; dy++) {
			for (int dx = 0; dx <= xMax; dx++) {
				Probe probe = new Probe(dx, dy);
				while (true) {
					Point pos = probe.step();
					if (pos.y >= yMin && pos.y <= yMax && pos.x >= xMin && pos.x <= xMax) {
						possibleVelocities++;
						break;
					} else if (pos.y < yMin) {
						break;
					}
				}
			}
		}
		/*
		 * for(int dx = 0; dx < xMax; dx++) { for(int dy = 0; dy < 10; dy++) { Probe
		 * probe = new Probe(dx, dy); Point pos = probe.step(); } }
		 */

		System.out.println(possibleVelocities);
	}
}
