package it.polito.tdp.corsi.model;

public class Divisione implements Comparable<Divisione>{
	private String CDS;
	private int n;
	
	public Divisione(String cDS, int n) {
		CDS = cDS;
		this.n = n;
	}

	public String getCDS() {
		return CDS;
	}

	public void setCDS(String cDS) {
		CDS = cDS;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public int compareTo(Divisione o) {
		return o.getCDS().compareTo(this.CDS);
	}
	
	
}
