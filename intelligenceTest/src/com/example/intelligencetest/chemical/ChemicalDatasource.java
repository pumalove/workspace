package com.example.intelligencetest.chemical;

public class ChemicalDatasource {
	
	Chemical chemical;
	public ChemicalDatasource() {
		chemical = new Chemical("Formidor", "Liquid", "+47 815 49 300");
	}
	
	public Chemical getChemical() {
		return chemical;
	}
	
}
