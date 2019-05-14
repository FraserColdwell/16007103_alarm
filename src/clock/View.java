package clock;



import javax.swing.JFrame;


import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Arrays;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.ArrayList;



public class View implements Observer {
    
    ClockPanel panel;
    JLabel timebetweenlbl = new JLabel();
    
    public int priority;
    
    //creates instance of SortedArrayPriorityQueue
    public SortedArrayPriorityQueue<Object> q = new SortedArrayPriorityQueue<>(20);
    //creates instance of alarm
    alarm a = new alarm();
    //creates instance of iCal
    iCal iCal = new iCal();
    
    
    
    public View(Model model) throws QueueUnderflowException {
        JFrame frame = new JFrame();
        
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Start of border layout code
        
        
        
        
        Container pane = frame.getContentPane();
        
        //creates setalarm button at the top of the panel
        JButton setalarm = new JButton("Set Alarm");
        pane.add(setalarm, BorderLayout.PAGE_START);
        
        panel.setPreferredSize(new Dimension(300, 350));
        pane.add(panel, BorderLayout.CENTER);
        
        //event listener for set alarm button that opens up a jframe and allows users t enter the alarm details
        setalarm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
                final JFrame addsetalarmpanel = new JFrame();
                String hour[]={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
                String minute[]={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"};
                String day[]={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                String month[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
                
                
                JComboBox hr = new JComboBox(hour);
                JComboBox min = new JComboBox(minute);
                JComboBox daycb = new JComboBox(day);
                JComboBox monthcb = new JComboBox(month);
                
                
                JLabel hourlbl = new JLabel("Hour");
                JLabel minlbl = new JLabel("Minute");
                JLabel daylbl = new JLabel("Day");
                JLabel monthlbl = new JLabel("Month");
                
                JButton set = new JButton("Set Alarm");
                addsetalarmpanel.add(set, BorderLayout.PAGE_START);
                
                //simple back button that disposes of the current frame
                JButton back = new JButton("Back");
                back.setBounds(140, 280, 100, 20);
                addsetalarmpanel.add(back);
                            
                back.addActionListener(new ActionListener(){
                
                    public void actionPerformed(ActionEvent e)
                            {
                      
                                addsetalarmpanel.dispose();
                       
                       
                            }
                            });
                
                hourlbl.setBounds(100, 30, 40, 20);
                minlbl.setBounds(100, 80, 40, 20);
               
                hr.setBounds(100, 50,180,20);
                min.setBounds(100,100, 180, 20);
                
                daylbl.setBounds(100, 130, 40, 20);
                daycb.setBounds(100, 150, 180,20);
                
                monthlbl.setBounds(100, 180, 40, 20);
                monthcb.setBounds(100, 200, 180, 20);
                
                set.setBounds(140, 250, 100, 20);
                
                addsetalarmpanel.add(hr);
                addsetalarmpanel.add(min);
                addsetalarmpanel.add(daycb);
                addsetalarmpanel.add(monthcb);
                addsetalarmpanel.add(hourlbl);
                addsetalarmpanel.add(minlbl);
                addsetalarmpanel.add(daylbl);
                addsetalarmpanel.add(monthlbl);
                
                addsetalarmpanel.setLayout(null);
                addsetalarmpanel.setSize(400,500);
                
                addsetalarmpanel.setVisible(true);
                
                 
                //Set alarm button that deals with actually inserting the alarm into the priorityqueue
                set.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        //gets the values from the comboboxes
                        String hour = String.valueOf(hr.getSelectedItem());
                        String minute = String.valueOf(min.getSelectedItem());
                        String day = String.valueOf(daycb.getSelectedItem());
                        String month = String.valueOf(monthcb.getSelectedItem());
                        
                        //combines the values into a time variable
                        String time = hour+ ":" +  minute + ":" + day + "" + month;
                        
                        try {
                            //converts datetime to epochtime
                            int epochtime = a.epoch(hour, minute, day, month);
                            //checks to make sure date hasn't already past
                            boolean past = a.checkDate(day, epochtime, minute, hour, month);
                            if (past == false)
                            {
                                //adds alarm to the queue
                                q.add(time, epochtime);
                                priority = q.returnPriority();
                               
                                
                              

                            //alerts user alarm has successfully been set
                            JDialog t = new JDialog(frame, "Alarm Set For : " + time + " On: " + day + "," + month, true);
                            t.setLocationRelativeTo(frame);
                            t.setSize(300,20);
                            t.setVisible(true);
                            
                            addsetalarmpanel.dispose();
                            }
                            else
                            {
                                //input validation asks users to enter valid information
                                addsetalarmpanel.dispose();
                                JDialog t = new JDialog(frame, "Can't set an alarm that has already past");
                                t.setLocationRelativeTo(frame);
                                t.setSize(500,20);
                                t.setVisible(true);
                                
                            }
                            
                            
                            
                            
                            
             
                        } catch (ParseException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (QueueOverflowException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);}
                        
                        
                    }
                    
            });
        }});
        
       
         
       
        
    
         //creates button on the left of the screen which loads alarms from the ics file
        JButton viewalarms = new JButton("Load Alarms");
        pane.add(viewalarms, BorderLayout.LINE_START);
        
        //when clicked this event listener gets an array containing all the datetimes on the file, coverts them into epoch and inserts them into the queue
        viewalarms.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                      
                       
                           
                            
                            int i =0;
                            
                        try {
                            int [] epochtime = null;
                            //runs the readIcal method which returns an array of epochtimes
                            epochtime = iCal.readIcal();
                            
                            //loops through each index of the epochtime array and inserts it's values into the queue
                            for (i=0; i<=epochtime.length-1;i++){
                                
                                String converted = a.epochtodatetime(epochtime[i]);
                               
                                String[] seperateditems = a.epochsplit(converted);
                                String hour = seperateditems[3];
                                String minute = seperateditems[4];
                                String day = seperateditems[2];
                                String month = seperateditems[1];
                                    
                                String time = hour+ ":" +  minute + ":" + day + ":" + month;
                                q.add(time, epochtime[i]);
                            }
                            ;
                            
                        } catch (IOException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (QueueOverflowException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                       
                       
                        
                    }
                    
            });
    
                   
        
        //creates button which allows users to view and or edit their existing alarms
        JButton editalarms = new JButton("View or Edit Alarms");
        pane.add(editalarms, BorderLayout.PAGE_END);
         
        //when clicked this method creates a new jframe that displays a combobox containing all their alarms
        editalarms.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                       final JFrame editalarm = new JFrame();
                       editalarm.setSize(400,500);
                       editalarm.setLayout(null);
                       
                       //int[] alarmarray = new int[2];
                       int [] alarmarray = q.returnPriorityLoop();
                       
                       int length = q.count();
                       System.out.println("count");
                       System.out.println(length);
                       
                       String[] datetimearray = new String[length+1];
                       String datetime = null;
                       
                       for (int i=0; i<=q.count();i++)
                        {
                            int currentepochtime = alarmarray[i];
                            
                            
                            System.out.println("Current epoch "+ currentepochtime);
                            datetime = a.epochtodatetime(currentepochtime);
                            
                            datetimearray[i] = datetime;
                              
                        }
                            
                            JLabel datetimelbl = new JLabel("Select Alarm to Edit");
                            datetimelbl.setBounds(100, 30, 160, 20);
                            editalarm.add(datetimelbl);
                            
                            JComboBox datetimecb = new JComboBox(datetimearray);
                            datetimecb.setBounds(100,50, 200, 20);
                            editalarm.add(datetimecb);
                            
                            JButton alarmselected = new JButton("EditAlarm");
                            alarmselected.setBounds(100, 90, 100, 20);
                            editalarm.add(alarmselected);
                            
                            JButton back = new JButton("Back");
                            back.setBounds(100, 120, 100, 20);
                            editalarm.add(back);
                            
                            //simple back button which disposes current jframe
                            back.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                      
                                editalarm.dispose();
                       
                        
                            }
                    
            });
                            
                            //simple delete button which deletes the selected alarm
                            JButton delete = new JButton("Delete");
                            delete.setBounds(200, 90, 100, 20);
                            editalarm.add(delete);
                           
                            
                            delete.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                                    String alarmtodelete = String.valueOf(datetimecb.getSelectedItem()); 
                                    System.out.println("deleting this alarm");
                                    System.out.println(alarmtodelete);
                                    String[] seperateditems = a.epochsplit(alarmtodelete);
                                    String hour = seperateditems[3];
                                    String minute = seperateditems[4];
                                    String day = seperateditems[2];
                                    String month = seperateditems[1];
                                    
                                    String time = hour+ ":" +  minute + ":" + day + ":" + month;
                                    
                                    
                                try {
                                    System.out.println("time");
                                    System.out.println(hour+ minute+ day+ month);
                                    int epochtime = a.epoch(hour, minute, day, month);
                                    System.out.println(epochtime);
                                    q.remove(epochtime);
                                    editalarm.dispose();
                                    
                                } catch (ParseException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (QueueUnderflowException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    
                                
                                    
                                    
                                    
                                    
                                    
                       
                                    
                        
                            }
                    
            });
                            
                            
                            
                            editalarm.setVisible(true); 
                            
                            //This button takes the alarm the user selected to edit and opens a new jframe where they are able to edit it's details
                            alarmselected.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                             
                            final JFrame editframe = new JFrame();
                            editframe.setSize(400,500);
                            editframe.setLayout(null);
                            
                            String alarmtoedit = String.valueOf(datetimecb.getSelectedItem());  
                            
                            //converts the datetime into an array containing the month, day, hour and minute
                            String[] seperateditems = a.epochsplit(alarmtoedit);
                            
                            
                            //creates 4 combo boxes who's default value is the previously set details
                            String hour[]={seperateditems[3],"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
                            String minute[]={seperateditems[4],"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"};
                            String day[]={seperateditems[2],"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                            String month[]={seperateditems[1],"01","02","03","04","05","06","07","08","09","10","11","12"};
                            
                            
                            
                            JComboBox hr = new JComboBox(hour);
                            JComboBox min = new JComboBox(minute);
                            JComboBox daycb = new JComboBox(day);
                            JComboBox monthcb = new JComboBox(month);
                            
                            hr.setBounds(100, 150,180,20);
                            min.setBounds(100,200, 180, 20);
                            monthcb.setBounds(100, 250, 180, 20);
                            daycb.setBounds(100, 300, 180,20);
                            
                            editframe.add(hr);
                            editframe.add(min);
                            editframe.add(daycb);
                            editframe.add(monthcb);
                            
                            JLabel hourlbl = new JLabel("Hour");
                            JLabel minlbl = new JLabel("Minute");
                            JLabel daylbl = new JLabel("Day");
                            JLabel monthlbl = new JLabel("Month");
                            
                            hourlbl.setBounds(100, 130, 40, 20);
                            minlbl.setBounds(100, 180, 60, 20);
                            daylbl.setBounds(100, 280, 40, 20);
                            monthlbl.setBounds(100, 230, 40, 20);
                            
                            editframe.add(hourlbl);
                            editframe.add(minlbl);
                            editframe.add(daylbl);
                            editframe.add(monthlbl); 
                            
                            JButton update = new JButton("Update Alarm");
                            update.setBounds(100, 320, 100, 20);
                            editframe.add(update);
                            
                            JButton back = new JButton("Back");
                            back.setBounds(100, 100, 100, 20);
                            editframe.add(back);
                            
                            //simple back button that disposes of the current jframe
                            back.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                                editframe.dispose();
                                editalarm.setVisible(true);
                            }
                    
            });
                                //takes the updated details from the comobo boxes
                                String hour1 = String.valueOf(hr.getSelectedItem());
                                String minute1 = String.valueOf(min.getSelectedItem());
                                String day1 = String.valueOf(daycb.getSelectedItem());
                                String month1 = String.valueOf(monthcb.getSelectedItem());
                                
                                
                                try {
                                    //removes the editted alarm
                                    int epochtime = a.epoch(hour1, minute1, day1, month1);
                                    q.remove(epochtime);
                                } catch (ParseException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (QueueUnderflowException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                
                            //update button reinserts the alarm using the updated details
                            update.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                                
                        
                                
                                String time = hour+ ":" +  minute + ":" + day + ":" + month;
                                System.out.println("time");
                                   System.out.println(time);     
                                
                                    //gets updated details from the comboboxes
                                    String hour = String.valueOf(hr.getSelectedItem());
                                    String minute = String.valueOf(min.getSelectedItem());
                                    String day = String.valueOf(daycb.getSelectedItem());
                                    String month = String.valueOf(monthcb.getSelectedItem());
                                    
                                    
                                try {
                                    //converts to epochtime
                                    int epochtime = a.epoch(hour, minute, day, month);
                                    //inserts into the queue
                                    q.add(time, epochtime);
                                } catch (ParseException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (QueueOverflowException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    
                                
                                editframe.dispose();
                                
                                JDialog t = new JDialog(frame, "Alarm Edited");
                                t.setLocationRelativeTo(frame);
                                t.setSize(300,20);
                                t.setVisible(true);
                            }
                    
            });
                            
                            
                            editalarm.setVisible(true);
                            editalarm.dispose();
                            editframe.setVisible(true);
                            
                            
                            
                            }
                    
                            });
                            
                    
                            
                            
                            
                            
                            
                            
                            
                       
                       
                       
                       //int[] alarmarray = {1546387200,1546387200,1546387200};
                       //System.out.println(Arrays.toString(alarmarray));
                       //int[] alarmarray = new int[3];
                       //alarmarray = {"1546387200","1546387200","1546387200"};
                      
                   
                       
                    }
                    
            });
        
        //save alarms button creates an ics file and saves all the alarms into it
       JButton savetofile = new JButton("Save Alarms");
       pane.add(savetofile, BorderLayout.LINE_END);
        
       savetofile.addActionListener(new ActionListener(){
                
                    public void actionPerformed(ActionEvent e)
                            {
                        try {
                            //creates an empty array and populates it with all the epochtimes in the queue
                            int[] priorities = q.returnPriorityLoop();
                            
                            int i = 0;
                            int tailIndex = q.tailIndex();
                            
                            
                            //creates an instance of File
                            File file = iCal.createical();
                            
                            //starts the VCALENDAR Event
                            String fnew = iCal.startfile();
                            
                            //while loop that repeats however many records there is in the array
                            while(i <= tailIndex)
                            {
                                int epoch = priorities[i];
                                String formatted = a.epochtodatetime(epoch);
                                String[] seperateditems = a.epochsplit(formatted);
                                
                                
                                String hour=seperateditems[3];
                                String minute=seperateditems[4];
                                String day=seperateditems[2];
                                String month=seperateditems[1];
                                
                                int id = i;
                                
                                //writes a record to the file using the details from the current alarm
                                iCal.write(hour , minute, day, month, file, id, fnew);
                                
                                i++;
                            }
                            iCal.endfile(fnew);
                        } catch (IOException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                
                                
                       
                            }
                            });
        
        
       
       
        frame.pack();
        frame.setVisible(true);
        
       
    }
    
    
   @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
        long currentepoch = System.currentTimeMillis()/1000;
        
        timebetweenlbl.setVisible(false);
        long timebetween = priority - currentepoch;
        
        System.out.println("between" + timebetween);
        
        if (timebetween < 0)
        {
            
            timebetweenlbl.setText("No Upcoming Alarms");
            timebetweenlbl.setBounds(40,10, 300, 20);
            panel.add(timebetweenlbl);
            timebetweenlbl.setVisible(true);
            
        }
        else
        {
        timebetweenlbl.setText("Next alarm in :" + timebetween + " seconds");
  
        timebetweenlbl.setBounds(40,10, 300, 20);
        panel.add(timebetweenlbl);
        timebetweenlbl.setVisible(true);
        }
        if (currentepoch == priority)
            
        {
            final JFrame alarmtriggered = new JFrame();
            JDialog d = new JDialog(alarmtriggered, "Wakey Wakey", true);
            d.setBounds(100,20, 300, 20);
            d.setVisible(true);
            
            try {
              
                q.remove(priority);
                
            } catch (QueueUnderflowException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
