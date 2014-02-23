package no.pumalove.treningslogg;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

public class SQLView extends ListActivity{
	// private ListView lv;
	// Button bLastOpp;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto YO!
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		//Button bLastOpp = (Button) findViewById(R.id.bLastOpp);
		
		/**
		DatabaseHelper info = new DatabaseHelper(this);
		info.open();		
		final ArrayList<String> data = info.getData3();
		
		final ArrayList<String> idArray = info.getIDArray();
		info.close();		
		//tv.setText(data.get(1));
		**/
		
		  // 1. pass context and data to the custom adapter
        MyAdapter adapter = new MyAdapter(this, generateData());
 
        //2. setListAdapter
        setListAdapter(adapter);
				
		
		/**
		for (int i = 0; i < data.size(); i++){
			LinearLayout layout = new LinearLayout(this);
			String item = data.get(i);
			System.out.println("Item " + i + " : " + item);
			TextView tv2=new TextView(getApplicationContext());
		    tv2.setText(data.get(i));
		    layout.addView(tv2);
			}	
	    //lv = (ListView) findViewById(R.id.listView2);
        // This is the array adapter, it takes the context of the activity as a first // parameter, the type of list view as a second parameter and your array as a third parameter
        //final ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data);
        //lv.setAdapter(arrayAdapter); 
        
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
            	//Bundle dataBundle = new Bundle();
            	//dataBundle.putInt("Position", position);
                // selected item 
                String product = ((TextView) view).getText().toString();
                System.out.println("Sqlview "+position);
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), SingleListItem.class);
                // sending data to new activity
                //i.putExtra("product", product);
                //i.putExtra("position", position);

                Bundle bundle = new Bundle();
                bundle.putInt("id", position);    
                i.putExtras(bundle);
                //i.putExtra("position", pos);
               // i.putExtra("data2", data2);
                startActivity(i);
               
            }
        });
        
        lv.setOnItemLongClickListener(new OnItemLongClickListener() {
        	public boolean onItemLongClick(AdapterView<?> parent, View view,
                    final int position, long id) {

        		
        		
        	System.out.println("-----FISHEBOLLHA------ ");
        	System.out.println("position " +position);
        	System.out.println(idArray.get(position)+ " ID til treningsøkta");
        	final String valgtID = idArray.get(position);
        	System.out.println("-----YOLO------");
        		//return false;
        	
        	AlertDialog.Builder builder = new AlertDialog.Builder(SQLView.this);
                builder.setMessage("Sikker på at du vil slette denne treningsøkta?")
                .setCancelable(false)
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                        	   System.out.println("-----Sletter------ ");
                        	   data.remove(position);
                        	   arrayAdapter.notifyDataSetChanged();
                        	   DatabaseHelper info = new DatabaseHelper(SQLView.this);
	                       		info.open();		
	                       		info.deleteEntry(valgtID);
	                       		info.close();		
                        	   
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
               // return true;
            
        	
        	
        	
			return false;
        	
            }

			
            

			
          });
        
	
	**/
		
	}
	  private ArrayList<Item> generateData(){
	        ArrayList<Item> items = new ArrayList<Item>();
	       items.add(new Item("Item 1","First Item on the list"));
	        items.add(new Item("Item 2","Second Item on the list"));
	        items.add(new Item("Item 3","Third Item on the list"));
	 
	        return items;
	    }

	
	
	}

			
		
	

