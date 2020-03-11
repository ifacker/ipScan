package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.function.OpenFile;

import java.io.File;
import java.util.List;

public class Controller {
    public Scene createPane(Stage primaryStage){
        //以 GridPane 作为底板
        GridPane root = new GridPane();

        //创建一个 GridPane 面板，主要是为了排列 hBoxUp 的按钮
        GridPane btnGridPane = new GridPane();
//        btnGridPane.setPadding(new Insets(5.5));
        btnGridPane.setVgap(5.5);
        btnGridPane.setHgap(5.5);

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
        Button btnClear = new Button("清理 IP");
        Button btnFuck = new Button("一键日卫星");
        Button btnSetting = new Button("设置");

        //创建textArea
        TextArea textAreaIn = new TextArea();
        TextArea textAreaOutGood = new TextArea();
        TextArea textAreaOutBad = new TextArea();

        //创建标签
        Label labelGood = new Label("Good IP:");
        Label labelBad = new Label("Bad IP:");

        //给各个控件赋能
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file == null) {
                    return;
                }
                //获取选择目录的地址
                String filePath = file.getPath();

                //打开并读取文件
                OpenFile openFile = new OpenFile();
                List<String> ipList = openFile.readFile(filePath);
                for (String ip:ipList){
                    textAreaIn.setText(textAreaIn.getText() + ip+ "\r\n" );
                }
            }
        });
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        btnSetting.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                Setting setting = new Setting();
                Scene scene = setting.setConfig(stage);
                stage.setScene(scene);
                stage.show();
            }
        });

        textAreaIn.setPromptText("请输入 IP 地址...\r\n比如：\r\n192.168.1.1\r\n192.168.1.2");
        textAreaOutGood.setPromptText("这里显示可以访问的 IP 地址...");
        textAreaOutBad.setPromptText("这里显示访问失败的 IP 地址...");

        vBox.setPadding(new Insets(5.5));

        hBoxUp.getChildren().add(textAreaIn);
        hBoxUp.getChildren().add(btnGridPane);
        btnGridPane.add(btnStart, 0, 0);
        btnGridPane.add(btnAdd, 0, 1);
        btnGridPane.add(btnClear, 0,2);
        btnGridPane.add(btnSetting, 0, 3);
//        btnGridPane.add(btnFuck, 0,3);
//        hBoxUp.getChildren().add(btnAdd);
//        hBoxUp.getChildren().add(btnStart);

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
