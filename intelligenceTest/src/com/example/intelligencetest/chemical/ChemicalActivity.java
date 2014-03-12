package com.example.intelligencetest.chemical;

import com.example.intelligencetest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChemicalActivity extends Activity {
	
	String name;
	String emergencyphone;
	String type;
	ChemicalDatasource chemData;
	
	public ChemicalActivity() {
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_checmical_layout);
		
		chemData = new ChemicalDatasource();
		Chemical chemical = chemData.getChemical();
		
		Intent intent = getIntent();
		String id = intent.getStringExtra("id"); //unused
		
		TextView tvName = (TextView) findViewById(R.id.chemical_name);
		tvName.setText(chemical.getName());
		
		
	}
}
