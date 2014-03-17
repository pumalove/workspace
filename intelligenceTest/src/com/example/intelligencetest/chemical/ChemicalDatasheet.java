package com.example.intelligencetest.chemical;

import org.json.JSONException;
import org.json.JSONObject;

public class ChemicalDatasheet {
	
	String chemicalId;
	//producer
	String revisionDate;
	String pdfAddress;
	
	//producer
	String producerId;
	String producerName;
	
	//general
	String containtmentAndCleaning;
	String environmentalPrecatuions;
	
	//firefighting
	String fireFightingExtinguishingMedia;
	String fireFightingSpecialHazards;
	String fireFightingAdvice;
	
	//firstaid
	String firstAidIfInhaled;
	String firstAidOnSkinContact;
	String firstAidOnEyeContact;
	String firstAidOnIngestion;
	
	//icons and descriptions
	String[] hazardSymbols;
	String[] hazardSymbolsDescription;
	String[] hazardSymbolIconLink;
	
	
	public void setDataFromJSON(JSONObject json_data) {
		try {
			this.chemicalId = json_data.getString("chem_id");
			this.revisionDate = json_data.getString("revision_date");
			this.pdfAddress = json_data.getString("pdf");
			this.producerId = json_data.getString("producer_id");
			this.producerName = json_data.getString("producer_name");
			this.containtmentAndCleaning = json_data.getString("containment_and_cleaning");
			this.environmentalPrecatuions = json_data.getString("environmental_precatuions");
			this.fireFightingExtinguishingMedia = json_data.getString("extinguishing_media");
			this.fireFightingSpecialHazards = json_data.getString("special_hazards");
			this.fireFightingAdvice = json_data.getString("firefighting_advice");
			this.firstAidIfInhaled = json_data.getString("ifinhaled");
			this.firstAidOnSkinContact = json_data.getString("onskincontact");
			this.firstAidOnEyeContact = json_data.getString("oneyecontact");
			this.firstAidOnIngestion = json_data.getString("oningestion");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getChemicalId() {
		return chemicalId;
	}
	public void setChemicalId(String chemicalId) {
		this.chemicalId = chemicalId;
	}
	public String getRevisionDate() {
		return revisionDate;
	}
	public String getPdfAddress() {
		return pdfAddress;
	}
	public String getProducerId() {
		return producerId;
	}
	public String getProducerName() {
		return producerName;
	}
	public String getContaintmentAndCleaning() {
		return containtmentAndCleaning;
	}
	public String getEnvironmentalPrecatuions() {
		return environmentalPrecatuions;
	}
	public String getFireFightingExtinguishingMedia() {
		return fireFightingExtinguishingMedia;
	}
	public String getFireFightingSpecialHazards() {
		return fireFightingSpecialHazards;
	}
	public String getFireFightingAdvice() {
		return fireFightingAdvice;
	}
	public String getFirstAidIfInhaled() {
		return firstAidIfInhaled;
	}
	public String getFirstAidOnSkinContact() {
		return firstAidOnSkinContact;
	}
	public String getFirstAidOnEyeContact() {
		return firstAidOnEyeContact;
	}
	public String getFirstAidOnIngestion() {
		return firstAidOnIngestion;
	}
	public void setRevisionDate(String revisionDate) {
		this.revisionDate = revisionDate;
	}
	public void setPdfAddress(String pdfAddress) {
		this.pdfAddress = pdfAddress;
	}
	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	public void setContaintmentAndCleaning(String containtmentAndCleaning) {
		this.containtmentAndCleaning = containtmentAndCleaning;
	}
	public void setEnvironmentalPrecatuions(String environmentalPrecatuions) {
		this.environmentalPrecatuions = environmentalPrecatuions;
	}
	public void setFireFightingExtinguishingMedia(
			String fireFightingExtinguishingMedia) {
		this.fireFightingExtinguishingMedia = fireFightingExtinguishingMedia;
	}
	public void setFireFightingSpecialHazards(String fireFightingSpecialHazards) {
		this.fireFightingSpecialHazards = fireFightingSpecialHazards;
	}
	public void setFireFightingAdvice(String fireFightingAdvice) {
		this.fireFightingAdvice = fireFightingAdvice;
	}
	public void setFirstAidIfInhaled(String firstAidIfInhaled) {
		this.firstAidIfInhaled = firstAidIfInhaled;
	}
	public void setFirstAidOnSkinContact(String firstAidOnSkinContact) {
		this.firstAidOnSkinContact = firstAidOnSkinContact;
	}
	public void setFirstAidOnEyeContact(String firstAidOnEyeContact) {
		this.firstAidOnEyeContact = firstAidOnEyeContact;
	}
	public void setFirstAidOnIngestion(String firstAidOnIngestion) {
		this.firstAidOnIngestion = firstAidOnIngestion;
	}

		
}
