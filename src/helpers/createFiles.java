package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createFiles {

	public static void main(String[] args) {
		for(int i = 1; i <= 5; i++) {
			String path = "src/day" + i;
			File dir = new File(path);
			File example = new File(path + "/example.txt");
			File input = new File(path + "/input.txt");
			try{
		        BufferedWriter bwin = new BufferedWriter(new FileWriter(example.getAbsoluteFile()));
		        bwin.write("");
		        bwin.close();
		    }
		    catch (IOException e){
		        e.printStackTrace();
		        System.exit(-1);
		    }
		}
	}
}
