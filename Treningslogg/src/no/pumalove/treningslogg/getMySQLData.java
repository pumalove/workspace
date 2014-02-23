package no.pumalove.treningslogg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
 //****************************************************** 
// IKKE LENGRE I BRUK
//****************************************************

public class getMySQLData extends ListActivity {
	 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> productsList;
 
    // url to get all products list
    private static String url_all_products = "http://home.uia.no/jorgel11/App/dbPHP.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_OID = "oID";
    private static final String TAG_ONAVN = "oNavn";
    private static final String TAG_OBESKRIVELSE = "oBeskrivelse";
    private static final String TAG_OKATEGORI = "oKategori";
 
    // products JSONArray
    JSONArray products = null;

	public int map;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_products);
 
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllProducts().execute();
 
        // Get listview
        ListView lv = getListView();
 
        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                //String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                     //   .toString();
 
                // Starting new intent
                //Intent in = new Intent(getApplicationContext(),
                //        EditProductActivity.class);
                // sending pid to next activity
             //   in.putExtra(TAG_PID, pid);
 
                // starting new activity and expecting some response back
               // startActivityForResult(in, 100);
            }
        });
 
    }
 
    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
 
    }
 
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
    	
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getMySQLData.this);
            pDialog.setMessage("Laster ned nyeste øvelser fra databasen...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting All products from url
         * */
        
        
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            try{
            	 JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
         
           
 
            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());
           
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);
 
                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_OID);
                        final String name = c.getString(TAG_ONAVN);
                        final String besk = c.getString(TAG_OBESKRIVELSE);
                        final String kat = c.getString(TAG_OKATEGORI);
                        
                       final ArrayList<String> g = new ArrayList();
                        runOnUiThread(new Runnable() {
                        	  public void run() {
                        		  try{
                        			  System.out.println(g.size());
                                      DatabaseHelper dbh = new DatabaseHelper(getMySQLData.this);
                                  	dbh.open();
                                  	ArrayList<String> g = dbh.checkBibData(name);
                                  	
                                  	System.out.println(g.size());
                                  	if(g.size()>=1){
                                  		System.out.println(name + " " + kat + " eksisterer allerede i databasen");
                                  	}
                                  	else if(g.size()==0)
                                  	{
                                  		System.out.println(name + " " + kat + " eksisterer ikke  i databasen, legger inne øvelser");	
                                  	dbh.createEntry_bib(name.trim(), besk, kat);                                  	
                                  	System.out.println(name + besk+ kat);
                                  	dbh.close();
                                  	}
                                  	
                                      }
                                      catch(Exception e){
                                      	System.out.println(e);
                                      }
                        	  }
                        	});
                       
                        System.out.println(name + " "+ besk);
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_OID, id);
                        map.put(TAG_ONAVN, name);
                        map.put(TAG_OBESKRIVELSE, besk);
                        map.put(TAG_OKATEGORI, kat);

                        
 
                        // adding HashList to ArrayList
                        productsList.add(map);
                        
                        
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    //Intent i = new Intent(getApplicationContext(),
                          //  NewProductActivity.class);
                    // Closing all previous activities
                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   // startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            
            }
            catch(Exception e)
            {
            	System.out.println(e);
            }
			return null;
        }
            
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                    		getMySQLData.this, productsList,
                            R.layout.list_item, new String[] { TAG_OID,
                                    TAG_ONAVN, TAG_OBESKRIVELSE, TAG_OKATEGORI},
                            new int[] { R.id.oID, R.id.oNavn, R.id.oBeskrivelse, R.id.oKategori});
                    // updating listview
                    setListAdapter(adapter);
                }
            });
 
        }
        
        
 
    }
}
