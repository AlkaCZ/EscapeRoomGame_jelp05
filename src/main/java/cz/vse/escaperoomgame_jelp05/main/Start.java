package cz.vse.escaperoomgame_jelp05.main;

import cz.vse.escaperoomgame_jelp05.logika.*;
import cz.vse.escaperoomgame_jelp05.uiText.TextoveRozhrani;

/**
 * Třída pro spuštění aplikace
 */
public class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        ui.hraj();
    }
}
