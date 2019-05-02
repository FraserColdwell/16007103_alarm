package clock;



import javax.swing.JFrame;


import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;



public class View implements Observer {
    
    ClockPanel panel;
    public int priority;
    public SortedArrayPriorityQueue<Object> q = new SortedArrayPriorityQueue<>(20);;
    
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
                        
                        alarm a = new alarm();
                        
                        try {
                            int epochtime = a.epoch(hour, minute, day, month);
                            epochtime = a.addDay( day,  epochtime,  minute,  hour,  month);
                            q.add(time, epochtime);
                            
                            priority = q.returnPriority();
                            System.out.println(priority);
                            
                            
             
                        } catch (ParseException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (QueueOverflowException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);}
                       
                        
                        
                        
                        
                        JDialog t = new JDialog(frame, "Alarm Set For : " + time + " On: " + day + "," + month, true);
                        t.setLocationRelativeTo(frame);
                        t.setSize(300,20);
                        t.setVisible(true);
                        
                        
                        
                    }
                    
            });
        }});
        
       
         
       
        
    
         
        JButton viewalarms = new JButton("View Alarms");
        pane.add(viewalarms, BorderLayout.LINE_START);
        
        viewalarms.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                       final JFrame viewalarms = new JFrame();
                       viewalarms.setSize(400,500);
                       viewalarms.setVisible(true); 
                       
                       String alarmdetails = q.toString();
                       String left[] = alarmdetails.split(":");
                       
                       System.out.println(q.toString());
                       System.out.println(left[0]);
                        
                    }
                    
            });
    
                   
        
         
       // button = new JButton("Long-Named Button 4 (PAGE_END)");
        //pane.add(button, BorderLayout.PAGE_END);
         
       // button = new JButton("5 (LINE_END)");
       // pane.add(button, BorderLayout.LINE_END);
        
        // End of borderlayout code
        
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
              
                q.remove();
                
            } catch (QueueUnderflowException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
