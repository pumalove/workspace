package no.pumalove.treningslogg;
/* -----------------------------
    Imports
-----------------------------*/
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
 
 /* -----------------------------
    Main starts
-----------------------------*/
public class Main extends Activity {
/* -----------------------------
    Declararing variables used in the code
-----------------------------*/
    ImageButton b, bReg;
    EditText username,password;
    TextView tv;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    boolean haveConnectedWifi = false;
    boolean haveConnectedMobile = false;
    String huskUsername;
    
     
    @Override
/* -----------------------------
    onCreate:
    Setting listener on buttons and 
    creating threads.
-----------------------------*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // setting the layout
         
        b = (ImageButton)findViewById(R.id.Button01);  //finding the button from the layout xml
        bReg = (ImageButton)findViewById(R.id.bRegUser);
        username = (EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        
        
        
        
        
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Username","");
        System.out.println("SP Username "+ name);
        if(!name.equalsIgnoreCase(""))
        {
          // logged in
        	Intent i = new Intent("android.intent.action.MainMenu");
            //String[] myStrings = new String[] {username.getText().toString().trim()};
            //String myStrings = username.getText().toString().trim();
           // i.putExtra("strings", myStrings);
        	User.setName(name);
            i.putExtra("id", name);
    		startActivity(i);
        }
        
        	 bReg.setOnClickListener(new OnClickListener(){

     			@Override
     			public void onClick(View arg0) {
     				Intent iReg = new Intent("android.intent.action.RegUser");
             		startActivity(iReg);
     			}
             	
             });
             
             b.setOnClickListener(new OnClickListener() { // creating clicklistener on button
                 @Override
                 public void onClick(View v) {
                     dialog = ProgressDialog.show(Main.this, "", "Logger inn...", true);
                      new Thread(new Runnable() {//making a thread
                             public void run() {                        	
                                 login();//running login()
                             }
                           }).start();          
                 	}
             });
        
        
      
        
       
       
    }//onCreate

/* -----------------------------
    login: 
    Checking if user can reach server. If not -> timeout
    Getting the values for username and password.
    httpPOST to PHP-file. 
    Checking the PHP-response.
-----------------------------*/     
    void login(){
        try{            
        	
        	// Checking if user can reach server
        	HttpGet httpGet = new HttpGet("http://home.uia.no/jorgel11/App/check2.php");
        	HttpParams httpParameters = new BasicHttpParams();
        	// Set the timeout in milliseconds until a connection is established.
        	// The default value is zero, that means the timeout is not used. 
        	int timeoutConnection = 3000;
        	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        	// Set the default socket timeout (SO_TIMEOUT) 
        	// in milliseconds which is the timeout for waiting for data.
        	int timeoutSocket = 5000;
        	HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        	DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
        	HttpResponse response2 = httpClient.execute(httpGet);
        	response2.getEntity().consumeContent();
        	// If check fails, returns, and thread stops
        }
        catch(Exception e){
        	showAlertInternett();
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
        
        try{ 
        	//Sending values to the PHP-script
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://home.uia.no/jorgel11/App/check2.php"); // 
            //creating arraylist
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
            nameValuePairs.add(new BasicNameValuePair("username",username.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("password",password.getText().toString().trim())); 
            huskUsername = username.getText().toString().trim();
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
           
        	User.setName(huskUsername);
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
            response.getEntity().consumeContent();
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);

            System.out.println("Respons : " + response); 
            runOnUiThread(new Runnable() {
                public void run() {
                    //tv.setText("Respons fra PHP : " + response);
                    dialog.dismiss();
                }
            });

           
             
            if(response.equalsIgnoreCase("Bruker funnet")){
            	
            	//Log.v("EditText", username.getText().toString());
            	
            	  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            	  SharedPreferences.Editor editor = preferences.edit();
            	  editor.putString("Username", huskUsername);
            	  editor.commit();
            	
            	
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(Main.this,"Logget inn", Toast.LENGTH_SHORT).show();
                    }
                });
                 
                
                
                Intent i = new Intent("android.intent.action.MainMenu");
                i.putExtra("id", username.getText().toString().trim());
        		startActivity(i);
            }
            
            else{
                showAlert();                
            }
             
        }
        catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }//login ends
/* -----------------------------
    showAlert: 
    Gives the user feedback. 
    Wrong user or password.
-----------------------------*/
    public void showAlert(){
        Main.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                builder.setTitle("Prøv igjen.");
                builder.setMessage("Feil brukernavn eller passord.")  
                       .setCancelable(false)
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                           }
                       });                     
                AlertDialog alert = builder.create();
                alert.show();               
            }
        });
    }//showAlert ends
    
    public void showAlertInternett(){
        Main.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                builder.setTitle("Kan ikke nå serveren.");
                builder.setMessage("Sjekk internettilkobling.")  
                       .setCancelable(false)
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                        	   //alert.dismiss();
                           }
                       });                     
                AlertDialog alert = builder.create();
                alert.show();               
            }
        });
    }//showAlert ends

}//end main