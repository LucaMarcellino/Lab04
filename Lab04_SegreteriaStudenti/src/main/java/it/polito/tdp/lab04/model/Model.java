package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	StudenteDAO sDao=new StudenteDAO();
	CorsoDAO cDao= new CorsoDAO();
	
	public List<String> tornaNomiCorsi(){
		List<String> nomeCorsi= new LinkedList<String>();
		
		nomeCorsi.add("");
		for(Corso c :cDao.getTuttiICorsi()) {
			nomeCorsi.add(c.getNome());
		}
		return nomeCorsi;
	}
	
	public Studente richiamaStudente(int matricola) {
		
		
		Studente sTemp= sDao.getStudenteByMatricola(matricola);
		return sTemp;
	}
	
	public List<Studente> RichiamoDellaLista(Corso corso){
		
		List<Studente> listaStudenti = new LinkedList<Studente>(cDao.getStudentiIscrittiAlCorso(corso));	
		return listaStudenti;
	}
	
}
