/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GraciousGraham
 */
public class alarmTest {
    
    public alarmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of epoch method, of class alarm.
     */
    @Test
    public void testEpoch() throws Exception {
        System.out.println("epoch");
        String hour = "12";
        String min = "30";
        String day = "03";
        String month = "06";
        alarm instance = new alarm();
        int expResult = 1559561400;
        int result = instance.epoch(hour, min, day, month);
        assertEquals(expResult, result);
        
  
    }

    /**
     * Test of checkDate method, of class alarm.
     */
    @Test
    public void testCheckDate() throws Exception {
        System.out.println("checkDate");
        String day = "03";
        int epochtime = 1559561400;
        String minute = "30";
        String hour = "12";
        String month = "06";
        alarm instance = new alarm();
        boolean expResult = false;
        boolean result = instance.checkDate(day, epochtime, minute, hour, month);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of epochtodatetime method, of class alarm.
     */
    @Test
    public void testEpochtodatetime() {
        System.out.println("epochtodatetime");
        int epochtime = 1559561400;
        alarm instance = new alarm();
        String expResult = "2019-06-03 12:30:00";
        String result = instance.epochtodatetime(epochtime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     
    }

    /**
     * Test of epochsplit method, of class alarm.
     */
    @Test
    public void testEpochsplit() {
        System.out.println("epochsplit");
        String formatted = "2019-06-03 12:30:00";
        alarm instance = new alarm();
        String[] expResult = {"2019","06","03","12","30"};
        String[] result = instance.epochsplit(formatted);
        System.out.println(result[1]);
        assertArrayEquals(expResult, result);
   
    }
    
}
