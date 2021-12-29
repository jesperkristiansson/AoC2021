package day24;

public class Inp extends Instruction {

	public Inp(Variable var, Operand op) {
		super(var, op);
	}

	@Override
	protected int value(int val1, int val2, String input) {
		return Character.digit(input.charAt(0), 10);
	}
}