package valuma.kayttoliittyma;

/**
 * Luo maastoikkunan ja valikot
 * @author varpushaukka
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int maastonKoko;

    public Kaytli() {
        ruutukoko = 700;
        maastonKoko = 150;
        alustaMaasto();
    }
    
    public void alustaMaasto() {
        maasto = new Maasto(maastonKoko);
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
        
        ActionListener porrask = new Porraskuuntelija2(alusta);
        portaat.addActionListener(porrask);
        random.addActionListener(new Randomkuuntelija(alusta));
        tasainen.addActionListener(new Tasakuuntelija(alusta));
        
        valikko.add(valikkonappi);
        kehys.setJMenuBar(valikko);
        container.add(alusta);
    }

    public JFrame getFrame() {
        return kehys;
    }
    
}
class Randomkuuntelija implements ActionListener {

    private Maastoikkuna ikkuna;

    public Randomkuuntelija(Maastoikkuna ikkuna) {
        this.ikkuna = ikkuna;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ikkuna.alustaRandomMaasto();
    }
    
}
class Porraskuuntelija2 implements ActionListener {

    private Maastoikkuna ikkuna;

    public Porraskuuntelija2(Maastoikkuna ikkuna) {
        this.ikkuna = ikkuna;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ikkuna.alustaPorrasMaasto();
        System.out.println("Luotu uusi maasto");
    }
}
class Tasakuuntelija implements ActionListener {

    private Maastoikkuna ikkuna;

    public Tasakuuntelija(Maastoikkuna ikkuna) {
        this.ikkuna = ikkuna;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ikkuna.alustaTasaMaasto();
    }
    
}


