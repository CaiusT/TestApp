package com.mycompany.testapp;

import java.util.Collections;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JFileChooser;

public class PlanInfo {
    private final StringProperty planName = new SimpleStringProperty();
    public final StringProperty planNameProperty() {
        return planName;
    }
    public final String getPlanName() {
        return planNameProperty().get();
    }
    public final void setPlanName(String name) {
        planNameProperty().set(name);
    }
    
    private final IntegerProperty planNumber = new SimpleIntegerProperty();
    public final IntegerProperty planNumberProperty() {
        return planNumber;
    }
    public final int getPlanNumber() {
        return planNumberProperty().get();
    }
    public final void setPlanNumber(int number) {
        planNumberProperty().set(number);
    }
    
    private final ObservableList<String[]> revisionList = FXCollections.observableArrayList();
    public void addRevision() {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setDialogTitle("Add a file");
        int returnVal = fileChooser.showOpenDialog(null);
        
        if(returnVal != JFileChooser.APPROVE_OPTION) {
            Alert inf = new Alert(Alert.AlertType.INFORMATION);
            
            inf.setTitle("Warning");
            inf.setHeaderText(null);
            inf.setContentText("A file has not been choosen.");
            inf.show();
        } else {
            if(revisionList.isEmpty()) {
                revisionList.add(new String[]{"1", String.valueOf(fileChooser.getSelectedFile().toPath())});
            } else {
                revisionList.add(new String[]{String.valueOf(revisionList.size()+1), String.valueOf(fileChooser.getSelectedFile().toPath())});
            }
            if(revisionList.size() > 1) revisionList.sort(new RevisionComparator().reversed());
        }
    }
    public final ObservableList<String[]> getRevisionList() {
        return revisionList;
    }
    public final void setRevisionList(List<String[]> list) {
        if(!list.isEmpty()) {
            for(String[] item : list) {
                revisionList.add(item);
            }
            Collections.reverse(revisionList);
        }
    }
    public void deleteRevision(String revision) {
        for(int i = 0; i < revisionList.size(); i++) {
            if(revisionList.get(i)[0].equals(revision)) {
                revisionList.remove(i);
                break;
            }
        }
    }
    
    public PlanInfo(String name, int number) {
        setPlanName(name);
        setPlanNumber(number);
    }
    
    public PlanInfo(String name, int number, List<String[]> list) {
        setPlanName(name);
        setPlanNumber(number);
        setRevisionList(list);
    }
    
    class RevisionComparator implements java.util.Comparator<String[]> {
        @Override
        public int compare(String[] a, String[] b) {
            return Integer.parseInt(a[0]) - Integer.parseInt(b[0]);
        }
    }
}
