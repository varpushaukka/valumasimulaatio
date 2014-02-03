
package valuma.kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author varpushaukka
 */
public class Valikko implements ActionListener{
    private Kaytli kali;
    

    public Valikko(Kaytli kali) {
        this.kali = kali;
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        kali.alustaMaasto();
    }
    
}
