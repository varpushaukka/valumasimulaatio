package valuma.logiikkatest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import valuma.logiikka.Maasto;
import valuma.logiikka.Paivittaja;

/**
 *
 * @author hennaruo@cs
 */
public class PaivittajaTest {

    Maasto testiMaa;
    Paivittaja paivaTest;

    @Before
    public void setUp() {
        testiMaa = new Maasto(90);
        paivaTest = new Paivittaja(testiMaa);
    }

    @Test
    public void konstruktoriToimii() {
        Paivittaja plop = new Paivittaja(testiMaa);
        assertNotNull(plop);
    }

    @Test
    public void sadeTekeeYmpyran() {
        paivaTest.sada(40, 40, 10);
        for (int i = 0; i < testiMaa.getKoko(); i++) {
            for (int j = 0; j < testiMaa.getKoko(); j++) {
                if (etaisyys(Math.abs(40 - i), Math.abs(40 - j)) > 10) assertEquals(0f, testiMaa.getVedenkorkeus(i, j), 0.0001);
            }
        }
    }

    @Test
    public void paivityksenJalkeenVesiPlussalla() {
        paivaTest.paivita();
        for (int i = 0; i < testiMaa.getKoko(); i++) {
            for (int j = 0; j < testiMaa.getKoko(); j++) {
                assertTrue(testiMaa.getVedenkorkeus(i, j) >= 0);
            }
        }
    }
    @Test
    public void nollausPoistaaVedet() {
        for (int i = 0; i < testiMaa.getKoko(); i++) {
            for (int j = 0; j < testiMaa.getKoko(); j++) {
                assertTrue(testiMaa.getVedenkorkeus(i, j) == 0);
            }
        }
    }

    private double etaisyys(int vaskateetti, int viekateetti) {
        return Math.sqrt(Math.pow(vaskateetti, 2) + Math.pow(viekateetti, 2));
    }

}
