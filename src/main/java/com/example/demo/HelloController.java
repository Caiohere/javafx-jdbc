package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    private  ArrayList<String> sqlResults;
    @FXML
    private MenuItem menuItemIncidentPerType;
    @FXML
    private MenuItem menuItemIncidentPerGravity;
    @FXML
    private MenuItem menuItemInspectionPerType;
    @FXML
    private MenuItem menuItemTrainingFrequency;
    @FXML
    private MenuItem menuItemIncidentTendencyPerMonth;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private Button buttonNewLineDepartment;
    @FXML
    private BarChart<String, Double> incidentPerTypeBarChart;
    @FXML
    private BarChart<String, Double> incidentPerGravityBarChart;
    @FXML
    private BarChart<String, Double> inspectionPerTypeBarChart;
    @FXML
    private BarChart<String, Double> TrainingFrequencyPerMonthBarChart;
    @FXML
    private BarChart<String, Double> IncidentTendencyPerMonthBarChart;
    @FXML
    private CategoryAxis incidentCategoryAxis;
    @FXML
    private NumberAxis incidentNumberAxis;

    @FXML
    public void onMenuItemIncidentType() throws IOException {
        loadView("IncidentPerType.fxml");
        //loadView("HomeView.fxml");
    }
    @FXML
    public void onMenuItemIncidentPerGravity() throws IOException {
        loadView("IncidentPerGravity.fxml");
    }
    @FXML
    public void onMenuItemInspectionPerType() throws IOException {
        loadView("inspectionPerType.fxml");
    }
    @FXML
    public void onMenuTrainingFrequency() throws IOException {
        loadView("TrainingFrequencyPerMonth.fxml");
    }
    @FXML
    public void onMenuIncidentTendencyPerMonth() throws IOException {
        loadView("IncidentTendencyPerMonth.fxml");
    }
    @FXML
    public void onMenuHome() throws IOException {
        loadView("HomeView.fxml");
    }

    @FXML
    public void onMenuItemAboutAction() throws IOException {
        loadView("AboutView.fxml");
    }
    public void onMenuItemAboutAction2() throws IOException {
        loadView("AboutView2.fxml");
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
        // Carregar os dados Acidentes por Tipo
        try {
            Model modeloIncidentPerType = new Model("SELECT tipo_incidente, COUNT(*) AS num_incidentes FROM incidentes GROUP BY tipo_incidente");
            sqlResults = modeloIncidentPerType.chart();
            int arraySize = sqlResults.size();

            XYChart.Series<String, Double> seriesIncidents = new XYChart.Series<>();
            //seriesIncidents.setName("Incidentes");

            for(int i=0;i<arraySize/2;i++){
                if (i == 0) {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i), Double.valueOf(sqlResults.get(i+1))));
                }
                else {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i * 2), Double.valueOf(sqlResults.get(i * 2 + 1))));
                }

            }
            incidentPerTypeBarChart.getData().add(seriesIncidents);
        }catch (Exception e){e.printStackTrace();}

        // Carregar os dados Acidentes por Gravidade
        try {
            Model modeloIncidentPerGravity = new Model("SELECT gravidade, COUNT(*) AS num_incidentes FROM incidentes GROUP BY gravidade");
            sqlResults = modeloIncidentPerGravity.chart();
            int arraySize = sqlResults.size();

            XYChart.Series<String, Double> seriesIncidents = new XYChart.Series<>();
            seriesIncidents.setName("Incidentes");

            for(int i=0;i<arraySize/2;i++){
                if (i == 0) {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i), Double.valueOf(sqlResults.get(i+1))));
                }
                else {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i * 2), Double.valueOf(sqlResults.get(i * 2 + 1))));
                }

            }
            incidentPerGravityBarChart.getData().add(seriesIncidents);
        }catch (Exception e){e.printStackTrace();}

        // Carregar os dados Inspeção por Tipo (conforme)
        try {
            Model modeloInspectionPerType = new Model("SELECT tipo_inspecao, COUNT(*) AS num_inspecoes, resultados FROM inspecoes GROUP BY tipo_inspecao, resultados HAVING resultados = \"conforme\"");
            sqlResults = modeloInspectionPerType.chart();
            int arraySize = sqlResults.size();

            XYChart.Series<String, Double> seriesIncidents = new XYChart.Series<>();
            seriesIncidents.setName("CONFORME");

            for(int i=0;i<arraySize/2;i++){
                if (i == 0) {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i), Double.valueOf(sqlResults.get(i+1))));
                }
                else {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i * 2), Double.valueOf(sqlResults.get(i * 2 + 1))));
                }

            }
            // Carregar os dados Inspeção por Tipo (não conforme)
            Model modeloInspectionPerType2 = new Model("SELECT tipo_inspecao, COUNT(*) AS num_inspecoes, resultados FROM inspecoes GROUP BY tipo_inspecao, resultados HAVING resultados = \"não conforme\"");
            sqlResults = modeloInspectionPerType2.chart();
            int arraySize2 = sqlResults.size();

            XYChart.Series<String, Double> seriesIncidents2 = new XYChart.Series<>();
            seriesIncidents2.setName("NÃO CONFORME");

            for(int i=0;i<arraySize2/2;i++){
                if (i == 0) {
                    seriesIncidents2.getData().add(new XYChart.Data<>(sqlResults.get(i), Double.valueOf(sqlResults.get(i+1))));
                }
                else {
                    seriesIncidents2.getData().add(new XYChart.Data<>(sqlResults.get(i * 2), Double.valueOf(sqlResults.get(i * 2 + 1))));
                }

            }
            inspectionPerTypeBarChart.getData().addAll(seriesIncidents, seriesIncidents2);
        }catch (Exception e){e.printStackTrace();}
        // Carregar os dados Frequência de Treinamento
        try {
            Model modeloIncidentPerGravity = new Model("SELECT DATE_FORMAT(data_treinamento, '%Y-%m') AS mes_ano, COUNT(*) AS num_treinamentos\n" +
                    "FROM treinamentos\n" +
                    "GROUP BY mes_ano\n" +
                    "ORDER BY mes_ano");
            sqlResults = modeloIncidentPerGravity.chart();
            int arraySize = sqlResults.size();

            XYChart.Series<String, Double> seriesIncidents = new XYChart.Series<>();
            //seriesIncidents.setName("Incidentes");

            for(int i=0;i<arraySize/2;i++){
                if (i == 0) {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i), Double.valueOf(sqlResults.get(i+1))));
                }
                else {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i * 2), Double.valueOf(sqlResults.get(i * 2 + 1))));
                }

            }
            TrainingFrequencyPerMonthBarChart.getData().add(seriesIncidents);
        }catch (Exception e){e.printStackTrace();}
        // Carregar os dados Tendências de Acidentes
        try {
            Model modeloIncidentPerGravity = new Model("SELECT DATE_FORMAT(data_incidente, '%Y-%m') AS mes_ano, COUNT(*) AS num_incidentes\n" +
                    "FROM incidentes\n" +
                    "GROUP BY mes_ano\n" +
                    "ORDER BY mes_ano");
            sqlResults = modeloIncidentPerGravity.chart();
            int arraySize = sqlResults.size();

            XYChart.Series<String, Double> seriesIncidents = new XYChart.Series<>();
            //seriesIncidents.setName("Incidentes");

            for(int i=0;i<arraySize/2;i++){
                if (i == 0) {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i), Double.valueOf(sqlResults.get(i+1))));
                }
                else {
                    seriesIncidents.getData().add(new XYChart.Data<>(sqlResults.get(i * 2), Double.valueOf(sqlResults.get(i * 2 + 1))));
                }

            }
            IncidentTendencyPerMonthBarChart.getData().add(seriesIncidents);
        }catch (Exception e){e.printStackTrace();}
    }

}

