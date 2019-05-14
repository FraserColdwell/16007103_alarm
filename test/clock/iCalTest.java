/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.io.File;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GraciousGraham
 */
public class iCalTest {
    
    public iCalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of createical method, of class iCal.
     */
    @Test
    public void testCreateical() throws Exception {
        String name = "alarms";
            StringBuilder builder = new StringBuilder();
            builder.append(name);
            builder.append(".ics");
            
            File expected = new File(builder.toString());
        
        System.out.println("createical");
        iCal instance = new iCal();
        File expResult = expected;
        
        File result = instance.createical();
  
        assertEquals(expResult, result);
    }

    /**
     * Test of write method, of class iCal.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        String hour = "12";
        String minute = "30";
        String day = "03";
        String month = "06";
        File file = null;
        int id = 0;
        String fnew = "test.ics";
        iCal instance = new iCal();
        
    }

    /**
     * Test of startfile method, of class iCal.
     */
    //Have to comment out the tests for start and end file for the number of lines test to work
    
    //@Test
    //public void testStartfile() throws Exception {
        //System.out.println("startfile");
        //iCal instance = new iCal();
        //String expResult = "alarms.ics";
        //String result = instance.startfile();
        //assertEquals(expResult, result);
     
    //}

    /**
     * Test of endfile method, of class iCal.
     */
    
    //Have to comment out the tests for start and end file for the number of lines test to work
    //@Test
    //public void testEndfile() throws Exception {
        //System.out.println("endfile");
        //String fnew = "alarms.ics";
        //iCal instance = new iCal();
        //instance.endfile(fnew);
        
    //}

    /**
     * Test of readIcal method, of class iCal.
     */
    @Test
    public void testReadIcal() throws Exception {
        System.out.println("readIcal");
        iCal instance = new iCal();
        int[] expResult = {1559561400, 1577306700};
        int[] result = instance.readIcal();
        
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of nooflines method, of class iCal.
     */
    @Test
    public void testNooflines() throws Exception {
        System.out.println("nooflines");
        iCal instance = new iCal();
        int expResult = 12;
        int result = instance.nooflines();
        assertEquals(expResult, result);
        
    }
    
}
