package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private ArtsmiaDAO dao;
	private SimpleDirectedGraph<Exhibition, DefaultEdge> grafo;
	private List<Exhibition> mostre;
	
	
	public Model(){
		this.dao= new ArtsmiaDAO();
	}

	public List<Integer> getAllYears(){
		return dao.getAllYears();
	}
	
	public List<Exhibition> getAllExhibitions(int anno){
		this.mostre=dao.getAllExhibitions(anno);
		return mostre;
	}
	
	public boolean creaGrafo(int anno){
		
		grafo = new SimpleDirectedGraph<Exhibition, DefaultEdge>(DefaultEdge.class);
		this.mostre=dao.getAllExhibitions(anno);
		
		Graphs.addAllVertices(grafo, mostre);
		//System.out.println(grafo);
		
		for(Exhibition e1 : grafo.vertexSet()){
			for(Exhibition e2 : grafo.vertexSet()){
				if(e2.getBegin()>e1.getBegin() && e1.getEnd()>e2.getBegin()){
					grafo.addEdge(e1, e2);
				}
			}
		}
		//System.out.println(grafo);
		int max = 0;
		Exhibition e = null;
		
		for(Exhibition se : grafo.vertexSet()){
			if(grafo.outDegreeOf(se)>max){
				max= grafo.outDegreeOf(se);
				e = se;
			}
		}
		System.out.println(e);
		
		KosarajuStrongConnectivityInspector <Exhibition, DefaultEdge> ci = new KosarajuStrongConnectivityInspector <Exhibition, DefaultEdge>(grafo);
		boolean connesso = ci.isStronglyConnected();
		return connesso;
	}
	
	public Exhibition calcolaMaggiorNumeroOpere(){
		
		for(Exhibition e : grafo.vertexSet()){
			e.setOggetti(dao.listObject(e));
		}
		int max=0;
		Exhibition eMax = null;
		for(Exhibition e : grafo.vertexSet()){
			if(e.getOggetti().size()>max){
				max = e.getOggetti().size();
				eMax = e;
			}
		}
		
		return eMax;
	}
	
	
	public List<Studente> simula(int anno, int n){
		
		List<Exhibition> mostre = new ArrayList<>();
		for(Exhibition e : grafo.vertexSet()){
			if(e.getBegin()==anno){
				mostre.add(e);
			}
		}
//		Random r = new Random();
//		int scelta = r.nextInt(mostre.size());
		Exhibition partenza = null;
		for(Exhibition e : grafo.vertexSet()){
			if(e.getExhibitionId()==1610){
				partenza = e;
				break;
			}
		}
		
		System.out.println(partenza);
		Simulazione sim = new Simulazione(grafo);
		
		sim.inserisci(n, partenza);
		List<Studente> studenti = sim.run();
		Collections.sort(studenti);
		
		return studenti;
		
		
		
	}
	
	
}
