package no.pumalove.treningslogg;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
 // Legger til info i databasen
public class UserPage extends Activity implements OnClickListener {
	

Button sqlView, sqlModify, sqlGetInfo, sqlDelete, buttonNext, buttonPrevious;
ImageButton sqlUpdate;
EditText sqlUsername, sqlName, sqlRow;
 String usernameString;
 String[] myStrings;
	int selectedyear;
	int selecteddayOfMonth;
	int selectedMonth;
	CalendarView calendarView;
	//CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);

	
	//	public  void nextMonth(){
		
		
		//	long cur = calendarView.getDate();
		//calendarView.setDate(cur + 1000L*60*60*24*40);
		//selectedMonth = selectedMonth +1;
		//System.out.println(selectedMonth);
		//System.out.println(cur);
		
		//  }
	//public  void prevMonth(){
		//long cur = calendarView.getDate();
		//calendarView.setDate(cur + 1000L*60*60*24*40);
		//selectedMonth = selectedMonth -1;
		//System.out.println(selectedMonth);
		//System.out.println(cur);
	   // }
 

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userpage);
        
        sqlUpdate = (ImageButton) findViewById(R.id.bSQLUpdate);
        
        sqlName = (EditText) findViewById(R.id.etSQLName);
        
      
        sqlUpdate.setOnClickListener(this);
        
      
    	String test = User.getName();
    	System.out.println("Dette er en test! Brukernavn:" + test);
        
       
         CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);   
        
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {

            public void onSelectedDayChange(CalendarView view, int year, int month,
                    int dayOfMonth) {
                //int unFuckaMonth = month + 1; // January is month nr 0. has to add one extra
                selectedMonth = month + 1;
                selectedyear = year;
            	selecteddayOfMonth = dayOfMonth;
                //String curDate = String.valueOf(d);             
                //System.out.println(year + " " + unFuckaMonth + " " + dayOfMonth);
                
            }
        });             
}
    
    
    
    public void onClick(View arg0){
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    	String uname = preferences.getString("Username","");

        String id = uname;
       // myStrings = intent.getStringArrayExtra("strings");
       // System.out.println("Brukernavn", myStrings);
        //System.out.println(Arrays.toString(myStrings));
        System.out.println("brukernavn : " + id);
    	
    	switch (arg0.getId()){
    	case R.id.bSQLUpdate:
    		boolean didItWork = true;
    		if(selecteddayOfMonth == 0){
    			Dialog d = new Dialog(this);
    			d.setTitle("Ops! Prøv igjen");
				TextView tv = new TextView(this);
				tv.setText("Velg dato");
				d.setContentView(tv);
				d.show();
    		}
    		else if(sqlName.getText().toString().equals("")){
    			Dialog d = new Dialog(this);
    			d.setTitle("Ops! Prøv igjen");
				TextView tv = new TextView(this);
				tv.setText("Velg et navn på treningsøkta");
				d.setContentView(tv);
				d.show();
    		}
    		else{
    			
    		
    		try{
    		//String username = sqlUsername.getText().toString();
    		String username = User.getName();;
    		String name = sqlName.getText().toString();
    		//int dDate = (selectedyear + selectedMonth + selecteddayOfMonth);
    		String date = (selecteddayOfMonth + "."+ selectedMonth +"."+ selectedyear);
    		System.out.println(date);
    		//System.out.println(dDate);
    		DatabaseHelper entry = new DatabaseHelper(UserPage.this);
    		entry.open();
    		entry.createEntry(username, name,date);
    		entry.close();
    		
    		SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(this);
      	    SharedPreferences.Editor editor = p.edit();
      	    editor.putString("OUser", username);
      	    editor.putString("OName", name);
      	    editor.putString("ODate", date);
      	    editor.commit();
    		
    		}catch (Exception e){
    			didItWork = false;
    			String error = e.toString();
    			Dialog d = new Dialog(this);
				d.setTitle("Her skjedde det noe feil. Fyll ut alle felter!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
    		}finally{
    			if(didItWork){
    				//Dialog d = new Dialog(this);
    				//d.setTitle("Heck Yea!");
    				//TextView tv = new TextView(this);
    				//tv.setText("Success");
    				//d.setContentView(tv);
    				//d.show();
    				// Redirect to new activity and restoring values from SP
    				Intent i = new Intent("android.intent.action.AddOvelse");
            		startActivity(i);
    				
    			}
    			
    		}
    		
    		break;
    		}

    		
    	
    	}
    
    	System.out.println(selectedyear + " " + selectedMonth + " " + selecteddayOfMonth);
   
    }

}