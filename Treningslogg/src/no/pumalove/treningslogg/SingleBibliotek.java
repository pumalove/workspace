package no.pumalove.treningslogg;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleBibliotek extends Activity{
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.single_list_item_view);
	        int pos=0;
	        Intent i = getIntent();
	        Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	            pos = extras.getInt("id");
	            
	        }
	        //String koko = String.valueOf(pos);
	        
	        DatabaseHelper info = new DatabaseHelper(this);
			info.open();		
			//ArrayList<String> data2 = info.getData3();  
			ArrayList<String> IDArrayBib = info.getIDArrayBib();
			String popo = IDArrayBib.get(pos);
			ArrayList<String> res = info.checkBibDataID(popo);
			//ArrayList<String> res = info.getOData(koko);
			info.close();
	        
	        
	        TextView txtProduct = (TextView) findViewById(R.id.textViewList);
	        TextView txtRes = (TextView) findViewById(R.id.twRes);
	        
	     
	      
		// Bundle extras = getIntent().getExtras();
	        // getting attached intent data
	       // String product = i.getStringExtra("product");
	       //int pos = Intent.getIntent("position");
	       //int pos = i.getIntExtra("Position", position);
	       System.out.println("Singelist "+pos);
	        // displaying selected product name
	        //txtProduct.setText(data2.get(pos));
	        for(int f = 0; f<res.size(); f++){
	        	txtRes.setText(res.get(f)+ "\n");
	        }
	        
	       //System.out.println(Arrays.toString(data2));
	      // System.out.println(data2.get(pos));
	       System.out.println(IDArrayBib.get(pos)+ " ID til bibøvelse");
	         
	    }
}
