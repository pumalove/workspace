package com.example.intelligencetest.chemical;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.util.Log;

import com.example.intelligencetest.persons.Person;

public class ChemicalDatasource {
	
	private static final String LOGTAG = "ChemicalDatasource";
	
	
	Chemical currentChemical;
	ChemicalDatasheet currentDatasheet;
	
	public ChemicalDatasource() {
		//chemical = new Chemical("Formidor", "Liquid", "+47 815 49 300");
	}
	
	
	public Chemical getChemicalById(String id) {
		JSONArray jArray = null;

		String result = null;

		StringBuilder sb = null;

		InputStream is = null;


		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		try{
			 nameValuePairs.add(new BasicNameValuePair("chemid", id.trim()));
			 HttpClient httpclient = new DefaultHttpClient();
			 
			 HttpPost httppost = new HttpPost("http://home.uia.no/jorgel11/ICA/getAllChemInfo.php?chemid=3");
			 httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			 HttpResponse response = httpclient.execute(httppost);
			 HttpEntity entity = response.getEntity();
			 is = entity.getContent();
		}catch(Exception e){
	         Log.e("log_tag", "Error in http connection"+e.toString());
		}
		//convert response to string
		try {
			
	       BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	       sb = new StringBuilder();
	       sb.append(reader.readLine() + "\n");
	
	       String line="0";
	       while ((line = reader.readLine()) != null) {
	                      sb.append(line + "\n");
	       }
	       is.close();
	       result=sb.toString();
	       Log.i(LOGTAG, result);
       	} catch(Exception exception) {
            Log.e("log_tag", "Error converting result "+exception.toString());
        }
		
		try {
			JSONArray array = new JSONArray(result);
			JSONObject object = array.getJSONObject(0);
			jArray = object.getJSONArray("chem");
			JSONObject json_data = null;
	      for(int i=0;i<jArray.length();i++){
	    	  json_data = jArray.getJSONObject(i);
	          	createChemicalFromJSON(json_data);
	          	
	         }
	      }
	      catch(JSONException JSONException){
	       Log.i(LOGTAG , "No data found: + " + JSONException.toString());
	      } catch (ParseException parseException) {
	    	  parseException.printStackTrace();
	      }
		
		return currentChemical;
	}
	
	
	private void createDatasheetFromJSON(JSONObject json_data) {
		currentDatasheet = new ChemicalDatasheet();
		
		try {
			currentDatasheet.setChemicalId(json_data.getString("chem_id"));
			currentDatasheet.setRevisionDate(json_data.getString("revision_date"));
			currentDatasheet.setChemicalId(json_data.getString("pdf"));
			currentDatasheet.setChemicalId(json_data.getString("producer_id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
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
		this.firstAidOnIngestion = json_data.getString("oningestion");*/
	}
	
	private void createChemicalFromJSON(JSONObject json_data) {
		currentChemical = new Chemical();
		try {
			currentChemical.setName(json_data.getString("chem_name"));
			currentChemical.setType(json_data.getString("chem_type"));
			currentChemical.setChemicalId(json_data.getString("chem_id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
