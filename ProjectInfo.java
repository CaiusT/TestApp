package com.mycompany.testapp;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectInfo {
    private final StringProperty projectName = new SimpleStringProperty();
    public final StringProperty projectNameProperty() {
        return this.projectName;
    }
    public final String getProjectName() {
        return projectNameProperty().get();
    }
    public final void setProjectName(String name) {
        projectNameProperty().set(name);
    }
    
    private final ObservableList<PlanInfo> plansList = FXCollections.observableArrayList();
    public ObservableList<PlanInfo> getPlansList() {
        return plansList;
    }
    public final void setPlansList(List<PlanInfo> list) {
        if(!list.isEmpty()) {
            for(PlanInfo plan : list) {
                plansList.add(plan);
            }
        }
    }
    public void addPlan(PlanInfo plan) {
        if(plan!= null) {
            plansList.add(plan);
            plansList.sort(new PlansComparator());
        }
    }
    public void deletePlan(int planNumber) {
        for(int i = 0; i < plansList.size(); i++) {
            if(plansList.get(i).getPlanNumber() == planNumber) {
                plansList.remove(i);
                break;
            }
        }
    }
    
    public ProjectInfo(String name, List<PlanInfo> plans) {
        setProjectName(name);
        if(plans != null) setPlansList(plans);
    }
    
    class PlansComparator implements java.util.Comparator<PlanInfo> {
        @Override
        public int compare(PlanInfo a, PlanInfo b) {
            return a.getPlanNumber() - b.getPlanNumber();
        }
    }
}
