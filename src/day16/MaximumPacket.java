package day16;

import java.util.List;

public class MaximumPacket extends OperationPacket {

	public MaximumPacket(int version, int bitLength, List<Packet> subPackets) {
		super(version, bitLength, subPackets);
	}

	@Override
	protected long calculateValue() {
		long value = Integer.MIN_VALUE;
		for(Packet p : subPackets) {
			long subValue = p.calculateValue();
			if(subValue > value) {
				value = subValue;
			}
		}
		return value;
	}
}
