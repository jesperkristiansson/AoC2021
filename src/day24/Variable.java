package day24;

import java.util.Map;

public class Variable implements Operand {
	private char variable;
	
	public Variable(char variable) {
		this.variable = variable;
	}

	@Override
	public int value(Map<Character, Integer> memory) {
		return memory.getOrDefault(variable, 0);
	}
	
	public char getVariable() {
		return variable;
	}
}