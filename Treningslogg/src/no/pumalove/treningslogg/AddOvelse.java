package no.pumalove.treningslogg;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class AddOvelse extends Activity {
	protected static final int WRAP_CONTENT = 0;
	protected static final float CENTER = 0;
	ImageButton b, d, button1, button2;
	String megaID, settVerdi;
	Dialog dialog;
	int teller =0;
	int sett = 0;
	int r1 = 0;
	int v1 = 0;
	int r2 = 0;
	int v2 = 0;
	int r3 = 0;
	int v3 = 0;
	int r4 = 0;
	int v4 = 0;
	int r5 = 0;
	int v5 = 0;
	ArrayList<String> superIdArray = new ArrayList<String>();
	ArrayList<String> superOvelseArray = new ArrayList<String>();
	ArrayList<Integer> superSettArray = new ArrayList<Integer>();
	final  TextView[] pairs=new TextView[50]; // Dette er litt swag! Maks 50 liss! Øvelser liss!
  	final  TextView[][] pairsLabelSett=new TextView[10][10];
  	final  TextView[][] pairsLabelRep=new TextView[10][10];
  	final  TextView[][] pairsLabelKg=new TextView[10][10];
  	final EditText[][] pairsEt = new EditText[10][10];
  	final EditText[][] pairsKg = new EditText[10][10];
  	final ImageView[] pairsStrek = new ImageView[50];
  	final LinearLayout[][] settLayout = new LinearLayout[10][10];
	//ArrayList<String> superIdArray;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.addovelse);
	         
	        
	        b = (ImageButton)findViewById(R.id.bBib);  //finding the button from the layout xml
	        d = (ImageButton)findViewById(R.id.imageButton2);  //finding the button from the layout xml
	   
	        
	        

	       
	        
	        

	        
	        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	        String date = preferences.getString("ODate","");
	        String username = preferences.getString("OUser","");
	        String name = preferences.getString("OName","");
	        
	        
	        DatabaseHelper info = new DatabaseHelper(this);
			info.open();		
			//ArrayList<String> data2 = info.getData3();
			final String radID = info.getID(date, username, name);
			megaID = radID;
			ArrayList<String> res = info.getOData(radID);
			ArrayList<String> navn = info.getODataNavn(radID);
			info.close();
	        
	        

	        
	        
	       
	        
	        
	        
	        
	       
	        b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					//noe
					
				
					
					showCustomDialog();
					
					
					
					
					
					
					/**DatabaseHelper i = new DatabaseHelper(AddOvelse.this);
							
					//ArrayList<String> data2 = info.getData3();
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AddOvelse.this);
			        String date = preferences.getString("ODate","");
			        String username = preferences.getString("OUser","");
			        String name = preferences.getString("OName","");
			        i.open();
					String radID = i.getID(date, username, name);
					i.createEntry_Ovelse(radID, "Benkpress", 5, 12, 13, 10, 5, 4, 23, 24, 55, 22, 88);
					i.createEntry_Ovelse(radID, "Kneboy", 23, 44, 32, 10, 5, 4, 23, 24, 55, 22, 88);
					i.close();
					**/
					
				}
	        	
	        });
	        
	        
	        
	 
	        d.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					//noe
					
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AddOvelse.this);
			        int ressize = preferences.getInt("ressize",0);
					int tellerO = 1;
					DatabaseHelper f = new DatabaseHelper(AddOvelse.this);
					f.open();
					ArrayList<String> yoloswagIDArray = f.getODataID(radID);
					f.close();
					
					
						//System.out.println("superidarray størrelse: "+superIdArray.size());
					for(int i = 0; i<ressize; i++){
						int swagfish = superSettArray.get(i);
						String swagID = superIdArray.get(i);
						int tellerSett = 1;
						System.out.println("Øvelse "+ pairs[tellerO].getText().toString());
						String ovelse = pairs[tellerO].getText().toString();
						System.out.println("Antall " + swagfish);
						int antall = swagfish;
						System.out.println("ID " + swagID);
						String id = swagID;
						String yoloid = yoloswagIDArray.get(i);
						
						/**
						
						for(int i2 = 0; i2<swagfish; i2++){
						
							
							
							System.out.println("pairsET verdi: "+ pairsEt[tellerO][tellerSett].getText().toString());
							System.out.println("pairsKg verdi: "+ pairsKg[tellerO][tellerSett].getText().toString());
							System.out.println("ressize verdi: "+ ressize);
							//System.out.println("SuperDuperSett verdi: "+ superSettArray.get(i));
							System.out.println("teller o "+ tellerO +" tellerSett " +tellerSett);
							
							tellerSett++;
						}
						**/
						
						if(swagfish==1){
							System.out.println(pairsEt[tellerO][1].getText().toString());
							System.out.println(pairsKg[tellerO][1].getText().toString());
							// Rep og kg 1
							if(pairsEt[tellerO][1].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[tellerO][1].getText().toString());
							}
							if(pairsKg[tellerO][1].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[tellerO][1].getText().toString());
							}
							if(pairsEt[tellerO][1].getText().toString().trim().length()==0){
								r1 = 0;
							}
							if(pairsKg[tellerO][1].getText().toString().trim().length()== 0){
								v1 = 0;
							}
							
							
									
						}
						
						if(swagfish==2){
							System.out.println(pairsEt[tellerO][1].getText().toString());
							System.out.println(pairsKg[tellerO][1].getText().toString());
							System.out.println(pairsEt[tellerO][2].getText().toString());
							System.out.println(pairsKg[tellerO][2].getText().toString());
							// Rep og kg 1
							if(pairsEt[tellerO][1].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[tellerO][1].getText().toString());
								}
							if(pairsKg[tellerO][1].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[tellerO][1].getText().toString());
								}
							 if(pairsEt[tellerO][1].getText().toString().trim().length()== 0){
									r1 = 0;
								}
							 if(pairsKg[tellerO][1].getText().toString().trim().length()== 0){
									v1 = 0;
								}
							// Rep og kg 2
							if(pairsEt[tellerO][2].getText().toString().trim().length()> 0){
								r2 = Integer.valueOf(pairsEt[tellerO][2].getText().toString());
								}
							if(pairsKg[tellerO][2].getText().toString().trim().length()> 0){
								v2 = Integer.valueOf(pairsKg[tellerO][2].getText().toString());
								}
							 if(pairsEt[tellerO][2].getText().toString().trim().length()== 0){
									r2 = 0;
								}
							 if(pairsKg[tellerO][2].getText().toString().trim().length()== 0){
									v2 = 0;
								}
						}
						if(swagfish==3){
							System.out.println(pairsEt[tellerO][1].getText().toString());
							System.out.println(pairsKg[tellerO][1].getText().toString());
							System.out.println(pairsEt[tellerO][2].getText().toString());
							System.out.println(pairsKg[tellerO][2].getText().toString());
							System.out.println(pairsEt[tellerO][3].getText().toString());
							System.out.println(pairsKg[tellerO][3].getText().toString());
							// Rep og kg 1
							if(pairsEt[tellerO][1].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[tellerO][1].getText().toString());
								}
							if(pairsKg[tellerO][1].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[tellerO][1].getText().toString());
								}
							if(pairsEt[tellerO][1].getText().toString().trim().length()== 0){
									r1 = 0;
								}
							if(pairsKg[tellerO][1].getText().toString().trim().length()== 0){
									v1 = 0;
								}
							// Rep og kg 2
							if(pairsEt[tellerO][2].getText().toString().trim().length()> 0){
								r2 = Integer.valueOf(pairsEt[tellerO][2].getText().toString());
								}
							if(pairsKg[tellerO][2].getText().toString().trim().length()> 0){
								v2 = Integer.valueOf(pairsKg[tellerO][2].getText().toString());
								}
							if(pairsEt[tellerO][2].getText().toString().trim().length()== 0){
									r2 = 0;
								}
							if(pairsKg[tellerO][2].getText().toString().trim().length()== 0){
									v2 = 0;
								}
							// Rep og kg 3
							if(pairsEt[tellerO][3].getText().toString().trim().length()> 0){
								r3 = Integer.valueOf(pairsEt[tellerO][3].getText().toString());
								}
							if(pairsKg[tellerO][3].getText().toString().trim().length()> 0){
								v3 = Integer.valueOf(pairsKg[tellerO][3].getText().toString());
								}
							 if(pairsEt[tellerO][3].getText().toString().trim().length()== 0){
									r3 = 0;
								}
							 if(pairsKg[tellerO][3].getText().toString().trim().length()== 0){
									v3 = 0;
								}
						}
						if(swagfish==4){
							System.out.println(pairsEt[tellerO][1].getText().toString());
							System.out.println(pairsKg[tellerO][1].getText().toString());
							System.out.println(pairsEt[tellerO][2].getText().toString());
							System.out.println(pairsKg[tellerO][2].getText().toString());
							System.out.println(pairsEt[tellerO][3].getText().toString());
							System.out.println(pairsKg[tellerO][3].getText().toString());
							System.out.println(pairsEt[tellerO][4].getText().toString());
							System.out.println(pairsKg[tellerO][4].getText().toString());
							// Rep og kg 1
							if(pairsEt[tellerO][1].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[tellerO][1].getText().toString());
								}
							 if(pairsKg[tellerO][1].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[tellerO][1].getText().toString());
								}
							 if(pairsEt[tellerO][1].getText().toString().trim().length()== 0){
									r1 = 0;
								}
							 if(pairsKg[tellerO][1].getText().toString().trim().length()== 0){
									v1 = 0;
								}
							// Rep og kg 2
							 if(pairsEt[tellerO][2].getText().toString().trim().length()> 0){
								r2 = Integer.valueOf(pairsEt[tellerO][2].getText().toString());
								}
							 if(pairsKg[tellerO][2].getText().toString().trim().length()> 0){
								v2 = Integer.valueOf(pairsKg[tellerO][2].getText().toString());
								}
							 if(pairsEt[tellerO][2].getText().toString().trim().length()== 0){
									r2 = 0;
								}
							 if(pairsKg[tellerO][2].getText().toString().trim().length()== 0){
									v2 = 0;
								}
							// Rep og kg 3
							 if(pairsEt[tellerO][3].getText().toString().trim().length()> 0){
								r3 = Integer.valueOf(pairsEt[tellerO][3].getText().toString());
								}
							 if(pairsKg[tellerO][3].getText().toString().trim().length()> 0){
								v3 = Integer.valueOf(pairsKg[tellerO][3].getText().toString());
								}
							 if(pairsEt[tellerO][3].getText().toString().trim().length()== 0){
									r3 = 0;
								}
							 if(pairsKg[tellerO][3].getText().toString().trim().length()== 0){
									v3 = 0;
								}
							// Rep og kg 4
							 if(pairsEt[tellerO][4].getText().toString().trim().length()> 0){
								r4 = Integer.valueOf(pairsEt[tellerO][4].getText().toString());
								}
							 if(pairsKg[tellerO][4].getText().toString().trim().length()> 0){
								v4 = Integer.valueOf(pairsKg[tellerO][4].getText().toString());
								}
							 if(pairsEt[tellerO][4].getText().toString().trim().length()== 0){
									r4 = 0;
								}
							 if(pairsKg[tellerO][4].getText().toString().trim().length()== 0){
									v4 = 0;
								}
						}
						if(swagfish==5){
							System.out.println(pairsEt[tellerO][1].getText().toString());
							System.out.println(pairsKg[tellerO][1].getText().toString());
							System.out.println(pairsEt[tellerO][2].getText().toString());
							System.out.println(pairsKg[tellerO][2].getText().toString());
							System.out.println(pairsEt[tellerO][3].getText().toString());
							System.out.println(pairsKg[tellerO][3].getText().toString());
							System.out.println(pairsEt[tellerO][4].getText().toString());
							System.out.println(pairsKg[tellerO][4].getText().toString());
							System.out.println(pairsEt[tellerO][5].getText().toString());
							System.out.println(pairsKg[tellerO][5].getText().toString());
							// Rep og kg 1
							if(pairsEt[tellerO][1].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[tellerO][1].getText().toString());
								}
							 if(pairsKg[tellerO][1].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[tellerO][1].getText().toString());
								}
							 if(pairsEt[tellerO][1].getText().toString().trim().length()== 0){
									r1 = 0;
								}
							 if(pairsKg[tellerO][1].getText().toString().trim().length()== 0){
									v1 = 0;
								}
							// Rep og kg 2
							 if(pairsEt[tellerO][2].getText().toString().trim().length()> 0){
								r2 = Integer.valueOf(pairsEt[tellerO][2].getText().toString());
								}
							 if(pairsKg[tellerO][2].getText().toString().trim().length()> 0){
								v2 = Integer.valueOf(pairsKg[tellerO][2].getText().toString());
								}
							 if(pairsEt[tellerO][2].getText().toString().trim().length()== 0){
									r2 = 0;
								}
							 if(pairsKg[tellerO][2].getText().toString().trim().length()== 0){
									v2 = 0;
								}
							// Rep og kg 3
							 if(pairsEt[tellerO][3].getText().toString().trim().length()> 0){
								r3 = Integer.valueOf(pairsEt[tellerO][3].getText().toString());
								}
							 if(pairsKg[tellerO][3].getText().toString().trim().length()> 0){
								v3 = Integer.valueOf(pairsKg[tellerO][3].getText().toString());
								}
							 if(pairsEt[tellerO][3].getText().toString().trim().length()== 0){
									r3 = 0;
								}
							 if(pairsKg[tellerO][3].getText().toString().trim().length()== 0){
									v3 = 0;
								}
							// Rep og kg 4
							 if(pairsEt[tellerO][4].getText().toString().trim().length()> 0){
								r4 = Integer.valueOf(pairsEt[tellerO][4].getText().toString());
								}
							 if(pairsKg[tellerO][4].getText().toString().trim().length()> 0){
								v4 = Integer.valueOf(pairsKg[tellerO][4].getText().toString());
								}
							 if(pairsEt[tellerO][4].getText().toString().trim().length()== 0){
									r4 = 0;
								}
							 if(pairsKg[tellerO][4].getText().toString().trim().length()== 0){
									v4 = 0;
								}
							// Rep og kg 5
							 if(pairsEt[tellerO][5].getText().toString().trim().length()> 0){
								r5 = Integer.valueOf(pairsEt[tellerO][5].getText().toString());
								}
							 if(pairsKg[tellerO][5].getText().toString().trim().length()> 0){
								v5 = Integer.valueOf(pairsKg[tellerO][5].getText().toString());
								}
							 if(pairsEt[tellerO][5].getText().toString().trim().length()== 0){
									r5 = 0;
								}
							 if(pairsKg[tellerO][5].getText().toString().trim().length()== 0){
									v5 = 0;
								}
						}
						 if(swagfish==0 || swagfish>5){
							System.out.println("du e teit, antall for høyt eller lavt, yolo?");
							r1 = 0;
							v1 = 0;
							r2 = 0;
							v2 = 0;
							r3 = 0;
							v3 = 0;
							r4 = 0;
							v4 = 0;
							r5 = 0;
							v5 = 0;
						}
						
						 System.out.println("Starter å legge inn øvelser");
							DatabaseHelper ff = new DatabaseHelper(AddOvelse.this);
							ff.open();
							ff.updateEntry_Ovelse(yoloid, id, ovelse, antall, r1, v1, r2, v2, r3, v3, r4, v4, r5, v5);
							ff.close();
							System.out.println("Ferdig med å legge inn øvelser");
						
						tellerO++;
						
						r1 = 0;
						v1 = 0;
						r2 = 0;
						v2 = 0;
						r3 = 0;
						v3 = 0;
						r4 = 0;
						v4 = 0;
						r5 = 0;
						v5 = 0;
					}
			        
			        
			        	
						
					
					
					
					
					/**		
					//ArrayList<String> data2 = info.getData3();
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AddOvelse.this);
			        String date = preferences.getString("ODate","");
			        String username = preferences.getString("OUser","");
			        String name = preferences.getString("OName","");
					
					DatabaseHelper f = new DatabaseHelper(AddOvelse.this);
					f.open();
					String radID = f.getID(date, username, name);
					String noen = f.getOData(radID);
					f.close();
					System.out.println(megaID+" megaID");
					System.out.println(radID+" radID");
					System.out.println(noen + "noen");
					TextView txtDate = (TextView) findViewById(R.id.twRes);
					txtDate.setText("Treningsøkt den "+noen);
					**/
					
				}
	        	
	        });
	    
	      
	         
	    }
	 protected void showCustomDialog() {
			// TODO Auto-generated method stub
		 	final Dialog dialog = new Dialog(AddOvelse.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.customdailog_addovelse);
			
			final EditText editText = (EditText)dialog.findViewById(R.id.editText2);
			
			ImageButton button = (ImageButton)dialog.findViewById(R.id.bLastOpp);
			
			
			final LinearLayout myLayout = (LinearLayout) findViewById(R.id.my_layout);
			
			
			final LayoutParams lp = new LayoutParams( LayoutParams.WRAP_CONTENT,    LayoutParams.WRAP_CONTENT);
			final LayoutParams mp = new LayoutParams( LayoutParams.MATCH_PARENT,    LayoutParams.WRAP_CONTENT);
			
			/**
			final RelativeLayout.LayoutParams pairsl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			final RelativeLayout.LayoutParams pairsLabelRepl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			final RelativeLayout.LayoutParams pairsLabelKgl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			final RelativeLayout.LayoutParams pairsEtl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			final RelativeLayout.LayoutParams pairsKgl = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			**/
			//--------------------------------------------------------------------------------------------------------------------
	      
	      	//--------------------------------------------------------------------------------------------------------------------
	      	
	      	
	      	/**
	      	for(int l=0; l<0; l++)
	        {
	            pairs[l] = new TextView(AddOvelse.this);
	            pairs[l].setTextSize(15);
	            pairs[l].setLayoutParams(lp);
	            pairs[l].setId(l);
	            pairs[l].setText((l) + "hei");
	            pairs[l].setGravity(Gravity.CENTER);
	            
	            pairsEt[l] = new EditText(AddOvelse.this);
	            pairsEt[l].setLayoutParams(lp);
	            pairsEt[l].setId(l);
	            pairsEt[l].setGravity(Gravity.RIGHT);
	            
	            
	            
	            
	            
	            
	            myLayout.addView(pairs[l]);
	            myLayout.addView(pairsEt[l]);
	        }
			**/
			
			button.setOnClickListener(new View.OnClickListener() {
				 
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

						String myEditValue = editText.getText().toString();
						if(myEditValue.equals("")){
							myEditValue="0";
						}
						
					sett = Integer.parseInt(myEditValue);
					if(sett==0 || sett>5){
						runOnUiThread(new Runnable() {
				            public void run() {
				                AlertDialog.Builder builder = new AlertDialog.Builder(AddOvelse.this);
				                builder.setTitle("Feil input.");
				                builder.setMessage("Fyll ut en verdi som er mellom 1 og 5.")  
				                       .setCancelable(false)
				                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
				                           public void onClick(DialogInterface dialog, int id) {
				                           }
				                       });                     
				                AlertDialog alert = builder.create();
				                alert.show();               
				            }
				        });
					}
					else{
						
					
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AddOvelse.this);
			        String date = preferences.getString("ODate","");
			        String username = preferences.getString("OUser","");
			        String name = preferences.getString("OName","");
			        String valgtVerdi = preferences.getString("valgtVerdi","");
			        //int i = preferences.getInt("antallSett",0);
					
					DatabaseHelper g = new DatabaseHelper(AddOvelse.this);
					g.open();		
					String radID = g.getID(date, username, name);
					g.createEntry_Ovelse(radID, valgtVerdi, sett, r1, v1, r2, v2, r3, v3, r4, v4, r5, v5);
					//String res = g.getOData(radID);
					ArrayList<String> res = g.getOData(radID);
					ArrayList<String> navn = g.getODataNavn(radID);
					//info.createEntry_bib(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
					superSettArray.add(sett);
					superIdArray.add(radID);
					superOvelseArray.add(valgtVerdi);
					g.close();
					System.out.println("Swagfish swag like" +sett);
					System.out.println("yolo swag larsen " +res);
					System.out.println("Øvelse: " +navn.get(0));
					System.out.println("lengde "+res.size());
					
					// TextView
					myLayout.setPadding(0, 25, 0, 0);
					pairs[res.size()] = new TextView(AddOvelse.this);
		            pairs[res.size()].setTextSize(20);
		            pairs[res.size()].setLayoutParams(lp);
		            pairs[res.size()].setId(res.size());
		            pairs[res.size()].setText(navn.get(teller));
		            pairs[res.size()].setGravity(Gravity.CENTER);
		            pairs[res.size()].setPadding(0, 0, 0, 0);
		            pairs[res.size()].setHeight(50);
		            
		            
					pairsStrek[res.size()] = new ImageView(AddOvelse.this);
					pairsStrek[res.size()].setImageResource(R.drawable.line);
					pairsStrek[res.size()].setLayoutParams(lp);
		            pairsStrek[res.size()].setId(res.size());
		           
		          
		            
		           
		            
		            teller++;
		            myLayout.addView(pairs[res.size()]);
		            myLayout.addView(pairsStrek[res.size()]);
		            for(int i=1;i<=sett; i++){
		            settLayout[res.size()][i] = new LinearLayout(AddOvelse.this);
		            settLayout[res.size()][i].setOrientation(LinearLayout.HORIZONTAL);
		            settLayout[res.size()][i].setGravity(Gravity.CENTER);
		            if(i == 1){
		            	settLayout[res.size()][i].setPadding(0, 25, 0, 0);
		            }
		            if(i == sett){
		            }
		            else{
		            	settLayout[res.size()][i].setPadding(0, 10, 0, 0);
		            }
		            
		            // TextView Sett
		            pairsLabelSett[res.size()][i] = new TextView(AddOvelse.this);
		            pairsLabelSett[res.size()][i].setTextSize(15);
		            pairsLabelSett[res.size()][i].setLayoutParams(lp);
		            pairsLabelSett[res.size()][i].setId(i);
		            pairsLabelSett[res.size()][i].setText("Sett " + i);
		            pairsLabelSett[res.size()][i].setPadding(50, 0, 50, 0);
		            
		            // TextView Rep
		            pairsLabelRep[res.size()][i] = new TextView(AddOvelse.this);
		            pairsLabelRep[res.size()][i].setTextSize(15);
		            pairsLabelRep[res.size()][i].setLayoutParams(lp);
		            pairsLabelRep[res.size()][i].setId(res.size());
		            pairsLabelRep[res.size()][i].setText("Rep");
		            pairsLabelRep[res.size()][i].setPadding(50, 0, 50, 0);
		            
		            // TextView Kg
		            pairsLabelKg[res.size()][i] = new TextView(AddOvelse.this);
		            pairsLabelKg[res.size()][i].setTextSize(15);
		            pairsLabelKg[res.size()][i].setLayoutParams(lp);
		            pairsLabelKg[res.size()][i].setId(res.size());
		            pairsLabelKg[res.size()][i].setText("Kg");
		            pairsLabelKg[res.size()][i].setPadding(50, 0, 50, 0);
		            
		            // EditText
		            pairsEt[res.size()][i] = new EditText(AddOvelse.this);
		            pairsEt[res.size()][i].setLayoutParams(lp);
		            pairsEt[res.size()][i].setId(i);
		            pairsEt[res.size()][i].setInputType(InputType.TYPE_CLASS_NUMBER);
		            //pairsEt[res.size()][i].setPadding(50, 0, 50, 0);
		            //pairsEt[res.size()][i].setHeight(40);
		            pairsEt[res.size()][i].setWidth(175);
		            
		            pairsKg[res.size()][i] = new EditText(AddOvelse.this);
		            pairsKg[res.size()][i].setLayoutParams(lp);
		            pairsKg[res.size()][i].setId(i);
		            pairsKg[res.size()][i].setInputType(InputType.TYPE_CLASS_NUMBER);
		            //pairsKg[res.size()][i].setPadding(50, 0, 50, 0);
		            //pairsKg[res.size()][i].setHeight(40);
		            pairsKg[res.size()][i].setWidth(175);
		            
		            settLayout[res.size()][i].addView(pairsLabelSett[res.size()][i]);
		            settLayout[res.size()][i].addView(pairsLabelRep[res.size()][i]);
		            settLayout[res.size()][i].addView(pairsEt[res.size()][i]);
		            settLayout[res.size()][i].addView(pairsLabelKg[res.size()][i]);
		            settLayout[res.size()][i].addView(pairsKg[res.size()][i]);
		            System.out.println("settidyoloswagdobbellokkeduck  "+res.size() +"  "+ i);
					
		            myLayout.addView(settLayout[res.size()][i]);
		            
		            SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(AddOvelse.this);
	            	  SharedPreferences.Editor editor = p.edit();
	            	  editor.putInt("ressize", res.size());
	            	//  editor.putInt("antallSett", i);
	            	  editor.commit();
		            }
		            /**
		            //---------------------Layout--------------------------------------------
		            pairsl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		            pairsl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		            pairsl.setMargins(0, 15, 0, 0);
		            
		            
		            pairsLabelKgl.width = 50;
		            pairsLabelKgl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		            pairsLabelKgl.addRule(RelativeLayout.BELOW, pairs[res.size()].getId());
		            pairsLabelKgl.setMargins(0, 15, 30, 0);
		            
		            
		            pairsLabelRepl.addRule(RelativeLayout.LEFT_OF, pairsLabelKg[res.size()].getId());
		            pairsLabelRepl.addRule(RelativeLayout.BELOW, pairs[res.size()].getId());
		            pairsLabelRepl.setMargins(0, 15, 100, 0);
		            pairsLabelRepl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		            
		            pairsKgl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		            pairsKgl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		            pairsKgl.addRule(RelativeLayout.BELOW, pairsLabelKg[res.size()].getId());
		            pairsKgl.addRule(RelativeLayout.ALIGN_LEFT, pairsLabelKg[res.size()].getId());
		            pairsKgl.addRule(RelativeLayout.ALIGN_RIGHT, pairsLabelKg[res.size()].getId());
		            pairsKgl.setMargins(0, 15, 30, 0);
					
					//---------------------Layout-SLUTT--------------------------------------
		            **/
		            
		            
		            
					
					//addListenerOnSpinnerItemSelection();
					dialog.dismiss();
				}
				}
			});
			
			
			ImageButton button2 = (ImageButton)dialog.findViewById(R.id.button2);
			
			
			button2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			
			
			final Spinner spinner2 = (Spinner) dialog.findViewById(R.id.spinner1);
			
			DatabaseHelper g = new DatabaseHelper(AddOvelse.this);
			g.open();		
			ArrayList<String> data = g.getBibOvelser();
			//info.createEntry_bib(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
			g.close();
					
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddOvelse.this,android.R.layout.simple_spinner_item, data);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spinner2.setAdapter(dataAdapter);
			
			
			spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					String valgtVerdi = String.valueOf(spinner2.getSelectedItem());
					//EditText antallSett = (EditText)findViewById(R.id.editText2);
					// int i = Integer.parseInt(antallSett.getText().toString());
					System.out.println(valgtVerdi);
					 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AddOvelse.this);
	            	  SharedPreferences.Editor editor = preferences.edit();
	            	  editor.putString("valgtVerdi", valgtVerdi);
	            	//  editor.putInt("antallSett", i);
	            	  editor.commit();
					
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
					
			dialog.show();
		}
	 

			
		  
	 
	 
	

}
