package sample.tabGUI;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.tabGUI.tabTwo.TabTwoSshMod;

/**
 * @Description: 爆破模块$
 * @Author: ifacker
 * @Date: $
 */
public class TabTwo {
    public TabPane createTab(Stage primaryStage){

        //创建一个tabPane
        TabPane tabPanes = new TabPane();

        //创建 tab SSH爆破
        Tab tabSSH = new Tab("SSH");
        tabSSH.setClosable(false);
        TabTwoSshMod tabTwoSshMod = new TabTwoSshMod();
        Pane paneSSH = tabTwoSshMod.sshMod(primaryStage);
        tabSSH.setContent(paneSSH);

        //创建 tab 3389爆破
        Tab tab3389 = new Tab("3389");
        tab3389.setClosable(false);


        tabPanes.getTabs().add(tabSSH);
        tabPanes.getTabs().add(tab3389);


        return tabPanes;
    }
}
