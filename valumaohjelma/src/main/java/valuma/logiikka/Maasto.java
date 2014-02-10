
package valuma.logiikka;

import java.util.Random;

/**
 * Luo maaston ja vedet maastolle, sekä laskee vesien valumista
 * @author varpushaukka
 */
public class Maasto {
    private float[][] maankorkeus;
    private float[][] vedenkorkeus;
    private int koko;
    private Random rnd;
    
/**
 * Alustaa korkeusmatriisit
 * @param koko            maaston leveys ja pituus
 */
    public Maasto(int koko) {
        this.koko = koko;
        maankorkeus = new float[koko][koko];
        vedenkorkeus = new float[koko][koko];
        rnd = new Random();
        this.asetaMaasto();
        this.asetaVesi();
    }
    

    public void asetaMaasto() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
//                setMaankorkeus(i, j, 10 + rnd.nextFloat());
                setMaankorkeus(i, j, 10 + (koko - i) / 15 + (koko - j) / 15);
//                setMaankorkeus(i, j, 11);

            }
        }
    }
    
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
        if (x < 0 || x >= koko) return true;
        return y < 0 || y >= koko;
    }
    
    public int getKoko() {
        return this.koko;
    }
        
    public float getMaankorkeus(int x, int y) {
        if (this.onkoUlkopuolella(x, y)) return 0; //koska valumisen on tarkoitus toimia maaston ulkopuolellakin
        return maankorkeus[x][y];
    } 
    
    public float getVedenkorkeus(int x, int y) {
        if (this.onkoUlkopuolella(x, y)) return 0;
        return vedenkorkeus[x][y];
    }
    
    public float getYhteiskorkeus(int x, int y) {
        return this.getMaankorkeus(x,y) + this.getVedenkorkeus(x,y);
    }
    
    public void setMaankorkeus(int x, int y, float korkeus) {
        if (this.onkoUlkopuolella(x, y)) return;
        maankorkeus[x][y] = korkeus;
    }
    
    public void setVedenkorkeus(int x, int y, float korkeus) {
        if (this.onkoUlkopuolella(x, y)) return;
        vedenkorkeus[x][y] = korkeus;
    }
    
    public void lisaaVetta(int x, int y, float maara) {
        setVedenkorkeus(x, y, getVedenkorkeus(x, y) + maara);
    }
    
    public void lisaaMaata(int x, int y, float maara) {
        setMaankorkeus(x, y, getMaankorkeus(x, y) + maara);
    }
    
    public float paljonkoLuovuttaaAinetta(int x, int y, float toivekorkeus) {
        float pyynto = getYhteiskorkeus(x, y) - toivekorkeus;
        float maxLuovutus = getVedenkorkeus(x, y) / 0.9f;
        if (maxLuovutus >= pyynto) return pyynto;
        return maxLuovutus;
    }
    
    public void lisaaAinetta(int x, int y, float muutos) {
        lisaaVetta(x, y, muutos * 0.9f);
        lisaaMaata(x, y, muutos * 0.1f);
    }
    
    public void siirraMaata(int x1, int y1, int x2, int y2, float maara) {
        lisaaMaata(x2, y2, maara);
        lisaaMaata(x1, y1, -maara);
    }
    
    public void siirraVetta(int x1, int y1, int x2, int y2, float maara) {
        lisaaVetta(x2, y2, maara);
        lisaaVetta(x1, y1, -maara);
    }
      
    public void siirraAinetta(int x1, int y1, int x2, int y2, float maara) {
        siirraMaata(x1, y1, x2, y2, maara * 0.1f);
        siirraVetta(x1, y1, x2, y2, maara * 0.9f);
    }
    
    public void valuta(int x1, int y1, int x2, int y2, int vx1, int vy1, int vx2, int vy2) {
        // 
        float keskiarvo = (getYhteiskorkeus(x1, y1) + getYhteiskorkeus(x2, y2)) / 2;
        float virtaus = (getYhteiskorkeus(x1, y1) - getYhteiskorkeus(x2, y2)) / 2;
        if (virtaus < 0) return;
        if (virtaus > getVedenkorkeus(x1, y1)) virtaus = getVedenkorkeus(x1, y1);
        float lahtevanMaanOsuus = 0.5f * virtaus / (0.5f + virtaus);
        float lahtevanMaanMaara = virtaus * lahtevanMaanOsuus;
        float lahtevanVedenMaara = virtaus - lahtevanMaanMaara;
        siirraMaata(x1, y1, x2, y2, lahtevanMaanMaara * 0.7f);
        siirraMaata(vx1, vy1, x2, y2, lahtevanMaanMaara * 0.15f);
        siirraMaata(vx2, vy2, x2, y2, lahtevanMaanMaara * 0.15f);
        siirraVetta(x1, y1, x2, y2, lahtevanVedenMaara);
        
        
    }

    public void tasaa(int x1, int y1, int x2, int y2) {
        float keskiarvo = (getYhteiskorkeus(x1, y1) + getYhteiskorkeus(x2, y2)) / 2;
        float eka = paljonkoLuovuttaaAinetta(x1, y1, keskiarvo);
        float toka = paljonkoLuovuttaaAinetta(x2, y2, keskiarvo);
        if (eka > 0) {
            siirraAinetta(x1, y1, x2, y2, eka);
        }
        if (toka > 0) {
            siirraAinetta(x2, y2, x1, y1, toka);
        }
    }
    
    /**
     * tasaa kahden päällekkäisen solun välillä vedenpinnan mahdollisimman tasaiseksi
     * @param x
     * @param y 
     */
    public void tasaaPysty(int x, int y) {
        valuta(x, y, x, y +1, x - 1, y, x + 1, y);
//        tasaa(x, y, x, y + 1);
    }
    /**
     * tasaa kahden vierekkäisen solun välillä vedenpinnan mahdollisimman tasaiseksi
     * @param x
     * @param y 
     */
    public void tasaaVaaka(int x, int y) {
        valuta(x, y, x + 1, y, x, y -1, x, y + 1);
//        tasaa(x, y, x + 1, y);
    }

}