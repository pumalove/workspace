package com.example.intelligencetest.persons;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class PersonDatasource {
	
	
	private static final String LOGTAG = "PersonDataSource";
	
	List<Person> personList = new ArrayList<Person>();
	char[] alphabet = "abcdefghijklmnopqrstuvwxyz¾¿Œ".toCharArray();
	List<Character> sections;
	
	public PersonDatasource() {
		personList.add(new Person("Stian", "Abrahamsen", 1, "0", "91799756"));
		personList.add(new Person("noen", "Abrahamsen", 1, "0", "91799756"));
		personList.add(new Person("noen", "Abrahamsen", 1, "0", "91799756"));
		personList.add(new Person("noen", "Abrahamsen", 1, "0", "91799756"));
		personList.add(new Person("noen", "Abrahamsen", 1, "0", "91799756"));
		personList.add(new Person("noen", "Abrahamsen", 1, "0", "91799756"));
		personList.add(new Person("noen", "Abrahamsen", 1, "0", "91799756"));
		personList.add(new Person("noen", "Abrahamsen", 1, "0", "91799756"));
		personList.add(new Person("Morgan", "Bolle", 1, "0", "91799756"));
		personList.add(new Person("Test", "Etternavn", 1, "0", "91799756"));
		personList.add(new Person("Morgan", "Etternavn2", 1, "0", "91799756"));
		personList.add(new Person("Bj¿rn", "Kirkegaard", 1, "0", "91799756"));
		personList.add(new Person("J¿rgen", "Lyngstad", 1, "0", "91799756"));
		personList.add(new Person("Morgan", "Rutternavn2", 1, "0", "91799756"));
		personList.add(new Person("Kristian", "Storvoll", 1, "0", "91799756"));
		personList.add(new Person("Kristian", "Storvoll", 1, "0", "91799756"));
		personList.add(new Person("Kristian", "Storvoll", 1, "0", "91799756"));
		personList.add(new Person("Kristian", "Storvoll", 1, "0", "91799756"));
		personList.add(new Person("Kristian", "Storvoll", 1, "0", "91799756"));
		personList.add(new Person("Morgan", "Spiker", 1, "0", "91799756"));
	}

	
	//see if the section allready exists
	public boolean hasSection(char letter) {	
		if(sections == null) return false;
		
		for(int i = 0; i < sections.size(); i++) {
			if(Character.toUpperCase(sections.get(i)) == Character.toUpperCase(letter)) return true;
		}
		return false;
	}
	

	public List<Character> getSections() {
		sections = new ArrayList<Character>();
		Log.i(LOGTAG, "Number of persons to create sections: " + personList.size());
		
		for(int a = 0; a < alphabet.length; a++) {
			for(Person p : personList) {
				if(String.valueOf(p.getLastname().charAt(0)).equalsIgnoreCase(String.valueOf(alphabet[a])) && 
						!hasSection(p.getLastname().charAt(0))) {
					sections.add(alphabet[a]);
				}
				
			}
		}
		
		return sections;
	}
	
	public int getLargestRowCount(char c) {
		int largestCount = 0;
		int counter = 0;
		
		for(Person p : personList) {
			if(String.valueOf(p.getLastname().charAt(0)).equalsIgnoreCase(String.valueOf(c))) {
				counter++;
			}
		}
		if(counter > largestCount) {
			largestCount = counter;
			counter = 0;
		} else {
			counter = 0;
		}

		Log.i(LOGTAG, "Larges row count: " + largestCount);
		return largestCount;
	}
	
	public Person[][] getPersonArray() {
		Person[][] personArray = new Person[sections.size()][];
		for(int section = 0; section < getSections().size(); section++) {
			int count = 0;
			personArray[section] = new Person[getLargestRowCount(sections.get(section))];
			for(Person p : personList) {
				if(String.valueOf(p.getLastname().charAt(0)).equalsIgnoreCase(String.valueOf(sections.get(section)))) {
					personArray[section][count] = p;
					count++;
				}
			}
		}
		
		return personArray;
	}
	
	public List<Person> getPersonList() {
		return personList;
	}
}
