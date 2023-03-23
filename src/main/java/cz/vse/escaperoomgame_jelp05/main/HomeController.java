package cz.vse.escaperoomgame_jelp05.main;

import cz.vse.escaperoomgame_jelp05.logika.Hra;
import cz.vse.escaperoomgame_jelp05.logika.IHra;
import cz.vse.escaperoomgame_jelp05.logika.Prostor;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class HomeController implements Pozorovatel {
    @FXML
    private ListView panelVychodu;
    @FXML
    private Button odesliButton;
    @FXML
    private TextArea vystup;
    @FXML
    private TextField vstup;

    private IHra hra = new Hra();
    private ObservableList<Prostor>  seznamVychodu = FXCollections.observableArrayList();
    @FXML
    private void initialize(){
     vystup.appendText(hra.vratUvitani()+"\n");
     Platform.runLater(() -> vstup.requestFocus());
     panelVychodu.setItems(seznamVychodu);
     hra.getHerniPlan().registruj(this);
     aktualizujSeznamVychodu();
    }
    @FXML
    private void aktualizujSeznamVychodu(){
     seznamVychodu.clear();
     seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }
@FXML
    private void odesliVstup(ActionEvent actionEvent) {
    String prikaz = vstup.getText();
    vystup.appendText("> "+prikaz +"\n\n");
    String vysledek = hra.zpracujPrikaz(prikaz);
    vystup.appendText(vysledek +"\n\n");
    vstup.clear();

    if (hra.konecHry()){
        vystup.appendText(hra.vratEpilog());
        vstup.setDisable(true);
        odesliButton.setDisable(true);
        }
    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert ukonceni = new Alert(Alert.AlertType.CONFIRMATION, "Opravdu chceš ukončit hru ?");
        Optional<ButtonType> result = ukonceni.showAndWait();
        if (result.isPresent() && result.get()  == ButtonType.OK){
            Platform.exit();
        }
    }

    @Override
    public void aktualizuj() {
        aktualizujSeznamVychodu();
    }
}
