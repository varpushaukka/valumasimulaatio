
package valuma.kayttoliittyma;

import javax.swing.SwingUtilities;

/**
 *
 * @author varpushaukka
 */
public class Paaohjelma {
        public static void main( String[] args ) {
            Kaytli ikkuna = new Kaytli();
            SwingUtilities.invokeLater(ikkuna);
        }

}
