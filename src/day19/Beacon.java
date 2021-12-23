package day19;

public class Beacon {
	public int x;
	public int y;
	public int z;

	public Beacon(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Beacon rotate(int[][] matrix) {
		int newX = matrix[0][0] * x + matrix[0][1] * y + matrix[0][2] * z;
		int newY = matrix[1][0] * x + matrix[1][1] * y + matrix[1][2] * z;
		int newZ = matrix[2][0] * x + matrix[2][1] * y + matrix[2][2] * z;
		return new Beacon(newX, newY, newZ);
	}

	public int[] distancesTo(Beacon other) {
		int[] distances = new int[3];
		distances[0] = x - other.x;
		distances[1] = y - other.y;
		distances[2] = z - other.z;
		return distances;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	@Override
	public int hashCode() {
		return 31*x+29*y+23*z;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Beacon) {
			Beacon o = (Beacon) other;
			return x == o.x && y == o.y && z == o.z;
		} else {
			return false;
		}
	}
}
