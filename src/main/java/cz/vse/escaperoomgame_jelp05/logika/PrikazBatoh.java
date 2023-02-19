package cz.vse.escaperoomgame_jelp05.logika;
/**
 *  Zde je struktura příkazu batoh
 *
 */

public class PrikazBatoh implements IPrikaz{
    /**
     Nastavení toho jak se bude příkaz volat jako příkaz
     */
    private static final String NAZEV = "batoh";
    /**
     Herní plán
     */
    private HerniPlan plan;
    /**
     Předpis příkazu
     */
    public PrikazBatoh(HerniPlan plan){
        this.plan = plan;
    }
    @Override
    public String provedPrikaz(String... parametry) {
        if (plan.getKosicek().seznamVeci().isEmpty()){
           return "Batoh je prázdný!";
        }
        else {
            return plan.getKosicek().seznamVeci();
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
