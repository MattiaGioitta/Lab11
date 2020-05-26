/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();
    	River r = this.boxRiver.getValue();
    	this.model.getRiverInformation(r);
    	Double k = Double.parseDouble(this.txtK.getText());
    	this.model.simulator(r, k);
    	this.txtResult.appendText("Numero giorni: "+this.model.getNumGiorni()+"\n");
    	this.txtResult.appendText("Capacita Media : "+this.model.getCapacitaMedia().intValue());

    }
    @FXML
    void doCompleta(ActionEvent event) {
    	River r = this.boxRiver.getValue();
    	if(r == null) {
    		this.txtResult.appendText("Errore: scegli un fiume");
    		return;
    	}
    	this.model.getRiverInformation(r);
    	this.txtStartDate.setText(r.getFirstDate());
    	this.txtEndDate.setText(r.getEndDate());
    	this.txtNumMeasurements.setText(""+r.getFlows().size());
    	this.txtFMed.setText(""+r.getFlowAvg());

    }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
    	this.model = model;
    	this.boxRiver.getItems().addAll(this.model.getRivers());
    	
    }
}
