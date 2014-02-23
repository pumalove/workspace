package com.example.scanner;

import com.example.intelligencetest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;



public class GridMenu extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.gridview, container, false);
		  GridView gridview = (GridView) view.findViewById(R.id.gridview);
		    gridview.setAdapter(new ImageAdapter(getActivity()));

		    gridview.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		            Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
		        }
		
		
	});
			return gridview;
	
	
}
}
