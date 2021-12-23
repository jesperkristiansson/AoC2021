package day4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import helpers.Helpers;

public class Bingo {
	public static void main(String[] args) throws IOException {
		String[] rows = Helpers.fileAsStrings("bingo.txt");
		String[] nums = rows[0].split(",");
		ArrayList<Board> boards = new ArrayList<>();
		for (int i = 2; i < rows.length; i++) {
			if (i % 6 == 2) {
				String[] boardRows = Arrays.copyOfRange(rows, i, i + 5);
				boards.add(new Board(boardRows));
			}
		}
		for (String num : nums) {
			ArrayList<Board> rm = new ArrayList<Board>();
			for (Board b : boards) {
				b.markNumber(num);
				if(b.checkWin()) {
					if(boards.size()==1) {
						System.out.println(b.score(Integer.parseInt(num)));
						System.exit(0);
					}
					rm.add(b);
				}
			}
			for(Board b : rm) {
				boards.remove(b);
			}
		}
	}
}