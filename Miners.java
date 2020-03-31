import java.util.*;
import java.io.*;
public class Miners implements Runnable{
 // Blockchain blockchain= new Blockchain();
  Block block;
  String PreviousHash;
  MerkelTreeNode root;
  int difficulty;
  int nunceS;// actual value of nunce
  int nunceE;// value used in multithreading
  int num;// number of block which miner is mining
  public Miners(String PreviousHash,MerkelTreeNode root,int difficulty,int nunceS,int nunceE,int num){
  	 this.PreviousHash=PreviousHash;
  	 this.root=root;
  	 this.difficulty=difficulty;
  	 this.nunceS=nunceS;
  	 this.nunceE=nunceE;
  	 this.num=num;
  }
  public void run(){
    // here to start the mining f function is called 
     block = new Block(PreviousHash,root,difficulty,nunceS,nunceE,num);
		block.f(difficulty);
  }
}
