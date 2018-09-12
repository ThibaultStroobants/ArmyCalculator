package ui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.CountryDb;
import domain.model.Connection;
import domain.model.Country;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CountryDb countries;
	
	public Controller() {
		super();
		/*try {
			this.loadSavefile(null, true);
		} catch (FileNotFoundException e) {
			throw new ControllerException(e);
		} catch (ClassNotFoundException e) {
			throw new ControllerException(e);
		} catch (IOException e) {
			throw new ControllerException(e);
		}*/
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "index.jsp";
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "none";
		}
		
		switch (action) {
		case "countryOverview": // Country
			destination = doCountryOverview(request, response);
			break;
			
		case "newCountry":
			destination = "newCountry.jsp";
			break;
			
		case "addCountry":
			destination = doAddCountry(request, response);
			break;
			
		case "fetchCountryForUpdate":
			destination = doFetchCountryForUpdate(request, response);
			break;
			
		case "updateCountry":
			destination = doUpdateCountry(request, response);
			break;
			
		case "fetchCountryForDelete":
			destination = doFetchCountryForDelete(request, response);
			break;
		
		case "deleteCountry":
			destination = doDeleteCountry(request, response);
			break;
			
		case "connectionOverview": // Connection
			destination = doConnectionOverview(request, response);
			break;
			
		case "newConnection":
			destination = "newConnection.jsp";
			break;
			
		case "addConnection":
			destination = doAddConnection(request, response);
			break;
			
		case "fetchConnectionForUpdate":
			destination = doFetchConnectionForUpdate(request, response);
			break;
			
		case "updateConnection":
			destination = doUpdateConnection(request, response);
			break;
			
		case "fetchConnectionForDelete":
			destination = doFetchConnectionForDelete(request, response);
			break;
		
		case "deleteConnection":
			destination = doDeleteConnection(request, response);
			break;
			
		case "showAdministration": // administration
			destination = "administration.jsp";
			break;
			
		case "saveCountries":
			destination = doSaveCountries(request, response);
			break;
			
		case "loadCountries":
			destination = doLoadCountries(request, response);
			break;

		default:
			destination = "index.jsp";
			break;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}

	private String doCountryOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Country> countries = this.countries.getAll();
		request.setAttribute("countries", countries);
		return "countryOverview.jsp";
	}
	
	private String doAddCountry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "newCountry.jsp";
		
		Country country = new Country();
		
		List<String> result = new ArrayList<String>();
		getID(country, request, result);
		getName(country, request, result);
		getIncome(country, request, result);
		getTerritories(country, request, result);
		
		if (result.size() > 0) {
			request.setAttribute("result", result);
		} else {
			try {
				countries.add(country);
				request.setAttribute("success", true);
			} catch (Exception e) {
				result.add(e.getMessage());
				request.setAttribute("result", result);
			}
		}
		
		return destination;
	}

	private void getID(Country country, HttpServletRequest request, List<String> result) {
		String countryID = request.getParameter("countryID");
		request.setAttribute("countryIDPreviousValue", countryID);
		try {
			country.setId(countryID);
			request.setAttribute("countryIDClass", "has-success");
		} catch (Exception e) {
			request.setAttribute("countryIDClass", "has-error");
			result.add(e.getMessage());
		}
	}
	
	private void getName(Country country, HttpServletRequest request, List<String> result) {
		String name = request.getParameter("name");
		request.setAttribute("namePreviousValue", name);
		try {
			country.setName(name);
			request.setAttribute("nameClass", "has-success");
		} catch (Exception e) {
			request.setAttribute("nameClass", "has-error");
			result.add(e.getMessage());
		}
	}
	
	private void getIncome(Country country, HttpServletRequest request, List<String> result) {
		int income = Integer.valueOf(request.getParameter("income"));
		request.setAttribute("incomePreviousValue", income);
		try {
			country.setIncome(income);
			request.setAttribute("incomeClass", "has-success");
		} catch (Exception e) {
			request.setAttribute("incomeClass", "has-error");
			result.add(e.getMessage());
		}
	}
	
	private void getTerritories(Country country, HttpServletRequest request, List<String> result) {
		int territories = Integer.valueOf(request.getParameter("territories"));
		request.setAttribute("territoriesPreviousValue", territories);
		try {
			country.setTerritories(territories);
			request.setAttribute("territoriesClass", "has-success");
		} catch (Exception e) {
			request.setAttribute("territoriesClass", "has-error");
			result.add(e.getMessage());
		}
	}
	
	private String doFetchCountryForUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "updateCountry.jsp";
		Country country;
		String countryID = request.getParameter("countryid");
		country = countries.get(countryID);
		
		request.setAttribute("country", country);
		return destination;
	}

	private String doUpdateCountry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = doCountryOverview(request, response);
		
		Country country = new Country();
		
		List<String> result = new ArrayList<String>();
		getID(country, request, result);
		getName(country, request, result);
		getIncome(country, request, result);
		getTerritories(country, request, result);
		
		if (result.size() > 0) {
			request.setAttribute("result", result);
			destination = "updateCountry.jsp";
		} else {
			try {
				countries.update(country);
				destination = doCountryOverview(request, response);
			} catch (Exception e) {
				result.add(e.getMessage());
				request.setAttribute("result", result);
				destination = "updateCountry.jsp";
			}
		}
		return destination;
	}
	
	private String doFetchCountryForDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "deleteCountry.jsp";
		Country country;
		String countryID = request.getParameter("countryid");
		country = countries.get(countryID);
		
		request.setAttribute("country", country);
		return destination;
	}

	private String doDeleteCountry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination;
		String countryID = request.getParameter("id");
		countries.delete(countryID);
		destination = doCountryOverview(request, response);
		return destination;
	}

	private String doConnectionOverview(HttpServletRequest request, HttpServletResponse response) {
		List<Country> countries = this.countries.getAll();
		request.setAttribute("countries", countries);
		return "connectionOverview.jsp";
	}

	private String doAddConnection(HttpServletRequest request, HttpServletResponse response) {
		String destination = "newConnection.jsp";
		Connection connection = new Connection();
		List<String> result = new ArrayList<String>();
		
		Country country = getCountry(connection, request, result);
		getAdjacentCountry(connection, request, result);
		getAdjacentTerritories(connection, request, result);
		
		if (result.size() > 0) {
			request.setAttribute("result", result);
		} else {
			try {
				country.addConnection(connection);
				request.setAttribute("success", true);
			} catch (Exception e) {
				result.add(e.getMessage());
				request.setAttribute("result", result);
			}
		}
		
		return destination;
	}

	private Country getCountry(Connection connection, HttpServletRequest request, List<String> result) {
		Country country = null;
		
		try {
			country = this.countries.get(request.getParameter("countryid"));
			request.setAttribute("countryidPreviousValue", country.getId());
			request.setAttribute("countryClass", "has-success");
		} catch (Exception e) {
			request.setAttribute("countryClass", "has-error");
			result.add(e.getMessage());
		}
		return country;
	}

	private void getAdjacentCountry(Connection connection, HttpServletRequest request, List<String> result) {
		String adjacentCountryId = request.getParameter("adjacentcountryid");
		request.setAttribute("adjacentCountryPreviousValue", adjacentCountryId);
		try {
			connection.setAdjacentCountry(this.countries.get(adjacentCountryId));
			request.setAttribute("adjacentCountryClass", "has-success");
		} catch (Exception e) {
			request.setAttribute("adjacentCountryClass", "has-error");
			result.add(e.getMessage());
		}
	}

	private void getAdjacentTerritories(Connection connection, HttpServletRequest request, List<String> result) {
		int adjacentTerritories = Integer.valueOf(request.getParameter("territories"));
		request.setAttribute("territoriesPreviousValue", adjacentTerritories);
		try {
			connection.setAdjacentTerritories(adjacentTerritories);
			request.setAttribute("adjacentTerritoriesClass", "has-success");
		} catch (Exception e) {
			request.setAttribute("adjacentTerritoriesClass", "has-error");
			result.add(e.getMessage());
		}
	}

	private String doFetchConnectionForUpdate(HttpServletRequest request, HttpServletResponse response) {
		String destination = "updateConnection.jsp";
		Country country;
		Connection connection;
		country = countries.get(request.getParameter("countryid"));
		connection = country.getConnection(request.getParameter("connectioncountry"));
		
		request.setAttribute("country", country);
		request.setAttribute("connection", connection);
		return destination;
	}

	private String doUpdateConnection(HttpServletRequest request, HttpServletResponse response) {
		String destination = doConnectionOverview(request, response);
		Connection connection = new Connection();
		List<String> result = new ArrayList<String>();
		
		Country country = getCountry(connection, request, result);
		getAdjacentCountry(connection, request, result);
		getAdjacentTerritories(connection, request, result);
		
		if (result.size() > 0) {
			request.setAttribute("result", result);
			destination = "updateConnection.jsp";
		} else {
			try {
				country.addConnection(connection);
				destination = doConnectionOverview(request, response);
			} catch (Exception e) {
				result.add(e.getMessage());
				request.setAttribute("result", result);
				destination = "updateConnection.jsp";
			}
		}
		
		return destination;
	}

	private String doFetchConnectionForDelete(HttpServletRequest request, HttpServletResponse response) {
		String destination = "deleteConnection.jsp";
		Country country;
		Connection connection;
		country = countries.get(request.getParameter("countryid"));
		connection = country.getConnection(request.getParameter("connectioncountry"));
		
		request.setAttribute("country", country);
		request.setAttribute("connection", connection);
		return destination;
	}

	private String doDeleteConnection(HttpServletRequest request, HttpServletResponse response) {
		String destination;
		Country country = countries.get(request.getParameter("countryid"));
		Connection connection = country.getConnection(request.getParameter("connectioncountry"));
		country.deleteConnection(connection);
		destination = doConnectionOverview(request, response);
		return destination;
	}

	private String doSaveCountries(HttpServletRequest request, HttpServletResponse response) {
		String Destination = "administration.jsp";
		String path = request.getParameter("path");
		boolean canOverwrite = Boolean.getBoolean(request.getParameter("overwrite"));
		try {
			storeSaveFile(path, canOverwrite);
			request.setAttribute("success", "Successfully saved all countries");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		return Destination;
	}

	private String doLoadCountries(HttpServletRequest request, HttpServletResponse response) {
		String Destination = "administration.jsp";
		String path = request.getParameter("path");
		boolean canOverwrite = Boolean.getBoolean(request.getParameter("overwrite"));
		try {
			loadSavefile(path, canOverwrite);
			request.setAttribute("success", "Successfully loaded in all countries");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		return Destination;
	}
	
	private void loadSavefile(String path, boolean canOverwrite) throws FileNotFoundException, IOException, ClassNotFoundException {
		String temp = "";
		for (char character : path.toCharArray()) {
			if (character == '/') {
				temp += '\\';
			} else {
				temp += character;
			}
		}
		
		if (path.length() == temp.length()) {
			path = temp;
		} else {
			throw new ControllerException("The path caused an error while converting backslashes into forwardslashes");
		}
		
		ObjectInputStream in = null;
		
		if (path.isEmpty()) {
			in = new ObjectInputStream(new FileInputStream(System.getProperty("user.home") + "\\Desktop\\Games\\Warzone\\EuropeScenario\\Countries.txt"));
		} else {
			in = new ObjectInputStream(new FileInputStream(path));
		}
		
		if (canOverwrite) {
			this.countries = (CountryDb) in.readObject();
		} else {
			this.countries.addCountries((CountryDb) in.readObject());
		}
		
		in.close();
	}
	
	private void storeSaveFile(String path, boolean canOverwrite) throws FileNotFoundException, IOException, ClassNotFoundException {
		String temp = "";
		for (char character : path.toCharArray()) {
			if (character == '/') {
				temp += '\\';
			} else {
				temp += character;
			}
		}
		
		if (path.length() == temp.length()) {
			path = temp;
		} else {
			throw new ControllerException("The path caused an error while converting backslashes into forwardslashes");
		}
		
		
		ObjectOutputStream out = null;
		
		if (path.isEmpty()) {
			path = System.getProperty("user.home") + "\\Desktop\\Games\\Warzone\\EuropeScenario\\Countries.txt";
		}
		
		File file = new File(path);
		if (file.isFile()) {
			out = new ObjectOutputStream(new FileOutputStream(path));
			if (canOverwrite) {
				out.writeObject(countries);
			} else {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
				CountryDb otherCountries = (CountryDb) in.readObject();
				in.close();
				CountryDb output = new CountryDb();
				output.addCountries(otherCountries);
				output.addCountries(countries);
				out.writeObject(output);
			}
		} else {
			throw new ControllerException("The path is not a file");
		}
		out.close();
	}
	
}
