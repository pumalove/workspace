package com.example.intelligencetest.chemical;

import com.example.intelligencetest.R;
import com.example.intelligencetest.persons.PersonFragmentTest.DatabaseOperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ChemicalActivity extends Activity {
	
	String name;
	String emergencyphone;
	String type;
	ChemicalDatasource chemData;
	
	Chemical chemical;
	ProgressDialog pDialog;
	
	
	//TextViews
	TextView tvName;
	
	public ChemicalActivity() {
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_checmical_layout);
		
		chemData = new ChemicalDatasource();
		tvName = (TextView) findViewById(R.id.chemical_name);
		
		DatabaseOperation dbOper = new DatabaseOperation();
		dbOper.execute();
		
		
		
	}
	
	
	public class DatabaseOperation extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			chemical = chemData.getChemicalById("3");
			return "Executed";
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			tvName.setText(chemical.getName());
			Log.i("ChemicalDatabaseoperation", "Done");
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();			
		    pDialog = new ProgressDialog(ChemicalActivity.this);
            pDialog.setMessage("Oppdaterer personlist...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
		}
		
		
	}
}
