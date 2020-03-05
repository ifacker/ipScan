package sample;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class Controller {
    public Scene createPane(){
        //以 GridPane 作为底板
        GridPane root = new GridPane();

        Scene scene = new Scene(root);
        return scene;
    }
}
