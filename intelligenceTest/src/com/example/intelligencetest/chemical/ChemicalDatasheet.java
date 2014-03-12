package com.example.intelligencetest.chemical;

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
