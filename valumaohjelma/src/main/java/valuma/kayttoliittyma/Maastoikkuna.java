package valuma.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import valuma.logiikka.Maasto;
import valuma.logiikka.Paivittaja;

/**
 *
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
    private Kaytli kali;
    
    public Maastoikkuna(Maasto maa, Paivittaja paivittaja, Kaytli kali) {
        this.maa = maa;
        this.paivittaja = paivittaja;
        this.kali = kali;
        super.setBackground(Color.BLACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    public void alustaMaasto() {
        kali.alustaMaasto();
        this.repaint();
    }
    
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        lahtox = this.getWidth() / 2;
        lahtoy = this.getHeight() / 4;
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
                    graphics.setColor(Color.BLUE);
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
        for (int i = 0; i < 200; i++ ) {
            paivittaja.paivita();
        }
        this.repaint();
        
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
