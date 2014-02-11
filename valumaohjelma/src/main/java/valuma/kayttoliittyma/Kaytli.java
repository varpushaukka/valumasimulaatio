package valuma.kayttoliittyma;

/**
 * Luo maastoikkunan ja valikot
 * @author varpushaukka
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import valuma.logiikka.Maasto;
import valuma.logiikka.Paivittaja;

public class Kaytli implements Runnable {

    private JFrame kehys;
    private Maasto maasto;
    private Paivittaja paivittaja;
    private JMenuBar valikko;
    JMenuItem portaat;
    private int ruutukoko;

    public Kaytli() {
        alustaMaasto();
        ruutukoko = 700;
    }
    
    public void alustaMaasto() {
        maasto = new Maasto(150);
        paivittaja = new Paivittaja(maasto);
    }

    public int getRuutukoko() {
        return ruutukoko;
    }

    
    @Override
    public void run() {
        kehys = new JFrame("Valumasimulaatio");
        kehys.setPreferredSize(new Dimension(ruutukoko, ruutukoko));
        kehys.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(kehys.getContentPane());
        
        kehys.pack();
        kehys.setVisible(true);

    }

    private void luoKomponentit(Container container) {
        valikko = new JMenuBar();

        JMenu valikkonappi = new JMenu("Valikko");
        JMenu uusiMaastovalikko = new JMenu("Vaihda maastoa");
        portaat = new JMenuItem("Portaat");
        JMenuItem random = new JMenuItem("Satunnainen");
        JMenuItem tasainen = new JMenuItem("Tasainen");
        uusiMaastovalikko.add(portaat);
        uusiMaastovalikko.add(random);
        uusiMaastovalikko.add(tasainen);
        valikkonappi.add(uusiMaastovalikko);
        JMenuItem poistaVedet = new JMenuItem("Poista vedet");
        valikkonappi.add(poistaVedet);
        Maastoikkuna alusta = new Maastoikkuna(maasto, paivittaja);
        ActionListener porrask = new Porraskuuntelija(alusta);
        portaat.addActionListener(porrask);
//        random.addActionListener();
        valikko.add(valikkonappi);
        kehys.setJMenuBar(valikko);
        container.add(alusta);
    }

    public JFrame getFrame() {
        return kehys;
    }
    
}
