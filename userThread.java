import java.util.*;
import java.nio.charset.*;

public class userThread implements Runnable{
	
	private String userName;
	private User user;
	private int x;
	private static CSP csp=new CSP();
	
	public userThread(String userName,User user){
		this.userName=userName;
		this.user=user;
	}
	
	public void run(){
		try{
			//DO NOT change this time, it's just so as to ensure all the users 
			//are added to the database before giving query like share to public
			Thread.sleep(1000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			for(;;){
				x=listProv.r.nextInt(4);//user operation decided randomly
				if(x==0){
					createFile();
				}
				else if(x==1){
					editFile();
				}
				else{
					shareFile();
				}
				//Feel free to change the "10000" below. That's the time in milliseconds the 
				// user waits before making a new operation
				//We kept it to be 10000 bcoz it neither too slow, nor too fast that we can't read anything on cmd
				Thread.sleep(10000);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//self-explanatory methods below
	private void createFile(){
		csp.queries("newFile "+userName+" "+randomString.getRS());
	}
	
	private void deleteFile(){
		if(user.getFiles().size()==0)
			return;
		int x=listProv.r.nextInt(user.getFiles().size());
		csp.queries("deleteFile "+userName+" "+user.getFiles().get(x).getFileName());
	}
	
	private synchronized void editFile(){
		if(user.getFiles().size()==0)
			return;
		int x=listProv.r.nextInt(user.getFiles().size());
		csp.queries("editFile "+userName+" "+user.getFiles().get(x).getFileName());
		while(!checkFileOpen.check(user.getFiles().get(x).getFileName()+"_"+userName));
	}
	
	private synchronized void shareFile(){
		if(user.getFiles().size()==0)
			return;
		int x=listProv.r.nextInt(user.getFiles().size());
		String s="shareFile "+userName+" "+user.getFiles().get(x).getFileName();
		int pub=listProv.r.nextInt(20);
		if(pub==19){
			s+=" public";
			csp.queries(s);
			return;
		}
		ArrayList<String> list=csp.getUserListExcept(userName);
		for(int i=0;i<list.size();i++){
			s+=" "+list.get(i);
		}
		csp.queries(s);
	}
	
}
