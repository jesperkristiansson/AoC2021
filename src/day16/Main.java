package day16;

import java.io.IOException;

import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day16/input.txt");
		String bits = Numbers.hexToBin(input[0]);
	//	System.out.println(bits);
		Packet packet = PacketBuilder.packet(bits);
		System.out.println(packet.value());
	}
}
