package com.mycompany.testapp;

import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class MainModel {
    
    private final ObjectProperty<String[]> selectedPlant = new SimpleObjectProperty<>();
    public ObjectProperty<String[]> selectedPlantProperty() {
        return selectedPlant;
    }
    public final String[] getSelectedPlant() {
        return selectedPlantProperty().get();
    }
    public final void setSelectedPlant(String[] planta) {
        selectedPlantProperty().set(planta);
    }
    
    private final ClientsInfo santalucia = new ClientsInfo("Santalucia Alimentos");
    
    public final ClientsInfo getClient() {
        return santalucia;
    }
    
    public void loadData() {
        santalucia.setProjectList(new ArrayList<ProjectInfo>(){
            {
                add(new ProjectInfo("ETA Concreto", /*This is the source of items for the ListView malfunctioning ->*/ new ArrayList<PlanInfo>(){{
                    add(new PlanInfo("Flowchart", 1, new ArrayList<String[]>(){{
                        add(new String[] {"1", "S:\\CLIENTES\\Blue Ville\\ETA\\Executivo - Atual\\Rev 01\\PDF - EXECUTIVO\\01 - A3 - Rev01 - FLUXOGRAMA.pdf"});
                        add(new String[] {"2", "S:\\CLIENTES\\Blue Ville\\ETA\\Executivo - Atual\\Rev 02\\PDF - EXECUTIVO\\01 - A3 - Rev02 - FLUXOGRAMA.pdf"});
                    }}));
                    add(new PlanInfo("Localization", 2, new ArrayList<String[]>(){{
                        add(new String[] {"1", "S:\\CLIENTES\\Blue Ville\\ETA\\Executivo - Atual\\Rev 01\\PDF - EXECUTIVO\\02 - A2 - Rev01 - LOCALIZAÇÃO.pdf"});
                    }}));
                    add(new PlanInfo("Layout", 3, new ArrayList<String[]>(){{
                        add(new String[] {"1", "S:\\CLIENTES\\Blue Ville\\ETA\\Executivo - Atual\\Rev 01\\PDF - EXECUTIVO\\03 - A2 - Rev01 - LAYOUT.pdf"});
                    }}));
                    add(new PlanInfo("Front View", 4, new ArrayList<String[]>(){{
                        add(new String[] {"1", "S:\\CLIENTES\\Blue Ville\\ETA\\Executivo - Atual\\Rev 01\\PDF - EXECUTIVO\\04 - A2 - Rev01 - VISTA A.pdf"});
                    }}));
                }}));
            }
        });
    }
}

