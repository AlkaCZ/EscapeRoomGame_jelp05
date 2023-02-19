package cz.vse.escaperoomgame_jelp05.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 */
public class PrikazJdi implements IPrikaz {
    /**
     Nastavení toho jak se bude příkaz volat jako příkaz
     */
    private static final String NAZEV = "jdi";
    /**
     Vytvoření instance hry, holder
     */
    private Hra hra;
    /**
     Vytvoření instance herního plánu, holder
     */
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra
    */    
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        plan = hra.getHerniPlan();
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            plan.setAktualniProstor(sousedniProstor);
            if(sousedniProstor.equals(plan.getVyherniProstor())){
                hra.setEpilog("Došel jsi do výherního prostoru a hra končí!");
                hra.setKonecHry(true);
            }
            return sousedniProstor.dlouhyPopis();
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
