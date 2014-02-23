package com.example.intelligencetest;

import java.util.List;
import com.applidium.headerlistview.HeaderListView;
import com.applidium.headerlistview.SectionAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class PersonFragmentTest extends Fragment {
	 private ProgressDialog pDialog;
	String[] values = new String[] { "Android List View", 
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android", 
            "Android Example", 
            "List View Source Code", 
            "List View Array Adapter", 
            "Android Example List View" 
           };
	
	
	List<Person> personList;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LongOperation MyTask= new LongOperation();
        MyTask.execute();
       HeaderListView list = new HeaderListView(getActivity());
       list.getListView();
       
       list.setAdapter(new MyAdapter());
       
       //Yoloswag
       
       return list;
	}
	
	
	public class MyAdapter extends SectionAdapter {

        public int numberOfSections() {
            return 4;
        }

        @Override
        public int numberOfRows(int section) {
            return 35;
        }

        @Override
        public Object getRowItem(int section, int row) {
            return null;
        }	

        @Override
        public boolean hasSectionHeaderView(int section) {
            return true;
        }

        @Override
        public View getRowView(int section, int row, View convertView, ViewGroup parent) {
    
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.simple_list_item_1, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.personName);
            tv.setText("section: " + section + " row: " + row);
            return convertView;
        }

        @Override
        public int getSectionHeaderViewTypeCount() {
            return 2;
        }

        @Override
        public int getSectionHeaderItemViewType(int section) {
            return section % 2;
        }

        @Override
        public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.person_list_section_divider, null);
            }

            TextView text = (TextView) convertView.findViewById(R.id.dividerText);
            text.setText("Header for section: " + section);
            
            return convertView;
        }
	}
	public class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                   
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText("Executed"); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        	 pDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
        	super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Oppdaterer personlist...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
	//End async
	
	

}
