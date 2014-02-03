/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valuma.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import valuma.logiikka.Maasto;
import valuma.logiikka.Paivittaja;

/**
 *
 * @author varpushaukka
 */
public class Maastoikkuna extends JPanel {
    private Maasto maa;
    private Paivittaja paivittaja;
    
    public Maastoikkuna(Maasto maa, Paivittaja paivittaja) {
        this.maa = maa;
        this.paivittaja = paivittaja;
        super.setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // kokeilu
        graphics.drawLine(200, 100, 100, 100);
        
    }
    
    
    
    
}
