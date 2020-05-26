package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulator {

	//PARAMETRI DI SIMULAZIONE
	private double capacita;	
	private double probabilita = 0.05;
	private River r;//river to take in exam
	private double fattore;//factor chosen by the user
	private List<Flow> flows;//flows of the river
	
	
	//OUTPUT DA CALCOLARE
	private int numGiorni;//numero dei giorni in cui non si è potuta garantire l'erogazione minima
	private double capacitaMedia;//capacitaMedia/count
	
	//CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	
	//STATO DEL SISTEMA
	private double capienzaTot;//Q
	private double flowMin;//fmin
	private int count;//counter for the total day
	
	/**
	 * It initializes the parameter of the world and the queue of events
	 * @param r River chosen
	 * @param k factor chosen
	 */
	public void init(River r, Double k) {
		//Initialize the value passed by user
		this.r = r;
		this.fattore = k;
		this.flows = r.getFlows();
		//Initialize parameter of the world system
		this.numGiorni = 0;
		this.capacitaMedia = 0.0;
		this.count = 0;
		this.flowMin = r.getFlowAvg()*0.8;
		//initialize the priority queue
		this.queue = new PriorityQueue<>();
		for(Flow f : this.flows) {
			Event e = new Event(f.getDay(),f);
			this.queue.add(e);
		}
		//
		this.capienzaTot = fattore*r.getFlowAvg()*3600*24*30;
		//initialize the first step, updating parameter
		this.capacita = this.capienzaTot/2;
		
		this.capacitaMedia+=this.capacita;
		this.count++;
		
	}
	/**
	 * it is the run method for the simulation
	 */
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			System.out.println(e);
			processEvent(e);
		}
	}
	
	
	
	
	/**
	 * 
	 * @param e Event to process
	 */
	private void processEvent(Event e) {
		double fin = e.getFlow().getFlow();
		double fout = 0.00;
		if(this.capacita<=this.capienzaTot) {
			Random random = new Random();
			float prob = random.nextFloat();
			if(prob<this.probabilita) {
				fout = 10*this.flowMin;
			}
			if(fin>=this.flowMin) {
				this.capacita+=(fin-fout);
				//sum C in excess
				this.capacitaMedia+=this.capacita;
				this.count++;
			}
			else if (fin<this.flowMin) {
				this.capacita-=(this.flowMin-fin);
				//diff 
				this.capacitaMedia+=this.capacita;
				this.count++;
				this.numGiorni++;
			}
		}
		else {
			//tracimazione
			this.capacitaMedia+=this.capacita;
			this.numGiorni++;
			this.count++;
		}
		
	}

	public int getNumGiorni() {
		return numGiorni;
	}
	public double getCapacitaMedia() {
		return capacitaMedia/this.count;
	}
	
}
