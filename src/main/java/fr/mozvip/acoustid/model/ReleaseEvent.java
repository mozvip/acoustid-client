package fr.mozvip.acoustid.model;

import fr.mozvip.acoustid.AcoustIdDate;

public class ReleaseEvent {
	
	private String country;
	private AcoustIdDate date;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public AcoustIdDate getDate() {
		return date;
	}
	public void setDate(AcoustIdDate date) {
		this.date = date;
	}
	
	

}
