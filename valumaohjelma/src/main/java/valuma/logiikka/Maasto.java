package valuma.logiikka;

import java.util.Random;

/**
 * Luo maaston ja vedet maastolle, sekä laskee vesien valumista
 *
 * @author varpushaukka
 */
public class Maasto {

    /**
     * maaston korkeuden tallettava matriisi
     */
    private float[][] maankorkeus;
    /**
     * veden korkeuden tallettava matriisi
     */
    private float[][] vedenkorkeus;
    /**
     * maastomatriisin koko
     */
    private int koko;
    /**
     * kerroin sille kuinka paljon virtaava vesi kuluttaa maastoa
     */
    private final float tasautuvuus = 0.1f;
    /**
     * satunnaisuuden laskentaan käytettävä olio
     */
    private Random rnd;

    /**
     * Alustaa korkeusmatriisit
     *
     * @param koko maaston leveys ja pituus
     */
    public Maasto(int koko) {
        this.koko = koko;
        maankorkeus = new float[koko][koko];
        vedenkorkeus = new float[koko][koko];
        rnd = new Random();
        this.asetaPorrasMaasto();
        this.asetaVesi();
    }

    /**
     * asettaa solujen korkeudet portaikkomaastoksi, jossa kukin porras on 15x15
     * pistettä
     */
    public void asetaPorrasMaasto() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                setMaankorkeus(i, j, 10 + (koko - i) / 15 + (koko - j) / 15);
            }
        }
    }

    /**
     * asettaa jokaisen solun korkeudeuden satunnaisesti siten että kokeus on
     * jotain väliltä 10-11
     */
    public void asetaSatunnainenMaasto() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                setMaankorkeus(i, j, 10 + rnd.nextFloat());
            }
        }
    }

    /**
     * asettaa jokaisen solun korkeudeksi 11
     */
    public void asetaTasainenMaasto() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                setMaankorkeus(i, j, 11);
            }
        }
    }

    /**
     * asettaa 20x20 kokoisen alueen korkeuden satunnaisesti
     */
    public void asetaSatunnainenPorrasMaasto() {
        for (int x = 0; x < koko; x++) {
            for (int y = 0; y < koko; y++) {
                float korkeus;
                if (x % 20 == 0 && y % 20 == 0) {
                    korkeus = 10 + 1 / (0.1f + rnd.nextFloat());
                } else if (x % 20 == 0) {
                    korkeus = getMaankorkeus(x, y - 1);
                } else {
                    korkeus = getMaankorkeus(x - 1, y);
                }
                setMaankorkeus(x, y, korkeus);
            }
        }
    }

    /**
     * asettaa veden korkeudeksi kullekin solulle 0
     */
    public void asetaVesi() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                setVedenkorkeus(i, j, 0f);
            }
        }
    }

    /**
     * tarkistaa onko piste maastomatriisin ulkopuolella
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return true jos piste on ulkopuolella ja muuten false
     */
    public boolean onkoUlkopuolella(int x, int y) {
        if (x < 0 || x >= koko) {
            return true;
        }
        return y < 0 || y >= koko;
    }

    /**
     * @return maaston koon
     */
    public int getKoko() {
        return this.koko;
    }

    /**
     * palauttaa maan korkeuden tietyssä pisteessä
     * @param x
     * @param y
     * @return korkeus float-tyyppisenä
     */
    public float getMaankorkeus(int x, int y) {
        if (this.onkoUlkopuolella(x, y)) {
            return 0; //koska valumisen on tarkoitus toimia maaston ulkopuolellakin
        }
        return maankorkeus[x][y];
    }

    /**
     * palauttaa veden korkeuden tietyssä pisteessä
     * @param x
     * @param y
     * @return korkeus float-tyyppisenä
     */
    public float getVedenkorkeus(int x, int y) {
        if (this.onkoUlkopuolella(x, y)) {
            return 0;
        }
        return vedenkorkeus[x][y];
    }

    /**
     * palauttaa maan ja veden yhteenlasketun korkeuden tietyssä pisteessä
     * @param x
     * @param y
     * @return yhteiskorkeus float-tyyppisenä
     */
    public float getYhteiskorkeus(int x, int y) {
        return this.getMaankorkeus(x, y) + this.getVedenkorkeus(x, y);
    }

    public void setMaankorkeus(int x, int y, float korkeus) {
        if (this.onkoUlkopuolella(x, y)) {
            return;
        }
        maankorkeus[x][y] = korkeus;
    }

    public void setVedenkorkeus(int x, int y, float korkeus) {
        if (this.onkoUlkopuolella(x, y)) {
            return;
        }
        vedenkorkeus[x][y] = korkeus;
    }

    /**
     * lisää tiettyyn pisteeseen parametrina annetun määrän vettä
     * @param x
     * @param y
     * @param maara lisättävän veden määrä
     */
    public void lisaaVetta(int x, int y, float maara) {
        setVedenkorkeus(x, y, getVedenkorkeus(x, y) + maara);
    }

    /**
     * lisää tiettyyn pisteeseen parametrina annetun määärän maata
     * @param x
     * @param y
     * @param maara lisättävän maan määrä
     */
    public void lisaaMaata(int x, int y, float maara) {
        setMaankorkeus(x, y, getMaankorkeus(x, y) + maara);
    }

