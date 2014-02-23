package no.pumalove.treningslogg;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;


public class lastOpp extends Activity{
	
	    /**
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.lastopp);
	        
	        DatabaseHelper info = new DatabaseHelper(lastOpp.this);
 			info.open();
 			final ArrayList<String> yoloIDA = info.getAllOID();
 			final ArrayList<String> treningsidA = info.getOId();
 			final ArrayList<String> onameA = info.getOname();
 		//	final ArrayList<String> settA = info.getSett();
 		//	final ArrayList<String> rep1A = info.getRep1();
 		//	final ArrayList<String> vekt1A = info.getVekt1();
 			info.close();
			
			
			
			/**
			ArrayList<String> rep2A = new ArrayList<String>();
			ArrayList<String> rep3A = new ArrayList<String>();
			ArrayList<String> rep4A = new ArrayList<String>();
			ArrayList<String> rep5A = new ArrayList<String>();
			
			ArrayList<String> vekt2A = new ArrayList<String>();
			ArrayList<String> vekt3A = new ArrayList<String>();
			ArrayList<String> vekt4A = new ArrayList<String>();
			ArrayList<String> vekt5A = new ArrayList<String>();
			 
	
		
 			
			
	        new Thread(new Runnable() {//making a thread
	        	
                public void run() {          
                	JSONObject jsonObject= new JSONObject();
         			JSONArray jsonArray=new JSONArray();
                	for(int i = 0; i<yoloIDA.size(); i++){
                	  

                	
                	try {
            		
                		//jsonObject.put("yoloid",yoloIDA.get(i));
                	    //jsonObject.put("treningsid",treningsidA.get(i));
                	    //jsonObject.put("username",User.getName().toString());
                	  //  jsonObject.put("oname",onameA.get(i));
                	 //   jsonObject.put("sett",settA.get(i));
                	 //   jsonObject.put("rep1",rep1A.get(i));
                	//    jsonObject.put("vekt1",vekt1A.get(i));
                	//    jsonArray.put(jsonObject);
                		
					}
                	
                	
                	catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                	
		
        			try{
        				 HttpClient httpClient = new DefaultHttpClient();
        			        HttpPost httpPost = new HttpPost("http://home.uia.no/jorgel11/App/addUserOvelser.php");

        			        
        			            HttpEntity httpEntity = new StringEntity(jsonArray.toString());
        			            httpPost.setEntity(httpEntity);
        			            //httpClient.execute(httpPost);
        			        //    HttpResponse response2 = httpClient.execute(httpPost);
        			      //  	response2.getEntity().consumeContent();
        			       
                
        			}
        			catch(Exception e){
        				System.out.println(e);
        			}         
                }
                }
              }).start();  
	        
			//Create JSON string start
			
	        
	 }
**/
	 
}
