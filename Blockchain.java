import java.util.ArrayList;

public class Blockchain {
	
	static ArrayList<Block> blockchain = new ArrayList<Block>();
	//Block genesis=new Block("0",null,0,0,100000,1);
	
	public Blockchain(){
		//blockchain.add(genesis); 
	}
	
	public Boolean isChainValid() {
		// to check whether the blockchain is valid or not
		Block currentBlock; 
		Block previousBlock;
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			if(!currentBlock.returnHash().equals(currentBlock.calculateHash()))
				return false;
			if(!previousBlock.returnHash().equals(currentBlock.returnPreviousHash()))
				return false;
		}
		return true;
	}
	
}

