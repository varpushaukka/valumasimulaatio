
package valuma.logiikkatest;

import org.junit.Before;
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
    public void maagetteriToimii() {
        assertNotNull(testiMaa.getMaankorkeus(20, 20));
    }
    
    @Test
    public void vesigetteriToimii() {
        assertNotNull(testiMaa.getVedenkorkeus(20, 20));
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
        assertTrue("maa: " + testiMaa.getMaankorkeus(5, 5) + " vesi: " + testiMaa.getVedenkorkeus(5, 5) + "maa2: " + testiMaa.getMaankorkeus(5, 6), 
                (Math.abs(testiMaa.getYhteiskorkeus(5, 5) - testiMaa.getYhteiskorkeus(5, 6)) < 0.0001f));
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
    }
    
    @Test
    public void tasaaPystyToimiiKunMaaOletus() {
        testiMaa.setVedenkorkeus(3, 5, 0.9f);
        testiMaa.setVedenkorkeus(3, 6, 0.2f);
        testiMaa.tasaaPysty(3, 5);
        assertTrue("maa: " + testiMaa.getMaankorkeus(3, 5) + " vesi: " + testiMaa.getVedenkorkeus(3, 5) + "maa2: " + testiMaa.getMaankorkeus(3, 6) + "vesi: " + testiMaa.getVedenkorkeus(3, 6), 
                (Math.abs(testiMaa.getYhteiskorkeus(3, 5) - testiMaa.getYhteiskorkeus(3, 6)) < 0.0001f));
    }
    
    @Test
    public void vaakatasaamisenJalkeenVettaSamaMaara() {
        testiMaa.setVedenkorkeus(4, 4, 0.6f);
        testiMaa.tasaaVaaka(4, 4);
        assertTrue(testiMaa.getVedenkorkeus(4, 4) + testiMaa.getVedenkorkeus(5, 4) == 0.6f);
    }
    
    @Test
    public void vaakatasaamisenJalkeenMaataSamaMaara() {    
        testiMaa.setMaankorkeus(5, 5, 10.3f);
        testiMaa.setMaankorkeus(6, 5, 10.3f);
        testiMaa.setVedenkorkeus(5, 5, 0.6f);
        testiMaa.tasaaVaaka(5, 5);
        assertTrue("Maa 5, 5: " + testiMaa.getMaankorkeus(5, 5) + " Maa 6, 5: " + testiMaa.getMaankorkeus(6,5), 
                testiMaa.getMaankorkeus(5, 5) + testiMaa.getMaankorkeus(6, 5) == 20.6f);
    }
    
    @Test
    public void vesiEiOleNegatiivinen() {
        //tämä on tarkoitettu invariantiksi, koska veden määrän ei milloinkaan kuulu olla negatiivinen
        for (int i = 0; i < testiMaa.getKoko(); i++) {
            for (int j = 0; j < testiMaa.getKoko(); j++) {
                assertTrue(testiMaa.getVedenkorkeus(i, j) >= 0);
            }
        }
    }
    
}