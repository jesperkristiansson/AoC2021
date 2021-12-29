package day1;

import java.io.IOException;
import helpers.Helpers;

public class depth {
	public static void main(String[] args) throws IOException{
		int[] result = Helpers.fileAsInts("src/day1/input.txt");
		int largerNo = 0;
		for(int i = 3; i < result.length; i++) {
			if(sumLastThree(result, i) > sumLastThree(result, i-1)) {
				largerNo++;
			}
		}
		System.out.println(largerNo);
	}
	
	public static int sumLastThree(int[] arr, int n) {
		return arr[n]+arr[n-1]+arr[n-2];
	}
}