
package clock;

import java.io.BufferedWriter;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;




public class alarm  {

    
  
    //converts datetime into epochtime and returns it to the view class
    public int epoch (String hour, String min, String day, String month) throws ParseException
    {
        String year = "2019";
        String sec = "00";
        String timestamp = (year+"-"+month+"-"+day+" "+hour+ ":"+min+":"+sec);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = sdf.parse(timestamp);
        long epoch = dt.getTime();
        long epochtime = epoch/1000;
        
        return (int) epochtime
    ;
        
    }
    
    
    
    //checks whether the alarm the user submitted has already past
    public boolean checkDate(String day, int epochtime, String minute, String hour, String month) throws ParseException {
        boolean past = false;
        long currentepoch = System.currentTimeMillis()/1000;
        if (currentepoch > epochtime)
        {
            past = true;
            
            
            return past;
        }
        return past;
    
    }
    
    //converts epochtime to datetime
    public String epochtodatetime(int epochtime){
       
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        long longepochtime = epochtime;
        
        String formatted = format.format(longepochtime*1000);
        
        System.out.println(formatted);
        return formatted;
    }
    
    //splits the datetime that epochtodatetime() generates into seperate variables
    public String[] epochsplit(String formatted) {
             
        String[] split1 = formatted.split("-");
        String year = split1[0];
        String month = split1[1];
        String day = split1[2];
        
        String[] split2 = day.split(" ");
        day = split2[0];
        
        
        String[] split3 = split2[1].split(":");
        String hour = split3[0];
        String minute = split3[1];
        
        String[] splitarray = {year,month,day,hour,minute};
        
        return splitarray;
        
    
    }
    

    
    
    
   
        

    

        
        
        }
    
            
   
    


    
    
    
    
    
   
    
    
   

    
    
    

