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

    public Paivittaja(Maasto maailma) {
        rand = new Random();
        vesimaa = maailma;
        koko = vesimaa.getKoko();
    }

    public void paivita(int maara) {
        for (int n = 0; n < maara; n++) {
            for (int i = 0; i < koko; i++) {
                for (int j = 0; j < koko; j++) {
                    if (rand.nextDouble() > 0.75) {
                        vesimaa.tasaaVaaka(i, j);
                        vesimaa.tasaaPysty(i, j);
                    }
                }
            }
        }
    }
    
    private double etaisyys(int vaskateetti, int viekateetti) {
        return Math.sqrt(Math.pow(vaskateetti, 2) + Math.pow(viekateetti, 2));
    }

    public void sada(int x, int y, int sade) {
        for (int i = x - sade; i < x + sade; i++) {
            for (int j = y - sade; j < y + sade; j++)
                if (etaisyys(Math.abs(x - i), Math.abs(y - j)) <= sade) {
                    vesimaa.lisaaVetta(i, j, 0.1f);
                }
        }
    }

    public void tulostaVesimaailma() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                System.out.print("[" + vesimaa.getVedenkorkeus(i, j) + "]");
            }
            System.out.println("\n");
        }
    }

}
