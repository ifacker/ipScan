package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.dataType.ConfigType;
import sample.dataType.HostStatus;
import sample.function.FileFun;
import sample.function.IpScan;
import sample.function.LoadConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {
    public Scene createPane(Stage primaryStage) {
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
        Button btnStop = new Button("停止");

        //创建textArea
        TextArea textAreaIn = new TextArea();
        TextArea textAreaOutGood = new TextArea();
        TextArea textAreaOutBad = new TextArea();

        //创建标签
        Label labelGood = new Label("Good IP:");
        Label labelBad = new Label("Bad IP:");

        //创建一个进度控件
//        final ProgressIndicator progressIndicators = null;
        ProgressBar progressBar = new ProgressBar(0);
//        progressBar.setProgress(0.5);

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

                FileFun fileFun = new FileFun();
                BufferedReader bufferedReader = fileFun.readFile(filePath);
                String str;
                try {
                    while ((str = bufferedReader.readLine()) != null) {
                        textAreaIn.setText(textAreaIn.getText() + str + "\r\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                OpenFile openFile = new OpenFile();
//                List<String> ipList = openFile.readFile(filePath);
//                for (String ip : ipList) {
//                    textAreaIn.setText(textAreaIn.getText() + ip + "\r\n");
//                }
            }
        });
        final Thread[] tmpThread = new Thread[1];
        final ProgressIndicator[] progressIndicators = new ProgressIndicator[1];

        btnStart.setOnAction(new EventHandler<ActionEvent>() {
//            private ProgressIndicator progressIndicators;

            @Override
            public void handle(ActionEvent event) {
//                progressBar.setProgress(0);

                ProgressIndicator progressIndicator = new ProgressIndicator();
                progressIndicators[0] = progressIndicator;
                vBox.getChildren().add(progressIndicator);
                btnStart.setDisable(true);


                //创建线程
//                Thread btnStartThread = null;

                Thread btnStartThread = new Thread() {
                    public void run() {
                        //清理输出窗口
                        textAreaOutGood.setText("");
                        textAreaOutBad.setText("");

//                OpenFile openFile = new OpenFile();
//                ConfigType configType = openFile.readConfigFile();
                        //加载配置文件
                        LoadConfig loadConfig = new LoadConfig();
                        ConfigType configType = loadConfig.loadConfig("config.ini");

                        //扫描
                        IpScan ipScan = new IpScan(configType.getConfigBody());
//                ipScan.ipScanner("10.54.19.75");

                        //获取IP地址
//                System.out.println(textAreaIn.getText());
                        String ipAll = textAreaIn.getText();
                        if (!ipAll.equals("")) {
                            List<HostStatus> hostStatusList = ipScan.hostLife(ipAll);

                            for (HostStatus hostStatus : hostStatusList) {

                                //处理 good IP 和 bad IP 并分别显示
                                if (hostStatus.getStatus() != null && hostStatus.getStatus()) {
                                    textAreaOutGood.setText(textAreaOutGood.getText() + hostStatus.getHost() + "\r\n");
                                } else if (hostStatus.getStatus() != null && !hostStatus.getStatus()) {
                                    textAreaOutBad.setText(textAreaOutBad.getText() + hostStatus.getHost() + "\r\n");
                                }

                                //扫描进度
//                                int len = hostStatusList.size();
//                                int progress = 100/len;
//                                progressBar.setProgress(++progress);
//                                System.out.println(len);


                            }
//                            progressBar.setProgress(1);
//                            vBox.clearConstraints(progressIndicator);

                            //此方法可以避免 在javafx中的非主线程中操作UI而导致的报错 ！！！！牛逼
                            Platform.runLater(() -> {
                                vBox.getChildren().remove(progressIndicator);
                            });
                            btnStart.setDisable(false);
                        } else {
                            //弹窗，需要加一个类！
                        }
                    }
                };
                btnStartThread.start();

                tmpThread[0] = btnStartThread;

//                progressIndicators[0].setProgress(1);
                //这里还差一个停止功能 还有开始的时候屏蔽开始按钮


            }
        });
        btnStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tmpThread[0] != null) {
                    tmpThread[0].stop();
                    vBox.getChildren().remove(progressIndicators[0]);
                }
                btnStart.setDisable(false);
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textAreaIn.setText("");
            }
        });
        btnSetting.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                Setting setting = new Setting();

                //加载配置文件
                LoadConfig loadConfig = new LoadConfig();
                ConfigType configType = loadConfig.loadConfig("config.ini");

                Scene scene = setting.setConfig(stage, configType);
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

        //设置按钮大小
        btnGridPane.setPrefWidth(200);
        btnStart.setMaxWidth(btnGridPane.getPrefWidth());
        btnAdd.setMaxWidth((btnGridPane.getPrefWidth()));
        btnClear.setMaxWidth(btnGridPane.getPrefWidth());
        btnSetting.setMaxWidth(btnGridPane.getPrefWidth());
        btnStop.setMaxWidth(btnGridPane.getPrefWidth());

//        btnStart.setMaxWidth(double.);

        btnGridPane.add(btnStart, 0, 0);
        btnGridPane.add(btnStop, 1, 0);
        btnGridPane.add(btnAdd, 0, 1);
        btnGridPane.add(btnClear, 1, 1);
        btnGridPane.add(btnSetting, 0, 2);
//        btnGridPane.add(progressIndicator, 0, 3);
//        btnGridPane.add(progressBar, 0, 3);
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
//        vBox.getChildren().add(progressBar);
//        vBox.getChildren().add(progressIndicators[0]);

        root.add(vBox, 0, 0);

        Scene scene = new Scene(root, 670, 400);
        return scene;
    }
}
