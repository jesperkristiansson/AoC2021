package day24;

public class Eql extends Instruction {

	public Eql(Variable var, Operand op) {
		super(var, op);
	}

	@Override
	protected int value(int val1, int val2, String input) {
		return val1 == val2 ? 1 : 0;
	}
}