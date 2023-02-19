package cz.vse.escaperoomgame_jelp05.logika;
/**
 *  Třída PrikazPouzit implementuje pro hru příkaz použít.
 *  Tato třída je součástí jednoduché textové hry.
 *  Příkaz pro použití vybrané věci z inventáře
 *
 */


public class PrikazPouzit implements IPrikaz{
    /**
     Nastavení toho jak se bude příkaz volat jako příkaz
     */

    private static final String NAZEV = "použít";
    /**
     Vytvoření instance hry, holder
     */
    private Hra hra;
    /**
     Vytvoření instance herního plánu, holder
     */
    private HerniPlan plan;

    /**
     Předpis příkazu
     */
    public PrikazPouzit (Hra hra) {
        this.hra = hra;
        plan = hra.getHerniPlan();
    }
    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "Musíš zadat co chceš použít!";
        }
        String nazevVeci = parametry[0];
  if (plan.getKosicek().obsahujeVec(nazevVeci)){
        Vec v = plan.getKosicek().vyndejZKosiku(nazevVeci);
        if (v.maSpecialniVlastnost()){
            switch (v.getNazev())
            {
                case "Baterka":
                    //Přidat kontrolu pokud už je rozsvíceno, možná prostě odebrat baterku
                    System.out.printf("Rozsvítil jsi " + v.getNazev() + ".  ");
                    if (plan.getAktualniProstor().obsahujeVec("Postel") && plan.getAktualniProstor().obsahujeVec("Noční_stolek")){
                        return null;
                    }


                   else if (plan.getAktualniProstor().getNazev() == "Osobní_pokoj"){
                        plan.getAktualniProstor().vlozVec(new Vec("Postel", false, false, true, false));
                        plan.getAktualniProstor().vlozVec(new Vec("Noční_stolek", false, false, true, false));
                        plan.getAktualniProstor().odeberVec("Tma");
                        System.out.println("Rozsvítil jsi v celém pokoji a díky tomu ho konečně můžeš pořádně prohledat");
                        System.out.println("Nová věc v místnosti : Postel Noční_stolek");


                    }
                   break;
                case "Zapalovač":
                    System.out.printf("Rozsvítil jsi " + v.getNazev());
                    //Nastavení konce hry pokud jsi v Skryté
                    if (plan.getKosicek().obsahujeVec("Nebezpečná_látka") && plan.getKosicek().obsahujeVec("Dokumenty_o_vývoji_látky")){
                        plan.setVyherniProstor(plan.getAktualniProstor());
                        System.out.printf( " a zničil jsi nebezpečnou zkoumanou látku i s dokumenty");
                        hra.setEpilog("Gratuluji, dokončnil jsi hru");
                        hra.setKonecHry(true);
                    }
                    else if (plan.getKosicek().obsahujeVec("Nebezpečná_látka") && !plan.getKosicek().obsahujeVec("Dokumenty_o_vývoji_látky")){
                        System.out.println("Nebezpečnou látku sice máš, ale pro splnění mise potřebuješ ješte dokumenty.");
                    }
                    else if (!plan.getKosicek().obsahujeVec("Nebezpečná_látka") && plan.getKosicek().obsahujeVec("Dokumenty_o_vývoji_látky")){
                        System.out.println("Dokumenty sice máš, ale pro splnění mise potřebuješ ješte nebezpečnou látku.");
                    }
                    break;

            }

        }
           plan.getKosicek().vlozDoKosicku(v);
        }
       else {
           return "Věc není v inventáři";
       }


        return "";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
