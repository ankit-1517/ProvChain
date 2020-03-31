import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*; 

public class datetime{
	
	public static String getDate () //this method returns date and time in the format dd-MM-yyyy HH:mm:ss
    { 
        Date d1 = new Date(); 
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate = dateFormat.format(d1);
        return strDate;
	}
	public static long time() // this method returns the time in seconds since 1/1/1970.
	{
		 Calendar calendar1 = Calendar.getInstance();
		 long timeinms=calendar1.getTimeInMillis();
		 return timeinms*1000;
	 }
}

