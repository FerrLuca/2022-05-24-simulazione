/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.itunes.model.Adiacenza;
import it.polito.tdp.itunes.model.Genre;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.Track;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnCreaGrafo"
	private Button btnCreaGrafo; // Value injected by FXMLLoader

	@FXML // fx:id="btnCreaLista"
	private Button btnCreaLista; // Value injected by FXMLLoader

	@FXML // fx:id="btnMassimo"
	private Button btnMassimo; // Value injected by FXMLLoader

	@FXML // fx:id="cmbCanzone"
	private ComboBox<Track> cmbCanzone; // Value injected by FXMLLoader

	@FXML // fx:id="cmbGenere"
	private ComboBox<Genre> cmbGenere; // Value injected by FXMLLoader

	@FXML // fx:id="txtMemoria"
	private TextField txtMemoria; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void btnCreaLista(ActionEvent event) {
		
		txtResult.clear();
		int memory = 0;
		Track cc;
		if (!model.grafoCreato()) {
			txtResult.setText("Crea prima il grafo!!");
			return;
		}
		if (cmbCanzone.getValue() == null) {
			txtResult.setText("Scegli una canzone!");
			return;
		} else {
			cc = cmbCanzone.getValue();
		}

		try {
			memory = Integer.parseInt(txtMemoria.getText());
		} catch (NumberFormatException n) {
			txtResult.setText("Inserire un valore numerico!");
			return;
		}
		
		List<Track> bestN = model.cercaLista(cc, memory);
		txtResult.appendText("Numero massimo di brani: " + bestN.size());

	}

	@FXML
	void doCreaGrafo(ActionEvent event) {
		txtResult.clear();
		model.creaGrafo(cmbGenere.getValue());
		txtResult.appendText("Grafo creato! ");
		txtResult.appendText("\n#VERTICI: " + model.numVertici());
		txtResult.appendText("\n#ARCHI: " + model.numArchi());

		this.cmbCanzone.getItems().clear();
		this.cmbCanzone.getItems().addAll(model.getVertici());
	}

	@FXML
	void doDeltaMassimo(ActionEvent event) {
		txtResult.clear();

		if (!model.grafoCreato()) {
			txtResult.setText("Crea prima il grafo!!");
			return;
		} else {
			for (Adiacenza a : model.deltaMassimo()) {
				txtResult.appendText("DELTA massimo:\n");
				txtResult.appendText(a.toString());
			}
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnMassimo != null : "fx:id=\"btnMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbCanzone != null : "fx:id=\"cmbCanzone\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbGenere != null : "fx:id=\"cmbGenere\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMemoria != null : "fx:id=\"txtMemoria\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		cmbGenere.getItems().addAll(model.getAllGenres());
	}

}
