package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sample.bean.ConfigType;
import sample.function.FileFun;

import java.io.File;


public class Setting {
    public Scene setConfig(Stage stage, ConfigType configType){
        //创建一个 FlowPane
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(5.5));
        flowPane.setVgap(5.5);
        flowPane.setHgap(5.5);

        //创建标签对象
        Label labelSetNmapPath = new Label("nmap 的绝对路径：");

        //创建一个输入框
        TextField textFieldSetNamePath = new TextField();
        if (configType != null) {
            textFieldSetNamePath.setText(configType.getConfigBody());
        }

        //创建按钮
        Button btnSubmit = new Button("确定");
        Button btnSelectDir = new Button("浏览...");

        btnSelectDir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File file = directoryChooser.showDialog(stage);
                if (file == null){
                    return;
                }
                textFieldSetNamePath.setText(file.getPath());
            }
        });
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileFun fileFun = new FileFun();
                String nmapConfig = "nmapPath:" + textFieldSetNamePath.getText() + ";\r\n";
                String filePath = "config.ini";
                fileFun.saveFile(filePath, nmapConfig);
                stage.close();
            }
        });


        flowPane.getChildren().add(labelSetNmapPath);
        flowPane.getChildren().add(textFieldSetNamePath);
        flowPane.getChildren().add(btnSelectDir);

        flowPane.getChildren().add(btnSubmit);


        Scene scene = new Scene(flowPane, 400, 400);
        return scene;
    }
}
