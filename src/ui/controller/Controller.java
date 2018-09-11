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
import domain.model.Country;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CountryDb countries;
	
	public Controller() {
		super();
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
		case "countryOverview":
			destination = doCountryOverview(request, response);
			break;
			
		case "connectionOverview":
			destination = doConnectionOverview(request, response);
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
			
		case "showAdministration":
			destination = "administration.jsp";
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

	private String doConnectionOverview(HttpServletRequest request, HttpServletResponse response) {
		List<Country> countries = this.countries.getAll();
		request.setAttribute("countries", countries);
		return "connectionOverview.jsp";
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
			destination = "newCountry.jsp";
		}
		
		else {
			try {
				countries.add(country);
				request.setAttribute("success", true);
				destination = "newCountry.jsp";
			} catch (Exception e) {
				result.add(e.getMessage());
				request.setAttribute("result", result);
				destination = "newUser.jsp";
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
		}
		else {
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
