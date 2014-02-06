
package valuma.kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;


/**
 *
 * @author varpushaukka
 */
public class Valikkokuuntelija implements ActionListener{
    private JPanel ikkuna;
    

    public Valikkokuuntelija(JPanel ikkuna) {
        this.ikkuna = ikkuna;
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
  //      ikkuna.alustaMaasto();
        System.out.println("Luotu uusi maasto");
    }
    
}
