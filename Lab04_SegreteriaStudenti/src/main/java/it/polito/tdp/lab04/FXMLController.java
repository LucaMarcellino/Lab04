package it.polito.tdp.lab04;

import java.net.URL;
import java.sql.SQLException;
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
	List<Corso> corsi;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> menuCorsi;

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
    	txtRisultato.clear();
    	boolean iscritto=false;
    	Corso c= menuCorsi.getValue();
    	int matricola= Integer.parseInt(txtMatricola.getText());
    	Studente s= new Studente(matricola,null,null,null);
    	iscritto = model.richiamodellAggiunta(s, c);
    	if(iscritto==true) {
    		txtRisultato.appendText("Sei riuscito ad iscriverti testa di cazzo,oppure un errore nel database");
    	}else
    		txtRisultato.appendText("C'è Stato un errore deficente, potresti anche essere già iscritto");
    	
    	

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
    	if(s.getMatricola()==-1) {
    		txtRisultato.appendText("Sei un grande figlio di puttana perche non sei iscritto");
    		return;
    	}
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("Inserisci un numero intero valido, grandissimo figlio di mignotta");
    	}
    	catch(NullPointerException npe) {
    		txtRisultato.appendText("Non sei iscritto figlio di puttana");
    	}
    }

    @FXML
    void searchCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	int matricola=0;
		try {
			matricola= Integer.parseInt(txtMatricola.getText());
		}catch(NumberFormatException nfe) {
			txtRisultato.appendText("inserisci una matricola, oppure una corretta");
		}
		
		if (menuCorsi.getValue() == null) {
			txtRisultato.setText("Selezionare un corso.");
			return;
		}
		
    	if(menuCorsi.getValue().getNome()=="") {
    		
    	List<Corso> listaCorsi= new LinkedList<Corso>(model.richiamoCorsoDaMatricola(matricola));
    	for(int i=0;i<listaCorsi.size();i++) {
    		if(listaCorsi.get(i).getPd()==-1) {
    			return;
    		}
    		txtRisultato.appendText(listaCorsi.get(i).getCodins()+"\t"+listaCorsi.get(i).getCrediti()+"\t"+listaCorsi.get(i).getNome()+"\t"+listaCorsi.get(1).getPd()+"\n");
    	}
    }else {
    	boolean trovato;
    	String corso=menuCorsi.getValue().getNome();
    	trovato=model.richiamoIscritto(matricola, corso);
    	if(trovato==true) {
    		txtRisultato.appendText("Bravo coglione sei iscritto");
    	}
    	else
    		txtRisultato.appendText("Non sei iscritto,coglione");
    	
    }
    

    	
    	
    	
    }

    @FXML
    void searchIscritti(ActionEvent event) {
    	
    	String s= menuCorsi.getValue().getNome();
    	if(s=="") {
    		txtRisultato.appendText("Scegli un corso lurido bastardo");
    	}
    	Corso c =new Corso(null,0,s,0);
    	List<Studente> listaStud=new LinkedList<Studente>(model.richiamoDellaLista(c));
    	for(int i=0;i<listaStud.size();i++) {
    		txtRisultato.appendText(listaStud.get(i).toString());
    	}
    	
    	
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
    	corsi=model.tornaNomiCorsi();
    	
    	menuCorsi.getItems().addAll(corsi);
    	
    	
    	txtNome.setDisable(true);
    	txtCognome.setDisable(true);
    	txtRisultato.setEditable(false);
    	
    	
    	
    }
}
