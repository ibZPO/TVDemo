package edu.ib;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TVDemoController {

    private  Tabulator tabulator;
    private ObservableList<PointTX> list= FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfMax;

    @FXML
    private TextField tfMin;

    @FXML
    private TableColumn<PointTX, Double> x;

    @FXML
    private Button btnCalculate;

    @FXML
    private TableColumn<PointTX, Double> time;

    @FXML
    private TableView<PointTX> table;

    @FXML
    void btnCalculateClicked(ActionEvent event) {
        table.getItems().clear(); // remove all previous values
        double a=Double.parseDouble(tfMin.getText().toString());
        double b=Double.parseDouble(tfMax.getText().toString());
        tabulator.calculate(a,b,20,(x)->Math.sin(x));
        list.addAll(PointTX.getPointsTX(tabulator.getxValues(),tabulator.getyValues()));
    }

    @FXML
    void initialize() {
        assert tfMax != null : "fx:id=\"tfMax\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert tfMin != null : "fx:id=\"tfMin\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert x != null : "fx:id=\"x\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert btnCalculate != null : "fx:id=\"btnCalculate\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tvdemo.fxml'.";

        tabulator= new Tabulator();

        time.setCellValueFactory(new PropertyValueFactory<PointTX,Double>("time"));
        x.setCellValueFactory(new PropertyValueFactory<PointTX,Double>("x"));
        table.setItems(list);

    }
}
