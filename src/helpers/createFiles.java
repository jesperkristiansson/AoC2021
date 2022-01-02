package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createFiles {

	public static void main(String[] args) {
		for(int i = 1; i <= 25; i++) {
			String path = "src/day" + i;
			File dir = new File(path);
			dir.mkdir();
			File example = new File(path + "/example.txt");
			File input = new File(path + "/input.txt");
			try{
		        BufferedWriter bwex = new BufferedWriter(new FileWriter(example.getAbsoluteFile()));
		        bwex.write("");
		        bwex.close();
		        BufferedWriter bwin = new BufferedWriter(new FileWriter(input.getAbsoluteFile()));
		        bwin.write("");
		        bwin.close();
		    }
		    catch (IOException e){
		        e.printStackTrace();
		    }
		}
	}
}
