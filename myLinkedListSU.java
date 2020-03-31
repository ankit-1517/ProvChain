public class myLinkedListSU{
	
	//linked list of users with whom a particular file has been shared
	
	private SULLNode head=null;
	private SULLNode endPointer=null;
	//method to add a user
	public void addUser(String userName){
		if(search(userName)==true){
			System.out.println("File already shared with "+userName);
			return;
		}
		String s=mySha256.getSHA(userName);
		SULLNode node;
		if(s!=null){
			node=new SULLNode(s);
		}
		else{
			System.out.println("Invalid user to share the file with!");
			return;
		}
		if(head==null){
			head=node;
			endPointer=head;
			return;
		}
		endPointer.next=node;
		endPointer=node;
	}
	//method to add a user only if it isn't akready present
	public void addWithCheck(String userName){
		if(search(userName)==false)
			addUser(userName);
	}
	//method to check if a user is present or not
	public boolean search(String userName){
		if(head==null)
			return false;
		SULLNode temp=head;
		for(;temp!=null;temp=temp.next){
			if(temp.getSU().equals(userName))
				return true;
		}
		return false;
	}
	//delete a user from the list
	public void deleteUser(String userName){
		if(head==null){
			System.out.println("File not shared with "+userName);
			return;
		}
		if(head.getSU().equals(userName)){
			head=head.next;
			return;
		}
		SULLNode temp=head.next;
		SULLNode temp2=head;
		for(;temp!=null;temp=temp.next){
			if(temp.getSU().equals(userName)){
				temp2.next=temp.next;
				return;
			}
			temp2=temp;
		}
		System.out.println("File not shared with "+userName);			
	}
	
}
