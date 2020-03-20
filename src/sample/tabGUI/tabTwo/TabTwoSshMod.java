package sample.tabGUI.tabTwo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.function.FileFun;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * @Description: SSH 爆破模块$
 * @Author: ifacker
 * @Date: $
 */




public class TabTwoSshMod {

    Double BUTTON_WIDTH = 90.0;

    public Pane sshMod(Stage primaryStage){

        //textArea IP
        TextArea textAreaIP = new TextArea();
        textAreaIP.setPadding(new Insets(3));
        textAreaIP.setPromptText("请输入 IP 地址...\r\n比如：\r\n192.168.1.1\r\n192.168.1.2");

        //textArea UserName
        TextArea textAreaUserName = new TextArea();
        textAreaUserName.setPadding(new Insets(3));
        textAreaUserName.setPromptText("请输入 UserName...\r\n比如：\r\nadministrator\r\nroot");

        //textArea Passwrod
        TextArea textAreaPassword = new TextArea();
        textAreaPassword.setPadding(new Insets(3));
        textAreaPassword.setPromptText("请输入 Passwrod...\r\n比如：\r\n123456\r\nroot");

        //button
        Button buttonAddIP = new Button("导入 IP");
        Button buttonClearIP = new Button("清除 IP");
        Button buttonAddUserName = new Button("导入用户名");
        Button buttonClearUserName = new Button("清除用户名");
        Button buttonAddPassword = new Button("导入密码");
        Button buttonClearPassword = new Button("清除密码");
        Button buttonStart = new Button("开始");

        //创建一个进度条
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.5);


        //GridPane
        GridPane gridPaneBtnIP = new GridPane();
        gridPaneBtnIP.setHgap(5.5);
        gridPaneBtnIP.setVgap(5.5);
        gridPaneBtnIP.add(buttonAddIP, 0, 0);

        GridPane gridPaneBtnUserName = new GridPane();
        gridPaneBtnUserName.setPadding(new Insets(5.5));
        gridPaneBtnUserName.add(buttonAddUserName, 0, 0);


/*        //这里使用的是 vbox + hbox 布局
        //hbox
        HBox hBoxIP = new HBox(5.5);
        HBox hBoxUserName = new HBox(5.5);
        HBox hBoxPassword = new HBox(5.5);
        HBox hBoxStart = new HBox(5.5);

//        hBoxIP.setPadding(new Insets(2.5));
        hBoxIP.getChildren().add(textAreaIP);
        hBoxIP.getChildren().add(buttonAddIP);

//        hBoxUserName.setPadding(new Insets(2.5));
        hBoxUserName.getChildren().add(textAreaUserName);
        hBoxUserName.getChildren().add(buttonAddUserName);

//        hBoxPassword.setPadding(new Insets(2.5));
        hBoxPassword.getChildren().add(textAreaPassword);
        hBoxPassword.getChildren().add(buttonAddPassword);

        hBoxStart.getChildren().add(progressBar);
        hBoxStart.getChildren().add(buttonStart);

        //vbox
        VBox vBox = new VBox(5.5);

        vBox.getChildren().add(hBoxIP);
        vBox.getChildren().add(hBoxUserName);
        vBox.getChildren().add(hBoxPassword);
        vBox.getChildren().add(hBoxStart);

        vBox.setPadding(new Insets(5.5));

 */

    //这里使用gridPane布局

        //创建Label
//        Label labelIP = new Label("IP 地址：");
//        Label labelUserName = new Label("用户名：");
//        Label labelPassword = new Label("密码：");

        //vbox放button和Label
        VBox vBoxIP = new VBox(5.5);
        VBox vBoxUserName = new VBox(5.5);
        VBox vBoxPassword = new VBox(5.5);

//        vBoxIP.getChildren().add(labelIP);
        vBoxIP.getChildren().add(buttonAddIP);
        vBoxIP.getChildren().add(buttonClearIP);
//        vBoxUserName.getChildren().add(labelUserName);
        vBoxUserName.getChildren().add(buttonAddUserName);
        vBoxUserName.getChildren().add(buttonClearUserName);
//        vBoxPassword.getChildren().add(labelPassword);
        vBoxPassword.getChildren().add(buttonAddPassword);
        vBoxPassword.getChildren().add(buttonClearPassword);

        //创建gridPane布局
        GridPane gridPane = new GridPane();

        //textarea靠左，button靠右布局
        gridPane.add(textAreaIP, 0, 0);
        gridPane.add(vBoxIP, 1, 0);
        gridPane.add(textAreaUserName, 0, 1);
        gridPane.add(vBoxUserName, 1, 1);
        gridPane.add(textAreaPassword, 0, 2);
        gridPane.add(vBoxPassword, 1, 2);
        gridPane.add(progressBar, 0, 3);
        gridPane.add(buttonStart, 1, 3);


        /*
        //textarea靠右，button靠左布局
        gridPane.add(vBoxIP, 0, 0);
        gridPane.add(textAreaIP, 1, 0);
        gridPane.add(vBoxUserName, 0, 1);
        gridPane.add(textAreaUserName, 1, 1);
        gridPane.add(vBoxPassword, 0, 2);
        gridPane.add(textAreaPassword, 1, 2);
        gridPane.add(buttonStart, 0, 3);
        gridPane.add(progressBar, 1, 3);
        */

        //设置按钮尺寸
        buttonAddIP.setPrefWidth(BUTTON_WIDTH);
        buttonAddUserName.setPrefWidth(BUTTON_WIDTH);
        buttonAddPassword.setPrefWidth(BUTTON_WIDTH);
        buttonStart.setPrefWidth(BUTTON_WIDTH);
        buttonClearIP.setPrefWidth(BUTTON_WIDTH);
        buttonClearUserName.setPrefWidth(BUTTON_WIDTH);
        buttonClearPassword.setPrefWidth(BUTTON_WIDTH);

        gridPane.setVgap(5.5);
        gridPane.setHgap(5.5);
        gridPane.setPadding(new Insets(5.5));


        //给 button 按钮赋值
        buttonAddIP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file == null) {
                    return;
                }
                String filePath = file.getPath();
                FileFun fileFun = new FileFun();
                BufferedReader bufferedReader = fileFun.readFile(filePath);
                String str = "";
                while (true) {
                    try {
                        if ((str = bufferedReader.readLine()) == null) break;
                        textAreaIP.setText(textAreaIP.getText() + str + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        buttonAddUserName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileFun fileFun = new FileFun();
                File file = fileChooser.showOpenDialog(primaryStage);
                BufferedReader bufferedReader = fileFun.readFile(file.getPath());
                String str = "";
                while (true) {
                    try {
                        if (((str = bufferedReader.readLine()) == null)) break;
                        textAreaUserName.setText(textAreaUserName.getText() + str + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        buttonAddPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(primaryStage);
                FileFun fileFun = new FileFun();
                BufferedReader bufferedReader = fileFun.readFile(file.getPath());
                String str = "";
                while (true) {
                    try {
                        if (((str = bufferedReader.readLine()) == null)) break;
                        textAreaPassword.setText(textAreaPassword.getText() + str + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        buttonClearIP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textAreaIP.setText("");
            }
        });
        buttonClearUserName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textAreaUserName.setText("");
            }
        });
        buttonClearPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textAreaPassword.setText("");
            }
        });
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        return gridPane;
    }
}
