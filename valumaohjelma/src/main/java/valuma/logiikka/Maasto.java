
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
                maankorkeus[i][j] = 10 + rnd.nextFloat();
            }
        }
    }
    
    public void asetaVesi() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                vedenkorkeus[i][j] = 0;
            }
        }
    }    
    
    private boolean onkoUlkopuolella(int x, int y) {
        if (x < 0 || x > koko) return true;
        if (y < 0 || y > koko) return true;
        return false;
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
    
    public float pisteenKeskiarvo(int x, int y) {
        //lasketaan neljän solun keskiarvo  
        float yhteensa = getYhteiskorkeus(x, y) + getYhteiskorkeus(x+1, y+1) +
                         getYhteiskorkeus(x, y+1) + getYhteiskorkeus(x+1, y);
        return yhteensa / 4;
    }
    
    public void asetaKorkeuteen(int x, int y, float keskiarvo) {
        float muutos = keskiarvo - getYhteiskorkeus(x, y);
        lisaaVetta(x, y, muutos * 0.9f);
        lisaaMaata(x, y, muutos * 0.1f);
    }
    
    public void valuta(int x, int y) {
        float keskiarvo = pisteenKeskiarvo(x, y);
        this.asetaKorkeuteen(x, y, keskiarvo);
        this.asetaKorkeuteen(x+1, y, keskiarvo);
        this.asetaKorkeuteen(x, y+1, keskiarvo);
        this.asetaKorkeuteen(x+1, y+1, keskiarvo);
    }
}

//maasto, päivittäjä ja maastoikkuna 