package day21;

import java.io.IOException;
import java.util.Map;

import helpers.Helpers;

public class Main {
	static int die = 1;
	static int dieRolls = 0;
	static Map<Integer, Integer> rollNumbers = Map.of(3, 1, 4, 3, 5, 6, 6, 7, 7, 6, 8, 3, 9, 1);
	static int winningScore = 21;
	
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day21/input.txt");
		int p1pos = Integer.parseInt(input[0].split(" ")[4]);
		int p2pos = Integer.parseInt(input[1].split(" ")[4]);
		int p1score = 0, p2score = 0;
		long[] score = { 0, 0 };
		newUniverse(score, p1pos, p2pos, p1score, p2score, false, 1);
		/*
		 * while(true) { p1pos = playerTurn(p1pos); p1score += p1pos; if(p1score >=
		 * 1000) { break; } p2pos = playerTurn(p2pos); p2score += p2pos; if(p2score >=
		 * 1000) { break; } } int lowestScore = p1score; if(p2score < p1score) {
		 * lowestScore = p2score; }
		 */
		System.out.println("p1: " + score[0] + " p2: " + score[1]);
	}

	static void newUniverse(long[] score, int p1pos, int p2pos, int p1score, int p2score, boolean p1turn, long count) {
		if (p1score >= winningScore) {
			score[0] += count;
		} else if (p2score >= winningScore) {
			score[1] += count;
		} else {
			p1turn = !p1turn;
			for (Map.Entry<Integer, Integer> entry : rollNumbers.entrySet()) {
				if (p1turn) {
					int nextPos = nextPos(p1pos, entry.getKey());
					newUniverse(score, nextPos, p2pos, p1score + nextPos, p2score, p1turn, count*entry.getValue());
				} else {
					int nextPos = nextPos(p2pos, entry.getKey());
					newUniverse(score, p1pos, nextPos, p1score, p2score + nextPos, p1turn, count*entry.getValue());
				}
			}
		}
		System.out.println("p1: " + score[0] + " p2: " + score[1]);
	}

	static int nextPos(int currentPos, int step) {
		int nextPos = (currentPos + step) % 10;
		if (nextPos == 0) {
			nextPos = 10;
		}
		return nextPos;
	}

	static int nextDice(int currentDice) {
		if (currentDice == 100) {
			return 1;
		} else {
			return currentDice++;
		}
	}

	static int playerTurn(int startPosition) {
		int endPosition = (startPosition + rollDie()) % 10;
		if (endPosition == 0) {
			endPosition = 10;
		}
		return endPosition;
	}

	static int rollDie() {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += die;
			if (die++ == 100) {
				die = 1;
			}
			dieRolls++;
		}
		return sum;
	}
}
