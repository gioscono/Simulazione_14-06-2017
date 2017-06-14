package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Studente implements Comparable<Studente>{
	
	private String mat;
	private Set<ArtObject> mostreVisitate;
	
	public Studente(String mat) {
		super();
		this.mat = mat;
		this.mostreVisitate =new HashSet<>();
	}
	
	public void osservaOpere(List<ArtObject> list){
		
		mostreVisitate.addAll(list);
	}

	
	
	
	
	public String getMat() {
		return mat;
	}

	public Set<ArtObject> getMostreVisitate() {
		return mostreVisitate;
	}

	@Override
	public int compareTo(Studente o) {
		// TODO Auto-generated method stub
		return -(this.mostreVisitate.size()-o.mostreVisitate.size());
	}

}
