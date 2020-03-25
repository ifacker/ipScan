package sample.test.smb;

import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @Description: smb资源管理器主函数$
 * @Author: ifacker
 * @Date: $
 */



public class TreeViews extends Application {
    public static ObservableList<FileDetail> data = FXCollections.observableArrayList();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Javafx 实现\"我的电脑\"资源管理器");
        String path = "~/";
        TreeItem<File> rootItem = new TreeItem<>(new File(path));
        for (File file : File.listRoots()) {
            FileTreeItem rootsitem = new FileTreeItem(file);
            rootItem.getChildren().add(rootsitem);
        }
        TreeView<File> tree = new TreeView<File>(rootItem);
        HBox root = new HBox();
        TableView<FileDetail> tableView = new TableView<>(data);
        TableColumn<FileDetail, String> firstColumn = new TableColumn<>("文件");
        firstColumn.setCellValueFactory(new PropertyValueFactory<FileDetail, String>("FileName"));
        firstColumn.setPrefWidth(120);
        TableColumn<FileDetail, String> secondColumn = new TableColumn<>("类型");
        secondColumn.setCellValueFactory(new PropertyValueFactory<FileDetail, String>("type"));
        secondColumn.setPrefWidth(120);
        TableColumn<FileDetail, String> thirdColumn = new TableColumn<>("最后修改");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<FileDetail, String>("LastModified"));
        thirdColumn.setPrefWidth(200);
        tableView.getColumns().setAll(firstColumn, secondColumn, thirdColumn);
        HBox.setHgrow(tree, Priority.ALWAYS);
        HBox.setHgrow(tableView, Priority.ALWAYS);
        root.getChildren().addAll(tree,tableView);
        tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<File>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<File>> observable, TreeItem<File> oldValue,
                                TreeItem<File> newValue) {
                ObservableList<TreeItem<File>> treelist = newValue.getChildren();
                ObservableList<FileDetail> tablelist = FXCollections.observableArrayList();
                for (TreeItem<File> item : treelist) {
                    FileDetail filedetail = new FileDetail(item.getValue());
                    tablelist.add(filedetail);
                }
                data.setAll(tablelist);
            }
        });
        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(600);
        primaryStage.show();
    }

}
