
package valuma.kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author varpushaukka
 */
public class Valikkokuuntelija implements ActionListener{
    private Kaytli kali;
    

    public Valikkokuuntelija(Kaytli kali) {
        this.kali = kali;
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        kali.alustaMaasto();
        System.out.println("Luotu uusi maasto");
    }
    
}
