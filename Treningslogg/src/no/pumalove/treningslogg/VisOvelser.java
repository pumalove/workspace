package no.pumalove.treningslogg;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;


public class VisOvelser extends Activity {
    
	private AdapterView<ListAdapter> lv;
	private ArrayAdapter<Item> adapter;
	ImageButton bLastOpp;
	

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.vis_ovelser);		
		DatabaseHelper info = new DatabaseHelper(this);
		info.open();		
		final ArrayList<String> data = info.getData3();		
		final ArrayList<String> idArray = info.getIDArray();
		info.close();	
		lv = (ListView) findViewById(R.id.listview);
		refresh();
		ImageButton bLastOpp = (ImageButton) findViewById(R.id.imageButton2);
		
		  bLastOpp.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					System.out.print("swag2");
					Intent i = new Intent("android.intent.action.lastOpp");
		    		startActivity(i);
				}
				
			});
		
		lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
                System.out.println("Sqlview "+position);
                Intent i = new Intent(getApplicationContext(), SingleListItem.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", position);    
                i.putExtras(bundle);
                startActivity(i);
               
            }
        });
        
        lv.setOnItemLongClickListener(new OnItemLongClickListener() {
        	public boolean onItemLongClick(AdapterView<?> parent, View view,
                    final int position, long id) {

        	final String valgtID = idArray.get(position);

        	AlertDialog.Builder builder = new AlertDialog.Builder(VisOvelser.this);
                builder.setMessage("Sikker på at du vil slette denne treningsøkta?")
                .setCancelable(false)
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                        	   System.out.println("-----Sletter------ ");
                        	   data.remove(position);
                        	    
                        	   DatabaseHelper info = new DatabaseHelper(VisOvelser.this);
	                       		info.open();		
	                       		info.deleteEntry(valgtID);
	                       		info.close();	
	                       		adapter.notifyDataSetChanged();
	                        	refresh();	
                        	   
                           }
                       })
                       .setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                        	   System.out.println("-----Sletter ikke------ "); 
                                dialog.cancel();
                           }
                       });
                AlertDialog alert = builder.create();
                alert.show();

			return false;
        	
            }
        });
	}
	
	
	private void refresh(){

		generateData(); 
				adapter = new MyAdapter(this, generateData());		
				lv = (ListView) findViewById(R.id.listview);
				lv.setAdapter(adapter); 
	}
	
	private ArrayList<Item> generateData(){ 
		ArrayList<Item> items = new ArrayList<Item>();	
		DatabaseHelper info = new DatabaseHelper(this);
		info.open();		
		final ArrayList<String> data = info.getTName();
		final ArrayList<String> date = info.getDate();
		info.close();		
		for(int i = 0; i<data.size(); i++){
			items.add(new Item("#"+(i+1) +" "+ data.get(i),date.get(i))); 
		}
	
	    
	    return items;
	}
	


} 
