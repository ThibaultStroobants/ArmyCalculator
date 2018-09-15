package domain.model;

import java.io.Serializable;

public class Connection implements Serializable {
	
	private static final long serialVersionUID = Long.parseLong("5044190751079313744");
	private Country adjacentCountry;
	private int adjacentTerritories;
	
	public Connection() {
		
	}
	
	public Connection(Country country, int adjacentTerritories) {
		setAdjacentCountry(country);
		setAdjacentTerritories(adjacentTerritories);
	}

	public Country getAdjacentCountry() {
		return adjacentCountry;
	}

	public int getAdjacentTerritories() {
		return adjacentTerritories;
	}

	public void setAdjacentCountry(Country adjacentCountry) {
		if (adjacentCountry == null) {
			throw new IllegalArgumentException("No country given");
		}
		this.adjacentCountry = adjacentCountry;
	}

	public void setAdjacentTerritories(int adjacentTerritories) {
		if (adjacentTerritories <= 0) {
			throw new IllegalArgumentException("Adjacent territories cannot be lower than 1");
		}
		this.adjacentTerritories = adjacentTerritories;
	}
	
}