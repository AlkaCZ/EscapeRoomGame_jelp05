package cz.vse.escaperoomgame_jelp05.main;

import cz.vse.escaperoomgame_jelp05.logika.Hra;
import cz.vse.escaperoomgame_jelp05.logika.IHra;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HomeController {
    @FXML
    private Button odesliButton;
    @FXML
    private TextArea vystup;
    @FXML
    private TextField vstup;

    private IHra hra = new Hra();
    @FXML
    private void initialize(){
     vystup.appendText(hra.vratUvitani()+"\n");
        Platform.runLater(() -> vstup.requestFocus());
    
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
}
