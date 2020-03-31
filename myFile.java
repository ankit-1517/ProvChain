public class myFile{
	
	private myLinkedListSU sharedUsers=new myLinkedListSU();//linked list of all users to whom this file had been shared
	private int provIndex;//index of this file in the provenance database, reduces the time to explicitly search when adding a provenance entry
	private String fileName;//fileName
	private FileWatcherTest fw;//the class to check edits (see related classes for more info)
	private String userName;//user who owns this file
	
	//basic self-explanatory methods below
	public myFile(String fileName, int provIndex,String userName){
		this.fileName=fileName;
		this.provIndex=provIndex;
		this.userName=userName;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public int getProvIndex(){
		return provIndex;
	}
	
	public myLinkedListSU getSU(){
		return sharedUsers;
	}
	
	public void setFileWatcherTest(){
		fw=new FileWatcherTest();
		fw.checkChanges(this.fileName,this.userName);				
	}
	
	public void setProvIndex(int provIndex){
		this.provIndex=provIndex;
	}
	
	public void addSU(String s){
		sharedUsers.addUser(s);
	}
	
	public void deleteUser(String s){
		sharedUsers.deleteUser(s);
	}
	
}
