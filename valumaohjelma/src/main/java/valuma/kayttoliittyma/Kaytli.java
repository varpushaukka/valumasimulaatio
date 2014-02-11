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
    JMenuItem maastonResetointinappi;
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
        valikkonappi.setMnemonic(KeyEvent.VK_A);
        maastonResetointinappi = new JMenuItem("Uusi maasto",
                         KeyEvent.VK_T);
        valikkonappi.add(maastonResetointinappi);
        Maastoikkuna alusta = new Maastoikkuna(maasto, paivittaja);
        ActionListener v = new Valikkokuuntelija(alusta);
        maastonResetointinappi.addActionListener(v);
        valikko.add(valikkonappi);
        kehys.setJMenuBar(valikko);
        container.add(alusta);
    }

    public JFrame getFrame() {
        return kehys;
    }
    
}
