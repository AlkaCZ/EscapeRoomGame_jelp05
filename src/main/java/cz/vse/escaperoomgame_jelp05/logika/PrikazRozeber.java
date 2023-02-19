package cz.vse.escaperoomgame_jelp05.logika;
/**
 *  Třída PrikazRozeber implementuje pro hru příkaz rozeber.
 *  Tato třída je součástí jednoduché textové hry.
 *  Příkaz pro rozebrání vybraného předmětu.
 *
 */

public class PrikazRozeber implements IPrikaz{
    /**
     Nastavení toho jak se bude příkaz volat jako příkaz
     */
    private static final String NAZEV = "rozeber";
    /**
     Vytvoření instance herniho Planu, holder
     */
    private HerniPlan plan;
    /**
     Předpis příkazu
     */
    public PrikazRozeber(HerniPlan plan){
        this.plan = plan;
    }
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Můžeš rozebrat jen jednu věc najednou!";
        }
        String nazevVeci = parametry[0];


        if (plan.getKosicek().obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = plan.getKosicek().vyndejZKosiku(nazevVeci);
            if (pozadovanaVec.jeRozebiratelna()){
                switch (pozadovanaVec.getNazev()){

                    case "set_doutníku_se_zapalovačem":
                        plan.getKosicek().vlozDoKosicku(new Vec("Doutník",true, false, false, false));
                        plan.getKosicek().vlozDoKosicku(new Vec("Zapalovač",true, true, false, false));
                        System.out.printf(pozadovanaVec.getNazev() + " jsi rozebral na samotný doutník a zapalovač a vložil jsi si je do batohu");
                  break;
                }
            }
            else {
                plan.getKosicek().vlozDoKosicku(pozadovanaVec);
                return pozadovanaVec.getNazev() + " nejde rozebrat";
            }
        }
        return "";
    }


    @Override
    public String getNazev() {
        return NAZEV;

    }
}
