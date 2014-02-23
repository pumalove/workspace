package no.pumalove.treningslogg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Bibliotek extends Activity{
	
	byte[] bytarray;
	ImageButton bAddOvelse ,button1;
	EditText txtOvelse, txtKat, txtBesk;
	
	
	//****************************************************
	
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
	private ArrayAdapter<String> arrayAdapter;
	private ListView lv;
	
	
	
	//****************************************************
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.bibliotek);
        ImageButton bAddOvelse = (ImageButton) findViewById(R.id.bAddOvelse);
        ImageButton button1 = (ImageButton) findViewById(R.id.bLastOpp); 
        refresh();
        
        
        
        button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				  new LoadAllProducts().execute();
				//Intent i3 = new Intent("android.intent.action.getMySQLData");
	    		//startActivity(i3);

				
			}
        	
        });
        
        
        
        /**
        DatabaseHelper dbh = new DatabaseHelper(Bibliotek.this);
    	dbh.open();		
    	final ArrayList<String> data = dbh.getBibOvelser();
    	final ArrayList<String> idArray = dbh.getIDArray();
    	dbh.close();		
    	//tv.setText(data.get(1));

    	for (int i = 0; i < data.size(); i++){
    		LinearLayout layout = new LinearLayout(this);
    		String item = data.get(i);
    		System.out.println("Item " + i + " : " + item);
    		TextView tv2=new TextView(getApplicationContext());
    	    tv2.setText(data.get(i));
    	    layout.addView(tv2);
    		}	
    	  **/
         lv = (ListView) findViewById(R.id.listView2);
        // This is the array adapter, it takes the context of the activity as a first // parameter, the type of list view as a second parameter and your array as a third parameter
        
        
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	
            	
                String product = ((TextView) view).getText().toString();
                System.out.println("Sqlview "+position);
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), SingleBibliotek.class);
              Bundle bundle = new Bundle();
                bundle.putInt("id", position);    
                i.putExtras(bundle);
                startActivity(i);
               
               
            }
            
        });
       
       
        bAddOvelse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showCustomDialog();
	
		
		
			}
			
			});
	
        lv.setOnItemLongClickListener(new OnItemLongClickListener() {
        	public boolean onItemLongClick(AdapterView<?> parent, View view,
                    final int position, long id) {
        	DatabaseHelper dbh = new DatabaseHelper(Bibliotek.this);
    	    dbh.open();		
    	    ArrayList<String> idArray = dbh.getIDArrayBib();
    	    final ArrayList<String> data = dbh.getBibOvelser();
    		dbh.close();
        		
        		
        	System.out.println("-----FISHEBOLLHA------ ");
        	System.out.println("position " +position);
        	System.out.println(idArray.get(position)+ " ID til treningsøkta");
        	final String valgtID = idArray.get(position);
        	System.out.println("-----YOLO------");
        		//return false;
        	
        	AlertDialog.Builder builder = new AlertDialog.Builder(Bibliotek.this);
                builder.setMessage("Sikker på at du vil slette denne treningsøkta?")
                .setCancelable(false)
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                        	   System.out.println("-----Sletter------ ");
                        	   data.remove(position);
                        	   arrayAdapter.notifyDataSetChanged();
                        	   DatabaseHelper info = new DatabaseHelper(Bibliotek.this);
                           		info.open();		
                           		info.deleteEntryBib(valgtID);
                           		info.close();		
                        	   refresh();
                           }
                       })
                       .setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                        	   System.out.println("-----Sletter ikke------ "); 
                                dialog.cancel();
                           }
                       });
                AlertDialog alert = builder.create();
                alert.show();
               // return true;

    		return false;
        	
            }

        	
        	
          });
      
        
      
        
        
      //****************************************************
        //Oncreate 
        
     // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
      //  new LoadAllProducts().execute();
 
        // Get listview
       // ListView lv = getListView();
        
        
      //****************************************************
        
        
        }
	
public void refresh(){
		
		//arrayAdapter.notifyDataSetChanged();
		//lv.invalidateViews();
		 DatabaseHelper dbh = new DatabaseHelper(Bibliotek.this);
	    	dbh.open();		
		final ArrayList<String> data = dbh.getBibOvelser();
		for (int i = 0; i < data.size(); i++){
    		LinearLayout layout = new LinearLayout(this);
    		String item = data.get(i);
    		System.out.println("Item " + i + " : " + item);
    		TextView tv2=new TextView(getApplicationContext());
    	    tv2.setText(data.get(i));
    	    layout.addView(tv2);
    		}	
         lv = (ListView) findViewById(R.id.listView2);
        // This is the array adapter, it takes the context of the activity as a first // parameter, the type of list view as a second parameter and your array as a third parameter
         arrayAdapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data);
        lv.setAdapter(arrayAdapter); 
        dbh.close();
        
        
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
            pDialog = new ProgressDialog(Bibliotek.this);
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
                                      DatabaseHelper dbh = new DatabaseHelper(Bibliotek.this);
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
        	refresh();
            pDialog.dismiss();
            
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                	/**
                    ListAdapter adapter = new SimpleAdapter(
                    		getMySQLData.this, productsList,
                            R.layout.list_item, new String[] { TAG_OID,
                                    TAG_ONAVN, TAG_OBESKRIVELSE, TAG_OKATEGORI},
                            new int[] { R.id.oID, R.id.oNavn, R.id.oBeskrivelse, R.id.oKategori});
                    // updating listview
                    setListAdapter(adapter);
                }
               
            });
             **/
                	
                	
 
        
        
        
 
    }

				
	
	
	

	
	});
            
        }
        
    }
	protected void showCustomDialog() {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(Bibliotek.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.customdailog);
		
		final EditText editText = (EditText)dialog.findViewById(R.id.editText1);
		final EditText editText2 = (EditText)dialog.findViewById(R.id.editText2);
		final EditText editText3 = (EditText)dialog.findViewById(R.id.editText3);
		ImageButton button = (ImageButton)dialog.findViewById(R.id.bLastOpp);
		
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(!editText.toString().equals(null)){
					if(!editText2.toString().equals(null)){
						if(!editText3.toString().equals(null)){
					

							//Intent i = new Intent(
							//		Intent.ACTION_PICK,
							//		android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							
							

							
					DatabaseHelper info = new DatabaseHelper(Bibliotek.this);
					info.open();		
					
					info.createEntry_bib(editText.getText().toString().trim(), editText2.getText().toString().trim(), editText3.getText().toString().trim());
					info.close();
					System.out.println("OK");	
				}
						else{
							System.out.println("Fyll inn beskrivelse");	
						}
				}
					else{
						System.out.println("Fyll inn kategori");	
					}
				}
				else{
					System.out.println("Fyll inn navn");	
				}
				
				
				
				
				
				dialog.dismiss();
				refresh();
				
			}
		});
		
		ImageButton button2 = (ImageButton)dialog.findViewById(R.id.button2);
		
		
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		
				
		dialog.show();
		
	}
	
}

    
  
    


	

         
    


