package day24;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ALU {
	private Map<Character, Integer> memory = new HashMap<Character, Integer>();
	private LinkedList<Instruction> instructions = new LinkedList<Instruction>();

	public void addInstruction(Instruction instruction) {
		instructions.offer(instruction);
	}

	public void runInstructions(String input) {
		for (Instruction instruction : instructions) {
			instruction.execute(memory, input);
			if (instruction instanceof Inp) {
				input = input.substring(1);
			}
		}
	}

	public void runInstructions(String input, int amount) {
		for (int i = 0; i < amount; i++) {
			instructions.get(i).execute(memory, input);
			if (instructions.get(i) instanceof Inp) {
				input = input.substring(1);
			}
		}
	}

	public void removeInstructions(int amount) {
		for (int i = 0; i < amount; i++) {
			instructions.poll();
		}
	}

	public int valueAt(char c) {
		return memory.getOrDefault(c, 0);
	}
}