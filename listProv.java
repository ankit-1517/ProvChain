import java.util.*;
import java.io.*;
public class listProv{
	//0 stores userName and 1 provSentence
	public static ArrayList<ArrayList<String>> prov=new ArrayList<>();// static array whuch gets during the blockmining;
	public static Random r=new Random();
    public static Thread t1;// thread for miner one
    public static Thread t2;// thread for miner two
    static{
    	t1=null;
    	t2=null;
    }
    public static Miners a;// instance for first miner
    public static Miners b;//instance for second miner
	public static int num;// number of block
	static {// intializing the variables
		num=1;
		a=null;
		b=null;
	}
	public static  synchronized void update(){
		// code for mining the block
		if(t1!=null&&t2!=null){
           
			//System.out.println(t1.isAlive()+"is t1 alive");
			if(!t1.isAlive()||!t2.isAlive()){
				// it means miner1 has mined the block so following code to stop the thread of miner 2 otherwise it will take some computing power
				if(!t1.isAlive()&&a!=null&&listProv.a.block.flag!=true){
					Blockchain.blockchain.add(a.block);
					System.out.println("chain is valid ?"+ProvenanceAuditor.isChainValid());
				   
					//System.out.println(num-1+"eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
					
					try{
					  //b.block.notifier=1;
					   listProv.b.block.flag=true;

					  t2=null;
					  t1=null;
				    }

				    catch(Exception e){
                       System.out.println("exception in listProv");
				    }
				    a=null;
					b=null;
				}
				else if(!t2.isAlive()&&b!=null&&listProv.b.block.flag!=true){
					// it means miner2 has mined the block
					Blockchain.blockchain.add(b.block);
					System.out.println(" chain is valid ? "+ProvenanceAuditor.isChainValid());
					try{
					//a.block.notifier=1;
				    listProv.a.block.flag=true;
					
					t1=null;
					t2=null;

					//System.out.println(num-1+"e111111ee1111eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				   }
				   catch(Exception e){
                        System.out.println("exception in list prov");
				   }
				   b=null;
				   a=null;
				}
			
		
		       if(prov.size()==0){
			        return;
			    }			
			 String s=r.nextDouble()*10000+"";
			 s=mySha256.getSHA(s);
			 MerkelTree temp=new MerkelTree(s);// creating merkel tree with target hash value			
			 temp.addTransictionsArray(prov);// adding transactions in the merkel tree
			 String PreviousHash="";// intialising the previous hash value
			 if(Blockchain.blockchain.size()>1){
				PreviousHash=Blockchain.blockchain.get(Blockchain.blockchain.size()-1).returnHash();// taking previous hash
				//System.out.println("lllllllllllllllllllllllllllllllllllllllllllPrevious hash"+PreviousHash);
			 }
			 a=new Miners(PreviousHash,temp.getRoot(),4,0,10000000,num);
             t1 = new Thread(a);
             t1.setName("t1"); 
             t1.start();// miner 1 started mining
             b=new Miners(PreviousHash,temp.getRoot(),4,10000000,20000000,num);
             num++; 
             t2= new Thread(b);
             t2.setName("t2"); // miner 2 started mining
             t2.start();
             prov.clear();// to delete the provenance stored in the array as it is already updated in the blockchain
             
		    }
		    return;
	    }
	else{// if ayny one of the thread is already null we have to create a  thread and start mining
       if(prov.size()==0){
				/*t1=new Thread();
				  t2=new Thread();*/
		 // means there is no entry in array list
				return;
			}			
			String s=r.nextDouble()*10000+"";
			s=mySha256.getSHA(s);
			MerkelTree temp=new MerkelTree(s);			
			temp.addTransictionsArray(prov);
			String PreviousHash="0";
             
			PreviousHash=Blockchain.blockchain.get(Blockchain.blockchain.size()-1).returnHash();
			a=new Miners(PreviousHash,temp.getRoot(),4,0,10000000,num);
            t1 = new Thread(a);
            t1.setName("t1"); 
            t1.start();// miner 1 started mining
            b=new Miners(PreviousHash,temp.getRoot(),4,10000000,20000000,num);
            num++;
            t2= new Thread(b);//miner 2 started mining
            t2.setName("t2"); 
            t2.start();
            prov.clear();// to delete the provenance stored in the array as it is already updated in the blockchain
	    }

	}
	
	public static void f(){
		while(true){update();}
	}
	
}
