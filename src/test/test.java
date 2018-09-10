package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import domain.db.CountryDb;
import domain.model.Connection;
import domain.model.Country;

public class test {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		Country portugal = new Country("A", "Portugal", 6, 18);
		Country spain = new Country("B", "Spain", 17, 50);
		Country france = new Country("C", "France", 31, 93);
		
		CountryDb countries = new CountryDb();
		countries.add(portugal);
		countries.add(spain);
		countries.add(france);
		
		Connection portspain = new Connection(spain, 10);
		Connection spainport = new Connection(portugal, 7);
		Connection francespain = new Connection(spain, 5);
		Connection spainfrance = new Connection(france, 5);
		
		portugal.addConnection(portspain);
		spain.addConnection(spainport);
		spain.addConnection(spainfrance);
		france.addConnection(francespain);
		
		for (Country country : countries.getAll()) {
			System.out.println(country.getName() + "\n");
			for (String calculatedArmies : country.getAllCalculatedArmiesPerTerritory()) {
				System.out.println(calculatedArmies);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("done");
		
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\Games\\Warzone\\EuropeScenario\\Countries.txt"));
		out.writeObject(countries);
		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(System.getProperty("user.home") + "\\Desktop\\Games\\Warzone\\EuropeScenario\\Countries.txt"));
		CountryDb countries2 = (CountryDb) in.readObject();
		in.close();
		List<Country> something = countries2.getAll();
		
		for (Country country : something) {
			System.out.println(country.getId() + " | " + country.getName() + " | " + country.getIncome() + " | " + country.getTerritories());
		}
		
		for (Country country : countries2.getAll()) {
			System.out.println(country.getName() + "\n");
			for (String calculatedArmies : country.getAllCalculatedArmiesPerTerritory()) {
				System.out.println(calculatedArmies);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("done");

	}

}
