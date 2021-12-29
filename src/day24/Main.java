package day24;

import java.io.IOException;

import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day24/input.txt");
		ALU alu = new ALU();
		for(String line : input) {
			alu.addInstruction(InstructionFactory.instruction(line));
		}
		String serial = "";
		for(int i = 0; i < 14; i++) {
			for(int j = 9; j> 0; j--) {
				alu.runInstructions(Integer.toString(j), 18);
				if(alu.valueAt('z') == 0) {
					serial += Integer.toString(j);
				}
			}
		}
		System.out.println(serial);
/*		for(long serial = 99999999999999L ; serial >= 11111111111111L; serial--) {
			System.out.println(serial);
			String ALUInput = Long.toString(serial);
			if(!ALUInput.contains("0")) {
				alu.runInstructions(ALUInput);
				if(alu.valueAt('z') == 0) {
					System.out.println(serial);
					break;
				}
			}
		}	*/
	}
}
