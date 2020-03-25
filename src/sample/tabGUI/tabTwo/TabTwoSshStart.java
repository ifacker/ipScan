package sample.tabGUI.tabTwo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * @Description: SSH爆破的开始页面$
 * @Author: ifacker
 * @Date: $
 */
public class TabTwoSshStart {

    Double TEXTAREA_HEIGHT = 200.0;

    public Scene startPane(List<String> listIp, List<String> listUname, List<String> listPasswd){

        //创建布局
        VBox root = new VBox(5.5);
        HBox hBoxTextArea = new HBox(5.5);
        HBox hBoxButton = new HBox(5.5);
        VBox vBoxIP = new VBox(5.5);
        VBox vBoxUname = new VBox(5.5);
        VBox vBoxPasswd = new VBox(5.5);
        VBox vBoxProgress = new VBox(5.5);

        //创建进度和显示Label
        Label labelProgress = new Label();
        ProgressBar progressBar = new ProgressBar(-1F);

        //创建 textArea
        TextArea textAreaIP = new TextArea();
        TextArea textAreaUname = new TextArea();
        TextArea textAreaPasswd = new TextArea();

        //设置 textArea 的高度
        textAreaIP.setPrefHeight(TEXTAREA_HEIGHT);
        textAreaUname.setPrefHeight(TEXTAREA_HEIGHT);
        textAreaPasswd.setPrefHeight(TEXTAREA_HEIGHT);

        //创建按钮
        Button btnStart = new Button("开始");
        Button btnStop = new Button("停止");
        Button btnOutPut = new Button("导出");

        //创建 Label
        Label labelIP = new Label("IP :");
        Label labelUname = new Label("UserName :");
        Label labelPasswd = new Label("Password :");


        vBoxIP.getChildren().addAll(labelIP, textAreaIP);
        vBoxUname.getChildren().addAll(labelUname, textAreaUname);
        vBoxPasswd.getChildren().addAll(labelPasswd, textAreaPasswd);

        vBoxProgress.getChildren().addAll(labelProgress, progressBar);

        hBoxButton.getChildren().addAll(btnStart, btnStop, btnOutPut);

        hBoxTextArea.getChildren().addAll(vBoxIP, vBoxUname, vBoxPasswd);

        root.getChildren().add(hBoxButton);
        root.getChildren().add(hBoxTextArea);
        root.getChildren().add(vBoxProgress);
        root.setPadding(new Insets(5.5));


        Scene scene = new Scene(root, 400, 300);

//        btnStart.setDisable(true);


        Thread[] thread = new Thread[1];




        //按钮赋能
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnStart.setDisable(true);
                //创建线程
                Thread threadStart = new Thread(){
                    public void run(){
                        for (String ip : listIp) {
                            for (String userName : listUname) {
                                for (String password : listPasswd) {
                                    labelProgress.setText(ip + " : " + userName + " : " + password);
                                }
                            }
                        }
                    }
                };
                thread[0] = threadStart;
                thread[0].start();
            }
        });
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnStart.setDisable(false);
                thread[0].stop();
            }
        });
        btnOutPut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        return scene;
    }

}
