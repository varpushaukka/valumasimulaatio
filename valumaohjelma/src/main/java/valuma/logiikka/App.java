package valuma.logiikka;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Maasto jee = new Maasto(10);
//        System.out.println(jee.getMaankorkeus(1,3));
//        System.out.println(jee.getVedenkorkeus(1,3));
        Maasto testiMaa = new Maasto(10);
        testiMaa.setMaankorkeus(5, 5, 10.3f);
        testiMaa.setMaankorkeus(6, 5, 10.3f);
        testiMaa.setVedenkorkeus(5, 5, 0.6f);
        testiMaa.tasaaVaaka(5, 5);
        System.out.println("maa55: " + testiMaa.getMaankorkeus(5, 5));
        System.out.println("maa65: " + testiMaa.getMaankorkeus(6, 5));
        System.out.println("vesi55: " + testiMaa.getVedenkorkeus(5, 5));
        System.out.println("vesi65: " + testiMaa.getVedenkorkeus(6, 5));
        System.out.println(testiMaa.getYhteiskorkeus(5, 5));
        System.out.println(testiMaa.getYhteiskorkeus(6, 5));
        System.out.println(testiMaa.getMaankorkeus(5, 5) + testiMaa.getMaankorkeus(6, 5));
        Paivittaja p = new Paivittaja(new Maasto(20));
        p.sada(10, 10, 6);
        p.tulostaVesimaailma();
//        p.sada(2, 2, 2f);
//        p.sada(4, 4, 1f);
//        p.sada(6, 6, 2f);
//        p.sada(8, 8, 2f);
//        System.out.println("");
//        p.paivita(5);
//        p.sada(9, 2, 4f);
//        p.tulostaVesimaailma();
//        System.out.println("");
        System.out.println("        ----------          ----------          -----------         -----------");
        p.paivita();
        p.paivita();
        p.paivita();
        p.paivita();
        p.paivita();
        p.tulostaVesimaailma();
        System.out.println("        ----------          ----------          -----------         -----------");
        p.tulostaMaamaailma();
        }
}
