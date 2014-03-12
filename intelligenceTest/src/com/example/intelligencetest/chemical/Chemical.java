package com.example.intelligencetest.chemical;

public class Chemical {

	String name;
	String type; //liquid - //gas
<<<<<<< HEAD
	
	public Chemical(String name, String type) {
		this.name = name;
		this.type = type;
=======
	String emergencyPhone;
	
	public Chemical(String name, String type, String emergencyPhone) {
		this.name = name;
		this.type = type;
		this.emergencyPhone = emergencyPhone;
>>>>>>> Kristian
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
<<<<<<< HEAD
=======

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}
>>>>>>> Kristian
	
	
}
