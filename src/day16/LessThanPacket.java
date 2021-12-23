package day16;

import java.util.List;

public class LessThanPacket extends OperationPacket {

	public LessThanPacket(int version, int bitLength, List<Packet> subPackets) {
		super(version, bitLength, subPackets);
	}

	@Override
	protected long calculateValue() {
		if (subPackets.get(0).calculateValue() < subPackets.get(1).calculateValue()) {
			return 1;
		} else {
			return 0;
		}
	}
}
