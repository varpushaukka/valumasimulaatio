package valuma.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author varpushaukka
 */
public class Porraskuuntelija implements ActionListener {

    private Maastoikkuna ikkuna;

    public Porraskuuntelija(Maastoikkuna ikkuna) {
        this.ikkuna = ikkuna;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ikkuna.alustaPorrasMaasto();
        System.out.println("Luotu uusi maasto");
    }

}
