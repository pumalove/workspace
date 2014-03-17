package com.example.intelligencetest.chemical;

import com.example.intelligencetest.R;
import com.example.intelligencetest.persons.PersonFragmentTest.DatabaseOperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChemicalActivity extends Activity {
	
	String id;
	String name;
	String emergencyphone;
	String type;
	ChemicalDatasource chemData;
	
	Chemical chemical;
	ChemicalDatasheet datasheet;
	
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
		
		
		Intent intent = getIntent();
		id = intent.getStringExtra("id");
		chemData = new ChemicalDatasource();
		tvName = (TextView) findViewById(R.id.chemical_name);
		
		DatabaseOperation dbOper = new DatabaseOperation();
		dbOper.execute();

		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.firstaid_measures_click);
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent showFirstaidMeasures = new Intent(ChemicalActivity.this, ShowFirstaidMeasures.class);
                showFirstaidMeasures.putExtra("onSkinContact", datasheet.getFirstAidOnSkinContact());
                startActivity(showFirstaidMeasures);
			}
		});
		
	}
	
	
	public class DatabaseOperation extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			chemical = chemData.getChemicalById(id);
			return "Executed";
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			tvName.setText(chemical.getName());
			datasheet = chemData.getCurrentDatasheet();
			Log.i("ChemicalDatabaseoperation", "Done");
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();			
		    pDialog = new ProgressDialog(ChemicalActivity.this);
            pDialog.setMessage("Oppdaterer..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
		}
		
		
	}
}
