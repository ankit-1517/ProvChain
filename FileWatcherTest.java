import java.util.*;
import java.io.*;

public class FileWatcherTest {
	
	//explained in checkFileOpen class
	
	public static void checkChanges(String fileName, String userName) {
		try{
			String absoluteFilePath = address.fileAddress +fileName+"_"+userName+".txt";
			File f = new File(absoluteFilePath);
			TimerTask task = new FileWatcher(f){
				protected void onChange(File file) {
					if(fileExists(f)){
						String s="File "+fileName+" chaged by "+userName+" at "+datetime.getDate();
						CSP temp=new CSP();
						ArrayList<String> x=new ArrayList<>();
						x.add(userName);
						x.add(fileName+" created by "+userName);
						listProv.prov.add(x);
						listProv.update();
						System.out.println(s);
						temp.addProv(fileName,userName,s);
					}						
				}
			};
			Timer timer = new Timer();
			timer.schedule(task,new Date(),1000);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static boolean fileExists(File f){
		if(f.exists()&&!f.isDirectory())
			return true;
		return false;     
	}
	
	public static void main(String args[]){
		checkChanges("AK","");
	}
}
