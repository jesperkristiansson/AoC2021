package day24;

public class Div extends Instruction {

	public Div(Variable var, Operand op) {
		super(var, op);
	}

	@Override
	protected int value(int val1, int val2, String input) {
		return val1 / val2;
	}
}