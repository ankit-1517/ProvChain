import java.util.*;

public class User{
	//this class is implemented using threads
	private String userName,userHash;//userName and his userHash
	private ArrayList<myFile> files=new ArrayList<>();//list of files owned by him/shared to him
	public User next;//node for next user in linked list
	private userThread thread;// thread class that implements all the user operations
	//constructor to initialise all the stuff
	public User(String userName,String userHash){
		this.userName=userName;
		this.userHash=userHash;
		thread=new userThread(this.userName,this);
	}
	//starts the user thread
	public void startThread(){
		thread.run();		
	}
	//self-explanatory methods
	public String getUserName(){
		return userName;
	}
	
	public ArrayList<myFile> getFiles(){
		return files;
	}
	
	public int getFile(String fileName){
		for(int i=0;i<files.size();i++){
			if(files.get(i).getFileName().equals(fileName))
				return i;
		}
		//System.out.println("File "+fileName+" not found");
		return -1;
	}
	
	public void createFile(String fileName){
		myFile myF=new myFile(fileName,UserOperations.createFile(fileName,userName),userName);
		myF.setFileWatcherTest();
		files.add(myF);
		String prov=fileName+" created by "+userName+" at "+datetime.getDate();
		ArrayList<String> x=new ArrayList<>();
		x.add(userName);
		x.add(fileName+" created by "+userName);
		listProv.prov.add(x);
		listProv.update();
		provDB.addProv(files.get(files.size()-1).getProvIndex(),prov);		
	}
	
	public String getFileNameNew(String fileName){
		int index=getFile(fileName);
		if(index==-1)
			return fileName+"_"+userName;
		for(int i=0;i<files.size();i++)
			if(getFile(fileName+i)==-1)
				return fileName+i+"_"+userName;
		return fileName+files.size()+"_"+userName;
	}
	//called while sharing files. as we said in "demo", A sents to B the encrypted version, later on decrypted by B
	//both encryption and decryption are done by this method
	//to be precise, first encryption, then that methods send that file to , and then calls the decrypt method
	public void encryter(String oldFileName,String newFileName){
		try{
			Encrypt e=new Encrypt();
			e.Encrypting(Rename.add+oldFileName+".txt",Rename.add+newFileName,userName);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//to be called upon sharing
	public String addFile(String fileName,String fromName){
		int index=getFile(fileName);
		int i=0;
		String s;
		if(index==-1){
			myFile file=new myFile(fileName,provDB.createNewEntry(fileName+"_"+userName),userName);
			file.setFileWatcherTest();
			files.add(file);
			String prov=fileName+" shared to "+userName+" at "+datetime.getDate();
			System.out.println(prov);
			provDB.addProv(files.get(files.size()-1).getProvIndex(),prov);
			s=fileName+"_"+userName;
		}
		else{
			for(i=0;i<files.size();i++)
				if(getFile(fileName+i)==-1)
					break;	
			myFile file=new myFile(fileName+i,provDB.createNewEntry(fileName+i+"_"+userName),userName);
			s=fileName+i+"_"+userName;
			file.setFileWatcherTest();
			files.add(file);
			String prov=fileName+" shared to "+userName+" at "+datetime.getDate();
			provDB.addProv(files.get(files.size()-1).getProvIndex(),prov);
		}
		encryter(fileName+"_"+fromName,s);//s include newUserName
		if(index==-1)
			return fileName;
		return fileName+i;
	}
	
	public void editFile(String fileName){
		int index=getFile(fileName); 
		if(index==-1)
			return;		
		UserOperations.openFile(fileName,userName);
	}	
	
	public void shareToUsers(String fileName, ArrayList<String> users){
		int index=getFile(fileName);
		if(index==-1)
			return;
		for(int i=0;i<users.size();i++){
			files.get(index).getSU().addWithCheck(users.get(i));
			String prov=fileName+" shared by "+userName+" to "+users.get(i)+" at "+datetime.getDate();
			ArrayList<String> x=new ArrayList<>();
			x.add(userName);
			String str=fileName+" shared by "+userName+" to "+users.get(i);
			x.add(str);
			System.out.println(str);
			listProv.prov.add(x);
			listProv.update();
			provDB.addProv(files.get(index).getProvIndex(),prov);
			CSP temp=new CSP();
			temp.addFileToUser(userName,users.get(i),fileName);
		}
	}
	
	public void deleteFile(String fileName){
		int index=getFile(fileName);
		if(index==-1)
			return;
		String prov=fileName+" deleted by "+userName+" at "+datetime.getDate();
		provDB.addProv(files.get(index).getProvIndex(),prov);
		ArrayList<String> x=new ArrayList<>();
		x.add(userName);
		x.add(fileName+" deleted by "+userName);
		System.out.println(fileName+" deleted by "+userName);
		listProv.prov.add(x);
		listProv.update();
		files.remove(index);
	}
}
