package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private MenuItem menuItemSeller;
    @FXML
    private MenuItem menuItemDepartment;
    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemSellerAction(){
        System.out.println("Hello World");
    }
    @FXML
    public void onMenuItemDepartmentAction() throws IOException {
        loadView("DepartmentListView.fxml");
    }
    @FXML
    public void onMenuItemAboutAction() throws IOException {
        loadView("AboutView.fxml");
    }


    private void loadView(String viewPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
        VBox newVBox = loader.load();

        Scene mainScene = HelloApplication.getMainScene();
        VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

        Node mainMenu = mainVBox.getChildren().getFirst();
        mainVBox.getChildren().clear();

        mainVBox.getChildren().add(mainMenu);
        mainVBox.getChildren().addAll(newVBox.getChildren());
    }

    @Override
    public void initialize(URL uri, ResourceBundle rd) {

    }
}