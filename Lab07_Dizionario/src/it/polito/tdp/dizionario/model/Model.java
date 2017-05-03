package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {

	UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	WordDAO dao = new WordDAO();
	
	public List<String> createGraph(int numeroLettere) {
		
		List<String> parole = dao.getAllWordsFixedLength(numeroLettere);
		
		for(String p : parole)
			grafo.addVertex(p);
		
		for(String p : grafo.vertexSet()){
			
			for(String s : grafo.vertexSet()){
				
				if(!grafo.containsEdge(p, s) && this.analizzaParole(p, s)){
					
					grafo.addEdge(p, s);
				}
			}
		}
		
		return parole ;
	}

	public List<String> displayNeighbours(String parolaInserita) {

		if(grafo.containsVertex(parolaInserita))
		 return Graphs.neighborListOf(grafo, parolaInserita);
		
		return null;
	}

	public String findMaxDegree() {
		
		String temp = "";
		int max = 0;
		
		for(String p : grafo.vertexSet()){
			
			if(grafo.degreeOf(p)>max){
				max = grafo.degreeOf(p);
				temp=p;
			}
		}
		
		String s = "Parola = "+temp+" con grado max = "+grafo.degreeOf(temp);
		
		for(String p : this.displayNeighbours(temp)){
			s += "\n"+p;
		}
		
		return s;
	}
	
	public boolean analizzaParole(String x, String y){
		
		char c1[] = x.toCharArray();
		char c2[] = y.toCharArray();
		int c = 0;
		
		for(int i = 0; i<c1.length; i++){
			
			if(c1[i]!=c2[i])
				c++;
		}
		
		if(c==1)
			return true;
		else
			return false;
	}
	
	
	/*metodo JGraphT*/
	
//	public List<String> findAllNeighbours(String s){
//		
//		List<String> vicini = new ArrayList<String>();
//		BreadthFirstIterator<String, DefaultEdge> bfv = new BreadthFirstIterator<>(grafo, s);       metodo JGraphT
//		
//		while(bfv.hasNext()){
//			vicini.add(bfv.next());
//		}
//		
//		return vicini;
//	}
	
	
	 /*metodo recursion*/
	
//	public List<String> findAllNeighbours(String s){
//		
//		if(!grafo.containsVertex(s)){
//			return null;
//		}
//		
//		List<String> vicini = new ArrayList<String>();
//		vicini.add(s);
//		this.recursive(vicini, s);
//		
//		return vicini;
//	}
//    private void recursive(List<String> vicini, String s) {
//	
//    	List<String> neighbours = Graphs.neighborListOf(grafo, s);
//    	
//    	
//    	for(String x : neighbours){
//    		
//    		if(!vicini.contains(x)){
//    			vicini.add(x);
//    			this.recursive(vicini, x);
//    		}
//    	}
//   }
	
	
	/*metodo iterativo*/
	
//	public List<String> findAllNeighbours(String s){
//		
//		if(!grafo.containsVertex(s)){
//			return null;
//		}
//			
//		List<String> daVisitare = new ArrayList<String>();
//		List<String> visitati = new ArrayList<String>();;
//		
//		daVisitare.addAll(displayNeighbours(s));		
//		
//		while(daVisitare.size()!=0){
//			
//			visitati.add(daVisitare.get(0));
//			String t = daVisitare.remove(0);
//			
//			for(String x : displayNeighbours(t)){
//				if(!daVisitare.contains(x) && !visitati.contains(x))
//					daVisitare.add(x);
//			}
//		}
//		
//		return visitati;
//	}
	
	
	
	
}
