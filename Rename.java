import java.io.*;

public class Rename{
	
	public static String add=address.fileAddress;
	//this method is used as:-
	//suppose a sends file f to b
	//it is sent as f_encrypted
	//when decrypting, we obviosuly want the file name to be same, just that's why
	public static void rename(String oldName,String newName){
		try {
			File f=new File(add+oldName);
			File newfile=new File(add+newName);
			if(f.renameTo(newfile)){
				return;
			}
        } 
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}
