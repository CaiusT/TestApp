package com.mycompany.testapp;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PlanListCell extends ListCell<PlanInfo> {
    @FXML
    private BorderPane planPane;
    @FXML
    private Label planNumber;
    @FXML
    private Label planName;
    @FXML
    private ListView revisionList;
    @FXML
    private HBox btnBox;
    @FXML
    private Button deletePlan;
    
    private MainModel model;
    private ProjectInfo project;
    private FXMLLoader loader;
    private PlanInfo plan;
    
    public PlanListCell(MainModel model, ProjectInfo project) {
        if(this.model != null) {
            throw new IllegalStateException("Model can only be initialized once.");
        }
        this.model = model;
        this.project = project;
        
        setStyle("-fx-background-color: #FFFFFF");
        
        selectedProperty().addListener((obs, oldValue, newValue) -> {
            loader = new FXMLLoader(getClass().getResource("planListCellOpen.fxml"));
            loader.setController(this);
            
            try {
                loader.load();
            } catch(IOException io) {
                Logger.getLogger(PlanListCell.class.getName()).log(Level.SEVERE, null, io);
            }
            
            btnBox.setVisible(false);
            btnBox.setManaged(false);
            
            if(!isSelected()) {
                loader = new FXMLLoader(getClass().getResource("planListCell.fxml"));
                loader.setController(this);
                
                try {
                    loader.load();
                } catch (IOException io) {
                    Logger.getLogger(PlanListCell.class.getName()).log(Level.SEVERE, null, io);
                }
                loader.getController();
            }
            
            planName.setText(plan.getPlanName());
            planNumber.setText("0" + String.valueOf(plan.getPlanNumber()));
            revisionList.setItems(plan.getRevisionList());
            revisionList.setCellFactory(revCellList -> new PlanRevisionCell(model));
            
            setText(null);
            setGraphic(planPane);
            
            revisionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String[]>() {
                @Override
                public void changed(ObservableValue<? extends String[]> observable, String[] oldValue, String[] newValue) {
                    model.setSelectedPlant(newValue);
                    btnBox.setVisible(true);
                    btnBox.setManaged(true);
                }
            });
        });
    }
    
    @Override
    public void updateItem(PlanInfo plan, boolean empty) {
        super.updateItem(plan, empty);
        
        if(empty || plan == null) {
            setText(null);
            setGraphic(null);
        } else {
            if(loader == null) {
                loader = new FXMLLoader(getClass().getResource("planListCell.fxml"));
                loader.setController(this);
                
                try {
                    loader.load();
                } catch(IOException io) {
                    Logger.getLogger(PlanListCell.class.getName()).log(Level.SEVERE, null, io);
                }
                
                this.plan = plan;
                
                planName.setText(plan.getPlanName());
                planNumber.setText("0" + String.valueOf(plan.getPlanNumber()));
                
                setText(null);
                setGraphic(planPane);
            }
        }
    }
    
    @FXML
    public void deletePlan(ActionEvent event) {
        ButtonType all = new ButtonType("All revisions");
        ButtonType rev = new ButtonType("Revision 0" + model.getSelectedPlant()[0]);
        ButtonType cancel = new ButtonType("Back", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete all plans from " + plan.getPlanName() + " or just Revision 0" + model.getSelectedPlant()[0] + "?",all, rev, cancel);
            
        confirm.setTitle("Delete plan");
        confirm.setHeaderText(null);
        
        Optional<ButtonType> result = confirm.showAndWait();
        
        if(!result.isPresent() || result.get() == ButtonType.CLOSE) {
            Alert inf = new Alert(Alert.AlertType.INFORMATION);
            
            inf.setTitle("Warning");
            inf.setHeaderText(null);
            inf.setContentText("Operation cancelled.");
            inf.show();
        } else if(result.get() == all) {
            project.deletePlan(plan.getPlanNumber());
        } else if(result.get() == rev) {
            plan.deleteRevision(model.getSelectedPlant()[0]);
        }
    }
}
