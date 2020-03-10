package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.test.Test;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //测试类
        Test test = new Test();
        test.openFile();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("探测存活主机（ipScan）");

        //创建一个新的面板
        Controller controller = new Controller();
        Scene root1 = controller.createPane(primaryStage);
        primaryStage.setScene(root1);

//        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
