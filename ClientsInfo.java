package com.mycompany.testapp;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClientsInfo {
    private final StringProperty name = new SimpleStringProperty();
    
    public final StringProperty nameProperty() {
        return this.name;
    }
    public final String getName() {
        return nameProperty().get();
    }
    public final void setName(String name) {
        nameProperty().set(name);
    }
    
    private final ObservableList<ProjectInfo> projectsList = FXCollections.observableArrayList();
    
    public final ObservableList<ProjectInfo> getProjectList() {
        return this.projectsList;
    }
    public final void setProjectList(List<ProjectInfo> projects) {
        if(projects != null) this.projectsList.addAll(projects);
    }
    public final ProjectInfo getProject(String name) {
        for(ProjectInfo project : projectsList) {
            if(project.getProjectName().equals(name)) {
                return project;
            }
        }
        return null;
    }
    public final boolean hasProject() {
        return !projectsList.isEmpty();
    }
    
    public ClientsInfo(String name) {
        setName(name);
    }
}