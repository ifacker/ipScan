package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {
    public Scene createPane(){
        //以 GridPane 作为底板
        GridPane root = new GridPane();

        //创建垂直排列的面板
        VBox vBox = new VBox(5.5);

        //创建水平排列的面板
        HBox hBox = new HBox(5.5);

        //创建按钮
        Button btnAdd = new Button("导入 IP");

        //创建textArea
        TextArea textArea = new TextArea();
        textArea.setAccessibleText("请输入 IP 地址...");
        textArea.

        hBox.getChildren().add(textArea);
        hBox.getChildren().add(btnAdd);

        vBox.getChildren().add(hBox);

        root.add(vBox, 0, 0);

        Scene scene = new Scene(root);
        return scene;
    }
}
