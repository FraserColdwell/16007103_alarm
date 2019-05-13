package clock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.ArrayList;

    public class iCal {

        

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
    
    
        public void readIcal()
        {
            
        }
    }
    