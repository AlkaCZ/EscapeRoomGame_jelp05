package cz.vse.escaperoomgame_jelp05.logika;
/**
 * Třída Věc, která předepisuje jak bude vypadat
 */

public class Vec {
    /**
     * Vlastnost věci název
     */
    private String nazev;
    /**
     * Vlastnost věci přenositelnost
     */
    private boolean prenositelna;
    /**
     * Vlastnost věci, pokud má věc nějakou speciální vlastnost
     */

    private boolean specialniVlastnost;
    /**
     * Vlastnost věci pokud je prohledatelná
     */

    private boolean prohledatelna;
    /**
     * Vlastnost věci pokud je zamčená
     */

    private boolean zamcena;
    /**
     * Vlastnost věci pokud lze rozebrat
     */

    private boolean rozebiratelna;




    /**
     * Vytvoření věci se zadaným názvem
     *
     * @param nazev        Jméno věci, jednoznačný identifikátor,
     *                     pokud možno jedno slovo
     * @param prenositelna Parametr určuje, zda je věc
     *                     přenositelná hráčem
     */

    /**
     * Předpis věci bez toho aby šla rozebrat
     */
    public Vec(String nazev, boolean prenositelna, boolean specialniVlastnost, boolean prohledatelna, boolean zamcena) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.specialniVlastnost = specialniVlastnost;
        this.prohledatelna = prohledatelna;
        this.zamcena = zamcena;
    }
    /**
     * Předpis věci s tím že lze rozebrat
     */
    public Vec(String nazev, boolean prenositelna, boolean specialniVlastnost, boolean prohledatelna, boolean zamcena, boolean rozebiratelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.specialniVlastnost = specialniVlastnost;
        this.prohledatelna = prohledatelna;
        this.zamcena = zamcena;
        this.rozebiratelna = rozebiratelna;
    }

    /**
     * Vrací název věci.
     *
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrací informaci o tom, zda je věc přenositelná ve hře.
     *
     * @return true, pokud je věc přenositelná, jinak false
     */
    public boolean jePrenositelna() {
        return prenositelna;
    }
    /**
     * Vrací informaci o tom, zda  věc má speciální vlastnost.
     *
     * @return true, pokud je věc má speciální vlastnost, jinak false
     */
    public boolean maSpecialniVlastnost() {
        return specialniVlastnost;
    }
    /**
     * Vrací informaci o tom, zda lze věc prohledat.
     *
     * @return true, pokud je věc jde prohledat, jinak false
     */
    public boolean jeProhledatelna() {
        return prohledatelna;
    }
    /**
     * Vrací informaci o tom, zda je věc zamčená.
     *
     * @return true, pokud je věc zamčená, jinak false
     */
    public boolean jeZamcena() { return zamcena; }

    /**
     * Vrací informaci o tom, zda je věc rozebíratelná.
     *
     * @return true, pokud je věc lze rozebrat, jinak false
     */
    public boolean jeRozebiratelna(){return rozebiratelna;}
    /**
     * Nastaví vlastnost zamcena na zadanou hodnotu.
     *
     * @return true, pokud je zadáno true, jinak false
     */

    public boolean Odemceni(boolean nepravda){
       return this.zamcena = nepravda;
    }
    /**
     * Nastaví vlastnost prohledatelna na zadanou hodnotu.
     *
     * @return true, pokud je zadáno true, jinak false
     */
    public boolean setProhledatelna(boolean pravda){
       return this.prohledatelna = pravda;
    }



}
