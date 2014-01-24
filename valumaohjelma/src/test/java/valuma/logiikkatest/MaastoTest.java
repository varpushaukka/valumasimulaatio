/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valuma.logiikkatest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import valuma.logiikka.Maasto;
import static org.junit.Assert.*;

/**
 *
 * @author varpushaukka
 */
public class MaastoTest {
    
    public MaastoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void konstruktoriAsettaaMaastonOikein() {
        Maasto testiMaa = new Maasto(10);
        
    }
}