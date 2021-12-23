package day16;

import java.util.List;

public abstract class OperationPacket extends Packet {
	protected List<Packet> subPackets;

	public OperationPacket(int version, int length, List<Packet> subPackets) {
		super(version, length);
		this.subPackets = subPackets;
	}

	@Override
	protected int subPacketVersionSum() {
		int versionSum = version();
		for(Packet p : subPackets) {
			versionSum += p.subPacketVersionSum();
		}
		return versionSum;
	}
	
}
