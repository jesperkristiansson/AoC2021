package day24;

import java.util.Map;

public abstract class Instruction {
	protected Variable var;
	protected Operand op;
	
	public Instruction(Variable var, Operand op) {
		this.var = var;
		this.op = op;
	}
	
	protected abstract int value(int val1, int val2, String input);
	
	public void execute(Map<Character, Integer> memory, String input) {
		int val1 = var.value(memory);
		int val2 = op.value(memory);
		memory.put(var.getVariable(), value(val1, val2, input));
	}
}