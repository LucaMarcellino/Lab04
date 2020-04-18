package it.polito.tdp.lab04.DAO;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	Map<String,Corso> mappaCorsi=new LinkedHashMap<String,Corso>();
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";
		
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Corso c= new Corso (codins, numeroCrediti,nome,periodoDidattico);
				corsi.add(c);
				mappaCorsi.put(codins, c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(Corso corso) {
		Corso c= null;
		if(mappaCorsi.containsKey(corso.getCodins())) {
			c= mappaCorsi.get(corso.getCodins());
		}
		return c;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		String sql="SELECT s.matricola,s.nome,s.cognome,s.CDS FROM studente AS s,corso AS c,iscrizione AS i "
				+ "WHERE s.matricola=i.matricola AND c.codins=i.codins AND c.nome=?";
		List<Studente> listStudenti=new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getNome());
			ResultSet rs = st.executeQuery();
			
			
			
			while(rs.next()) {
				Studente s= new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
				listStudenti.add(s);
			}

		conn.close();
		return listStudenti;
		}
		catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}

	} 


	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		String sql="INSERT INTO iscrizione VALUES (?,?)";
		boolean fatto=false;
		StudenteDAO sdao=new StudenteDAO();

		
		if(corso.getNome()=="" || sdao.getStudenteByMatricola(studente.getMatricola())==null) {
			return fatto;
		}
		

		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2,corso.getCodins());
			ResultSet rs = st.executeQuery();
			fatto=true;
			
			conn.close();
			return fatto;
			}catch (SQLException e) {
				return fatto;
				
			}
		
	
	}
	

	
	

}
