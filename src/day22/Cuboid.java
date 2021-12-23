package day22;

public class Cuboid {
	long xMin;
	long xMax;
	long yMin;
	long yMax;
	long zMin;
	long zMax;
	boolean on;

	public Cuboid(long xMin, long xMax, long yMin, long yMax, long zMin, long zMax, boolean on) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.zMin = zMin;
		this.zMax = zMax;
		this.on = on;
	}

	public boolean intersects(Cuboid other) {
		boolean xOverlaps = xMin <= other.xMax && other.xMin <= xMax;
		boolean yOverlaps = yMin <= other.yMax && other.yMin <= yMax;
		boolean zOverlaps = zMin <= other.zMax && other.zMin <= zMax;
		return xOverlaps && yOverlaps && zOverlaps;
	}

	public Cuboid intersection(Cuboid other) {
		return new Cuboid(Math.max(xMin, other.xMin), Math.min(xMax, other.xMax), Math.max(yMin, other.yMin),
				Math.min(yMax, other.yMax), Math.max(zMin, other.zMin), Math.min(zMax, other.zMax), !other.on);
	}

	public long volume() {
		long volume = (xMax - xMin + 1) * (yMax - yMin + 1) * (zMax - zMin + 1);
		if (!on) {
			volume = -volume;
		}
		return volume;
	}
}
