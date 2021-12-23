package day20;

import java.io.IOException;

import helpers.Helpers;

public class Main {
	static final char light = '#';
	static final char dark = '.';
	static final int padding = 2;

	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day20/input.txt");
		boolean switchPadding = input[0].charAt(0) == light;
		boolean darkPadding = true;
		String enhancement = input[0];

		int imageHeight = input.length - 2;
		int imageWidth = input[2].length();
		char[][] image2 = new char[imageWidth][imageHeight];
		for (int r = 2; r < imageHeight + 2; r++) {
			for (int c = 0; c < imageWidth; c++) {
				image2[r - 2][c] = input[r].charAt(c);
			}
		}
		for (int i = 0; i < 50; i++) {
			System.out.println(i);
			char currentPadding;
			if (darkPadding) {
				currentPadding = dark;
			} else {
				currentPadding = light;
			}
			image2 = enhanceImage(image2, enhancement, currentPadding);
			if(switchPadding) {
			darkPadding = !darkPadding;
			}
		}
		System.out.println(countPixels(image2));
	}

	static char getPixel(char[][] image, int x, int y, char currentPadding) {
		try {
			return image[y][x];
		} catch (Exception e) {
			return currentPadding;
		}
	}

	static char[][] enhanceImage(char[][] image, String enhancement, char currentPadding) {
		int imageHeight = image.length;
		int imageWidth = image[0].length;
		char[][] enhancedImage = new char[imageHeight + padding][imageWidth + padding];
		// char[][] enhancedImage = padImage(image, currentPadding);
		for (int x = -padding / 2; x < imageWidth + padding / 2; x++) {
			for (int y = -padding / 2; y < imageHeight + padding / 2; y++) {
				String binaryString = "";
				for (int dy = -1; dy <= 1; dy++) {
					for (int dx = -1; dx <= 1; dx++) {
						if (getPixel(image, x + dx, y + dy, currentPadding) == light) {
							binaryString += "1";
						} else {
							binaryString += "0";
						}
					}
				}
				int enhanceIndex = Integer.parseInt(binaryString, 2);
				char enhancedPixel = enhancement.charAt(enhanceIndex);
				enhancedImage[y + 1][x + 1] = enhancedPixel;
			}
		}
		return enhancedImage;
	}

	static int countPixels(char[][] image) {
		int pixels = 0;
		for (int y = 0; y < image.length; y++) {
			for (int x = 0; x < image[y].length; x++) {
				if (image[y][x] == light) {
					pixels++;
				}
			}
		}
		return pixels;
	}
}