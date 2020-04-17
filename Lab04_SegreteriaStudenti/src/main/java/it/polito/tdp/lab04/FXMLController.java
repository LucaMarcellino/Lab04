package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> menuCorsi;

    @FXML
    private Button btnIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private CheckBox checkMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCorsi;

    @FXML
    private Button btnIscriviti;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doIscrizione(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtRisultato.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	checkMatricola.setSelected(false);

    }
    
    @FXML
    void doStudente(ActionEvent event) {
    		txtRisultato.clear();
    	
    	if(txtMatricola.getText().isEmpty()) {
    		txtRisultato.appendText("Scrivi una matricola figlio di puttana");
    		return;
    	}
    	
    		
    	try {
    	int matricola= Integer.parseInt(txtMatricola.getText());
    	Studente s= model.richiamaStudente(matricola);
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("Inserisci un numero intero valido, grandissimo figlio di mignotta");
    	}
    }

    @FXML
    void searchCorsi(ActionEvent event) {

    }

    @FXML
    void searchIscritti(ActionEvent event) {
    	
    	String s= menuCorsi.getValue();
    	if(s=="") {
    		txtRisultato.appendText("Scegli un corso lurido bastardo");
    	}
    	Corso c =new Corso(null,0,s,0);
    	List<Studente> listaStud=new LinkedList<Studente>(model.RichiamoDellaLista(c));
    	txtRisultato.appendText(listaStud.toString());
    	
    	
    }

    @FXML
    void initialize() {
        assert menuCorsi != null : "fx:id=\"menuCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscritti != null : "fx:id=\"btnIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkMatricola != null : "fx:id=\"checkMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscriviti != null : "fx:id=\"btnIscriviti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	
    	menuCorsi.getItems().addAll(model.tornaNomiCorsi());
    	txtNome.setDisable(true);
    	txtCognome.setDisable(true);
    	txtRisultato.setDisable(true);
    	
    	
    	
    }
}
