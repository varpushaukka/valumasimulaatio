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
import valuma.logiikka.Porrasmaasto;

/**
 * Luo maaston grafiikat
 * @author varpushaukka
 */
public class Maastoikkuna extends JPanel implements MouseListener, MouseMotionListener {
    private Maasto maa;
    private Paivittaja paivittaja;
    private int lahtox;
    private int lahtoy;
    private double xskaalaus = 1.5;
    private double zskaalaus = 1.7; 
    private double korkeusskaalaus = 10; 
    private int hiirenZmaastossa = -1;
    private int hiirenXmaastossa = -1;
    private Timer ajastin;

    
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
    
    //TODO *** pitää miettiä miten tämä toimii ***
    public void alustaPorrasMaasto() {
        maa.asetaPorrasMaasto();
        maa.asetaVesi();
        paivittaja.nollaa(maa);
        this.repaint();
    }
    
    public void alustaRandomMaasto() {
        maa.asetaSatunnainenMaasto();
        maa.asetaVesi();
        paivittaja.nollaa(maa);
        this.repaint();
    }
    
    public void alustaTasaMaasto() {
        maa.asetaTasainenMaasto();
        maa.asetaVesi();
        paivittaja.nollaa(maa);
        this.repaint();
    }
    
    //TODO *** tämän metodin voisin pilkkoa pienempiin osiin ***
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
    
    private int maastoXksi(int rx, int ry) {
        return (int) ((rx - lahtox + (ry - lahtoy) * zskaalaus) / (xskaalaus + zskaalaus));
    }
    
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
