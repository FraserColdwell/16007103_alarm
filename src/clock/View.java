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

import java.util.logging.Level;
import java.util.logging.Logger;



public class View implements Observer {
    
    ClockPanel panel;
    public int priority;
    public SortedArrayPriorityQueue<Object> q = new SortedArrayPriorityQueue<>(20);
    alarm a = new alarm();
    
    public View(Model model) {
        JFrame frame = new JFrame();
        
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        Container pane = frame.getContentPane();
        
        JButton setalarm = new JButton("Set Alarm");
        pane.add(setalarm, BorderLayout.PAGE_START);
        
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
        
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
                
                 
                
                set.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        String hour = String.valueOf(hr.getSelectedItem());
                        String minute = String.valueOf(min.getSelectedItem());
                        String day = String.valueOf(daycb.getSelectedItem());
                        String month = String.valueOf(monthcb.getSelectedItem());
                        
                        String time = hour+ ":" +  minute + ":" + day + "" + month;
                        
                        
                        
                        try {
                            int epochtime = a.epoch(hour, minute, day, month);
                            epochtime = a.addDay( day,  epochtime,  minute,  hour,  month);
                            boolean past = a.checkDate(day, epochtime, minute, hour, month);
                            if (past == false)
                            {
                            q.add(time, epochtime);
                            
                            
                               
                                
                            
                            JDialog t = new JDialog(frame, "Alarm Set For : " + time + " On: " + day + "," + month, true);
                            t.setLocationRelativeTo(frame);
                            t.setSize(300,20);
                            t.setVisible(true);
                            priority = q.returnPriority();
                            addsetalarmpanel.dispose();
                            }
                            else
                            {
                                addsetalarmpanel.dispose();
                                JDialog t = new JDialog(frame, "Can't set an alarm that has already past");
                                t.setLocationRelativeTo(frame);
                                t.setSize(500,20);
                                t.setVisible(true);
                                
                            }
                            
                            
                            System.out.println(priority);
                            
                            
             
                        } catch (ParseException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (QueueOverflowException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);}
                        
                        
                    }
                    
            });
        }});
        
       
         
       
        
    
         
        JButton viewalarms = new JButton("Load Alarms");
        pane.add(viewalarms, BorderLayout.LINE_START);
        
        viewalarms.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                      
                       
                       
                       
                       
                        
                    }
                    
            });
    
                   
        
         
        JButton editalarms = new JButton("View or Edit Alarms");
        pane.add(editalarms, BorderLayout.PAGE_END);
         
        editalarms.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                       final JFrame editalarm = new JFrame();
                       editalarm.setSize(400,500);
                       editalarm.setLayout(null);
                       
                       //int[] alarmarray = new int[2];
                       int [] alarmarray = q.returnPriorityLoop();
                       String[] datetimearray = new String[2];
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
                            
                            back.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                      
                                editalarm.dispose();
                       
                        
                            }
                    
            });
                            
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
                            
                            alarmselected.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                             
                            final JFrame editframe = new JFrame();
                            editframe.setSize(400,500);
                            editframe.setLayout(null);
                            
                            String alarmtoedit = String.valueOf(datetimecb.getSelectedItem());  
                            System.out.println(alarmtoedit);
                            
                            String[] seperateditems = a.epochsplit(alarmtoedit);
                            
                            String hour[]={seperateditems[3],"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
                            String minute[]={seperateditems[4],"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"};
                            String day[]={seperateditems[2],"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                            String month[]={seperateditems[1],"01","02","03","04","05","06","07","08","09","10","11","12"};
                            
                            System.out.println(seperateditems[3]);
                            System.out.println(seperateditems[4]);
                            System.out.println(seperateditems[2]);
                            System.out.println(seperateditems[1]);
                            
                            System.out.println(Arrays.toString(hour));
                            
                            
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
                            
                            back.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                                editframe.dispose();
                                editalarm.setVisible(true);
                            }
                    
            });
                                String hour1 = String.valueOf(hr.getSelectedItem());
                                String minute1 = String.valueOf(min.getSelectedItem());
                                String day1 = String.valueOf(daycb.getSelectedItem());
                                String month1 = String.valueOf(monthcb.getSelectedItem());
                                
                                
                                try {
                                    int epochtime = a.epoch(hour1, minute1, day1, month1);
                                    q.remove(epochtime);
                                } catch (ParseException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (QueueUnderflowException ex) {
                                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                
                            
                            update.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                                
                        
                                
                                String time = hour+ ":" +  minute + ":" + day + ":" + month;
                                System.out.println("time");
                                   System.out.println(time);     
                                
                                    String hour = String.valueOf(hr.getSelectedItem());
                                    String minute = String.valueOf(min.getSelectedItem());
                                    String day = String.valueOf(daycb.getSelectedItem());
                                    String month = String.valueOf(monthcb.getSelectedItem());
                                    
                                    
                                try {
                                    int epochtime = a.epoch(hour, minute, day, month);
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
        
       JButton savetofile = new JButton("Save Alarms");
       pane.add(savetofile, BorderLayout.LINE_END);
        
       savetofile.addActionListener(new ActionListener(){
                
                    public void actionPerformed(ActionEvent e)
                            {
                      
                                
                       
                            }
                            });
        
        
        frame.pack();
        frame.setVisible(true);
        
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
        long currentepoch = System.currentTimeMillis()/1000;
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
