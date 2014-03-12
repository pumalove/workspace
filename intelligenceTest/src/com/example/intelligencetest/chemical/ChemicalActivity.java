package com.example.intelligencetest.chemical;

import com.example.intelligencetest.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

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
		
		
		TextView tvName = (TextView) findViewById(R.id.chemical_name);
		tvName.setText(chemical.getName());
		
		
	}
}
