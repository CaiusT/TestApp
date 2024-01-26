package com.mycompany.testapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    private Parent root;
    
    private final MainModel model = new MainModel();
    
    @Override
    public void start(Stage stage) throws Exception {
        model.loadData();
        
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("projectTab.fxml"));
        root = mainLoader.load();
        
        ProjectTabController mainController = mainLoader.getController();
        mainController.initModel(model);
        
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        
        stage.setScene(scene);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
