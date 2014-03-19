package com.example.intelligencetest.chemical;

public class ChemicalDatasource {
	
	Chemical chemical;
	public ChemicalDatasource() {
		chemical = new Chemical("Melk", "Liquid");
	}
	
	public Chemical getChemical() {
		return chemical;
	}
	
}
