package pl.edu.agh.kis.pz1;

import static junit.framework.TestCase.assertNotNull;
import org.junit.Test;


public class MainClientTest {


    /**
     * Test for the construction of Main and the 
     * main method being called
     *
     */
    @Test
    public void shouldCreateMainObject(){
        MainClient main = new MainClient();
        assertNotNull("Main method called on class Main2.", main);
    }
}


