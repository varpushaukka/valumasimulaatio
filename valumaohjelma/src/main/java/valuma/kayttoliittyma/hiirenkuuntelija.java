/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valuma.kayttoliittyma;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import valuma.logiikka.Paivittaja;

/**
 *
 * @author varpushaukka
 */
public class hiirenkuuntelija implements MouseListener {
    private Paivittaja p;

    public hiirenkuuntelija(Paivittaja p) {
        this.p = p;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        p.sada(me.getX(), me.getY(), 30);
        System.out.println("vettä sataa pisteeseen " + me.getX() + ", " + me.getY());
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
   
    }

    @Override
    public void mouseEntered(MouseEvent me) {
  
    }

    @Override
    public void mouseExited(MouseEvent me) {
  
    }
    
}
