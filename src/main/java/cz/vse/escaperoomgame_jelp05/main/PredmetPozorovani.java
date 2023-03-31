package cz.vse.escaperoomgame_jelp05.main;
/**
Interface pro Observable Item, neboli předmět pozorování.
 */
public interface PredmetPozorovani {
    /**
     Metoda pro registraci pozorovatelů, observerů.
     */
    void registruj(ZmenaHry zmenaHry, Pozorovatel pozorovatel);

}
