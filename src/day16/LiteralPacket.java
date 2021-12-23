package day16;

public class LiteralPacket extends Packet {
	private long value;
	
	public LiteralPacket(int version, int length, long value) {
		super(version, length);
		this.value = value;
	}

	@Override
	protected int subPacketVersionSum() {
		return version();
	}
	
	protected long calculateValue() {
		return value;
	}
}
