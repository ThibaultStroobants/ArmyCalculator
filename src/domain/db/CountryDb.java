package domain.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.model.Country;

public class CountryDb implements Serializable {
	
	private static final long serialVersionUID = Long.parseLong("5044190751079313744");	
	private Map<String, Country> countries = new HashMap<String, Country>();
	
	public CountryDb() {
		
	}
	
	public Country get(String countryId) {
		if (countryId == null) {
			throw new DbException("No id given");
		}
		return this.countries.get(countryId);
	}
	
	public List<Country> getAll() {
		return new ArrayList<Country>(countries.values());
	}
	
	public void add(Country country) {
		if (country == null) {
			throw new IllegalArgumentException("No country given");
		}
		if (countries.containsKey(country.getId())) {
			throw new DbException("Country already exists");
		}
		this.countries.put(country.getId(), country);
	}
	
	public void update(Country country) {
		if (country == null) {
			throw new IllegalArgumentException("No country given");
		}
		if (!countries.containsKey(country.getId())){
			throw new DbException("Country not found");
		}
		this.countries.put(country.getId(), country);
	}
	
	public void delete(String countryId){
		if (countryId == null) {
			throw new IllegalArgumentException("No id given");
		}
		countries.remove(countryId);
	}

	public void addCountries(CountryDb countries) {
		for (Country country : countries.getAll()) {
			this.add(country);
		}
	}
	
	public List<Country> getSortedCountries(String sortby) {
			
		return null;
	}
	
}