package clock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

    public class iCal {

        //creates the ics file
        public File createical() throws IOException
        {
            String name = "alarms";
            StringBuilder builder = new StringBuilder();
            builder.append(name);
            builder.append(".ics");
            
            File file = new File(builder.toString());
            
            
      
            return file;
        }
        
        
        //writes the alarm details into the ics file
    public void write(String hour, String minute, String day, String month, File file,int id, String fnew) {
        
            
    
            String eventBegin = "\nBEGIN:VEVENT \n";
            String eventEnd =   "END:VEVENT";
           

            
            
            
            
            String testExample = "UID:"+id+"\nDTSTART:"+"2019"+month+day+"T"+hour+minute+"\nDTEND:"+"2019"+month+day+"T"+hour+minute+"\n";
            
            try {
                
            

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

               //creates an instance of filewrites passing in parameters fnew(filename) and true which means it will append and not overwrite
                FileWriter ics = new FileWriter(fnew,true);
                
                //writes the details to the ics file
                ics.write(eventBegin);
                ics.write(testExample);
                ics.write(eventEnd);
                //bw.write(eventBegin);
                //bw.write(testExample);
                //bw.write(eventEnd);
                
                ics.close();
                //bw.close();

                System.out.println("Done");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    
        //writes the beginning of the ics file
        public String startfile() throws IOException
        {       
                String fnew="alarms.ics";
                FileWriter f2 = new FileWriter(fnew,true);
                String calBegin = "BEGIN:VCALENDAR";
                f2.write(calBegin);
                f2.close();
                return fnew;
        }
        
        //writes the ending of the ics file
        public void endfile(String fnew) throws IOException
        {             
                FileWriter f2 = new FileWriter(fnew,true);
                String calBegin = "\nEND:VCALENDAR\n";
                f2.write(calBegin);
                f2.close();
                
        }
    
        //reads the data from the ical file and inserts all the alarms into the queue
        public int[] readIcal() throws FileNotFoundException, IOException, ParseException, QueueOverflowException
        {
            //sets the path of the ics file
            String path = "alarms.ics";
            
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            alarm a = new alarm();
            int numberoflines = nooflines();
            
            //creates a string array that is the length = however many lines the file has
            String[] textData = new String[numberoflines];
            
            int i;
            
            //creates a string array that is the length = however many lines the file has(Because that gives you the number of alarms)
            int[] epochtime = new int[numberoflines/5];
            System.out.println(Arrays.toString(epochtime));
            
            int index =0;
            System.out.println("before" + index);
            
            //for loop which goes through each line of the file and inserting the data into the textData array
            for (i=0; i< numberoflines; i++)
            {
                textData[i] = br.readLine();
                System.out.println(textData[i]);
                
                if (textData[i].contains("DTSTART")){
                    int line = i;
                    System.out.println("line :" + line);
                    System.out.println("Index :" + index);
                    String month = textData[line].charAt(12) + "" + textData[line].charAt(13);
                    
                    //sets the day variable using data from the ics file    
                    String day = textData[line].charAt(14) + "" + textData[line].charAt(15);
         
                    //sets the hour variable using data from the ics file
                    String hour = textData[line].charAt(17) + "" + textData[line].charAt(18);
         
                    //sets the min variable using data from the ics file
                    String min = textData[line].charAt(19) + "" + textData[line].charAt(20);
              
                    //sets the epochtime[currentindex] to the epochtime returned from the epoch method
                    epochtime[index] = a.epoch(hour, min, day, month);
                    
                    index = index +1;
                    
                    
                }
                
                
            }
            
                

             
            return epochtime;
            
       
        }
        
        
        //simple class that returns the number of lines found in the ics file
        public int nooflines() throws IOException 
        {
            String path = "alarms.ics";
            FileReader file_to_read = new FileReader(path);
            BufferedReader bf = new BufferedReader(file_to_read);
            
            String aLine;
            int nooflines = 0;
            //loops through for however long readLine != null
            while ((aLine = bf.readLine()) != null) {
                //adds one to the counter
                nooflines++;
            }
            bf.close();
            return nooflines; 
        }
    }
    