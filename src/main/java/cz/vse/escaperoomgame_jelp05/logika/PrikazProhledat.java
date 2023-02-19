package cz.vse.escaperoomgame_jelp05.logika;
/**
 *  Třída PrikazProhledat implementuje pro hru příkaz prohledat.
 *  Tato třída je součástí jednoduché textové hry.
 *  Příkaz pro prohledání vybrané věci v prostoru/místnosti.
 *
 */


public class PrikazProhledat implements IPrikaz{
    /**
     Nastavení toho jak se bude příkaz volat jako příkaz
     */
    private static final String NAZEV = "prohledat";
    /**
     Vytvoření instance herního plánu, holder
     */
    private HerniPlan plan;
    /**
     Předpis příkazu
     */
    public PrikazProhledat(HerniPlan plan){
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "Můžeš prohledat jen jednu věc najednou!";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniMistnost = plan.getAktualniProstor();

        if (aktualniMistnost.obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = aktualniMistnost.vratVec(nazevVeci);
            if (pozadovanaVec.jeProhledatelna() && !pozadovanaVec.jeZamcena()) {

                switch (pozadovanaVec.getNazev()){
                    case "Šatník":
                        //prohledání šatníku
                        plan.getAktualniProstor().vlozVec(new Vec("Kabát", false,false,true,false));
                        pozadovanaVec.setProhledatelna(false);
                        System.out.println("Prohledal jsi šatník a našel jsi v něm kabát");
                        System.out.println("Nová věc v místnosti : Kabát");
                        break;
                    case "Stolek":
                        //prohledání stolku
                        plan.getAktualniProstor().vlozVec(new Vec("set_doutníku_se_zapalovačem", true,true,false,false, true));
                        pozadovanaVec.setProhledatelna(false);
                        System.out.println("Prohledal jsi stolek a našel jsi v něm set doutníku se zapalovačem");
                        System.out.println("Nová věc v místnosti : set_doutníku_se_zapalovačem");
                        break;
                    case "Kabát":
                        //prohledání kabátu
                        plan.getAktualniProstor().vlozVec(new Vec("Zvláštní_kód", true,false,false,false));
                        pozadovanaVec.setProhledatelna(false);
                        System.out.println("Prohledal jsi kabát a našel jsi v něm nějaký kód.");
                        System.out.println("Nová věc v místnosti : Zvláštní_kód");
                        break;
                    case "Pracovní_stůl":
                        //prohledání pracovního stolu
                        plan.getAktualniProstor().vlozVec(new Vec("Šuplík", false,false,false,true));
                        pozadovanaVec.setProhledatelna(false);
                        System.out.println("Prohledal jsi pracovní stůl a našel jsi na první pohled skrytý a zamčený šuplík.");
                        System.out.println("Nová věc v místnosti : Šuplík");
                        break;
                    case "Šuplík":
                        plan.getAktualniProstor().vlozVec(new Vec("Dokumenty_o_vývoji_látky", true,false,false,false));
                        System.out.println("Prohledal jsi šuplík a našel jsi v něm dokumenty a popis tajné látky.");
                        System.out.println("Nová věc v místnosti : Dokumenty_o_vývoji_látky");
                        break;

                    case "Stojan":
                        //prohledání stojanu
                        System.out.println("Prohledal jsi stojan ale nic jsi tam nenašel.");
                        break;
                    case "Skříň":
                        //prohledání skříně
                        System.out.println("Prohledal jsi skříň ale nic jsi tam nenašel.");
                        break;
                    case "Řídící_deska":
                        //prohledání Řídící desky
                        plan.getAktualniProstor().vlozVec(new Vec("Klíček_od_šuplíku", true,false,false,false));
                        pozadovanaVec.setProhledatelna(false);
                        System.out.println("Prohledal jsi řídící desku a našel jsi v ní klíček od šuplíku, nejspíše od pracovního stolu z laboratoře.");
                        System.out.println("Nová věc v místnosti : Klíček_od_šuplíku");
                        break;

                    case "Postel":
                        //prohledání postele
                        System.out.println("Prohledal jsi postel a zjistil jsi že to je opravdu velmi pohodlná postel.");
                        break;
                    case "Noční_stolek":
                        //prohledání nočního stolku
                        plan.getAktualniProstor().vlozVec(new Vec("Klíče_od_šatníku", true,false,false,false));
                        pozadovanaVec.setProhledatelna(false);
                        System.out.println("Prohledal jsi noční stolek a našel jsi nějaké malé klíčky, nejspíše od šatníku.");
                        System.out.println("Nová věc v místnosti : Klíče_od_šatníku");
                        break;
                    case "Truhla":
                        System.out.println("Prohledal jsi truhlu a našel jsi v ní hledanou nebezpečnou látku, kterou jsi ihned vzal do batohu.");
                        plan.getKosicek().vlozDoKosicku(new Vec("Nebezpečná_látka", true,false,false,false));
                     break;
                }

            }
            if (pozadovanaVec.jeProhledatelna() && pozadovanaVec.jeZamcena()){
                return nazevVeci + " nelze prohledat, protože je zamčený.";
            }

            }
        return "";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
