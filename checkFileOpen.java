import java.awt.Desktop;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.*;

/*this class, along fileWatcherTest is used to know when is a particular file editted
 * As soon as we create a file, an instance of this class is attached to the file
 * Whenever the file is modified, the corresponding provenance can be seen on the CMD screen
 * It's also added to the provenace database*/

public class checkFileOpen{
	
	public static boolean check(String fileName){
		String filePath = Rename.add+fileName+".txt";	
		File file = new File(filePath); 
        File sameFileName = new File(filePath);
		if(file.renameTo(sameFileName)){
			return true; 
		}
		return false;
	}
	
	public static void main(String args[]){
		while(!check("nnr_gahkdmpymxq")){System.out.println("OPEN");};
		System.out.println("CLOSED");
	}
	
}
