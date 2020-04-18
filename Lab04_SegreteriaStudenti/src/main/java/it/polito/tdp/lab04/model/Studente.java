package it.polito.tdp.lab04.model;

public class Studente {

	private int matricola;
	private String nome;
	private String cognome;
	private String cds;
	
	public Studente(int matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCds() {
		return cds;
	}
	public void setCds(String cds) {
		this.cds = cds;
	}
	
	@Override
	public String toString() {
		String sb;
		sb=String.format("%-10s", matricola)+String.format("%-20s", nome)+String.format("%-20s", cognome)+String.format("%-10s",cds)+"\n";
		return sb;
	}
	
	
	
}
