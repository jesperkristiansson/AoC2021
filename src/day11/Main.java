package day11;

import java.io.IOException;

import helpers.Helpers;

public class Main {

	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day11/input.txt");
		Octopus[][] octo = new Octopus[10][10];
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				octo[r][c] = new Octopus(Character.getNumericValue(input[r].charAt(c)));
			}
		}
		for(int i = 0; i < 1000; i++) {
			step(octo);
			
			boolean allZero = true;
			for(int r = 0; r < 10; r++) {
				for(int c = 0; c < 10; c++) {
					if(octo[r][c].getEnergy() != 0) {
						allZero = false;
						break;
					}
				}
			}
			if(allZero) {
				System.out.println(i+1);
				return;
			}
		}
	}
	
	private static int step(Octopus[][] octo) {
		int flashes = 0;
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				flashes += stepOcto(octo, r, c);
			}
		}
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 10; c++) {
				octo[r][c].endStep();
			}
		}
		return flashes;
	}
	
	private static int stepOcto(Octopus[][] octo, int r, int c) {
		int flashes = 0;
		if(octo[r][c].increase()) {
			flashes += 1 + increaseNeighbors(octo, r, c);
		}
		return flashes;
	}

	private static int increaseNeighbors(Octopus[][] octo, int r, int c) {
		int flashes = 0;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(i != 0 || j != 0) {
					try {
						flashes += stepOcto(octo, r+i, c+j);
					} catch (Exception e) {}
				}
			}
		}
		return flashes;
	}
}