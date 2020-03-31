import java.util.*;

public class myLinkedListCSP{
	
	//linked list of users present currently. implemented in CSP(cloud service provider) class
	//just has basic linked list methods
	
	private User head=null;
	private User endPointer=null;
	
	public synchronized void addUser(String userName){
		if(search(userName)==true){
			System.out.println("Username "+userName+" already taken");
			return;
		}
		String s=mySha256.getSHA(userName);
		User node;
		if(s!=null){
			node=new User(userName,s);			
		}
		else{
			System.out.println("Invalid username. Please try a different one");
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
	
	public boolean search(String userName){
		if(head==null)
			return false;
		User temp=head;
		for(;temp!=null;temp=temp.next){
			if(temp.getUserName().equals(userName))
				return true;
		}
		return false;
	}
	
	public User getUser(String userName){
		if(head==null)
			return null;
		User temp=head;
		for(;temp!=null;temp=temp.next){
			if(temp.getUserName().equals(userName))
				return temp;
		}
		return null;
	}
	
	//used when a user shares his file to public
	//except is there since we won't add it to himself
	public ArrayList<String> getULE(String except){		
		ArrayList<String> list=new ArrayList<>();
		User temp=head;
		for(;temp!=null;temp=temp.next){
			int x=listProv.r.nextInt(3);
			if(x==2){
				if(!temp.getUserName().equals(except))
					list.add(temp.getUserName());
			}
		}
		return list;
	}
	
	public ArrayList<String> getAllUsers(){
		ArrayList<String> s=new ArrayList<>();
		User temp=head;
		for(;temp!=null;temp=temp.next){
			s.add(temp.getUserName());
		}
		return s;
	}
	
	public void deleteUser(String userName){
		if(head==null){
			System.out.println("User "+userName+" doesn't exist");
			return;
		}
		if(head.getUserName().equals(userName)){
			head=head.next;
			return;
		}
		User temp=head.next;
		User temp2=head;
		for(;temp!=null;temp=temp.next){
			if(temp.getUserName().equals(userName)){
				temp2.next=temp.next;
				return;
			}
			temp2=temp;
		}
		System.out.println("User "+userName+" doesn't exist");
	}
	
}
