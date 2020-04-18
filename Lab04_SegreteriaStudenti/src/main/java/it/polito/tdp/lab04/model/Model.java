package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	StudenteDAO sDao=new StudenteDAO();
	CorsoDAO cDao= new CorsoDAO();
	
	public List<Corso> tornaNomiCorsi(){
		List<Corso> nomeCorsi= new LinkedList<Corso>();
		
		nomeCorsi.add(new Corso(null,0,"",0));
		for(Corso c :cDao.getTuttiICorsi()) {
			nomeCorsi.add(c);
		}
		return nomeCorsi;
	}
	
	public Studente richiamaStudente(int matricola) {
		Studente sTemp;
		if(sDao.getStudenteByMatricola(matricola)==null) {
			sTemp=new Studente(-1,null,null,null);
		}
		
		 sTemp= sDao.getStudenteByMatricola(matricola);
		return sTemp;
	}
	
	public List<Studente> richiamoDellaLista(Corso corso){
		
		List<Studente> listaStudenti = new LinkedList<Studente>(cDao.getStudentiIscrittiAlCorso(corso));	
		return listaStudenti;
	}
	
	public List<Corso> richiamoCorsoDaMatricola(int matricola){
		List<Corso> listaCorsi =new LinkedList<Corso>(sDao.getCorsoByMatricola(matricola));
		List<Corso> listaCorsin =new LinkedList<Corso>();
		if(sDao.getStudenteByMatricola(matricola)==null) {
			Corso c= new Corso (null,-1,null,-1);
			listaCorsin.add(c);
			return listaCorsin;
		}
		
		return listaCorsi;
	}
	
	public boolean richiamoIscritto(int matricola,String nome) {
		return sDao.isIscritto(matricola, nome);
	}
	
	
	public boolean richiamodellAggiunta(Studente s, Corso c) {
		
		return cDao.inscriviStudenteACorso(s, c);
		
	}
}
