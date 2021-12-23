package day16;

import java.util.ArrayList;
import java.util.List;

public class PacketBuilder {
	public static Packet packet(String bits) {
		int version = (int) Numbers.binToDec(bits.substring(0, 3));
		int type = (int) Numbers.binToDec(bits.substring(3, 6));
		if (type == 4) { // literal
			String leftToParse = bits.substring(6);
			StringBuilder literal = new StringBuilder();
			int length = 6;
			while (leftToParse.charAt(0) == '1') {
				literal.append(leftToParse.substring(1, 5));
				leftToParse = leftToParse.substring(5);
				length += 5;
			}
			literal.append(leftToParse.substring(1, 5));
			length += 5;
			return new LiteralPacket(version, length, Numbers.binToDec(literal.toString()));
		} else { // operation
			int lengthType = (int) Numbers.binToDec(bits.substring(6, 7));
			List<Packet> subPackets = new ArrayList<Packet>();
			int length = 7;
			if (lengthType == 0) {
				int totalLength = (int) Numbers.binToDec(bits.substring(7, 22));
				length += 15 + totalLength;
				String subPacks = bits.substring(22, 22 + totalLength);
				while (subPacks.length() != 0) {
					Packet newPacket = packet(subPacks);
					subPackets.add(newPacket);
					subPacks = subPacks.substring(newPacket.length());
				}
			} else {
				int numberOfSubPackets = (int) Numbers.binToDec(bits.substring(7, 18));
				length += 11;
				String subPacks = bits.substring(18);
				for (int i = 0; i < numberOfSubPackets; i++) {
					Packet newPacket = packet(subPacks);
					subPackets.add(newPacket);
					length += newPacket.length();
					subPacks = subPacks.substring(newPacket.length());
				}
			}
			switch (type) {
			case 0:
				return new SumPacket(version, length, subPackets);
			case 1:
				return new ProductPacket(version, length, subPackets);
			case 2:
				return new MinimumPacket(version, length, subPackets);
			case 3:
				return new MaximumPacket(version, length, subPackets);
			case 5:
				return new GreaterThanPacket(version, length, subPackets);
			case 6:
				return new LessThanPacket(version, length, subPackets);
			case 7:
				return new EqualPacket(version, length, subPackets);
			default:
					System.out.println("Invalid type");
					return null;
			}
		}
	}
}