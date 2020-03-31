import java.util.*;

public class provDB{
	
	//this is provenance database, that stores provenances as strings
	//arraylist for storin prov.
	private static ArrayList<ArrayList<String>> prov=new ArrayList<ArrayList<String>>();
	private static int size=0;
	
	//add a new statement to database
	public static void addProv(int index, String provenance){
		ArrayList<String> list=new ArrayList<String>();
		list=prov.get(index);
		list.add(provenance);
		return;
	}
	
	//wrote just because we thought we might use it, but never came into use
	public static void deleteProv(String fileName){
		int i=0;
		ArrayList<String> list=new ArrayList<String>();
		for(;i<size;i++){
			list=prov.get(i);
			if(list.get(0).equals(fileName)){
				prov.remove(i);
				size--;
				return;
			}				
		}
	}
	
	public static ArrayList<String> returnProv(String fileName){
		int i=0;
		ArrayList<String> list=new ArrayList<String>();
		for(;i<size;i++){
			list=prov.get(i);
			if(list.get(0).equals(fileName))
				return list;
		}
		return null;
	}
	
	public static int createNewEntry(String fileName){
		ArrayList<String> list=new ArrayList<String>();
		list.add(fileName);
		prov.add(list);
		size++;
		return size-1;
	}	
		
}
