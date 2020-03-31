public class Main implements Runnable{
	
	//this class is called by projectMain class. if we consider a hierarchy, it's above everyone except projectMain
	//Further, it implements users
	
	private static CSP csp=new CSP();
	
	public void run(){
		csp.queries("newUser "+randomString.getRS());
	}
	
}
