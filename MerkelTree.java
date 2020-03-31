import java.util.*;
import java.io.*;
class MerkelTreeNode{
  // implementation is such that one sibling is a transaction and other is hash function and hashed the value of data from two sibling to their parent 
  private 	String Hashvalue;
  private 	MerkelTreeNode Right;// Right child
  private 	MerkelTreeNode Left;// left child
  private 	MerkelTreeNode parent;
  private String userOperation;// operation done by the user
  private String userId;// user which this transactions correspond to
  private MerkelTree tree;//tree t which this merkel tree belongs
  public Boolean isContainUserOperation;//as there are two types of node one that contain useroperation and other that does not contain user operation
  	public MerkelTreeNode(MerkelTreeNode parent,String userOperation,String userId,MerkelTree tree,Boolean a){
		Right=null;
		Left=null;
		this.parent=parent;
        this.userOperation=userOperation;
        this.userId=userId;
        mySha256 obj= new mySha256();
        Hashvalue=obj.getSHA(userId+userOperation);
        this.tree=tree;
        isContainUserOperation=a;
	}
   public String getHashValue(){
        return Hashvalue;
   }
   public MerkelTree getTree(){
   	return tree;
   }
   public MerkelTreeNode getRightChild(){
   	return Right;
   }
   public MerkelTreeNode getLeftChild(){
   	return Left;
   }
   public MerkelTreeNode getParent(){
   	return parent;
   }
   public String getUserId(){
       return userId;
   }
   public String getUserOperation(){
   	return userOperation;
   }
   public void  setHashValue(String Hashvalue){
      this.Hashvalue=Hashvalue;
   }
   public void  setRightChild(MerkelTreeNode Right){
   	this.Right=Right;
   }
   public void  setLeftChild(MerkelTreeNode Left){
   	this.Left=Left;
   }
   public void setParent(MerkelTreeNode parent){
   	this.parent=parent;
   }
  
}
public class MerkelTree{
private String TargetHashValue;
private MerkelTreeNode root;
  public MerkelTree(String TargetHashValue){
    this.root=new MerkelTreeNode(null,TargetHashValue,null,this,false);
    this.TargetHashValue=TargetHashValue;
    }
    public MerkelTreeNode getRoot(){
	  return root;
    }
    public void addTransictions(String userOperation,String userId){
       MerkelTreeNode newNode=new MerkelTreeNode(null,userOperation,userId,this,true);// this contain the node which contains the transactions 
       MerkelTreeNode p= new MerkelTreeNode(null,root.getHashValue(),newNode.getHashValue(),this,false);// this is the node which contain the hash value not the user operation
       p.setLeftChild(root);
       root.setParent(p);
       p.setRightChild(newNode);
       root.setParent(p);
       root=p;
    }
    public MyLinkedList<String> findByUserId(String userId){// find all the transactions corresponding to the user id
	  MyLinkedList<String> list=new MyLinkedList<>();
         MerkelTreeNode temp=root;
       while(temp!=null){
    	/* if(temp.isContainUserOperation==true&&temp.getHashValue().equals(HashValue)){
             list.addLast(temp.getUserOperation());
    	 }*/
    	 if(/*temp.isContainUserOperation==false&&*/temp.getRightChild()!=null){
    	 	//System.out.println(temp.getRightChild().getUserId());
    	 	if(temp.getRightChild().getUserId().equals(userId)){
    	 		list.addLast(temp.getRightChild().getUserOperation());
    	 	}
    	 	temp=temp.getLeftChild();
    	 }
    	 temp=temp.getLeftChild();
        }
        return list;
    }
    
    public String findByHashValue(String HashValue){
    	// assuming that each transictions has different hashvalue
       MerkelTreeNode temp=root;
       while(temp!=null){
    	 /*if(temp.isContainUserOperation==true&&temp.getHashValue().equals(HashValue)){
             return temp.getUserOperation();
    	 }*/
    	 if(/*temp.isContainUserOperation==false&&*/temp.getRightChild()!=null){
    	 	if(temp.getRightChild().getHashValue().equals(HashValue)){
    	 		return temp.getRightChild().getUserOperation();
    	 	}
    	 	temp=temp.getLeftChild();
    	 }
    	 temp=temp.getLeftChild();
        }
        return null;
    }
    public void addTransictionsArray(ArrayList<ArrayList<String>> list){
    	int i;
         for(i=0;i<list.size();i++){
         	addTransictions(list.get(i).get(1),list.get(i).get(0));
         }


    }
     /*  public static void main(String[] args) {
    	MerkelTree tree=new MerkelTree("13550350a8681c84c861aac2e5b440161c2b33a3e4f302ac680ca5b686de48de");
    	tree.addTransictions("i am the boss","254");
    	System.out.println(tree.findByUserId("254").head().getElement());
    	//System.out.println(tree.getRoot().getRightChild().getUserOperation());
    }*/

}