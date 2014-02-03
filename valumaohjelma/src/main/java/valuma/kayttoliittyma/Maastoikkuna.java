/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valuma.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author varpushaukka
 */
public class Maastoikkuna extends JPanel {

    public Maastoikkuna() {
        super.setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // kokeilu
        graphics.drawLine(200, 100, 100, 100);
        
    }
    
    
    
    
}
