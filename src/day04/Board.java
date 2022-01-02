package day04;

import java.util.Arrays;

public class Board {
	private final int boardSize = 5;
	private String[][] board = new String[boardSize][boardSize];
	private Boolean[][] checks = new Boolean[boardSize][boardSize];

	public Board(String[] arr) {
		board = Arrays.stream(arr).map(s -> s.split(" +")).map(array -> trimArray(array)).toArray(String[][]::new);
		resetChecks();
	}

	private String[] trimArray(String[] arr) {
		String[] trimmedArray = new String[boardSize];
		int i = 0;
		for (String s : arr) {
			if (!s.equals("")) {
				trimmedArray[i] = s;
				i++;
			}
		}
		return trimmedArray;
	}
	
	private void resetChecks() {
		for (int r = 0; r < boardSize; r++) {
			for (int c = 0; c < boardSize; c++) {
				checks[r][c] = false;
			}
		}
	}

	public void markNumber(String num) {
		for (int r = 0; r < boardSize; r++) {
			for (int c = 0; c < boardSize; c++) {
				if (board[r][c].equals(num)) {
					checks[r][c] = true;
				}
			}
		}
	}

	public boolean checkWin() {
		for (int i = 0; i < boardSize; i++) {
			boolean row = true;
			boolean col = true;
			for (int j = 0; j < boardSize; j++) {
				if(!checks[i][j]) {
					row = false;
				}
				if(!checks[j][i]) {
					col = false;
				}
			}
			if(row || col) {
				return true;
			}
		}
		return false;
	}
	
	public int score(int num) {
		int score = 0;
		for (int r = 0; r < boardSize; r++) {
			for (int c = 0; c < boardSize; c++) {
				if (!checks[r][c]) {
					score += Integer.parseInt(board[r][c]);
				}
			}
		}
		return score*num;
	}
}