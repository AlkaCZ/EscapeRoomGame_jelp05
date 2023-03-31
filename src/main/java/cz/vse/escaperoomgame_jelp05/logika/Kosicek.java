package cz.vse.escaperoomgame_jelp05.logika;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
Třída pro definování vlastností košíčku
 */
public class Kosicek {

    /**
     Omezení velikosti košíčku/batohu
     */
    private  int omezeniKosicku;

    /**
     Set který bude obsahovat vložené věci
     */
    private  Set<Vec> obsahKosicku;

    /**
     *  zde omezujeme velikost kosíčku
     *  @param  omezeniKosicku udává jeho velikost
     *
     */
    public Kosicek(int omezeniKosicku){
        this.omezeniKosicku = omezeniKosicku;
        obsahKosicku = new HashSet<>();
    }
    /**
     *  zde vkládáme věci do kosíčku
     *  @param  neco udává co chceme vložit
     *  @return udává, zda se uspělo vložit věc nebo ne
     */

    public boolean  vlozDoKosicku(Vec neco){
        if(this.obsahKosicku.size()< omezeniKosicku){
            this.obsahKosicku.add(neco);
            return true;
        }
        return false;
    }
    /**
     * zde vybíráme věci z kosíčku
     *
     * @param nazev udává co chceme vzít
     */

    public Vec vyndejZKosiku(String nazev){
        for (Vec neco: obsahKosicku){
            if (neco.getNazev().equals(nazev)){
                this.obsahKosicku.remove(neco);
                return neco;
            }
        }
        return null;
    }

    /**
     *  zde kontrolujeme, zda daná věc je v kosíčku
     *  @param nazev udává co hledáme
     *  @return udává, zda se daná věc v kosíčku našla
     */
    public Boolean obsahujeVec(String nazev) {
        for (Vec neco: obsahKosicku){
            if (neco.getNazev().equals(nazev)){
                return true;
            }
        }
        return false;
    }

    /**
     *  zde vypisujeme seznam věcí
     *
     *  @return udává seznam věcí v jednom Stringu
     */
    public String seznamVeci(){
        StringBuilder seznam = new StringBuilder();
        for (Vec neco: obsahKosicku){
            seznam.append(neco.getNazev()).append(" ");
        }
        return seznam.toString();
    }
    /**
     * Metoda pro navrácení obsahu košíčku ve tvaru Věc
     */
    public Collection<Vec> getSeznamVeci(){
        return obsahKosicku;
    }

}
