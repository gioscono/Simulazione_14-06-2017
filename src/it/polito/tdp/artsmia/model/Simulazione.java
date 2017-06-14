package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class Simulazione {
	
	private List<Studente> studenti;
	
	private PriorityQueue<Evento> queue;
	private SimpleDirectedGraph<Exhibition, DefaultEdge> grafo;
	
	public Simulazione(SimpleDirectedGraph<Exhibition, DefaultEdge> grafo){
		studenti = new ArrayList<>();
		this.queue = new PriorityQueue<>();
		this.grafo = grafo;
	}

	
	public void inserisci(int n, Exhibition partenza){
		for(int i =0; i<n; i++){
			String mat="S_"+i;
			Studente s = new Studente(mat);
			queue.add(new Evento(s,partenza));
			studenti.add(s);
			s.osservaOpere(partenza.getOggetti());
		}
	}
	
	public List<Studente> run(){
		
		
		while(!queue.isEmpty()){
			
			Evento e = queue.poll();
			//VEDO QUALI SONO LE PROSSIME MOSTRE DOVE PUO ANDARE
			List<Exhibition> prossime = Graphs.successorListOf(grafo, e.getNext());
			System.out.println(prossime.size());
			if(prossime.size()>0){
				//CALCOLO CASUALMENTE LA PROSSIMA MOSTRA
				Random r = new Random();
				int scelta = r.nextInt(prossime.size());
				Exhibition prossima = prossime.get(scelta);
				
				//LO STUDENTE VISUALIZZA LE OPERE
				e.getStudente().osservaOpere(prossima.getOggetti());
				System.out.println(prossima.getOggetti().size());
				
				//GENERO UN NUOVO EVENTO
				queue.add(new Evento(e.getStudente(), prossima));
			}
			
			
			
			
			
		}
		
		return studenti;
		
	}
}
