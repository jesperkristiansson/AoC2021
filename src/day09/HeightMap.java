package day09;

import java.util.ArrayList;

public class HeightMap {
	private int[][] heights;
	private boolean[][] lowPoints;
	private int w;
	private int h;
	static boolean[][] visited;

	public HeightMap(String[] map) {
		w = map[0].length();
		h = map.length;
		heights = new int[h][w];
		lowPoints = new boolean[h][w];
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				heights[r][c] = Character.getNumericValue(map[r].charAt(c));
			}
		}
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				lowPoints[r][c] = isLow(r, c);
			}
		}
	}

	public ArrayList<Integer> findBasins() {
		ArrayList<Integer> basinSizes = new ArrayList<Integer>();
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if (lowPoints[r][c]) {
					visited = new boolean[h][w];
					int size = travel(r, c, -1);
					basinSizes.add(size);
				}
			}
		}
		return basinSizes;
	}

	private int travel(int r, int c, int prev) {
		if(!inRange(r,c)) {
			return 0;
		}
		int height = heights[r][c];
		if (visited[r][c] || height <= prev || height == 9) {
			return 0;
		} else {
			visited[r][c] = true;
			return 1 + travel(r + 1, c, height) + travel(r - 1, c, height)
					+ travel(r, c + 1, height) + travel(r, c - 1, height);

		}
	}
	
	private boolean inRange(int r, int c) {
		return r >= 0 && r < h && c >= 0 && c < w;
	}

	private boolean isLow(int r, int c) {
		if (r - 1 < 0) {
			if (c - 1 < 0) {
				return heights[r][c] < heights[r + 1][c] && heights[r][c] < heights[r][c + 1];
			} else if (c + 1 > w - 1) {
				return heights[r][c] < heights[r + 1][c] && heights[r][c] < heights[r][c - 1];
			} else {
				return heights[r][c] < heights[r + 1][c] && heights[r][c] < heights[r][c - 1]
						&& heights[r][c] < heights[r][c + 1];
			}
		} else if (r + 1 > h - 1) {
			if (c - 1 < 0) {
				return heights[r][c] < heights[r - 1][c] && heights[r][c] < heights[r][c + 1];
			} else if (c + 1 > w - 1) {
				return heights[r][c] < heights[r - 1][c] && heights[r][c] < heights[r][c - 1];
			} else {
				return heights[r][c] < heights[r - 1][c] && heights[r][c] < heights[r][c - 1]
						&& heights[r][c] < heights[r][c + 1];
			}
		} else {
			if (c - 1 < 0) {
				return heights[r][c] < heights[r - 1][c] && heights[r][c] < heights[r + 1][c]
						&& heights[r][c] < heights[r][c + 1];
			} else if (c + 1 > w - 1) {
				return heights[r][c] < heights[r - 1][c] && heights[r][c] < heights[r + 1][c]
						&& heights[r][c] < heights[r][c - 1];
			} else {
				return heights[r][c] < heights[r - 1][c] && heights[r][c] < heights[r + 1][c]
						&& heights[r][c] < heights[r][c - 1] && heights[r][c] < heights[r][c + 1];
			}
		}
	}

	public ArrayList<Integer> lowPoints() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if (lowPoints[r][c]) {
					list.add(heights[r][c]);
				}
			}
		}
		return list;
	}
}
