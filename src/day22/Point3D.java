package day22;

public class Point3D {
	private int x;
	private int y;
	private int z;
	
	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public int hashCode() {
		return x*31+y*29+z*23;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Point3D) {
			Point3D o = (Point3D) other;
			return x == o.x && y == o.y && z == o.z;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return x + " " + y + " " + z;
	}
}
