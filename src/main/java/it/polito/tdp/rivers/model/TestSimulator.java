package it.polito.tdp.rivers.model;

import java.util.List;

public class TestSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Simulator s = new Simulator();
		Model model = new Model();
		
		//s.init(r.get(1), 10.0);
		s.run();
		System.out.println(s.getNumGiorni());
		System.out.println(s.getCapacitaMedia());

	}

}
