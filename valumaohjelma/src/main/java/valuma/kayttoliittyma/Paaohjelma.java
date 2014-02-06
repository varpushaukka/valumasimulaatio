
package valuma.kayttoliittyma;

import javax.swing.SwingUtilities;

/**
 * Valumasimulaatio -projektin main-suoritusluokka
 * @author varpushaukka
 */
public class Paaohjelma {
        public static void main( String[] args ) {
            Kaytli ikkuna = new Kaytli();
            SwingUtilities.invokeLater(ikkuna);
        }

}
