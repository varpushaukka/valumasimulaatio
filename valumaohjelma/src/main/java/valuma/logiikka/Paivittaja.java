
package valuma.logiikka;

import java.util.Random;

/**
 *
 * @author hennaruo
 */
public class Paivittaja {
    private Maasto vesimaa;
    private int koko;
    private Random rand;

    public Paivittaja(int koko) {
        rand = new Random();
        this.koko = koko;
        vesimaa = new Maasto(koko);
    }
    
    public void paivita() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                if (rand.nextInt(koko) == i) vesimaa.tasaaPysty(i, j);
                if (rand.nextInt(koko) == j) vesimaa.tasaaVaaka(i, j);
            }
        }
    }
    
    public void sada(int x, int y, float maara) {
        vesimaa.lisaaVetta(x, y, maara);
    }
    
    public void tulostaVesimaailma() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                System.out.print("[" + vesimaa.getVedenkorkeus(i, j) + "]");
            }
            System.out.println("");
        }    
    }
    
}
