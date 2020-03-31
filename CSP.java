import java.util.*;

public class CSP{
	
	private static myLinkedListCSP users=new myLinkedListCSP();//linked list containing all of its users
	private static GenerateKeys gk=new GenerateKeys();//instance of GenerateKeys class so as to generate private and public keys for each user
	
	//add a user to database
	public void addUser(String userName){
		users.addUser(userName);
	}
	
	//get user(instance of user class) given his userName
	private User getUser(String s){
		User temp=users.getUser(s);
		if(temp==null){
			System.out.println("User "+s+" doesn't exist");
			return null;
		}
		return temp;
	}
	
	//used when a user shares his file to public
	//except is there since we won't add it to himself
	public ArrayList<String> getUserListExcept(String except){		
		return users.getULE(except);		
	}
	
	//add a given file to a given user, called upon sharing files
	public void addFileToUser(String fromName,String toName,String fileName){
		User temp=getUser(toName);
		if(temp==null)
			return;
		String s=temp.addFile(fileName,fromName);
		//UserOperations.copyFile(fileName,s,fromName,toName);		
	}
	
	//add provenance entry to provenance databse
	public void addProv(String fileName, String userName, String prov){
		User user=users.getUser(userName);
		if(user==null)
			return;
		int index=user.getFile(fileName);
		if(index==-1)
			return;
		myFile f=user.getFiles().get(index);
		provDB.addProv(f.getProvIndex(),prov);
	}
	
	public void queries(String query){
		System.out.println(query);
		try{
			String s[] = query.split(" "); 
			//add a new user to database
			if(s[0].equals("newUser")){
				gk.generator(s[1]);
				users.addUser(s[1]);
				System.out.println("User "+s[1]+" added");
				users.getUser(s[1]).startThread();
				return;
			}
			//add a new file of given name to a given user
			else if(s[0].equals("newFile")){
				User temp=getUser(s[1]); 
				if(temp==null)
					return;
			    temp.createFile(s[2]);				
			}
			//deletes a file owned by a given user
			else if(s[0].equals("deleteFile")){
				User temp=getUser(s[1]);
				if(temp==null)
					return;	
				temp.deleteFile(s[2]);			
			}
			//open a file of given name for editting by a user of given userName
			else if(s[0].equals("editFile")){
				User temp=getUser(s[1]);
				if(temp==null)
					return;	
				temp.editFile(s[2]);
			}
			//share a given file, currently owned by a given user
			//if query is public, shared to everyone
			//else, shared only to specified users
			else if(s[0].equals("shareFile")){
				User temp=getUser(s[1]);
				if(temp==null)
					return;
				ArrayList<String> SU=new ArrayList<String>();
				if(s.length==3){
					System.out.println("No users given to share the file with");
					return;
				}
				if(s.length==4&&s[3].equals("public")){
					SU=users.getAllUsers();
					for(int i=0;i<SU.size();i++){
						if(SU.get(i).equals(s[1])){
							SU.remove(i);
							break;
						}
					}
					if(SU.size()==0){
						System.out.println("No other users right now");
						return;
					}
					temp.shareToUsers(s[2],SU);
				}
				else{
					for(int i=3;i<s.length;i++)
						SU.add(s[i]);
					temp.shareToUsers(s[2],SU);
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
