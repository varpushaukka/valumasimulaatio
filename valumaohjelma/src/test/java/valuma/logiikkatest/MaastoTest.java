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
    
    
    public boolean vesiEiOleNegatiivinen() {
        //tämä on tarkoitettu invariantiksi, koska veden määrän ei milloinkaan kuulu olla negatiivinen
        for (int i = 0; i < testiMaa.getKoko(); i++) {
            for (int j = 0; j < testiMaa.getKoko(); j++) {
                if(testiMaa.getVedenkorkeus(i, j) >= 0) continue;
                else return false;
            }
        }
        return true;
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
        assertTrue(vesiEiOleNegatiivinen());
    }
    
    @Test
    public void lisaaVettaToimii() {   
        testiMaa.lisaaVetta(2, 2, 0.2f);
        boolean onnistuiko = testiMaa.getVedenkorkeus(2, 2) == 0.2f;
        assertTrue(onnistuiko) ;
        assertTrue(vesiEiOleNegatiivinen());
    }
    @Test
    public void lisaaMaataToimii() {
        float alku = testiMaa.getMaankorkeus(1, 2);
        testiMaa.lisaaMaata(1, 2, 0.5f);
        assertTrue(testiMaa.getMaankorkeus(1, 2) - 0.5f == alku);
        assertTrue(vesiEiOleNegatiivinen());
    }
    
    @Test
    public void tasaaPystyToimii() {
        testiMaa.setMaankorkeus(5, 5, 10.5f);
        testiMaa.setMaankorkeus(5, 6, 10.5f);
        testiMaa.setVedenkorkeus(5, 5, 0.5f);
        testiMaa.setVedenkorkeus(5, 6, 0f);
        testiMaa.tasaaPysty(5, 5);
        assertTrue("maa: " + testiMaa.getMaankorkeus(5, 5) + " vesi: " + testiMaa.getVedenkorkeus(5, 5) + "maa2: " + testiMaa.getMaankorkeus(5, 6), 
                (Math.abs(testiMaa.getYhteiskorkeus(5, 5) - testiMaa.getYhteiskorkeus(5, 6)) < 0.0001f));
        assertTrue(vesiEiOleNegatiivinen());
    }
    
    @Test
    public void tasaaPystyToimiiKunMaaEpatasainen() {
        testiMaa.setMaankorkeus(5, 5, 10.5f);
        testiMaa.setMaankorkeus(5, 6, 10.0f);
        testiMaa.setVedenkorkeus(5, 5, 0.9f);
        testiMaa.setVedenkorkeus(5, 6, 0f);
        testiMaa.tasaaPysty(5, 5);
        assertTrue("maa: " + testiMaa.getMaankorkeus(5, 5) + " vesi: " + testiMaa.getVedenkorkeus(5, 5) + "maa2: " + testiMaa.getMaankorkeus(5, 6), 
                (Math.abs(testiMaa.getYhteiskorkeus(5, 5) - testiMaa.getYhteiskorkeus(5, 6)) < 0.0001f));
        assertTrue(vesiEiOleNegatiivinen());
    }
    
    @Test
    public void tasaaPystyToimiiKunMaaOletus() {
        testiMaa.setVedenkorkeus(3, 5, 0.9f);
        testiMaa.setVedenkorkeus(3, 6, 0.2f);
        testiMaa.tasaaPysty(3, 5);
        assertTrue("maa: " + testiMaa.getMaankorkeus(3, 5) + " vesi: " + testiMaa.getVedenkorkeus(3, 5) + "maa2: " + testiMaa.getMaankorkeus(3, 6) + "vesi: " + testiMaa.getVedenkorkeus(3, 6), 
                (Math.abs(testiMaa.getYhteiskorkeus(3, 5) - testiMaa.getYhteiskorkeus(3, 6)) < 0.0001f));
        assertTrue(vesiEiOleNegatiivinen());
    }
    
    @Test
    public void vaakatasaamisenJalkeenVettaSamaMaara() {
        testiMaa.setVedenkorkeus(4, 4, 0.6f);
        testiMaa.tasaaVaaka(4, 4);
        assertTrue(testiMaa.getVedenkorkeus(4, 4) + testiMaa.getVedenkorkeus(5, 4) == 0.6f);
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