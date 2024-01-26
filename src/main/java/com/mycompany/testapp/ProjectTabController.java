package com.mycompany.testapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


public class ProjectTabController implements Initializable {

    @FXML
    private ListView planList;
    
    private MainModel model;
    private ProjectInfo project;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void initModel(MainModel model) {
        if(this.model != null) {
            throw new IllegalStateException("Model can only be initialized once.");
        }
        this.model = model;
        this.project = model.getClient().getProjectList().get(0);
        
        planList.setItems(project.getPlansList());
        planList.setCellFactory(plantasCellList -> new PlanListCell(model, project));
    }
}
