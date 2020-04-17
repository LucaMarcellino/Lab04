package it.polito.tdp.lab04.DAO;

public class TestDB {

	public static void main(String[] args) {

		/*CorsoDAO cdao = new CorsoDAO();
		System.out.println(cdao.getTuttiICorsi());
		System.out.println("----------");
		StudenteDAO sdao = new StudenteDAO();
		System.out.println(sdao.getTuttiStudenti());*/
		StudenteDAO sdao = new StudenteDAO();
		System.out.println(sdao.getStudenteByMatricola(177600));
	}

}
