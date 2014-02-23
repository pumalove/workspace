package no.pumalove.treningslogg;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainMenu extends Activity  implements OnClickListener {
	ImageButton bAdd, bList, bLoggut2, bBib;
	//ImageView bAdd;
	//Button bLoggut;
	//String id;
	
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mainmenu);
	        bAdd = (ImageButton) findViewById(R.id.bAdd);
	        bList = (ImageButton) findViewById(R.id.bList);
	        bLoggut2 = (ImageButton) findViewById(R.id.bLoggut2);
	        bBib = (ImageButton) findViewById(R.id.bBib);
	        bAdd.setOnClickListener(this);
	        bList.setOnClickListener(this);  
	        bLoggut2.setOnClickListener(this); 
	        bBib.setOnClickListener(this); 
	         
	}
	 public void onClick(View arg0){
		 	Intent intent = getIntent();
	        String id = intent.getStringExtra("id");
	        System.out.println("brukernavn : " + id);
	    	switch (arg0.getId()){
		    	case R.id.bAdd:
		    		Intent i = new Intent("android.intent.action.UserPage");
		    		i.putExtra("id",id);
		    		startActivity(i);
		    		break;

		    	case R.id.bList:
		    		Intent i2 = new Intent("android.intent.action.VisOvelser");
		    		startActivity(i2);
		    		break;
		    		
		    	case R.id.bBib:
		    		Intent i3 = new Intent("android.intent.action.Bibliotek");
		    		startActivity(i3);
		    		break;
		    	
		    	
		    	case R.id.bLoggut2:
		    		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	            	 SharedPreferences.Editor editor = preferences.edit();
	            	 editor.putString("Username", null);
	            	 editor.commit();
	            	 String name = preferences.getString("Username","");
	                 System.out.println("SP Username "+ name);
	                 this.finish();
	                 Intent intent2 = new Intent(this, Main.class);
	                 this.startActivity(intent2);
		    		break;
	   
		    		
		    		
		    		
	    		}
	 }
}
