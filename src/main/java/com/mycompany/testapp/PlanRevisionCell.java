package com.mycompany.testapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;

public class PlanRevisionCell extends ListCell<String[]> {
    @FXML
    private BorderPane revisionPane;
    @FXML
    private Label revisionName;
    
    private MainModel model;
    private FXMLLoader loader;
    
    public PlanRevisionCell(MainModel model) {
        if(this.model != null) {
            throw new IllegalStateException("Model can only be initialized once.");
        }
        this.model = model;
        
        setStyle("-fx-background-color: #FFFFFF");
        
        selectedProperty().addListener((obs, oldValue, newValue) -> {
            revisionPane.setStyle("-fx-background-color: #79A138; -fx-background-radius: 5px; -fx-border-color: #79A138; -fx-border-radius: 5px");
            
            if(!isSelected()) {
                revisionPane.setStyle("-fx-background-color: #98CA48; -fx-background-radius: 5px; -fx-border-color: #98CA48; -fx-border-radius: 5px");
            }
            
            setText(null);
            setGraphic(revisionPane);
        });
    }
    
    @Override
    public void updateItem(String[] revision, boolean empty) {
        super.updateItem(revision, empty);
        
        if(empty || revision == null) {
            setText(null);
            setGraphic(null);
        } else {
            if(loader == null) {
                loader = new FXMLLoader(getClass().getResource("planRevisionCell.fxml"));
                loader.setController(this);
                
                try {
                    loader.load();
                } catch(IOException io) {
                    Logger.getLogger(PlanRevisionCell.class.getName()).log(Level.SEVERE, null, io);
                }
                
                revisionName.setText("REV 0" + revision[0]);
                
                setText(null);
                setGraphic(revisionPane);
            }
        }
    }
}
