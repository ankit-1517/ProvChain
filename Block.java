import java.time.*;
import java.time.format.DateTimeFormatter;
public class Block
	{		
	datetime Datetime=new datetime();// variable created to find date time	
	private String Hash="0";// hash value intializing it to 0 but it get modified in the constructor itself
	private String PreviousHash;// value of hash for revious block 
	private String TimeStamp; //as number of milliseconds since 1/1/1970.
	//Block Constructor.
	private MerkelTreeNode Root; 
	private long nunceS;// nunceS is actual nunce  value 
	private long nunceE;// nunceE is created as a aid for doing multithreading 
	public volatile Boolean flag=false;// created as  a aid for doing multithreading
	public int num;// number of block
	public Block(String previoushash,MerkelTreeNode root,int difficulty,int nunceS,int nunceE,int num) {
		//constructor class used to  initalize variables
		Root=root;
		TimeStamp=Long.toString(Datetime.time());
		PreviousHash = previoushash;
		this.nunceS=nunceS;
		this.nunceE=nunceE;
			this.num=num;
		/*while(mineBlock(difficulty)==false&&flag==false){
			nunceS=nunceS+20000000;
			nunceE=nunceE+20000000;
		}
		System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooo"+Blockchain.blockchain.size()+" "+Hash+" "+flag);// size will not be equal to num as block is not added yet
		*/
	
	}
	public void f(int difficulty){
		// created for mining the block
	while(mineBlock(difficulty)==false&&flag==false){
			nunceS=nunceS+20000000;
			nunceE=nunceE+20000000;
		}
	    //System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooo"+Blockchain.blockchain.size()+" "+Hash+" "+flag+" "+nunceS);// size will not be equal to num as block is not added yet;
	   /* if(Blockchain.blockchain.size()>1){
	    	System.out.println("ppppppppppphash value"+Blockchain.blockchain.get(Blockchain.blockchain.size()-1).returnHash());
	    }*/
		if(flag!=true&&num!=0){	
	    if(Thread.currentThread().getName().equals("t1")){
			  try{
			  //	listProv.t2.sleep(3000);
                listProv.b.block.flag=true;
                //System.out.println("i am here");
                //listProv.t2=null;
            }
            catch(Exception e){
            	System.out.println("Exception in block constructor"+num);
            }
		}
		else{
			try{
		   // listProv.t1.sleep(30000);		
            listProv.a.block.flag=true;
            //System.out.println("reached here");
			//listProv.t1=null;
		}
		catch(Exception e){
			System.out.println("Exception in block constructor"+num);
		}
		}
	  }
	}
	public String returnHash()
	{
		return this.Hash;
	}
	public String returnPreviousHash()
	{
		return this.PreviousHash;
	}
	public MerkelTreeNode returnRoot()
	{
		return this.Root;
	}
	public long returnNunceS()
	{
		return this.nunceS;
	}
	public String returnTimeStamp()
	{
		return this.TimeStamp;
	}
	public String calculateHash(){
      mySha256 sha = new mySha256();
      if(Root==null){
      	return sha.getSHA(PreviousHash+nunceS+TimeStamp+0); 
      }
      return sha.getSHA(PreviousHash+nunceS+TimeStamp+Root.getHashValue());
    }
	public String getCurrentTimeWithTimeZone(){
       // System.out.println("-----Current time of a different time zone using LocalTime-----");
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        LocalTime localTime=LocalTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime=localTime.format(formatter);
        //System.out.println("Current time of the day in Delhi: " + formattedTime);
		return formattedTime;
    }
    public Boolean mineBlock(int difficulty) {
    	//mine function it will set the value of hash and nonce
    	String target = new String(new char[difficulty]).replace('\0', '0'); 
		while(Hash.length()<difficulty&&nunceS<=nunceE&&flag==false){
               nunceS++;
               Hash=calculateHash();
		}
		while(!Hash.substring( 0, difficulty).equals(target)&&nunceS<=nunceE&&flag==false) {
			nunceS++;
			Hash = calculateHash();
		}
		if(flag==true){
             return true;
		}
		if(Hash.substring( 0, difficulty).equals(target)){
			System.out.println("Block mined "+num);
			//System.out.println("true");
             return true;
		}
		return false;
	}
}
