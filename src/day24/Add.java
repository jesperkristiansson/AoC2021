package day24;

public class Add extends Instruction {

	public Add(Variable var, Operand op) {
		super(var, op);
	}

	@Override
	protected int value(int val1, int val2, String input) {
		return val1 + val2;
	}
}