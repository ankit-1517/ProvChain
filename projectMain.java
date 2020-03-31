public class projectMain{
	
	//this is the main class that "controls" everything
	
	public static void main(String args[]){
		//default block
		Block genesis=new Block("0",null,0,0,1000000000,0);
		genesis.f(0);
	    Blockchain.blockchain.add(genesis);
	    System.out.println("first block added");
	    //4 threads to make users, because we don't know how much time would generating users take
	    //it may even never make new user, there's probability of everything :) 
		Thread t1=new Thread(new Main());
		t1.start();
		Thread t2=new Thread(new Main());
		t2.start();
		Thread t3=new Thread(new Main());
		t3.start();
		Thread t4=new Thread(new Main());
		t4.start();
		//statements add new users probabilistically
		try{
			for(;;){
				if(listProv.r.nextInt(5)==4){
					Thread t=new Thread(new Main());
					t.start();
				}	
				Thread.sleep(3000+listProv.r.nextInt(3000));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
