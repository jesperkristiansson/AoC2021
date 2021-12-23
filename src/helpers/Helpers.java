package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helpers {
	public static String[] fileAsStrings(String fileName) throws IOException{
		String[] result;
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			result = lines.collect(Collectors.toList()).toArray(new String[0]);
		}
		return result;
	}
	
	public static int[] fileAsInts(String fileName) throws IOException{
		int[] result;
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			result = lines
					.mapToInt(s -> Integer.parseInt(s))
					.toArray();
		}
		return result;
	}
}
