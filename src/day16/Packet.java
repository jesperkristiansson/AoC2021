package day16;

import java.util.List;

public abstract class Packet {
	private int version;
	private int bitLength;
	private List<Packet> subPackets;

	public Packet(int version, int bitLength) {
		this.version = version;
		this.bitLength = bitLength;
	}

	public int length() {
		return bitLength;
	}

	public long value() {
		long value = calculateValue();
		if (value < 0) {
			System.out.println("error");
		}
		return value;
	}

	protected abstract long calculateValue();

	public List<Packet> subPackets() {
		return subPackets;
	}

	protected int version() {
		return version;
	}

	protected abstract int subPacketVersionSum();

	public int versionSum() {
		return subPacketVersionSum();
	}
}
