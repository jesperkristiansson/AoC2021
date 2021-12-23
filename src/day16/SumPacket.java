package day16;

import java.util.List;

public class SumPacket extends OperationPacket {

	public SumPacket(int version, int bitLength, List<Packet> subPackets) {
		super(version, bitLength, subPackets);
	}

	@Override
	protected long calculateValue() {
		long value = 0;
		for(Packet p : subPackets) {
			value += p.calculateValue();
		}
		return value;
	}
}
