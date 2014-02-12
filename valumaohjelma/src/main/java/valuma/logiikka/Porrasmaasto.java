/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package valuma.logiikka;

/**
 *
 * @author hennaruo
 */
public class Porrasmaasto extends Maasto{

    public Porrasmaasto(int koko) {
        super(koko);
    }

    @Override
    public void asetaMaasto() {
        for (int i = 0; i < super.getKoko(); i++) {
            for (int j = 0; j < super.getKoko(); j++) {
                setMaankorkeus(i, j, 10 + (super.getKoko() - i) / 15 + (super.getKoko() - j) / 15);
            }
        }
    }

}
