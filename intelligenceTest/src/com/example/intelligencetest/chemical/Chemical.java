package com.example.intelligencetest.chemical;

public class Chemical {

	String name;
	String type; //liquid - //gas
	String emergencyPhone;
	
	public Chemical(String name, String type, String emergencyPhone) {
		this.name = name;
		this.type = type;
		this.emergencyPhone = emergencyPhone;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}



	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}
	
}
