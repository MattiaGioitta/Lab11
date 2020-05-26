package it.polito.tdp.rivers.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private Map<Integer, River> idMap;
	private Simulator sim;
	
	public Model() {
		this.dao = new RiversDAO();
	}
	/**
	 * get all rivers from database
	 * @return Collection of River
	 */
	public Collection<River> getRivers() {
		this.idMap = new HashMap<>();
		this.dao.getAllRivers(idMap);
		return this.idMap.values();
	}

	/**
	 * get all flows about river passed and set his flows and avg of flows
	 * @param r river passed as parameter
	 */
	public void getRiverInformation(River r) {
		List<Flow> flows = this.dao.getRiverInformation(r);
		r.setFlows(flows);
		r.setFlowAvg(this.calculateAvg(flows));	
		
	}
	
	public void simulator(River r, double k) {
		this.sim = new Simulator();
		sim.init(r, k);
		sim.run();		
	}
	public Integer getNumGiorni() {
		return sim.getNumGiorni();
	}
	public Double getCapacitaMedia() {
		return sim.getCapacitaMedia();
	}

	/**
	 * 
	 * @param flows list of all flows of a river
	 * @return avg of the value of the list passed
	 */
	private double calculateAvg(List<Flow> flows) {
		double sum = 0.0;
		int num = 0;
		for(Flow f : flows) {
			sum+=f.getFlow();
			num++;
		}
		return sum/num;
	}

}
