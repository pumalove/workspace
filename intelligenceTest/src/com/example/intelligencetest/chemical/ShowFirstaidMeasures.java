package com.example.intelligencetest.chemical;

import com.example.intelligencetest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowFirstaidMeasures extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_firstaid_data);
		
		Intent intent = getIntent();
		
		TextView tvOnSkinContact = (TextView) findViewById(R.id.firstaid_onSkinContact);
		tvOnSkinContact.setText(intent.getStringExtra("onSkinContact"));
	}
}
