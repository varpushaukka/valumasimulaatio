package valuma.logiikka;

import java.util.Random;

/**
 * Päivittää maastoa käyttäen maaston valumafunktioita. lisää vettä kohtiin
 * joissa sataa
 *
 * @author hennaruo
 */
public class Paivittaja {

    /**
     * Päivitettävä maasto-olio
     */
    private Maasto vesimaa;
    /**
     * Maastomatriisin koko
     */
    private int koko;
    /**
     * satunnaisuuden laskennan apulaisolio
     */
    private Random rand;
    /**
     * sateen keskipiste
     */
    private int sateenXpaikka;
    private int sateenYpaikka;

    /**
     * Alustaa oliomuuttujat
     *
     * @param maailma
     */
    public Paivittaja(Maasto maailma) {
        rand = new Random();
        vesimaa = maailma;
        koko = vesimaa.getKoko();
    }

    /**
     * vaihtaa maastomuuttujan viittauksen uuteen maasto-olioon heittää
     * vesisateet tyhjyyteen
     *
     * @param uusiMaa
     */
    public void nollaa(Maasto uusiMaa) {
        vesimaa = uusiMaa;
        sateenXpaikka = -20;
        sateenYpaikka = -20;
    }

    /**
     * kutsuu kerran valutusmetodia ja sateenlisäysmetodia
     */
    public void paivita() {
        valuta();
        sada(sateenXpaikka, sateenYpaikka, 10);
    }

    /**
     * kutsuu neljälle solulle tasausmetodeja koko² * 6 kertaa
     */
    public void valuta() {
        for (int i = 0; i < (koko * koko) * 6; i++) {
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

    /**
     * määrittelee alueen, jolle sataa vettä
     *
     * @param x
     * @param y
     */
    public void asetaSateenPaikka(int x, int y) {
        this.sateenXpaikka = x;
        this.sateenYpaikka = y;
    }

    /**
     * sada-metodin apufunktio ympyrän säteen laskemiseksi
     * @param vaskateetti x -koordinaattien erotus
     * @param viekateetti y -koordinaattien erotus
     * @return kahden pisteen etäisyys
     */
    private double etaisyys(int vaskateetti, int viekateetti) {
        return Math.sqrt(Math.pow(vaskateetti, 2) + Math.pow(viekateetti, 2));
    }

    /**
     * Lisää vettä ympyrän muotoiselle alueelle satunnaisen määrän
     *
     * @param x
     * @param y
     * @param sade ympyrän säde
     */
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
