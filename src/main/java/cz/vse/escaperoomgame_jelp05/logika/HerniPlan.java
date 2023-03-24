package cz.vse.escaperoomgame_jelp05.logika;


import cz.vse.escaperoomgame_jelp05.main.Pozorovatel;
import cz.vse.escaperoomgame_jelp05.main.PredmetPozorovani;
import cz.vse.escaperoomgame_jelp05.main.ZmenaHry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 */
public class HerniPlan implements PredmetPozorovani {

    /**
  Proměná aktuálního prostoru
     */
    private Prostor aktualniProstor;
    /**
     Proměná výherního prostoru
     */
    private Prostor vyherniProstor;
    /**
     Proměná skrytého prostoru, který ve hře není na první pohled vidět.
     Zobrazí se až po dané události.
     */
    private Prostor skrytyProstor;
    /**
     Proměná batohu/kosicku, do kterého se dávají vybrané předměty.
     */
    private Kosicek kosicek;
    private Map<ZmenaHry,Set<Pozorovatel>> seznamPozorovatelu = new HashMap<>();

    /**
     Založení hry a košíčku/batohu
     */

    public HerniPlan() {
        kosicek = new Kosicek(10);
        zalozProstoryHry();
        for (ZmenaHry zmenaHry : ZmenaHry.values()){
            seznamPozorovatelu.put(zmenaHry, new HashSet<>());
        }

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví predsin.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor predsin = new Prostor("Předsíň", "předsíň, první místnost do které jsi se dostal");
        Prostor chodba = new Prostor("Chodba", "chodba se šatníkem a s mnoha východy do dalších částí laboratoře");
        Prostor laborator = new Prostor("Laboratoř", "laboratoř, středně velká místnost, ve které se provádí pokusy a výzkum");
        Prostor ridiciMistnost = new Prostor("Řídící_místnost", "která je očividně hlavní místností v celém komplexu. Uprostřed stojí velkým pracovní stůl");
        Prostor osobniPokoj = new Prostor("Osobní_pokoj", "každý musí někde odpočívat, možná tady výzkumníci něco zapoměli");
        Prostor skrytaMistnost = new Prostor("Skrytá_místnost", "co byl předtím skrytý, co zde asi je ?");


        // přiřazují se průchody mezi prostory (sousedící prostory)
        predsin.setVychod(chodba);
        chodba.setVychod(ridiciMistnost);
        chodba.setVychod(laborator);
        chodba.setVychod(predsin);
        laborator.setVychod(chodba);
        ridiciMistnost.setVychod(chodba);
        ridiciMistnost.setVychod(osobniPokoj);
        osobniPokoj.setVychod(ridiciMistnost);
        skrytaMistnost.setVychod(ridiciMistnost);

        //Vytvoření Předmětů

        //Předsíň
        Vec baterka = new Vec("Baterka", true, true, false, false);
        predsin.vlozVec(baterka);
        /** TEST
        Vec TEST = new Vec("Zvláštní_kód", true,false,false,false);
        Vec Test2 = new Vec("Zapalovač",true, true, false, false);
        predsin.vlozVec(TEST);
        predsin.vlozVec(Test2);
        */

        //Chodba
        Vec stolek = new Vec("Stolek", false, false, true, false);
        Vec satnik = new Vec("Šatník", false, false, true, true);
        chodba.vlozVec(stolek);
        chodba.vlozVec(satnik);

        //Laboratoř
        Vec pracovniStul = new Vec("Pracovní_stůl", false, false, true, false);
        Vec stojan = new Vec("Stojan", false, false, true, false);
        laborator.vlozVec(pracovniStul);
        laborator.vlozVec(stojan);

        //Řídící místnost
        Vec skrin = new Vec("Skříň", false, false, true, true);
        Vec zvlastniDvirka = new Vec("Zvláštní_dvířka", false, false, true, true);
        Vec ridiciDeska = new Vec("Řídící_deska", false, false, true, false);
        ridiciMistnost.vlozVec(skrin);
        ridiciMistnost.vlozVec(zvlastniDvirka);
        ridiciMistnost.vlozVec(ridiciDeska);

        //Osobní pokoj
        Vec tma = new Vec("Tma", false,false,false,false);
        osobniPokoj.vlozVec(tma);

        //Skrytá místnost
        Vec truhla = new Vec("Truhla",false,false,true,false);
        skrytaMistnost.vlozVec(truhla);




        aktualniProstor = predsin;  // hra začíná v peředsíňi
        vyherniProstor = null; // Výherní prostor se přiřadí po zničení látky
        skrytyProstor = skrytaMistnost;

    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
        upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
    }



    public void setVyherniProstor(Prostor vyherniProstor) {
        this.vyherniProstor = vyherniProstor;
    }
    /**
     Metoda pro nastavení vchodu do tajné místnosti
     */

    public void setTajnyVchod(Prostor prostor){
        prostor.setVychod(skrytyProstor);
    }




    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }

    public Kosicek getKosicek(){return  kosicek;}


    @Override
    public void registruj(ZmenaHry zmenaHry,Pozorovatel pozorovatel) {
        seznamPozorovatelu.get(zmenaHry).add(pozorovatel);
    }
    private void upozorniPozorovatele(ZmenaHry zmenaHry) {
        for (Pozorovatel pozorovatel : seznamPozorovatelu.get(zmenaHry)){
            pozorovatel.aktualizuj();
        }
    }
}
