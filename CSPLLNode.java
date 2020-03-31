public class CSPLLNode{
	
	//node for linked list of Users
	
	private String userName, userHash;
	public CSPLLNode next;
	
	public CSPLLNode(String userName,String userHash){
		this.userName=userName;
		this.userHash=userHash;
		next=null;
	}
	
	public String getUserName(){
		return userName;
	}
	
}
