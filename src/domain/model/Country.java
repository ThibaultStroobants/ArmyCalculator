package domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.db.ConnectionDb;

public class Country implements Serializable {
	
	private static final long serialVersionUID = Long.parseLong("5044190751079313744");
	private ConnectionDb connections = new ConnectionDb();
	private String id;
	private String name;
	private int income;
	private int territories;
	
	public Country() {
		
	}
	
	public Country(String id, String name, int income, int territories) {
		setId(id);
		setName(name);
		setIncome(income);
		setTerritories(territories);
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getIncome() {
		return income;
	}

	public int getTerritories() {
		return territories;
	}
	
	public List<Connection> getConnections() {
		return this.connections.getAll();
	}
	
	public Connection getConnection(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("No name given");
		}
		return connections.get(name);
	}
	
	public int getCalculatedArmiesPerTerritory(Connection connection) {
		float out = this.income;
		out *= connection.getAdjacentCountry().getConnection(this.getName()).getAdjacentTerritories();
		out /= connection.getAdjacentTerritories();
		out *= connection.getAdjacentCountry().getTerritories();
		out /= this.getTerritories();
		out = Math.round(out);
		return (int) out;
	}
	
	public List<String> getAllCalculatedArmiesPerTerritory() {
		List<String> out = new ArrayList<>();
		for (Connection connection : this.getConnections()) {
			out.add(connection.getAdjacentCountry().getName() + ": " + String.valueOf(getCalculatedArmiesPerTerritory(connection)));
		}
		return out;
	}
	
	public void setId(String id) {
		if(id.isEmpty()){
			throw new IllegalArgumentException("No id given");
		}
		this.id = id;
	}

	public void setName(String name) {
		if(name.isEmpty()){
			throw new IllegalArgumentException("No name given");
		}
		this.name = name;
	}

	public void setIncome(int income) {
		if(income <= 0){
			throw new IllegalArgumentException("Income cannot be less than 1");
		}
		this.income = income;
	}

	public void setTerritories(int territories) {
		if(territories <= 0){
			throw new IllegalArgumentException("Territories cannot be less than 1");
		}
		this.territories = territories;
	}
	
	public void addConnection(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("No connection given");
		}
		if (connection.getAdjacentCountry() == this) {
			throw new IllegalArgumentException("Country can't be adjacent to itself");
		}
		connections.add(connection);
	}
	
	public void deleteConnection(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("No connection given");
		}
		if (!this.connections.isConnection(connection)) {
			throw new IllegalArgumentException("Connection doesn't exist");
		}
		this.connections.delete(connection);
	}
}
