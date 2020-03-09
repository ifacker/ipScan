package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sun.font.TextLabel;

public class Controller {
    public Scene createPane(){
        //以 GridPane 作为底板
        GridPane root = new GridPane();

        //创建垂直排列的面板
        VBox vBox = new VBox(5.5);
        VBox vBoxGood = new VBox(5.5);
        VBox vBoxBad = new VBox(5.5);

        //创建水平排列的面板
        HBox hBoxUp = new HBox(5.5);
        HBox hBoxDown = new HBox(5.5);

        //创建按钮
        Button btnAdd = new Button("导入 IP");
        Button btnStart = new Button("开始扫描");

        //创建textArea
        TextArea textAreaIn = new TextArea();
        TextArea textAreaOutGood = new TextArea();
        TextArea textAreaOutBad = new TextArea();

        //创建标签
        Label labelGood = new Label("Good IP:");
        Label labelBad = new Label("Bad IP:");


        textAreaIn.setPromptText("请输入 IP 地址...\r\n比如：\r\n192.168.1.1\r\n192.168.1.2");
        textAreaOutGood.setPromptText("这里显示可以访问的 IP 地址...");
        textAreaOutBad.setPromptText("这里显示访问失败的 IP 地址...");

        vBox.setPadding(new Insets(5.5));

        hBoxUp.getChildren().add(textAreaIn);
        hBoxUp.getChildren().add(btnAdd);
        hBoxUp.getChildren().add(btnStart);

        vBoxGood.getChildren().add(labelGood);
        vBoxGood.getChildren().add(textAreaOutGood);

        vBoxBad.getChildren().add(labelBad);
        vBoxBad.getChildren().add(textAreaOutBad);

        hBoxDown.getChildren().add(vBoxGood);
        hBoxDown.getChildren().add(vBoxBad);

        vBox.getChildren().add(hBoxUp);
        vBox.getChildren().add(hBoxDown);


        root.add(vBox, 0, 0);

        Scene scene = new Scene(root, 670, 400);
        return scene;
    }
}
