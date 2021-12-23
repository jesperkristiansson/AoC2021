package day1;

import java.io.IOException;
import helpers.Helpers;

public class depth {
	public static void main(String[] args) throws IOException{
		int[] result = Helpers.fileAsInts("depth.txt");
		int largerNo = 0;
		for(int i = 3; i < result.length; i++) {
			if(sumThree(result, i) > sumThree(result, i-1)) {
				largerNo++;
			}
		}
		System.out.println(largerNo);
	}
	
	public static int sumThree(int[] arr, int n) {
		return arr[n]+arr[n-1]+arr[n-2];
	}
}
