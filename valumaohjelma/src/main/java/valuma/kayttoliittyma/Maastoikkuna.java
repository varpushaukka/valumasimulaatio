package valuma.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import valuma.logiikka.Maasto;
import valuma.logiikka.Paivittaja;

/**
 * Luo maaston grafiikat
 * @author varpushaukka
 */
public class Maastoikkuna extends JPanel implements MouseListener, MouseMotionListener {
    /**
     * Konstruktorille annettu Maasto-olio
     */
    private Maasto maa;
    
    /**
     * Konstruktorille annettu Paivittaja-olio
     */
    private Paivittaja paivittaja;
    
    /**
     * kohta, josta maastoa aletaan piirtämään ruudulla
     */
    private int lahtox;
    private int lahtoy;
    
    /**
     * maaston kallistumisen määrittelevät muuttujat
     */
    private double xskaalaus = 1.5;
    private double zskaalaus = 1.7; 
    
    /**
     * Maaston esittämisen apuna käytettävä kerroin
     */
    private double korkeusskaalaus = 10; 
    
    /**
     * hiiren jäljen piirtämiseen ja sateiden tiputtamisen apumuuttujat
     */
    private int hiirenZmaastossa = -1;
    private int hiirenXmaastossa = -1;
    
    /**
     * animoinnin avuksi luotu Timer-olio
     */
    private Timer ajastin;

/**
 * Konstruktori, joka alustaa Timer-olion ja lisää hiirenkuuntelijat
 * @param maa
 * @param paivittaja 
 */    
    public Maastoikkuna(Maasto maa, Paivittaja paivittaja) {
        this.maa = maa;
        this.paivittaja = paivittaja;
        ActionListener ajastinkuuntelija = new Ajastinkuuntelija(this.paivittaja, this);
        ajastin = new Timer(100, ajastinkuuntelija);
        ajastin.start();
        super.setBackground(Color.BLACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    /**
     * Maaston maankorkeuden uudelleenasettava metodi
     */
    public void alustaPorrasMaasto() {
        maa.asetaPorrasMaasto();
        nollaaParametrit();
    }
    public void alustaRandomMaasto() {
        maa.asetaSatunnainenMaasto();
        nollaaParametrit();
    }
    public void alustaTasaMaasto() {
        maa.asetaTasainenMaasto();
        nollaaParametrit();
    }
    public void alustaSatPorrasMaasto() {
        maa.asetaSatunnainenPorrasMaasto();
        nollaaParametrit();
    }
    
    /**
     * alustusmetodien apuna käytettävä vesien tyhjentävä ja päivittäjän nollaava metodi
     */
    private void nollaaParametrit() {
        maa.asetaVesi();
        paivittaja.nollaa(maa);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        lahtox = this.getWidth() / 2;
        lahtoy = this.getHeight() / 2;
        Color ruskea = new Color(120, 60, 0);
        for (int x = 0; x < maa.getKoko(); x++) {
            for (int z = 0; z < maa.getKoko(); z++) {
                int rx = (int) (lahtox + xskaalaus * x - zskaalaus * z);
                int ry = (int) (lahtoy + x + z);
                if (x == hiirenXmaastossa && z == hiirenZmaastossa) {
                    graphics.setColor(Color.WHITE);
                    graphics.drawLine(rx, ry, rx, 0);
                } else {
                    graphics.setColor(ruskea);
                    graphics.drawLine(rx, ry,
                            rx, (int) (ry - maa.getMaankorkeus(x, z) * korkeusskaalaus));
                    if (maa.getVedenkorkeus(x, z) > 0 ) graphics.setColor(Color.BLUE);
                    else graphics.setColor(Color.GREEN);
                    graphics.drawLine(rx, (int) (ry - maa.getMaankorkeus(x, z) * korkeusskaalaus),
                            rx, (int) (ry - maa.getYhteiskorkeus(x, z) * korkeusskaalaus));
                }
            }
        }
    }
    
    /**
     * PaintComponentissa tehdyn projektion vuoksi ruudun koordinaatit eivät ole samoja maaston koordinaattien kanssa.
     * Tämä muuttaa ruudun koordinaatit takaisin maaston x-koordinaatiksi
     * @param rx    ruudun x-koordinaatti
     * @param ry    ruudun y-koordinaatti
     * @return      maaston x-koordinaatti
     */
    private int maastoXksi(int rx, int ry) {
        return (int) ((rx - lahtox + (ry - lahtoy) * zskaalaus) / (xskaalaus + zskaalaus));
    }
    
    /**
     * Muuttaa ruudun koordinaatit takaisin maaston z-koordinaatiksi
     * @param rx    ruudun x-koordinaatti
     * @param ry    ruudun y-koordinaatti
     * @return      maaston z-koordinaatti
     */
    private int maastoZksi(int rx, int ry) {
        return (int) ((-rx + lahtox + (ry - lahtoy) * xskaalaus) / (xskaalaus + zskaalaus));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        paivittaja.asetaSateenPaikka(maastoXksi(me.getX(), me.getY()), maastoZksi(me.getX(), me.getY()));
        System.out.println("vettä sataa pisteeseen " + maastoXksi(me.getX(), me.getY()) + ", " + maastoZksi(me.getX(), me.getY()));
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
   
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        hiirenXmaastossa = maastoXksi(me.getX(), me.getY());
        hiirenZmaastossa = maastoZksi(me.getX(), me.getY());
        this.repaint();
    }
    
}

/**
 * Timer-oliolle parametrina annettava olio
 * @author varpushaukka
 */
class Ajastinkuuntelija implements ActionListener {
    private Paivittaja paiv;
    private Maastoikkuna ikk;

    public Ajastinkuuntelija(Paivittaja paiv, Maastoikkuna ikk) {
        this.paiv = paiv;
        this.ikk = ikk;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        paiv.paivita();
        ikk.repaint();
    }
}
