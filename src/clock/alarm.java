
package clock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class alarm {
  
    
    public int epoch (String hour, String min, String day, String month) throws ParseException
    {
        String year = "2019";
        String sec = "00";
        String timestamp = (year+"-"+month+"-"+day+" "+hour+ ":"+min+":"+sec);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = sdf.parse(timestamp);
        long epoch = dt.getTime();
        long epochtime = epoch/1000;
        
        
       
        return (int) epochtime;
    }
    
    public int addDay(String day, int epochtime, String minute, String hour, String month) throws ParseException {
        long currentepoch = System.currentTimeMillis()/1000;
        String year = "2019";
        String newDay =day;
        
        if (currentepoch > epochtime)
        {
            
            int dayint = Integer.parseInt(day) + 1;
            newDay = Integer.toString(dayint);
            int updatedEpoch = epoch(hour, minute, newDay, month);
            
            return updatedEpoch;
        }
        return epochtime;
    
    }
    
    
    
    
    
   
    
    
   
}
    
    
    

