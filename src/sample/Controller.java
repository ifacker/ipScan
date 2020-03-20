package sample;


import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.tabGUI.TabOne;
import sample.tabGUI.TabTwo;

public class Controller {
    public Scene createPane(Stage primaryStage) {
        TabPane root = new TabPane();

        //创建选项卡
        TabOne tabOne = new TabOne();
        Pane pane1 = tabOne.createTab(primaryStage);

        TabTwo tabTwo = new TabTwo();
        TabPane pane2 = tabTwo.createTab(primaryStage);

        //添加 选项卡
        Tab tab1 = new Tab("主机探测");
        tab1.setContent(pane1);
        tab1.setClosable(false);

        Tab tab2 = new Tab("暴力破解");
        tab2.setContent(pane2);
        tab2.setClosable(false);

        root.getTabs().add(tab1);
        root.getTabs().add(tab2);

        Scene scene = new Scene(root, 670, 450);
        return scene;
    }
}
