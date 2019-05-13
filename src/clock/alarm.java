
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
    
    public String epochtodatetime(int epochtime){
       
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        long longepochtime = epochtime;
        
        String formatted = format.format(longepochtime*1000);
        
        System.out.println(formatted);
        return formatted;
    }
    
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
    
    public void write(String hour, String minute, String day, String month) {
        
            
    
            String eventBegin = "BEGIN:VEVENT \n";
            String eventEnd =   "END:VEVENT";
            String name = "alarms";
            StringBuilder builder = new StringBuilder();
            builder.append(name);
            builder.append(".ics");

            
            
            
            
            String testExample = "nDTSTART:"+"2019"+month+day+"T170000Z"+"\nDTEND:"+"2019"+month+day+"T170000Z\n";

            try {
                
            File file = new File(builder.toString());

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                
                bw.write(eventBegin);
                bw.write(testExample);
                bw.write(eventEnd);
                

                bw.close();

                System.out.println("Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    
   
        

    

        
        
        }
    
            
   
    


    
    
    
    
    
   
    
    
   

    
    
    

