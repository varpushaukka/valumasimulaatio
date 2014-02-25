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
    private int ruutukoko;
    private int maastonKoko;

    /**
     * alustaa ikkunan koon ja maaston koon
     */
    public Kaytli() {
        ruutukoko = 700;
        maastonKoko = 150;
        alustaMaasto();
    }
    
    /**
     * alustaa maaston ja päivittäjän. Konstruktori käyttää tätä.
     */
    public final void alustaMaasto() {
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

    /**
     * Luo kaikki käyttöliittymäkomponentit, eli valikot ja maastoikkunan.
     * @param container 
     */
    private void luoKomponentit(Container container) {
        JMenuBar valikko = new JMenuBar();

        JMenu valikkonappi = new JMenu("Valikko");
        JMenu uusiMaastovalikko = new JMenu("Vaihda maastoa");
        JMenuItem portaat = new JMenuItem("Portaat");
        JMenuItem random = new JMenuItem("Satunnainen");
        JMenuItem tasainen = new JMenuItem("Tasainen");
        JMenuItem satporras = new JMenuItem("Satunnaiset portaat");
        uusiMaastovalikko.add(portaat);
        uusiMaastovalikko.add(random);
        uusiMaastovalikko.add(tasainen);
        uusiMaastovalikko.add(satporras);
        valikkonappi.add(uusiMaastovalikko);
        
        
        Maastoikkuna alusta = new Maastoikkuna(maasto, paivittaja);
        
        ActionListener porrask = new Porraskuuntelija2(alusta);
        portaat.addActionListener(porrask);
        random.addActionListener(new Randomkuuntelija(alusta));
        tasainen.addActionListener(new Tasakuuntelija(alusta));
        satporras.addActionListener(new SatPorrasKuuntelija(alusta));
        
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
        System.out.println("Luotu satunnainen maasto");
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
        System.out.println("Luotu uusi porrasmaasto");
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
        System.out.println("Luotu tasainen maasto");
    }
    
}
class SatPorrasKuuntelija implements ActionListener {

    private Maastoikkuna ikkuna;

    public SatPorrasKuuntelija(Maastoikkuna ikkuna) {
        this.ikkuna = ikkuna;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ikkuna.alustaSatPorrasMaasto();
        System.out.println("Luotu satunnainen porrasmaasto");
    }
    
}


