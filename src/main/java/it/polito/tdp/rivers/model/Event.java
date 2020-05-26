package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event> {

	private LocalDate time;
	private Flow flow;
	/**
	 * @param time
	 * @param flow
	 */
	public Event(LocalDate time, Flow flow) {
		super();
		this.time = time;
		this.flow = flow;
	}
	/**
	 * @return the flow
	 */
	public Flow getFlow() {
		return flow;
	}
	/**
	 * @param flow the flow to set
	 */
	public void setFlow(Flow flow) {
		this.flow = flow;
	}
	/**
	 * @return the time
	 */
	public LocalDate getTime() {
		return time;
	}
	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.getTime());
	}
	@Override
	public String toString() {
		return "Event [time=" + time + ", flow=" + flow + "]";
	}
	
	
}
