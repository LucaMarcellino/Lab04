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
}
