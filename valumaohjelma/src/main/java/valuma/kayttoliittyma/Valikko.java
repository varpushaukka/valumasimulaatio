
package valuma.kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author varpushaukka
 */
public class Valikko implements ActionListener{
    private JPanel alusta;

    public Valikko(JPanel alusta) {
        this.alusta = alusta;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        alusta.setBackground(Color.red);
        
    }
    
}
