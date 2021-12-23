package day6;

public class LanternFish {
	private int numbers;
	
	public LanternFish(int days) {
		numbers = days;
	}
	
	public boolean step() {
		if(numbers == 0 ) {
			numbers = 6;
			return true;
		} else {
			numbers--;
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return numbers;
	}
	
	@Override
	public boolean equals(Object o) {
		return numbers == ((LanternFish) o).numbers;
	}
}
