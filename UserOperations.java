import java.awt.Desktop;
import java.io.*;
import java.util.*;
import java.nio.file.Files;

public class UserOperations{
	//please change the address in address.java file according to your pc, otherwise itll give errors
	
	//this methods create a file named fileName for user userName. 
	public static int createFile(String fileName,String userName){
		String absoluteFilePath = address.fileAddress+fileName+"_"+userName+".txt";
        File file = new File(absoluteFilePath);
        try{
			if(file.createNewFile()){
				System.out.println(fileName+" file Created");
				return provDB.createNewEntry(fileName+"_"+userName);
			}
			else{ 
				System.out.println(fileName+" already exists");
				return -1;
			}
		}
		catch(Exception e){
			System.out.println("Directory doesn't exist");
			return -1;
		}
	}
	
	//this method deletes a particular file from a users database
	public static void deleteFile(String fileName,String userName){
		File file = new File(address.fileAddress+fileName+"_"+userName+".txt");
		try{
			if(file.delete()){
				System.out.println(fileName+" deleted");
				provDB.deleteProv(fileName+"_"+userName);
			}
			else 
				System.out.println(fileName+" doesn't exist");
		}
		catch(Exception e){
			System.out.println("Directory doesn't exist");
		}
	}
	
	//this methods open a particular file to be editted by the main user of the file. note that editing this won't
	//affect the users who shared or to whom this file is shared
	public static void openFile(String fileName,String userName){
		File file = new File(address.fileAddress+fileName+"_"+userName+".txt");
		try{
			if(!Desktop.isDesktopSupported()){
				System.out.println("Desktop is not supported");
				return;
			}        
			Desktop desktop = Desktop.getDesktop();
			if(file.exists()){ 
				desktop.open(file);			
			}
			else
				System.out.println(fileName+" doesn't exist");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	//copies a file from one user account to a different account upon sharing
	//note that the file is encrypted while sharing is decrypted by the sharedUser by its private key later on
	public static void copyFile(String oldFileName,String newFileName,String mainUser,String sharedUser){
		File from = new File(address.fileAddress+oldFileName+"_"+mainUser+".txt");
		File to =new File(address.fileAddress+newFileName+"_"+sharedUser+".txt");
		try{
			Files.copy(from.toPath(),to.toPath());
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}
