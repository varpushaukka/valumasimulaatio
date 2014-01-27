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
    public void konstruktoriAsettaaMaastonOikein() {

        boolean testi = testiMaa.getMaankorkeus(1, 1) >= 10;
        if (!testi) fail("Maa on liian matala");
    }
    @Test
    public void konstruktoriAsettaaVedenOikein() {
        
        boolean testi = testiMaa.getVedenkorkeus(1, 1) == 0;
        if (!testi) fail("Vettä on väärä määrä");
    }
    @Test
    public void onkoUlkopuolellaToimiiOikein() {
        
        if (!testiMaa.onkoUlkopuolella(12, 12)) fail("pisteen pitäisi olla ulkopuolella");
    }
    @Test
    public void lisaaVettaToimii() {   
        testiMaa.lisaaVetta(2, 2, 0.2f);
        boolean onnistuiko = testiMaa.getVedenkorkeus(2, 2) == 0.2f;
        if (!onnistuiko) fail("veden lisäys epäonnistui");
    }
    @Test
    public void lisaaMaataToimii() {
        float alku = testiMaa.getMaankorkeus(1, 2);
        testiMaa.lisaaMaata(1, 2, 0.5f);
        if (testiMaa.getMaankorkeus(1, 2) - 0.5f != alku) fail("maata lisättiin väärä määrä");
    }
    
    @Test
    public void tasaaPystyToimii() {
        testiMaa.setMaankorkeus(5, 5, 10.5f);
        testiMaa.setMaankorkeus(5, 6, 10.5f);
        testiMaa.setVedenkorkeus(5, 5, 0.5f);
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