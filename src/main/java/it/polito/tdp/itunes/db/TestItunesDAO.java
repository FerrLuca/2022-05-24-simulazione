package it.polito.tdp.itunes.db;

import it.polito.tdp.itunes.model.Genre;

public class TestItunesDAO {

	public static void main(String[] args) {
		ItunesDAO dao = new ItunesDAO();
		System.out.println(dao.getArchi(new Genre(1, "Rock"), dao.getIdMap()));


	}

}
