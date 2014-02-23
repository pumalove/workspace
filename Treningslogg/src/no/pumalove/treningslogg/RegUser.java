package no.pumalove.treningslogg;
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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class RegUser extends Activity implements OnClickListener{
		ImageButton bRegUser, bBack;
		EditText username, password1, password2;
	    HttpPost httppost;
	    StringBuffer buffer;
	    HttpResponse response;
	    HttpClient httpclient;
	    List<NameValuePair> nameValuePairs;
	    ProgressDialog dialog = null;
	   
	    
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reguser);
		 username = (EditText)findViewById(R.id.etUsername);
	     password1 = (EditText)findViewById(R.id.etPassword1);
	     password2 = (EditText)findViewById(R.id.etPassword2);
	     bRegUser = (ImageButton)findViewById(R.id.bRegUser);
	     bBack = (ImageButton)findViewById(R.id.bRegBack);
	     
	     bRegUser.setOnClickListener(this);
	     bBack.setOnClickListener(this);
	    

		
	}
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
    	case R.id.bRegUser:
    		if(!username.getText().toString().equals("")){
    		if(!password1.getText().toString().equals("")){
    		if (password1.getText().toString().equals(password2.getText().toString())) {
				System.out.println("keycode match");
				  new Thread(new Runnable() {//making a thread
                        public void run() {
                        	
                            regUser();
                            
                        }
                      }).start();
			}
    		else if(!password1.getText().toString().equals("")){
    			 if (!password1.getText().toString().equals(password2.getText().toString())){
    	    	    System.out.println("keycode dont match");
    	    	    showAlertPassword();
    			 	}
    		}
		 
		 
    		}
    	}
    		if(username.getText().toString().equals("")){
    			runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(RegUser.this,"Fyll inn brukernavn", Toast.LENGTH_SHORT).show();
                        
                    }
                });
    		}
        		if(password1.getText().toString().equals("")){
        			runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(RegUser.this,"Fyll inn passord", Toast.LENGTH_SHORT).show();
                            
                        }
                    });
        		
        		}
    		break;

    	case R.id.bRegBack:
    		this.finish();
			Intent i2 = new Intent(this, Main.class);
    		this.startActivity(i2);
    		break;
		
	}
}
	public void regUser(){
        try{            
        	
        	// Checking if user can reach server
        	HttpGet httpGet = new HttpGet("http://home.uia.no/jorgel11/App/check2.php"); // CHANGE
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
        	//response.getEntity().consumeContent();
        	
        	// If check fails, returns, and thread stops
        }
        catch(Exception e){
        	//showAlertInternett();
           // dialog.dismiss();
            System.out.println("Når ikke server : " + e.getMessage());
        }
        
        try{ 
        	
        	  //Sending values to the PHP-script
                httpclient=new DefaultHttpClient();
                httppost= new HttpPost("http://home.uia.no/jorgel11/App/reguser.php"); // CHANGE
                //creating arraylist
                nameValuePairs = new ArrayList<NameValuePair>(2);
                // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
                nameValuePairs.add(new BasicNameValuePair("username",username.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
                nameValuePairs.add(new BasicNameValuePair("password",password1.getText().toString().trim())); 
               // huskUsername = username.getText().toString().trim();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //Execute HTTP Post Request
                //response=httpclient.execute(httppost);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);
                System.out.println("Respons : " + response); 
                
                if(response.equalsIgnoreCase("Fant ikke bruker")){
                	//User users = new User();
                	//users.setUsername(username.getText().toString());
                	//Log.v("EditText", username.getText().toString());
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(RegUser.this,"Ny bruker registrert", Toast.LENGTH_SHORT).show();
                            
                        }
                    }); 
                }
                else if(response.equalsIgnoreCase("Bruker eksisterer")){
                	//User users = new User();
                	//users.setUsername(username.getText().toString());
                	//Log.v("EditText", username.getText().toString());
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(RegUser.this,"Brukernavet er opptatt", Toast.LENGTH_SHORT).show();
                        }
                    }); 
                }
                else if(response.equalsIgnoreCase("no input")){
                	//User users = new User();
                	//users.setUsername(username.getText().toString());
                	//Log.v("EditText", username.getText().toString());
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(RegUser.this,"Fyll inn brukernavn", Toast.LENGTH_SHORT).show();
                        }
                    }); 
                }
                	
             
        }
        catch(Exception e){
           // dialog.dismiss();
            System.out.println("Feil med registrering : " + e.getMessage());
        }
    }//login ends
	
	   public void showAlert(){
	        RegUser.this.runOnUiThread(new Runnable() {
	            public void run() {
	                AlertDialog.Builder builder = new AlertDialog.Builder(RegUser.this);
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
	   public void showAlertPassword(){
	        RegUser.this.runOnUiThread(new Runnable() {
	            public void run() {
	                AlertDialog.Builder builder = new AlertDialog.Builder(RegUser.this);
	                builder.setTitle("Prøv igjen.");
	                builder.setMessage("Du har skrevet to ulike passord.")  
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

}
