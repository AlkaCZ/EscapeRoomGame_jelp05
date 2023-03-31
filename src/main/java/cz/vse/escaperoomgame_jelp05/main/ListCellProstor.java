package cz.vse.escaperoomgame_jelp05.main;

import cz.vse.escaperoomgame_jelp05.logika.Prostor;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 Třída pro nahrání a zobrazení obrázků všech prostorů.
 */
public class ListCellProstor extends ListCell<Prostor> {
    @Override
    protected void updateItem(Prostor prostor, boolean empty) {
        super.updateItem(prostor, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        }
        else {
            setText(prostor.getNazev());
            ImageView iw = new ImageView(getClass().getResource("prostory/"+prostor.getNazev()+".jpg").toExternalForm());
            iw.setFitHeight(75);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}
