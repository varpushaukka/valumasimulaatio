
package valuma.logiikka;

import java.util.Random;

/**
 *
 * @author varpushaukka
 */
public class Maasto {
    private float[][] maankorkeus;
    private float[][] vedenkorkeus;
    private int koko;
    private Random rnd;

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
                setMaankorkeus(i, j, 10 + rnd.nextFloat());
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
    
    public boolean onkoUlkopuolella(int x, int y) {
        if (x < 0 || x > koko) return true;
        if (y < 0 || y > koko) return true;
        return false;
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
    
    public void setMaankorkeus(int x, int y, float n) {
        if (this.onkoUlkopuolella(x, y)) return;
        maankorkeus[x][y] = n;
    }
    
    public void setVedenkorkeus(int x, int y, float n) {
        if (this.onkoUlkopuolella(x, y)) return;
        vedenkorkeus[x][y] = n;
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
    
    public void siirraAinetta(int x1, int y1, int x2, int y2, float maara) {
        lisaaAinetta(x2, y2, maara);
        lisaaAinetta(x1, y1, -maara);
    }
    
    public void tasaa(int x1, int y1, int x2, int y2) {
        float keskiarvo = (getYhteiskorkeus(x1, y1) + getYhteiskorkeus(x2, y2)) / 2;
        float eka = paljonkoLuovuttaaAinetta(x1, y1, keskiarvo);
        float toka = paljonkoLuovuttaaAinetta(x2, y2, keskiarvo);
        if (eka > 0) siirraAinetta(x1, y1, x2, y2, eka);
        if (toka > 0) siirraAinetta(x2, y2, x1, y1, toka);
    }
    
    public void tasaaPysty(int x, int y) {
        tasaa(x, y, x, y + 1);
    }
    public void tasaaVaaka(int x, int y) {
        tasaa(x, y, x + 1, y);
    }

//    
//    public float pisteenKeskiarvo(int x, int y) {
//        //lasketaan neljän solun keskiarvo  
//        float yhteensa = getYhteiskorkeus(x, y) + getYhteiskorkeus(x+1, y+1) +
//                         getYhteiskorkeus(x, y+1) + getYhteiskorkeus(x+1, y);
//        return yhteensa / 4;
//    }
    
//    public void valuta(int x, int y) {
//        // jokaisesta lasketaan paljonko luovuttaa vetta, 
//        // ne jotka suostuu luovuttamaan, luovuttaa sen verran kuin ne suostuu
//        
//        float luovutussumma;
//        float vastaanottajat;
//        
//        float keskiarvo = pisteenKeskiarvo(x, y);
//        this.asetaKorkeuteen(x, y, keskiarvo);
//        this.asetaKorkeuteen(x+1, y, keskiarvo);
//        this.asetaKorkeuteen(x, y+1, keskiarvo);
//        this.asetaKorkeuteen(x+1, y+1, keskiarvo);
//    }
}

//maasto, päivittäjä ja maastoikkuna 