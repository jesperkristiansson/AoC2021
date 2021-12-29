package day24;

public class InstructionFactory {
	public static Instruction instruction(String ins) {
		String[] split = ins.split(" ");
		String type = split[0];
		Variable var = new Variable(split[1].charAt(0));
		Operand op;
		//= split.length == 3 ? split[2].charAt(0) : '-';
		if(split.length == 3) {
			char c = split[2].charAt(0);
			op = Character.isDigit(c) ? new Value(Character.digit(c, 10)) : new Variable(c); 
		} else {
			op = new Value(0);
		}
		switch (type) {
		case "inp":
			return new Inp(var, op);
		case "add":
			return new Add(var, op);
		case "mul":
			return new Mul(var, op);
		case "div":
			return new Div(var, op);
		case "mod":
			return new Mod(var, op);
		case "eql":
			return new Eql(var, op);
		default:
			System.out.println("Invalid instruction");
			return null;
		}
	}
}
