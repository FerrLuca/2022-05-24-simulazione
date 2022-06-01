package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {

	private ItunesDAO dao;
	private Graph<Track, DefaultWeightedEdge> grafo;

	private Map<Integer, Track> idMap;

	private List<Track> bestList;

	public Model() {
		dao = new ItunesDAO();
		idMap = dao.getIdMap();
	}

	public void creaGrafo(Genre genere) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getVertici(genere, idMap));
		for (Adiacenza a : dao.getArchi(genere, idMap)) {
			Graphs.addEdgeWithVertices(this.grafo, a.getT1(), a.getT2(), a.getDeltaMs());
		}

	}

	public List<Track> cercaLista(Track c, int memory) {
		List<Track> parziale = new ArrayList<Track>();
		List<Track> disponibili = new ArrayList<Track>();
		Set<Track> connSongs;

		ConnectivityInspector<Track, DefaultWeightedEdge> insp = new ConnectivityInspector<>(grafo);
		connSongs = insp.connectedSetOf(c);

		disponibili.add(c);
		connSongs.remove(c);
		disponibili.add(c);

		parziale.add(c);

		bestList = new ArrayList<Track>();

		return bestList;
	}

	public void cerca(List<Track> parziali, List<Track> valide, int level, int memory) {

		if (this.sommaMemoria(parziali) > memory)
			return;

		if (parziali.size() > bestList.size()) {
			bestList = new ArrayList<Track>(parziali);
		}

		if (level == valide.size())
			return;
		
		parziali.add(parziali.get(level));
		this.cerca(parziali, valide, level + 1, memory);
		parziali.remove(level + 1);
		this.cerca(parziali, valide, level + 1, memory);
		
	}

	public int sommaMemoria(List<Track> lista) {
		int ris = 0;
		for (Track t : lista) {
			ris += t.getBytes();
		}
		return ris;
	}

	public List<Adiacenza> deltaMassimo() {
		List<Adiacenza> ris = new ArrayList<>();
		int max = 0;
		for (DefaultWeightedEdge e : grafo.edgeSet()) {
			int pesoE = (int) grafo.getEdgeWeight(e);
			if (pesoE > max) {
				max = pesoE;
				ris.clear();
				ris.add(new Adiacenza(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e), pesoE));
			} else if (pesoE == max) {
				ris.add(new Adiacenza(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e), pesoE));
			}
		}
		return ris;
	}

	public List<Genre> getAllGenres() {
		return dao.getAllGenres();
	}

	public Set<Track> getVertici() {
		return grafo.vertexSet();
	}

	public Set<DefaultWeightedEdge> getArchi() {
		return grafo.edgeSet();
	}

	public int numVertici() {
		return grafo.vertexSet().size();
	}

	public int numArchi() {
		return grafo.edgeSet().size();
	}

	public boolean grafoCreato() {
		if (this.grafo == null)
			return false;
		else
			return true;
	}

}
