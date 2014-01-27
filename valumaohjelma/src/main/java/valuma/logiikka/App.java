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
        testiMaa.setMaankorkeus(5, 5, 10.5f);
        testiMaa.setMaankorkeus(5, 6, 10.0f);
        testiMaa.setVedenkorkeus(5, 5, 0.5f);
        testiMaa.setVedenkorkeus(5, 6, 0f);
        testiMaa.tasaaPysty(5, 5);
        System.out.println(testiMaa.getVedenkorkeus(5, 5));
        System.out.println(testiMaa.getVedenkorkeus(5, 6));
        System.out.println(testiMaa.getYhteiskorkeus(5, 5));
        System.out.println(testiMaa.getYhteiskorkeus(5, 6));
    }
}
