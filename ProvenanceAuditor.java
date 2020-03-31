import java.util.*;
import java.io.*;
public class ProvenanceAuditor{
  // it is called whenever a Block is added  in the blockchain 
   public static synchronized Boolean isChainValid(){
     Blockchain bc= new Blockchain();
     return bc.isChainValid();
  }
  // this method is not used as such can be used but with some changes
   /*public static synchronized Boolean isProvenanceAdded(String provenance,String userId){
        ArrayList<Block>blockchain=Blockchain.blockchain;
         mySha256 obj= new mySha256();
        String hashValue=obj.getSHA(userId+provenance);
        String time=Long.toString(new datetime().time());
        int i=blockchain.size()-1;
        while(i>=0&&Integer.parseInt(time)-Integer.parseInt(blockchain.get(i).returnTimeStamp())<=600){
        	Block block=blockchain.get(i);
        	MerkelTreeNode temp=block.returnRoot();
        	MerkelTree tree=block.returnRoot().getTree();
          if(tree.findByHashValue(hashValue)==null){
                return false;
          }
          while(temp!=null){
          	if(temp.getLeftChild()==null){
          		break;
          	}
          	temp=temp.getLeftChild();
          }
        Boolean flag=true;
        mySha256 sha=new mySha256();
        while(flag==false){
        	if(temp.getParent()==null){
        		break;
        	}
           if(!sha.getSHA(temp.getHashValue()+temp.getParent().getRightChild().getHashValue()).equals(temp.getParent().getHashValue())){
           	return false;
           }
           temp=temp.getParent();
        }
          i--; 

        }
        return true;
        
   }*/


}