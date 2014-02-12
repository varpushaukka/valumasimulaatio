package valuma.logiikka;

import java.util.Random;

/**
 * Päivittää maastoa käyttäen maaston valumafunktioita.
 * lisää vettä kohtiin joissa sataa
 * @author hennaruo
 */
public class Paivittaja {

    private Maasto vesimaa;
    private int koko;
    private Random rand;
    private int sateenXpaikka;
    private int sateenYpaikka;

    public Paivittaja(Maasto maailma) {
        rand = new Random();
        vesimaa = maailma;
        koko = vesimaa.getKoko();
    }
    public void nollaa(Maasto uusiMaa) {
        vesimaa = uusiMaa;
        sateenXpaikka = -20;
        sateenYpaikka = -20;
    }

    public void paivita() {
        valuta();
        sada(sateenXpaikka, sateenYpaikka, 10);
    }

    public void valuta() {
        for (int i = 0; i < (koko * koko) * 2; i++) {
            int x = rand.nextInt(koko + 1) - 1;
            int y = rand.nextInt(koko + 1) - 1;
            if (rand.nextDouble() > 0.75) {
                vesimaa.tasaaVaaka(x, y);
                vesimaa.tasaaVaaka(x, y + 1);
                vesimaa.tasaaPysty(x, y);
                vesimaa.tasaaPysty(x + 1, y);
            }
        }
    }
    
    public void asetaSateenPaikka(int x, int y) {
        this.sateenXpaikka = x;
        this.sateenYpaikka = y;
    }

    private double etaisyys(int vaskateetti, int viekateetti) {
        return Math.sqrt(Math.pow(vaskateetti, 2) + Math.pow(viekateetti, 2));
    }

    public void sada(int x, int y, int sade) {
        for (int ind = 0; ind < vesimaa.getKoko(); ind++) {
            int i = rand.nextInt(vesimaa.getKoko());
            int j = rand.nextInt(vesimaa.getKoko());
            if (etaisyys(Math.abs(x - i), Math.abs(y - j)) <= sade) {
                vesimaa.lisaaVetta(i, j, 2f);
            }
        }
    }
    
  
 

}
