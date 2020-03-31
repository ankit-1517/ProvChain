public class randomString{
	
	//method to generate random string for userNames
	public static String getRS(){
		int len=listProv.r.nextInt(10)+2;
		int[] a=new int[len];
		String s="";
		for(int i=0;i<len;i++){
			s+=(char)(listProv.r.nextInt(26)+97);
		}
		return s;
	}
	
}
