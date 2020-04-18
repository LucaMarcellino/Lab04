package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
		Map<Integer,Studente> mappaStudenti=new LinkedHashMap<Integer,Studente>();
	
		
	public Map<Integer,Studente> getTuttiStudenti() {
		 String sql="SELECT * FROM studente"; 
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s= new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
				mappaStudenti.put(rs.getInt("matricola"),s);
			}


			conn.close();
			
			return mappaStudenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public Studente getStudenteByMatricola(int matricola) {
		mappaStudenti=getTuttiStudenti();
		Studente s=null;
		if(mappaStudenti.containsKey(matricola)) {
			 s= mappaStudenti.get(matricola);
			
		}
		return s;
	}
	
	public List<Corso> getCorsoByMatricola(int matricola){
		String sql="SELECT c.codins,c.crediti,c.nome,c.pd FROM studente AS s,corso AS c,iscrizione AS i "
				+ "WHERE s.matricola=i.matricola AND c.codins=i.codins AND s.matricola=?";
		List<Corso> listaCorsi = new LinkedList<Corso>();
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			Corso c=null;
			while(rs.next()) {
				c= new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				listaCorsi.add(c);
			}
			
			
			conn.close();
			return listaCorsi;
		
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public boolean isIscritto(int matricola,String nome) {
		String sql="SELECT c.codins FROM studente AS s,corso AS c,iscrizione AS i "
				+ "WHERE s.matricola=i.matricola AND c.codins=i.codins AND s.matricola=? AND c.nome=?";
		List<String> controllo =new LinkedList<String>();
		boolean iscritto=false;
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2,nome);
			ResultSet rs = st.executeQuery();
			String codins="";
			while(rs.next()) {
			codins=rs.getString("codins");
			controllo.add(codins);
			}
			if(controllo.size()==1) {
				iscritto=true;
			}
	
		
		
		conn.close();
		return iscritto;
	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	
	}
	
}
