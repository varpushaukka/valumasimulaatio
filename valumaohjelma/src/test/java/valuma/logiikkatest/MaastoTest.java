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
    
    Maasto testiMaa;
    
    @Before
    public void setUp() {
         testiMaa = new Maasto(10);
    }
    
    @Test
    public void vesiEiOleNegatiivinen() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(testiMaa.getVedenkorkeus(i, j) >= 0) ;
            }
        }
    }
 
    @Test
    public void konstruktoriAsettaaMaastonOikein() {
        Maasto test = new Maasto(10);
        boolean testi = test.getMaankorkeus(1, 1) >= 10;
        assertTrue(testi);
    }
    
    @Test
    public void konstruktoriAsettaaVedenOikein() {
        Maasto test = new Maasto(10);
        boolean testi = test.getVedenkorkeus(1, 1) == 0;
        assertTrue(testi);
    }
    
    @Test
    public void onkoUlkopuolellaToimiiOikein() {
        assertTrue(testiMaa.onkoUlkopuolella(12, 12));
    }
    
    @Test
    public void lisaaVettaToimii() {   
        testiMaa.lisaaVetta(2, 2, 0.2f);
        boolean onnistuiko = testiMaa.getVedenkorkeus(2, 2) == 0.2f;
        assertTrue(onnistuiko) ;
    }
    @Test
    public void lisaaMaataToimii() {
        float alku = testiMaa.getMaankorkeus(1, 2);
        testiMaa.lisaaMaata(1, 2, 0.5f);
        assertTrue(testiMaa.getMaankorkeus(1, 2) - 0.5f == alku);
    }
    
    @Test
    public void tasaaPystyToimii() {
        testiMaa.setMaankorkeus(5, 5, 10.5f);
        testiMaa.setMaankorkeus(5, 6, 10.5f);
        testiMaa.setVedenkorkeus(5, 5, 0.5f);
        testiMaa.setVedenkorkeus(5, 6, 0f);
        testiMaa.tasaaPysty(5, 5);
        if (!(Math.abs(testiMaa.getYhteiskorkeus(5, 5) - testiMaa.getYhteiskorkeus(5, 6)) < 0.0001f)) {
            fail("maa: " + testiMaa.getMaankorkeus(5, 5) + " vesi: " + testiMaa.getVedenkorkeus(5, 5) + "maa2: " + testiMaa.getMaankorkeus(5, 6));
        }
    }
    
    @Test
    public void tasaaPystyToimiiKunMaaEpatasainen() {
        testiMaa.setMaankorkeus(5, 5, 10.5f);
        testiMaa.setMaankorkeus(5, 6, 10.0f);
        testiMaa.setVedenkorkeus(5, 5, 0.5f);
        testiMaa.setVedenkorkeus(5, 6, 0f);
        testiMaa.tasaaPysty(5, 5);
        if (!(Math.abs(testiMaa.getYhteiskorkeus(5, 5) - testiMaa.getYhteiskorkeus(5, 6)) < 0.0001f)) {
            fail("maa: " + testiMaa.getMaankorkeus(5, 5) + " vesi: " + testiMaa.getVedenkorkeus(5, 5) + "maa2: " + testiMaa.getMaankorkeus(5, 6));
        }
    }
//    @Test
//    public void asetaKorkeuteenToimiiKunLisataan() {
//        float testi = testiMaa.getYhteiskorkeus(1, 1);
//        testiMaa.asetaKorkeuteen(1, 1, 10.999f);
//        if (testi >= testiMaa.getYhteiskorkeus(1, 1)) fail("korkeus ei lisäätynyt");
//    }
//    @Test
//    public void asetaKorkeuteenToimiiKunVahennetaan() {
//        float testi = testiMaa.getYhteiskorkeus(5, 5);
//        testiMaa.asetaKorkeuteen(5, 5, 10.01f);
//        if (testi <= testiMaa.getYhteiskorkeus(5, 5)) fail("korkeus ei vähentynyt");
//    }
}