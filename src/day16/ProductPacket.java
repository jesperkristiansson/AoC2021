package day16;

import java.util.List;

public class ProductPacket extends OperationPacket {

	public ProductPacket(int version, int bitLength, List<Packet> subPackets) {
		super(version, bitLength, subPackets);
	}

	@Override
	protected long calculateValue() {
		long value = 1;
		for(Packet p : subPackets) {
			value *= p.calculateValue();
		}
		return value;
	}
}
