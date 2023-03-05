module cz.vse.escaperoomgame_jelp05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.escaperoomgame_jelp05.main to javafx.fxml;
    exports cz.vse.escaperoomgame_jelp05.main;
}