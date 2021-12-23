package day19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import helpers.Helpers;

public class Main {
	final static int[][] xRotate = { { 1, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 } };
	final static int[][] yRotate = { { 0, 0, 1 }, { 0, 1, 0 }, { -1, 0, 0 } };
	final static int[][] zRotate = { { 0, -1, 0 }, { 1, 0, 0 }, { 0, 0, 1 } };

	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day19/input.txt");
		List<Scanner> scanners = getScanners(input);
		long startTime = System.currentTimeMillis();
		int[] bla = {0,0,0};
		scanners.get(0).consumedScanners.add(bla);
		while (scanners.size() > 1) {
			long currentTime = System.currentTimeMillis() - startTime;
			System.out.println(scanners.size() + " - " + currentTime);
			Scanner remove = doOnce(scanners);
			if (remove != null) {
				scanners.remove(remove);
			}
			// checkOverlap(scanners.get(0), scanners.get(1).allDirections());
		}
		System.out.println(largestDistance(scanners.get(0).consumedScanners));
	}

	public static int largestDistance(List<int[]> distances) {
		int largest = Integer.MIN_VALUE;
		for(int i = 0; i < distances.size(); i++) {
			for(int j = i+1; j < distances.size(); j++) {
				int[] first = distances.get(i);
				int[] second = distances.get(j);
				int distance = manhattan(first, second);
				if(distance > largest) {
					largest = distance;
				}
			}
		}
		return largest;
	}

	public static int manhattan(int[] first, int[] second) {
		return Math.abs(first[0] - second[0] + first[1] - second[1] + first[2] - second[2]);
	}

	static Scanner doOnce(List<Scanner> scanners) {
		for (Scanner scanner1 : scanners) {
			for (Scanner scanner2 : scanners) {
				if (!scanner1.equals(scanner2)) {
					if (checkOverlap(scanner1, scanner2)) {
						return scanner2;
					}
				}
			}
		}
		return null;
	}

	static boolean checkOverlap(Scanner scanner0, Scanner scanner1) {
		// Set<String> rotations = new HashSet<String>();
		int minOverlap = 11;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 4; y++) {
				for (int z = 0; z < 4; z++) {
					/*
					 * String rotation = ""; if(x % 2 == 0) { rotation += "" } if
					 * (!rotations.contains(scanner1)) { rotations.add(scanner1);
					 */
					for (Map.Entry<Beacon, Set<int[]>> entry0 : scanner0.distanceMap().entrySet()) {
						for (Map.Entry<Beacon, Set<int[]>> entry1 : scanner1.distanceMap().entrySet()) {
							int overlap = 0;
							for (int[] distance0 : entry0.getValue()) {
								for (int[] distance1 : entry1.getValue()) {
									if (Arrays.equals(distance0, distance1)) {
										overlap++;
									}
									if (overlap >= minOverlap) { // entry0.key "==" entry1.key
										int dx = entry0.getKey().x - entry1.getKey().x; // what the fuck?
										int dy = entry0.getKey().y - entry1.getKey().y;
										int dz = entry0.getKey().z - entry1.getKey().z;

										scanner0.addBeaconsFrom(scanner1, dx, dy, dz);
										for (int[] coords : scanner1.consumedScanners) {
											int[] newCoords = { coords[0] + dx, coords[1] + dy, coords[2] + dz };
											scanner0.consumedScanners.add(newCoords);
										}
										int[] newCoords = { dx, dy, dz };
										scanner0.consumedScanners.add(newCoords);
										return true;
									}
								}
							}
						}
						// }
					}
					scanner1 = scanner1.rotate(zRotate);
				}
				scanner1 = scanner1.rotate(yRotate);
			}
			scanner1 = scanner1.rotate(xRotate);
		}
		return false;
	}

	static List<Scanner> getScanners(String[] input) {
		List<Scanner> scanners = new ArrayList<Scanner>();
		int scannerNo = 0;
		for (String line : input) {
			if (line.isEmpty()) { // end of Scanner
				scannerNo++;
			} else if (line.charAt(1) == '-') { // new scanner
				scanners.add(new Scanner());
			} else {
				String[] split = line.split(",");
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);
				int z = Integer.parseInt(split[2]);
				scanners.get(scannerNo).addBeacon(new Beacon(x, y, z));
			}
		}
		return scanners;
	}
}