//    private float paljonkoLuovuttaaAinetta(int x, int y, float toivekorkeus) {
//        float pyynto = getYhteiskorkeus(x, y) - toivekorkeus;
//        float maxLuovutus = getVedenkorkeus(x, y) / 0.9f;
//        if (maxLuovutus >= pyynto) {
//            return pyynto;
//        }
//        return maxLuovutus;
//    }

//    private void lisaaAinetta(int x, int y, float muutos) {
//        lisaaVetta(x, y, muutos * 0.9f);
//        lisaaMaata(x, y, muutos * 0.1f);
//    }

    /**
     * kutsuu maan lisäysmetodeja positiivisin arvoin kohdesolulle ja negatiivisiin arvoin lähtösolulle
     * @param x1 lähtösolun x
     * @param y1 lähtösolun y
     * @param x2 kohdesolun x
     * @param y2 kohdesolun y
     * @param maara siirtyvä maan määrä
     */
    private void siirraMaata(int x1, int y1, int x2, int y2, float maara) {
        lisaaMaata(x2, y2, maara);
        lisaaMaata(x1, y1, -maara);
    }

    private void siirraVetta(int x1, int y1, int x2, int y2, float maara) {
        lisaaVetta(x2, y2, maara);
        lisaaVetta(x1, y1, -maara);
    }

//    private void siirraAinetta(int x1, int y1, int x2, int y2, float maara) {
//        siirraMaata(x1, y1, x2, y2, maara * 0.1f);
//        siirraVetta(x1, y1, x2, y2, maara * 0.9f);
//    }
    private void siirraMaataAlamakeen(int x1, int y1, int x2, int y2, float maara) {
        float enimmaissiirto = (getMaankorkeus(x1, y1) - getMaankorkeus(x2, y2)) / 2;
        if (enimmaissiirto < 0) {
            return;
        }
        if (maara > enimmaissiirto) {
            maara = enimmaissiirto;
        }
        siirraMaata(x1, y1, x2, y2, maara);
    }

    /**
     * Valuttaa vettä lähtöpisteestä kohdepisteeseen laskee pisteiden välillä
     * olevan korkeuseron ja virtauksen. Jos korkeusero on negatiivinen, ei tee
     * mitään. Siirtää maata lähtöpisteestä ja lähtöpisteen viereisistä
     * pisteistä kohdepisteeseen.
     *
     * @param x1 lähtöpisteen x
     * @param y1 lähtöpisteen y
     * @param x2 kohdepisteen x
     * @param y2 kohdepisteen y
     * @param vx1 lähtöpisteen viereisen pisteen x
     * @param vy1 lähtöpisteen viereisen pisteen y
     * @param vx2 lähtöpisteen toisen viereisen pisteen x
     * @param vy2 lähtöpisteen toisen viereisen pisteen y
     */
    public void valuta(int x1, int y1, int x2, int y2, int vx1, int vy1, int vx2, int vy2) {
        float korkeusero = (getYhteiskorkeus(x1, y1) - getYhteiskorkeus(x2, y2));
        if (korkeusero < 0) {
            return;
        }
        float virtaus = korkeusero / 2;
        if (virtaus > getVedenkorkeus(x1, y1)) {
            virtaus = getVedenkorkeus(x1, y1);
        }
        float lahtevanMaanOsuus = 0.5f * korkeusero / (0.5f + korkeusero);
        float lahtevanMaanMaara = virtaus * lahtevanMaanOsuus;
        float lahtevanVedenMaara = virtaus - lahtevanMaanMaara;
        siirraMaataAlamakeen(x1, y1, x2, y2, lahtevanMaanMaara * (1 - tasautuvuus));
        siirraMaataAlamakeen(vx1, vy1, x2, y2, lahtevanMaanMaara * tasautuvuus / 2f);
        siirraMaataAlamakeen(vx2, vy2, x2, y2, lahtevanMaanMaara * tasautuvuus / 2f);
        siirraVetta(x1, y1, x2, y2, lahtevanVedenMaara);
    }

//    private void tasaa(int x1, int y1, int x2, int y2) {
//        float keskiarvo = (getYhteiskorkeus(x1, y1) + getYhteiskorkeus(x2, y2)) / 2;
//        float eka = paljonkoLuovuttaaAinetta(x1, y1, keskiarvo);
//        float toka = paljonkoLuovuttaaAinetta(x2, y2, keskiarvo);
//        if (eka > 0) {
//            siirraAinetta(x1, y1, x2, y2, eka);
//        }
//        if (toka > 0) {
//            siirraAinetta(x2, y2, x1, y1, toka);
//        }
//    }
    /**
     * tasaa kahden päällekkäisen solun välillä vedenpinnan mahdollisimman
     * tasaiseksi
     *
     * @param x
     * @param y
     */
    public void tasaaPysty(int x, int y) {
        valuta(x, y, x, y + 1, x - 1, y, x + 1, y);
        valuta(x, y + 1, x, y, x - 1, y + 1, x + 1, y + 1);
//        tasaa(x, y, x, y + 1);
    }

    /**
     * tasaa kahden vierekkäisen solun välillä vedenpinnan mahdollisimman
     * tasaiseksi
     *
     * @param x
     * @param y
     */
    public void tasaaVaaka(int x, int y) {
        valuta(x, y, x + 1, y, x, y - 1, x, y + 1);
        valuta(x + 1, y, x, y, x + 1, y - 1, x + 1, y + 1);
//        tasaa(x, y, x + 1, y);
    }
}