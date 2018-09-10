package domain.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import domain.model.Connection;

public class ConnectionDb implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Connection> connections = new ArrayList<>();
	
	public ConnectionDb() {
		
	}
	
	public Connection get(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("No name given");
		}
		if (isConnection(name)) {
			return connections.get(getIndexOfCountry(name));
		} else {
			throw new DbException("Connection not found");
		}
	}
	
	public Connection get(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("No connection given");
		}
		if (isConnection(connection)) {
			connections.get(connections.indexOf(connection));
		} else {
			throw new DbException("Connection not found");
		}
		return null;
	}
	
	public List<Connection> getAll() {
		return connections;
	}
	
	public void add(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("No connection given");
		}
		if (isConnection(connection)) {
			throw new DbException("Connection already exists");
		}
		this.connections.add(connection);
	}
	
	public void delete(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("No connection given");
		}
		connections.remove(connection);
	}
	
	public boolean isConnection(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("No connection given");
		}
		if (connections.contains(connection)) {
			return true;
		}
		return false;
	}
	
	public boolean isConnection(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("No name given");
		}
		for (Connection connection : connections) {
			if (connection.getAdjacentCountry().getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public int getIndexOfCountry(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("no name given");
		}
		if (!isConnection(name)) {
			throw new DbException("Country not found");
		}
		for (int i = 0; i < connections.size(); i++) {
			if (connections.get(i).getAdjacentCountry().getName().equals(name)) {
				return i;
			}
		}
		return 0;
	}

}
