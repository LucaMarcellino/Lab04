package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		CorsoDAO cdao = new CorsoDAO();
		//System.out.println(cdao.getTuttiICorsi());
		//System.out.println("----------");
		//StudenteDAO sdao = new StudenteDAO();
		//System.out.println(sdao.getTuttiStudenti());
		StudenteDAO sdao = new StudenteDAO();
		//System.out.println(cdao.rest());
		//System.out.println(sdao.getCorsoByMatricola(177290));
		//System.out.print(sdao.isIscritto(177290, "diritto commerciale"));
		System.out.println(cdao.inscriviStudenteACorso(new Studente(177290,null,null,null),new Corso("01KSUPG",0,null,0)));
}
}
