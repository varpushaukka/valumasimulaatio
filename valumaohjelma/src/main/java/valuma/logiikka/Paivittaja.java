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

    public void paivita() {
        for (int n = 0; n < (koko * koko) * 2; n++) {
            int i = rand.nextInt(koko);
            int j = rand.nextInt(koko);
            if (rand.nextDouble() > 0.75) {
                vesimaa.tasaaVaaka(i, j);
                vesimaa.tasaaVaaka(i, j + 1);
                vesimaa.tasaaPysty(i, j);
                vesimaa.tasaaPysty(i + 1, j);
            }
        }
    }

    private double etaisyys(int vaskateetti, int viekateetti) {
        return Math.sqrt(Math.pow(vaskateetti, 2) + Math.pow(viekateetti, 2));
    }

    public void sada(int x, int y, int sade) {
        for (int n = 0; n < 20; n++) {
            for (int i = x - sade; i < x + sade; i++) {
                for (int j = y - sade; j < y + sade; j++) {
                    if (etaisyys(Math.abs(x - i), Math.abs(y - j)) <= sade && rand.nextDouble() > 0.70) {
                        vesimaa.lisaaVetta(i, j, 0.1f);
                    }
                }
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
    
    public void tulostaMaamaailma() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                System.out.print("[" + vesimaa.getMaankorkeus(i, j) + "]");
            }
            System.out.println("\n");
        }
    }

}
