package it.polito.tdp.itunes.model;

public class Adiacenza {

	private Track t1;
	private Track t2;
	private int deltaMs;

	public Adiacenza(Track t1, Track t2, int deltaMs) {
		this.t1 = t1;
		this.t2 = t2;
		this.deltaMs = deltaMs;
	}

	public Track getT1() {
		return t1;
	}

	public void setT1(Track t1) {
		this.t1 = t1;
	}

	public Track getT2() {
		return t2;
	}

	public void setT2(Track t2) {
		this.t2 = t2;
	}

	public int getDeltaMs() {
		return deltaMs;
	}

	public void setDeltaMs(int deltaMs) {
		this.deltaMs = deltaMs;
	}

	@Override
	public String toString() {
		return t1.getName() + " --- " + t2.getName() + " = " + this.deltaMs + "ms";
	}
	
	
	
	

}
