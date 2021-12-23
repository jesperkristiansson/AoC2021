package day11;

public class Octopus {
	private int energy;

	public Octopus(int startEnergy) {
		energy = startEnergy;
	}

	public boolean increase() {
		energy++;
		return energy == 10;
	}

	public void endStep() {
		if (energy > 9) {
			energy = 0;
		}
	}
	
	public int getEnergy() {
		return energy;
	}
	
	@Override
	public String toString() {
		return Integer.toString(energy);
	}
}
