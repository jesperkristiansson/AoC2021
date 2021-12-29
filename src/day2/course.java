package day2;

import java.io.IOException;
import helpers.Helpers;

public class course {
	public static void main(String[] args) throws IOException{
		String[] commands = Helpers.fileAsStrings("course.txt");
		int hor = 0;
		int depth = 0;
		int aim = 0;
		for(String command : commands) {
			String[] split = command.split(" ");
			if(split[0].equals("forward")) {
				hor += Integer.parseInt(split[1]);
				depth += aim*Integer.parseInt(split[1]);
			} else if(split[0].equals("down")){
				aim += Integer.parseInt(split[1]);
			} else if(split[0].equals("up")) {
				aim -= Integer.parseInt(split[1]);
			}
		}
		System.out.println(hor*depth);
	}
}
