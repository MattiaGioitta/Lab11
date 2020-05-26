package it.polito.tdp.rivers.db;

import java.util.HashMap;
import java.util.Map;

import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		Map<Integer, River> mappa = new HashMap<>();
		dao.getAllRivers(mappa);
		System.out.println(mappa.values());
	}

}
