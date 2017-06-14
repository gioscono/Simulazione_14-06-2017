package it.polito.tdp.artsmia.model;

public class Evento implements Comparable<Evento>{
	
	private Studente studente;
	private Exhibition next;

	
	
	
	
	public Studente getStudente() {
		return studente;
	}
	public Exhibition getNext() {
		return next;
	}
	public Evento(Studente studente, Exhibition next) {
		super();
		this.studente = studente;
		this.next = next;
	}
	
	
	
	@Override
	public int compareTo(Evento altro) {
		// TODO Auto-generated method stub
		return this.next.getBegin()-altro.getNext().getBegin();
	}

}
