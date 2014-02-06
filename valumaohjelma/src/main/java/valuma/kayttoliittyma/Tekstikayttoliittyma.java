/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package valuma.kayttoliittyma;

import valuma.logiikka.Maasto;
import valuma.logiikka.Paivittaja;

/**
 *
 * @author hennaruo@cs
 */
public class Tekstikayttoliittyma {
    private Paivittaja maailmanluoja;
    private Maasto pikkumaa;
    
    public Tekstikayttoliittyma() {
        
    }
       public void tulostaVesimaailma() {
        for (int i = 0; i < pikkumaa.getKoko(); i++) {
            for (int j = 0; j < pikkumaa.getKoko(); j++) {
                System.out.print("[" + pikkumaa.getVedenkorkeus(i, j) + "]");
            }
            System.out.println("\n");
        }
    }
    
    public void tulostaMaamaailma() {
        for (int i = 0; i < pikkumaa.getKoko(); i++) {
            for (int j = 0; j < pikkumaa.getKoko(); j++) {
                System.out.print("[" + pikkumaa.getMaankorkeus(i, j) + "]");
            }
            System.out.println("\n");
        }
    }
    public void run() {
//        String komento;
//        while (!komento.equals("")) {
//            komento = 
            
//        }
    }
    
    

    
    
    
}
