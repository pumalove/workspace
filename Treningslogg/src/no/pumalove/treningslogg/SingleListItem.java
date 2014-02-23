package no.pumalove.treningslogg;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleListItem extends Activity{
	
	final  TextView[] pairs=new TextView[50]; // Dette er litt swag! Maks 50 liss! Øvelser liss!
	final  TextView[][] pairsLabelSett=new TextView[10][10];
  	final  TextView[][] pairsLabelRep=new TextView[10][10];
  	final  TextView[][] pairsLabelKg=new TextView[10][10];
  	final EditText[][] pairsEt = new EditText[10][10];
  	final EditText[][] pairsKg = new EditText[10][10];
  	final LinearLayout[][] settLayout = new LinearLayout[10][10];
  	ImageButton d;
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
	private int antallSett;

	
	 public void onCreate(Bundle savedInstanceState) {   
	        super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.single_list_item_view);
	    	final LinearLayout myLayout = (LinearLayout) findViewById(R.id.my_layout2);
	    	final LayoutParams lp = new LayoutParams( LayoutParams.WRAP_CONTENT,    LayoutParams.WRAP_CONTENT);
	    	final LayoutParams mp = new LayoutParams( LayoutParams.MATCH_PARENT,    LayoutParams.WRAP_CONTENT);
	        int pos=0;
	        Intent i = getIntent();
	        Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	            pos = extras.getInt("id");
	            
	        }
	        //String koko = String.valueOf(pos);
	        
	        DatabaseHelper info = new DatabaseHelper(this);
			info.open();		
			ArrayList<String> data2 = info.getDataName();
			ArrayList<String> date = info.getDate();
			ArrayList<String> idArray = info.getIDArray();			
			final String popo = idArray.get(pos);
			ArrayList<String> yoloID = info.getOYoloID(popo);
			final ArrayList<String> res = info.getOData(popo);
			ArrayList<String> navn = info.getODataNavn(popo);
			final ArrayList<String> sett = info.getSett(popo);
			
			
			//ArrayList<String> res = info.getOData(koko);
			info.close();
			
		    
			
	        
	        TextView txtProduct = (TextView) findViewById(R.id.textViewList);
	        TextView txtRes = (TextView) findViewById(R.id.twRes);
	        
	        Typeface tf = Typeface.createFromAsset(getAssets(),
		            "fonts/KozGoPro-ExtraLight.otf");
	        txtProduct.setTypeface(tf);
	        Typeface tf2 = Typeface.createFromAsset(getAssets(),
		            "fonts/KozGoPro-Medium.otf");
	        txtRes.setTypeface(tf2);
	        d = (ImageButton)findViewById(R.id.bLagre);  //finding the button from the layout xml

		// Bundle extras = getIntent().getExtras();
	        // getting attached intent data
	       // String product = i.getStringExtra("product");
	       //int pos = Intent.getIntent("position");
	       //int pos = i.getIntExtra("Position", position);
	       System.out.println("Singelist "+pos);
	        // displaying selected product name
	        txtProduct.setText(data2.get(pos));
	        txtRes.setText(date.get(pos));
	        int teller=0;
	        // For hver øvelse
	        for(int f = 0; f<res.size(); f++){
	        	// Generer grafikk for hver øvelse
	        	// TextView Navn på øvelse med grønn ring rundt boks
				myLayout.setPadding(0, 25, 0, 0);
				pairs[f] = new TextView(SingleListItem.this);
	            pairs[f].setTextSize(20);
	            pairs[f].setLayoutParams(lp);
	            pairs[f].setId(f);
	            pairs[f].setText(navn.get(teller));
	            pairs[f].setGravity(Gravity.CENTER);
	           
	            pairs[f].setBackgroundResource(R.drawable.line);
	            myLayout.addView(pairs[f]);
	        	//txtRes.setText(res.get(f)+ "\n");
	            DatabaseHelper in = new DatabaseHelper(this);
				in.open();		
				ArrayList<String> idArray2 = in.getIDArray();			
				String popo2 = idArray2.get(pos);
				ArrayList<String> yoloID2 = in.getOYoloID(popo2);
				String yID = yoloID2.get(f);
				
				ArrayList<String> rep1 = in.getRep1(yID);
				ArrayList<String> vekt1 = in.getVekt1(yID);
				ArrayList<String> rep2 = in.getRep2(yID);
				ArrayList<String> vekt2 = in.getVekt2(yID);	
				ArrayList<String> rep3 = in.getRep3(yID);
				ArrayList<String> vekt3 = in.getVekt3(yID);
				ArrayList<String> rep4= in.getRep4(yID);
				ArrayList<String> vekt4 = in.getVekt4(yID);
				ArrayList<String> rep5= in.getRep5(yID);
				ArrayList<String> vekt5 = in.getVekt5(yID);
				//ArrayList<String> res = in.getOData(koko);
				in.close();
	            
				
	            
	        	//for løkke for hvert sett
	        	antallSett = Integer.parseInt(sett.get(f));
	        	for(int h = 0; h<antallSett; h++){
	        		    settLayout[f][h] = new LinearLayout(SingleListItem.this);
			            settLayout[f][h].setOrientation(LinearLayout.HORIZONTAL);
			            settLayout[f][h].setGravity(Gravity.CENTER);
			            if(h == 1){
			            	settLayout[f][h].setPadding(0, 25, 0, 0);
			            }
			            if(h == antallSett){
			            	settLayout[f][h].setPadding(0, 10, 0, 35);
			            }
			            else{
			            	settLayout[f][h].setPadding(0, 10, 0, 0);
			            }
			            // TextView Sett
			            pairsLabelSett[f][h] = new TextView(SingleListItem.this);
			            pairsLabelSett[f][h].setTextSize(15);
			            pairsLabelSett[f][h].setLayoutParams(lp);
			            pairsLabelSett[f][h].setId(teller);
			            pairsLabelSett[f][h].setText("Sett " + (f));
			            pairsLabelSett[f][h].setPadding(50, 0, 50, 0);
			          
			            
			            // TextView Rep
			            pairsLabelRep[f][h] = new TextView(SingleListItem.this);
			            pairsLabelRep[f][h].setTextSize(15);
			            pairsLabelRep[f][h].setLayoutParams(lp);
			            pairsLabelRep[f][h].setId(f);
			            pairsLabelRep[f][h].setText("Rep");
			            pairsLabelRep[f][h].setPadding(50, 0, 50, 0);
			            
			            // TextView Kg
			            pairsLabelKg[f][h] = new TextView(SingleListItem.this);
			            pairsLabelKg[f][h].setTextSize(15);
			            pairsLabelKg[f][h].setLayoutParams(lp);
			            pairsLabelKg[f][h].setId(f);
			            pairsLabelKg[f][h].setText("Kg");
			            pairsLabelKg[f][h].setPadding(50, 0, 50, 0);
			            
			            // EditText
			            pairsEt[f][h] = new EditText(SingleListItem.this);
			            pairsEt[f][h].setLayoutParams(lp);
			            pairsEt[f][h].setId(h);
			            /**
			            System.out.println(rep1.get(teller));
			            System.out.println(vekt1.get(teller));
			            System.out.println(rep2.get(teller));			            
			            System.out.println(vekt2.get(teller));
			            **/
			            if(h == 0){
			            	pairsEt[f][h].setText(rep1.get(0));
			            }
			            if(h == 1){
			            	pairsEt[f][h].setText(rep2.get(0));
			            }
			            if(h == 2){
			            	pairsEt[f][h].setText(rep3.get(0));
			            }
			            if(h == 3){
			            	pairsEt[f][h].setText(rep4.get(0));
			            }
			            if(h == 4){
			            	pairsEt[f][h].setText(rep5.get(0));
			            }
			           
			            
			            
			            pairsEt[f][h].setInputType(InputType.TYPE_CLASS_NUMBER);
			            //pairsEt[f][i].setPadding(50, 0, 50, 0);
			            //pairsEt[f][i].setHeight(40);
			            pairsEt[f][h].setWidth(175);
			            
			            pairsKg[f][h] = new EditText(SingleListItem.this);
			            pairsKg[f][h].setLayoutParams(lp);
			            pairsKg[f][h].setId(h);
			            
			            if(h == 0){
			            	pairsKg[f][h].setText(vekt1.get(0));
			            }
			            if(h == 1){
			            	pairsKg[f][h].setText(vekt2.get(0));
			            }
			            if(h == 2){
			            	pairsKg[f][h].setText(vekt3.get(0));
			            }
			            if(h == 3){
			            	pairsKg[f][h].setText(vekt4.get(0));
			            }
			            if(h == 4){
			            	pairsKg[f][h].setText(vekt5.get(0));
			            }
			            
			            pairsKg[f][h].setInputType(InputType.TYPE_CLASS_NUMBER);
			            //pairsKg[f][i].setPadding(50, 0, 50, 0);
			            //pairsKg[f][i].setHeight(40);
			            pairsKg[f][h].setWidth(175);
			            
			            settLayout[f][h].addView(pairsLabelSett[f][h]);
			            settLayout[f][h].addView(pairsLabelRep[f][h]);
			            settLayout[f][h].addView(pairsEt[f][h]);
			            settLayout[f][h].addView(pairsLabelKg[f][h]);
			            settLayout[f][h].addView(pairsKg[f][h]);
			            System.out.println("settidyoloswagdobbellokkeduck  "+f +"  "+ h);
						
			            myLayout.addView(settLayout[f][h]);
	        	}
	        	// grafikk for hvert sett
	        	teller++;
	        }
	        
	       //System.out.println(Arrays.toString(data2));
	       System.out.println(data2.get(pos));
	       System.out.println(idArray.get(pos)+ " ID til treningsøkta");
	         
	       
	       d.setOnClickListener(new OnClickListener(){


				@Override
				public void onClick(View arg0) {
					//noe
					
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SingleListItem.this);
			        int ressize = res.size();
					DatabaseHelper f = new DatabaseHelper(SingleListItem.this);
					f.open();
					ArrayList<String> yoloswagIDArray = f.getODataID(popo);
					f.close();

					for(int i = 0; i<ressize; i++){
						String swagfish = sett.get(i);
						String swagID = popo;
						String ovelse = pairs[i].getText().toString();
						System.out.println("Antall " + swagfish);
						int antall = Integer.parseInt(swagfish);
						System.out.println("ID " + swagID);
						String id = swagID;
						String yoloid = yoloswagIDArray.get(i);

						
						if(antall==1){
							System.out.println("i = " +i);
							System.out.println(pairsKg[i][0].getText().toString());
							// Rep og kg 1
							if(pairsEt[i][0].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[i][0].getText().toString());
							}
							if(pairsKg[i][0].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[i][0].getText().toString());
							}
							if(pairsEt[i][0].getText().toString().trim().length()==0){
								r1 = 0;
							}
							if(pairsKg[i][0].getText().toString().trim().length()== 0){
								v1 = 0;
							}
							
							
									
						}
						
						if(antall==2){
							System.out.println(pairsEt[i][0].getText().toString());
							System.out.println(pairsKg[i][0].getText().toString());
							System.out.println(pairsEt[i][1].getText().toString());
							System.out.println(pairsKg[i][1].getText().toString());
							// Rep og kg 0
							if(pairsEt[i][0].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[i][0].getText().toString());
								}
							if(pairsKg[i][0].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[i][0].getText().toString());
								}
							 if(pairsEt[i][0].getText().toString().trim().length()== 0){
									r1 = 0;
								}
							 if(pairsKg[i][0].getText().toString().trim().length()== 0){
									v1 = 0;
								}
							// Rep og kg 1
							if(pairsEt[i][1].getText().toString().trim().length()> 0){
								r2 = Integer.valueOf(pairsEt[i][1].getText().toString());
								}
							if(pairsKg[i][1].getText().toString().trim().length()> 0){
								v2 = Integer.valueOf(pairsKg[i][1].getText().toString());
								}
							 if(pairsEt[i][1].getText().toString().trim().length()== 0){
									r2 = 0;
								}
							 if(pairsKg[i][1].getText().toString().trim().length()== 0){
									v2 = 0;
								}
						}
						if(antall==3){
							System.out.println(pairsEt[i][0].getText().toString());
							System.out.println(pairsKg[i][0].getText().toString());
							System.out.println(pairsEt[i][1].getText().toString());
							System.out.println(pairsKg[i][1].getText().toString());
							System.out.println(pairsEt[i][2].getText().toString());
							System.out.println(pairsKg[i][2].getText().toString());
							// Rep og kg 0
							if(pairsEt[i][0].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[i][0].getText().toString());
								}
							if(pairsKg[i][0].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[i][0].getText().toString());
								}
							if(pairsEt[i][0].getText().toString().trim().length()== 0){
									r1 = 0;
								}
							if(pairsKg[i][0].getText().toString().trim().length()== 0){
									v1 = 0;
								}
							// Rep og kg 1
							if(pairsEt[i][1].getText().toString().trim().length()> 0){
								r2 = Integer.valueOf(pairsEt[i][1].getText().toString());
								}
							if(pairsKg[i][1].getText().toString().trim().length()> 0){
								v2 = Integer.valueOf(pairsKg[i][1].getText().toString());
								}
							if(pairsEt[i][1].getText().toString().trim().length()== 0){
									r2 = 0;
								}
							if(pairsKg[i][1].getText().toString().trim().length()== 0){
									v2 = 0;
								}
							// Rep og kg 2
							if(pairsEt[i][2].getText().toString().trim().length()> 0){
								r3 = Integer.valueOf(pairsEt[i][2].getText().toString());
								}
							if(pairsKg[i][2].getText().toString().trim().length()> 0){
								v3 = Integer.valueOf(pairsKg[i][2].getText().toString());
								}
							 if(pairsEt[i][2].getText().toString().trim().length()== 0){
									r3 = 0;
								}
							 if(pairsKg[i][2].getText().toString().trim().length()== 0){
									v3 = 0;
								}
						}
						if(antall==4){
							System.out.println(pairsEt[i][0].getText().toString());
							System.out.println(pairsKg[i][0].getText().toString());
							System.out.println(pairsEt[i][1].getText().toString());
							System.out.println(pairsKg[i][1].getText().toString());
							System.out.println(pairsEt[i][2].getText().toString());
							System.out.println(pairsKg[i][2].getText().toString());
							System.out.println(pairsEt[i][3].getText().toString());
							System.out.println(pairsKg[i][3].getText().toString());
							// Rep og kg 0
							if(pairsEt[i][0].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[i][0].getText().toString());
								}
							 if(pairsKg[i][0].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[i][0].getText().toString());
								}
							 if(pairsEt[i][0].getText().toString().trim().length()== 0){
									r1 = 0;
								}
							 if(pairsKg[i][0].getText().toString().trim().length()== 0){
									v1 = 0;
								}
							// Rep og kg 1
							 if(pairsEt[i][1].getText().toString().trim().length()> 0){
								r2 = Integer.valueOf(pairsEt[i][1].getText().toString());
								}
							 if(pairsKg[i][1].getText().toString().trim().length()> 0){
								v2 = Integer.valueOf(pairsKg[i][1].getText().toString());
								}
							 if(pairsEt[i][1].getText().toString().trim().length()== 0){
									r2 = 0;
								}
							 if(pairsKg[i][1].getText().toString().trim().length()== 0){
									v2 = 0;
								}
							// Rep og kg 2
							 if(pairsEt[i][2].getText().toString().trim().length()> 0){
								r3 = Integer.valueOf(pairsEt[i][2].getText().toString());
								}
							 if(pairsKg[i][2].getText().toString().trim().length()> 0){
								v3 = Integer.valueOf(pairsKg[i][2].getText().toString());
								}
							 if(pairsEt[i][2].getText().toString().trim().length()== 0){
									r3 = 0;
								}
							 if(pairsKg[i][2].getText().toString().trim().length()== 0){
									v3 = 0;
								}
							// Rep og kg 3
							 if(pairsEt[i][3].getText().toString().trim().length()> 0){
								r4 = Integer.valueOf(pairsEt[i][3].getText().toString());
								}
							 if(pairsKg[i][3].getText().toString().trim().length()> 0){
								v4 = Integer.valueOf(pairsKg[i][3].getText().toString());
								}
							 if(pairsEt[i][3].getText().toString().trim().length()== 0){
									r4 = 0;
								}
							 if(pairsKg[i][3].getText().toString().trim().length()== 0){
									v4 = 0;
								}
						}
						if(antall==5){
							System.out.println(pairsEt[i][0].getText().toString());
							System.out.println(pairsKg[i][0].getText().toString());
							System.out.println(pairsEt[i][1].getText().toString());
							System.out.println(pairsKg[i][1].getText().toString());
							System.out.println(pairsEt[i][2].getText().toString());
							System.out.println(pairsKg[i][2].getText().toString());
							System.out.println(pairsEt[i][3].getText().toString());
							System.out.println(pairsKg[i][3].getText().toString());
							System.out.println(pairsEt[i][4].getText().toString());
							System.out.println(pairsKg[i][4].getText().toString());
							// Rep og kg 0
							if(pairsEt[i][0].getText().toString().trim().length()> 0){
								r1 = Integer.valueOf(pairsEt[i][0].getText().toString());
								}
							 if(pairsKg[i][0].getText().toString().trim().length()> 0){
								v1 = Integer.valueOf(pairsKg[i][0].getText().toString());
								}
							 if(pairsEt[i][0].getText().toString().trim().length()== 0){
									r1 = 0;
								}
							 if(pairsKg[i][0].getText().toString().trim().length()== 0){
									v1 = 0;
								}
							// Rep og kg 1
							 if(pairsEt[i][1].getText().toString().trim().length()> 0){
								r2 = Integer.valueOf(pairsEt[i][1].getText().toString());
								}
							 if(pairsKg[i][1].getText().toString().trim().length()> 0){
								v2 = Integer.valueOf(pairsKg[i][1].getText().toString());
								}
							 if(pairsEt[i][1].getText().toString().trim().length()== 0){
									r2 = 0;
								}
							 if(pairsKg[i][1].getText().toString().trim().length()== 0){
									v2 = 0;
								}
							// Rep og kg 2
							 if(pairsEt[i][2].getText().toString().trim().length()> 0){
								r3 = Integer.valueOf(pairsEt[i][2].getText().toString());
								}
							 if(pairsKg[i][2].getText().toString().trim().length()> 0){
								v3 = Integer.valueOf(pairsKg[i][2].getText().toString());
								}
							 if(pairsEt[i][2].getText().toString().trim().length()== 0){
									r3 = 0;
								}
							 if(pairsKg[i][2].getText().toString().trim().length()== 0){
									v3 = 0;
								}
							// Rep og kg 3
							 if(pairsEt[i][3].getText().toString().trim().length()> 0){
								r4 = Integer.valueOf(pairsEt[i][3].getText().toString());
								}
							 if(pairsKg[i][3].getText().toString().trim().length()> 0){
								v4 = Integer.valueOf(pairsKg[i][3].getText().toString());
								}
							 if(pairsEt[i][3].getText().toString().trim().length()== 0){
									r4 = 0;
								}
							 if(pairsKg[i][3].getText().toString().trim().length()== 0){
									v4 = 0;
								}
							// Rep og kg 4
							 if(pairsEt[i][4].getText().toString().trim().length()> 0){
								r5 = Integer.valueOf(pairsEt[i][4].getText().toString());
								}
							 if(pairsKg[i][4].getText().toString().trim().length()> 0){
								v5 = Integer.valueOf(pairsKg[i][4].getText().toString());
								}
							 if(pairsEt[i][4].getText().toString().trim().length()== 0){
									r5 = 0;
								}
							 if(pairsKg[i][4].getText().toString().trim().length()== 0){
									v5 = 0;
								}
						}
						 if(antall==0 || antall>5){
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
							DatabaseHelper ff = new DatabaseHelper(SingleListItem.this);
							ff.open();
							ff.updateEntry_Ovelse(yoloid, id, ovelse, antall, r1, v1, r2, v2, r3, v3, r4, v4, r5, v5);
							ff.close();
							System.out.println("Ferdig med å legge inn øvelser");
						
						//i++; 
						
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
			        
	    }
	       });
	 

	 }
}
