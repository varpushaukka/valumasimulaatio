package valuma.logiikka;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Maasto jee = new Maasto(10);
        System.out.println(jee.getMaankorkeus(1,3));
        System.out.println(jee.getVedenkorkeus(1,3));
    }
}
