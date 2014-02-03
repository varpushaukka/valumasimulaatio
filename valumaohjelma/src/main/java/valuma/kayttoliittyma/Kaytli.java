package valuma.kayttoliittyma;

/**
 *
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

    private JFrame frame;
    private Maasto maasto;
    private Paivittaja paivittaja;
    private JMenuBar menubar;
    JMenuItem menuItem;

    public Kaytli() {
        maasto = new Maasto(500);
        paivittaja = new Paivittaja(maasto);

    }

    @Override
    public void run() {
        frame = new JFrame("Valumasimulaatio");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);

    }

    private void luoKomponentit(Container container) {
        menubar = new JMenuBar();

        JMenu menu = new JMenu("Valikko");
        menu.setMnemonic(KeyEvent.VK_A);
//        menu.getAccessibleContext().setAccessibleDescription(
//        "Asetukset");
        menuItem = new JMenuItem("Uusi maasto",
                         KeyEvent.VK_T);
        menu.add(menuItem);
        JPanel alusta = new Maastoikkuna();
        ActionListener v = new Valikko(alusta);
        menu.addActionListener(v);
        menubar.add(menu);
        frame.setJMenuBar(menubar);
        container.add(alusta);
    }

    public JFrame getFrame() {
        return frame;
    }
}
