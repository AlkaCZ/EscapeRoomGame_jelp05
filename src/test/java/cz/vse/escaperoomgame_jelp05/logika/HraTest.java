import cz.vse.escaperoomgame_jelp05.logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown() {
        hra1 = new Hra();
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("domeček", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi les");
        assertEquals(false, hra1.konecHry());
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi hluboký_les");
        assertEquals(false, hra1.konecHry());
        assertEquals("hluboký_les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }



    @Test
    public void testHry(){
        // Uvítání - zapnutí hry
        assertEquals(hra1.vratUvitani(), "Vítejte!\n" +
                "Toto je příběh o Červené Karkulce, babičce a vlkovi.\n" +
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
                "\n" +
                "Jsi v mistnosti/prostoru domeček, ve kterém bydlí Karkulka.\n" +
                "východy: les");

        // 1. krok hry JDI les
        assertEquals(hra1.zpracujPrikaz("jdi les"), "Jsi v mistnosti/prostoru" +
                " les s jahodami, malinami a pramenem vody.\n" +
                "východy: domeček hluboký_les sousedni_domecek");

        // 2. krok hry JDI hluboký_les
        assertEquals(hra1.zpracujPrikaz("jdi hluboký_les"), "Jsi v mistnosti/prostoru" +
                " temný les, ve kterém lze potkat vlka.\n" +
                "východy: chaloupka jeskyně les");

        // 3. krok hry JDI chaloupka
        assertEquals(hra1.zpracujPrikaz("jdi chaloupka"), "Jsi v mistnosti/prostoru" +
                " chaloupka, ve které bydlí babička Karkulky.\n" +
                "východy: hluboký_les");

        // Ověření ukončení hry a epilogu
        assertTrue(hra1.konecHry());
        assertEquals(hra1.vratEpilog(), "Došel jsi do výherního prostoru a hra končí!");
    }
}
