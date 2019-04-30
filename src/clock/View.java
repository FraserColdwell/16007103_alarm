package clock;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class View implements Observer {
    
    ClockPanel panel;
    
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
                String hour[]={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
                String minute[]={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"};
                JComboBox hr = new JComboBox(hour);
                JComboBox min = new JComboBox(minute);
                JLabel hourlbl = new JLabel("Hour");
                JLabel minlbl = new JLabel("Minute");
                
                JButton set = new JButton("Set Alarm");
                addsetalarmpanel.add(set, BorderLayout.PAGE_START);
                
                set.setBounds(140, 140, 100, 20);
                hourlbl.setBounds(100, 30, 40, 20);
                minlbl.setBounds(100, 80, 40, 20);
                hr.setBounds(100, 50,180,20);
                min.setBounds(100,100, 180, 20);
                
                addsetalarmpanel.add(hr);
                addsetalarmpanel.add(min);
                addsetalarmpanel.add(hourlbl);
                addsetalarmpanel.add(minlbl);
                addsetalarmpanel.setLayout(null);
                addsetalarmpanel.setSize(400,500);
                
                addsetalarmpanel.setVisible(true);
                
                set.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        String hour = String.valueOf(hr.getSelectedItem());
                        String minute = String.valueOf(min.getSelectedItem());
                        String time = hour+minute;
                        
                        
                        
                        JDialog t = new JDialog(frame, "Alarm Set For : " + time, true);
                        t.setLocationRelativeTo(frame);
                        t.setSize(200,20);
                        t.setVisible(true);
                        
                    }
                    
            });
        }});
        
       
         
       
        
    
         
        //button = new JButton("Button 3 (LINE_START)");
        //pane.add(button, BorderLayout.LINE_START);
         
       // button = new JButton("Long-Named Button 4 (PAGE_END)");
        //pane.add(button, BorderLayout.PAGE_END);
         
       // button = new JButton("5 (LINE_END)");
       // pane.add(button, BorderLayout.LINE_END);
        
        // End of borderlayout code
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
}
