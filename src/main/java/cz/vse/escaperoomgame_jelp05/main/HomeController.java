package cz.vse.escaperoomgame_jelp05.main;

import cz.vse.escaperoomgame_jelp05.logika.*;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 Třída která ovládá FXML rozhraní hry
 */
public class HomeController {
    /**
     Proměná pro seznam věcí v inventáři, který so zobrazuje v FXML.
     */
    @FXML
    private ListView inventarList;
    /**
     Proměná pro zobrazení hry z pohledu hráče.
     */
    @FXML
    private ImageView hrac;
    /**
     Proměná pro seznam prostorů ve hře, který so zobrazuje v FXML.
     */
    @FXML
    private ListView<Prostor> panelVychodu;
    /**
     Proměná pro tlačítko pro odeslání příkazu v FXML.
     */
    @FXML
    private Button odesliButton;
    /**
     Proměná pro tabuly která vypisuje průběh hry.
     */
    @FXML
    private TextArea vystup;
    /**
     Proměná pro TextField do kterého uživatel zadává své příkazy.
     */
    @FXML
    private TextField vstup;
    /**
     Vytvoření instance hry.
     */
    private IHra hra = new Hra();
    /**
     Vytboření seznamu vychodu z danné místnosti.
     */
    private ObservableList<Prostor>  seznamVychodu = FXCollections.observableArrayList();
    /**
    Vytboření seznamu souřadnic pro pohyb hráče na hracím poly.
     */
    private Map<String, Point2D> souradniceProstoru = new HashMap<>();
    /**
     *  Vytváří seznamu Inventáře.
     */
    private ObservableList<Vec> seznamInventare = FXCollections.observableArrayList();
    @FXML
    private void initialize(){
     vystup.appendText(hra.vratUvitani()+"\n");
     Platform.runLater(() -> vstup.requestFocus());
     panelVychodu.setItems(seznamVychodu);
     inventarList.setItems(seznamInventare);
     hra.getHerniPlan().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
         aktualizujSeznamVychodu();
         aktualizujPolohuHrace();
     });
     hra.registruj(ZmenaHry.ZMENA_HRY, () -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
        vlozSouradnice();
        panelVychodu.setCellFactory(param -> new ListCellProstor());

    }

    private void vlozSouradnice() {
        souradniceProstoru.put("Předsíň", new Point2D(-28,175));
        souradniceProstoru.put("Chodba", new Point2D(159,175));
        souradniceProstoru.put("Laboratoř", new Point2D(330,280));
        souradniceProstoru.put("Řídící_místnost", new Point2D(333,63));
        souradniceProstoru.put("Osobní_pokoj", new Point2D(520,144));
        souradniceProstoru.put("Skrytá_místnost", new Point2D(534,-8));
    }

    @FXML
    private void aktualizujSeznamVychodu(){
     seznamVychodu.clear();
     seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }

    private void aktualizujPolohuHrace(){
        String prostor = hra.getHerniPlan().getAktualniProstor().getNazev();
        hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
        hrac.setLayoutY(souradniceProstoru.get(prostor).getY());
    }
    @FXML
    private void aktualizujSeznamInventare(){
        seznamInventare.clear();
        seznamInventare.addAll(hra.getHerniPlan().getKosicek().getSeznamVeci());

    }
    private void aktualizujKonecHry() {
        if (hra.konecHry()){
            vystup.appendText(hra.vratEpilog());
        }

        vstup.setDisable(hra.konecHry());
        odesliButton.setDisable(hra.konecHry());
         panelVychodu.setDisable(hra.konecHry());
    }
@FXML
    private void odesliVstup(ActionEvent actionEvent) {
    String prikaz = vstup.getText();
    vstup.clear();
    zpracujPrikaz(prikaz);
    aktualizujSeznamInventare();
}

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> "+ prikaz +"\n\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek +"\n\n");
    }
    /**
     *  Metoda která se spouští při ukončení hry přes tlačítko.
     */
    public void ukoncitHru(ActionEvent actionEvent) {
        Alert ukonceni = new Alert(Alert.AlertType.CONFIRMATION, "Opravdu chceš ukončit hru ?");
        Optional<ButtonType> result = ukonceni.showAndWait();
        if (result.isPresent() && result.get()  == ButtonType.OK){
            Platform.exit();
        }
    }



    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
       Prostor cil = panelVychodu.getSelectionModel().getSelectedItem();
       if (cil==null)return;
       String prikaz = PrikazJdi.NAZEV + " " + cil.getNazev();
       zpracujPrikaz(prikaz);
    }
    @FXML
    private void napovedaKlik(ActionEvent actionEvent) {
        Stage napovedaStage = new Stage();
        WebView webView = new WebView();
        Scene napovedaScena = new Scene(webView);
        napovedaStage.setScene(napovedaScena);
        napovedaStage.show();
        webView.getEngine().load(getClass().getResource("napoveda.html").toExternalForm());
    }
    @FXML
    private void opakujKlik(ActionEvent actionEvent) {
        Alert opakuj = new Alert(Alert.AlertType.CONFIRMATION, "Opravdu chceš zopakovat hru ?");
        Optional<ButtonType> result = opakuj.showAndWait();
        if (result.isPresent() && result.get()  == ButtonType.OK){
            vystup.clear();
            hra = new Hra();
            aktualizujPolohuHrace();
            initialize();
            aktualizujKonecHry();
        }
    }
}
