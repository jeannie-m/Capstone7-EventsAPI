package co.grandcircus.EventsAPI.Entities;

import java.io.Serializable;

public class ZipCode implements Serializable {

	@Override
	public String toString() {
		return "ZipCode [zipCode=" + zipCode + "]";
	}

	private static final long serialVersionUID = 1L;
	
	private String zipCode;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}