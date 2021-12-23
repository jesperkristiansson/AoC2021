package day16;

public class Numbers {
	public static String hexToBin(String hex) {
		StringBuilder bits = new StringBuilder();
		for(char c : hex.toCharArray()) {
			int decimal = Integer.parseInt(String.valueOf(c), 16);
			String binary = Integer.toBinaryString(decimal);
			while(binary.length() < 4) {
				binary = "0" + binary;
			}
			bits.append(binary);
		}
		return bits.toString();
	}
	
	public static long binToDec(String bin) {
		return Long.parseLong(bin, 2);
	}
}