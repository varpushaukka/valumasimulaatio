
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
         testiMaa.asetaTasainenMaasto();
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
    public void satunnainenMaastoAsettaaSallittujaArvoja() {
        testiMaa.asetaSatunnainenMaasto();
        for (int i = 0; i < testiMaa.getKoko(); i++) {
            for (int j = 0; j < testiMaa.getKoko(); j++) {
                assertTrue(testiMaa.getMaankorkeus(i, j) >= 10 && testiMaa.getMaankorkeus(i, j) <= 11);
            }
        }
    }
    @Test
    public void tasainenMaastoOnTasainen() {
        for (int i = 0; i < testiMaa.getKoko(); i++) {
            for (int j = 0; j < testiMaa.getKoko(); j++) {
                assertTrue(testiMaa.getMaankorkeus(i, j) == 11);
            }
        }
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
    public void vesiEiOleNegatiivinen() {
        //tämä on tarkoitettu invariantiksi, koska veden määrän ei milloinkaan kuulu olla negatiivinen
        for (int i = 0; i < testiMaa.getKoko(); i++) {
            for (int j = 0; j < testiMaa.getKoko(); j++) {
                assertTrue(testiMaa.getVedenkorkeus(i, j) >= 0);
            }
        }
    }    

    private float neljanPisteenMaa(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
          return testiMaa.getMaankorkeus(x1, y1) + testiMaa.getMaankorkeus(x2, y2) + testiMaa.getMaankorkeus(x3, y3) + testiMaa.getMaankorkeus(x4, y4);
    }

    @Test
    public void tasaaPystyMaataSamaMaaraa() {
        testiMaa.setMaankorkeus(5, 5, 10.5f);
        testiMaa.setMaankorkeus(5, 6, 10.0f);
        testiMaa.setVedenkorkeus(5, 5, 0.5f);
        testiMaa.setVedenkorkeus(5, 6, 0f);
        float lahto = neljanPisteenMaa(5, 5, 5, 6, 4, 5, 6, 5);
        testiMaa.tasaaPysty(5, 5);
        float tulos = neljanPisteenMaa(5, 5, 5, 6, 4, 5, 6, 5);
        assertEquals(lahto, tulos, 0.0009);
    }
    
    @Test
    public void tasaaPystyToimiiKunMaaEpatasainen() {
        testiMaa.setMaankorkeus(5, 5, 10.5f);
        testiMaa.setMaankorkeus(4, 5, 11f);
        testiMaa.setMaankorkeus(6, 5, 10.8f);
        testiMaa.setMaankorkeus(5, 6, 10.0f);
        testiMaa.setVedenkorkeus(5, 5, 0.9f);
        testiMaa.setVedenkorkeus(5, 6, 0f);
        float lahto = neljanPisteenMaa(5, 5, 5, 6, 4, 5, 6, 5);
        testiMaa.tasaaPysty(5, 5);
        float tulos = neljanPisteenMaa(5, 5, 5, 6, 4, 5, 6, 5);
        assertEquals(lahto, tulos, 0.0009);
    }
    
    @Test
    public void tasaaPystyToimiiKunMaaOletus() {
        testiMaa.setVedenkorkeus(5, 5, 0.9f);
        testiMaa.setVedenkorkeus(5, 6, 0.2f);
        float lahto = neljanPisteenMaa(5, 5, 5, 6, 4, 5, 6, 5);
        testiMaa.tasaaPysty(3, 5);
        float tulos = neljanPisteenMaa(5, 5, 5, 6, 4, 5, 6, 5);
        assertEquals(lahto, tulos, 0.0009);
        }
//    
    @Test
    public void vaakatasaamisenJalkeenVettaSamaMaara() {
        testiMaa.setVedenkorkeus(4, 4, 0.6f);
        testiMaa.tasaaVaaka(4, 4);
        assertTrue(testiMaa.getVedenkorkeus(4, 4) + testiMaa.getVedenkorkeus(5, 4) == 0.6f);
    }
    


    
}