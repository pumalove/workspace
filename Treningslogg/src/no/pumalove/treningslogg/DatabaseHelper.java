package no.pumalove.treningslogg;


import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends Activity{
	
	
	
	
	
	//Bibliotek
	private static final String DATABASE_TABLE_BIBLIOTEK = "bibliotek";
	private static final String KEY_BIBID ="bibid";
	private static final String KEY_BIBNAVN ="bibnavn";
	private static final String KEY_BIBKAT ="bibkat";
	private static final String KEY_BIBBILDE ="bibbilde";
	private static final String KEY_BIBBESK ="bibbesk";
	//private static final String KEY_BIBBILDE ="bibilde"; // ikke lagt opp til å lagre bilder enda
	
	//Ovelse
	private static final String DATABASE_TABLE_OVELSE = "ovelse";
	private static final String KEY_TRENINGSID ="treningsid";
	private static final String KEY_TRENINGSID_YOLO ="treningsid_yolo";
	private static final String KEY_ONAME ="oname";
	private static final String KEY_SETT ="sett";
	
	private static final String KEY_REP1 ="rep1";
	private static final String KEY_REP2 ="rep2";
	private static final String KEY_REP3 ="rep3";
	private static final String KEY_REP4 ="rep4";
	private static final String KEY_REP5 ="rep5";
	
	private static final String KEY_VEKT1 ="vekt1";
	private static final String KEY_VEKT2 ="vekt2";
	private static final String KEY_VEKT3 ="vekt3";
	private static final String KEY_VEKT4 ="vekt4";
	private static final String KEY_VEKT5 ="vekt5";

	
	
	//Users
	private static final String DATABASE_NAME = "treningslogg";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "users";
	private static final String KEY_ROWID = "_id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_NAME = "name";
	private static final String KEY_DATE = "date";
	
	

	
		
	private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    private String aktivBruker;
    private String swag;
    
    //private String username;
    
    
    private  static class DbHelper extends SQLiteOpenHelper{
    	

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE + " (" + 
					 KEY_ROWID + " INTEGER PRIMARY KEY, " +
					 KEY_USERNAME + " TEXT NOT NULL, " +
					 KEY_DATE + " TEXT NOT NULL, " +
					 KEY_NAME + " TEXT NOT NULL);";
			
			String CREATE_TABLE2 = "CREATE TABLE " + DATABASE_TABLE_OVELSE + " (" + 
					 KEY_TRENINGSID_YOLO + " INTEGER PRIMARY KEY, " +
					 KEY_TRENINGSID + " INT, " +
					 KEY_USERNAME + " TEXT NOT NULL, " +
					 KEY_ONAME + " TEXT NOT NULL, " +
					 KEY_SETT + " INT, " +
					 KEY_REP1 + " INT, " +
					 KEY_REP2 + " INT, " +
					 KEY_REP3 + " INT, " +
					 KEY_REP4 + " INT, " +
					 KEY_REP5 + " INT, " +
					 KEY_VEKT1 + " INT, " +
					 KEY_VEKT2 + " INT, " +
					 KEY_VEKT3 + " INT, " +
					 KEY_VEKT4 + " INT, " +
					 KEY_VEKT5 + " INT);";
			
			String CREATE_TABLE3 ="CREATE TABLE " + DATABASE_TABLE_BIBLIOTEK + " (" + 
								 KEY_BIBID + " INTEGER PRIMARY KEY, " +
								 KEY_BIBNAVN + " TEXT NOT NULL, " +
								 KEY_BIBKAT + " TEXT NOT NULL, " +
								//KEY_BIBBILDE + " BLOB NOT NULL, " +
								 KEY_BIBBESK + " TEXT NOT NULL);";
			
			try
			{
			db.execSQL(CREATE_TABLE);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
			 db.execSQL(CREATE_TABLE2);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
			 db.execSQL(CREATE_TABLE3);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
		

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
			
		} 
		
    }//DB HELPER
    public  DatabaseHelper(Context c){
    	ourContext = c;
    }
    
    public DatabaseHelper open() throws SQLException{
    	ourHelper = new DbHelper(ourContext);
    	ourDatabase = ourHelper.getWritableDatabase();	
		return this;
    }
    
    public void close(){
    	ourHelper.close();
    }
    
    public long createEntry(String username, String name, String date){
    	
    	ContentValues cv = new ContentValues();
    	cv.put(KEY_USERNAME, username);
    	cv.put(KEY_NAME, name);
    	cv.put(KEY_DATE, date);
    	return ourDatabase.insert(DATABASE_TABLE, null, cv);
    	
    	
    }
    
public long createEntry_bib(String bibnavn, String bibkat, String bibbesk){
    	
    	ContentValues cv = new ContentValues();
    	cv.put(KEY_BIBNAVN, bibnavn);
    	cv.put(KEY_BIBKAT, bibkat);
    	cv.put(KEY_BIBBESK, bibbesk);
    	//cv.put(KEY_BIBBILDE, photo);
    	return ourDatabase.insert(DATABASE_TABLE_BIBLIOTEK, null, cv);
    	
    	
    	
    	
    }
 public long createEntry_Ovelse(String treningsid, String oname, int i, int j, int o, int k, int p, int l, int q, int m, int r, int n, int s){
	 	aktivBruker = User.getName();		
    	ContentValues cv = new ContentValues();
    	cv.put(KEY_TRENINGSID, treningsid);
    	cv.put(KEY_USERNAME, aktivBruker);
    	cv.put(KEY_ONAME, oname);
    	cv.put(KEY_SETT, i);
    	cv.put(KEY_REP1, j);
    	cv.put(KEY_VEKT1, o);
    	cv.put(KEY_REP2, k);
    	cv.put(KEY_VEKT2, p);
    	cv.put(KEY_REP3, l);
    	cv.put(KEY_VEKT3, q);
    	cv.put(KEY_REP4, m);
    	cv.put(KEY_VEKT4, r);
    	cv.put(KEY_REP5, n);
    	cv.put(KEY_VEKT5, s);
    	return ourDatabase.insert(DATABASE_TABLE_OVELSE, null, cv);
    
    	
    }
 public long updateEntry_Ovelse(String yoloid,String treningsid, String oname,int antall, int r1, int v1, int r2, int v2, int r3, int v3, int r4, int v4, int r5, int v5){
	 aktivBruker = User.getName();		
 	ContentValues cv = new ContentValues();
 	cv.put(KEY_USERNAME, aktivBruker);
 	cv.put(KEY_ONAME, oname);
 	cv.put(KEY_SETT, antall);
 	cv.put(KEY_REP1, r1);
 	cv.put(KEY_VEKT1, v1);
 	cv.put(KEY_REP2, r2);
 	cv.put(KEY_VEKT2, v2);
 	cv.put(KEY_REP3, r3);
 	cv.put(KEY_VEKT3, v3);
 	cv.put(KEY_REP4, r4);
 	cv.put(KEY_VEKT4, v4);
 	cv.put(KEY_REP5, r5);
 	cv.put(KEY_VEKT5, v5);
 	//String whereClause = "treningsid = " + "'"+treningsid+"'" + " AND " + "oname =" + "'"+oname+"'";
 	String whereClause = KEY_TRENINGSID +"="+ "'"+treningsid+"'" + " AND " + KEY_ONAME + "=" + "'"+oname+"'";
 	System.out.println(whereClause);
	return ourDatabase.update(DATABASE_TABLE_OVELSE, cv, KEY_TRENINGSID_YOLO +"=" + "'"+yoloid+"'" , null);
 
 	
 }
 
	public ArrayList<String> getOData(String id){
		
		
		String[] columns = new String [] {KEY_TRENINGSID, KEY_ONAME, KEY_SETT, KEY_REP1, KEY_REP2, KEY_REP3, KEY_REP4, KEY_REP5, KEY_VEKT1, KEY_VEKT2, KEY_VEKT3, KEY_VEKT4, KEY_VEKT5};
		aktivBruker = User.getName();	
		String whereClause = "treningsid ="+ "'"+id+"'" + " AND " + "username ="+ "'"+aktivBruker+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);
		
		//ArrayList<String> stringList = new ArrayList<String>();
		//String results = "";
		ArrayList<String> stringList = new ArrayList<String>();
		int treningsid = c.getColumnIndex(KEY_TRENINGSID);
		int oname = c.getColumnIndex(KEY_ONAME);
		int sett = c.getColumnIndex(KEY_SETT);
		int rep1 = c.getColumnIndex(KEY_REP1);
		int rep2 = c.getColumnIndex(KEY_REP2);
		int rep3 = c.getColumnIndex(KEY_REP3);
		int rep4 = c.getColumnIndex(KEY_REP4);
		int rep5 = c.getColumnIndex(KEY_REP5);
		int vekt1 = c.getColumnIndex(KEY_VEKT1);
		int vekt2 = c.getColumnIndex(KEY_VEKT2);
		int vekt3 = c.getColumnIndex(KEY_VEKT3);
		int vekt4 = c.getColumnIndex(KEY_VEKT4);
		int vekt5 = c.getColumnIndex(KEY_VEKT5);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			//stringList.add(c.getString(treningsid)+ " "+ c.getString(oname)+" "+ c.getString(sett)+" "+ c.getString(rep1)+" "+ c.getString(rep2)+" "+ c.getString(rep3)+" "+ c.getString(rep4)+" "+ c.getString(rep5)+" "+ c.getString(vekt1) +" "+ c.getString(vekt2)+" "+ c.getString(vekt3)+" "+ c.getString(vekt4)+" "+ c.getString(vekt5)+"\n");
			stringList.add(c.getString(treningsid)+ " "+ c.getString(oname)+" "+ c.getString(sett)+" "+ c.getString(rep1)+" "+ c.getString(vekt1)+" "+ c.getString(rep2)+" "+ c.getString(vekt2)+" "+ c.getString(rep3)+" "+ c.getString(vekt3) +" "+ c.getString(rep4)+" "+ c.getString(vekt4)+" "+ c.getString(rep5)+" "+ c.getString(vekt5)+"\n");
			
		}
		
		
		
		return stringList;
	}
	
	
	public ArrayList<String> getAllOData(){

		String[] columns = new String [] {KEY_TRENINGSID, KEY_ONAME, KEY_SETT, KEY_REP1, KEY_REP2, KEY_REP3, KEY_REP4, KEY_REP5, KEY_VEKT1, KEY_VEKT2, KEY_VEKT3, KEY_VEKT4, KEY_VEKT5};
		//String whereClause = "treningsid ="+ "'"+id+"'";
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);
		
		//ArrayList<String> stringList = new ArrayList<String>();
		//String results = "";
		ArrayList<String> stringList = new ArrayList<String>();
		int treningsid = c.getColumnIndex(KEY_TRENINGSID);
		int oname = c.getColumnIndex(KEY_ONAME);
		int sett = c.getColumnIndex(KEY_SETT);
		int rep1 = c.getColumnIndex(KEY_REP1);
		int rep2 = c.getColumnIndex(KEY_REP2);
		int rep3 = c.getColumnIndex(KEY_REP3);
		int rep4 = c.getColumnIndex(KEY_REP4);
		int rep5 = c.getColumnIndex(KEY_REP5);
		int vekt1 = c.getColumnIndex(KEY_VEKT1);
		int vekt2 = c.getColumnIndex(KEY_VEKT2);
		int vekt3 = c.getColumnIndex(KEY_VEKT3);
		int vekt4 = c.getColumnIndex(KEY_VEKT4);
		int vekt5 = c.getColumnIndex(KEY_VEKT5);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			//stringList.add(c.getString(treningsid)+ " "+ c.getString(oname)+" "+ c.getString(sett)+" "+ c.getString(rep1)+" "+ c.getString(rep2)+" "+ c.getString(rep3)+" "+ c.getString(rep4)+" "+ c.getString(rep5)+" "+ c.getString(vekt1) +" "+ c.getString(vekt2)+" "+ c.getString(vekt3)+" "+ c.getString(vekt4)+" "+ c.getString(vekt5)+"\n");
			stringList.add(c.getString(treningsid)+ " "+ c.getString(oname)+" "+ c.getString(sett)+" "+ c.getString(rep1)+" "+ c.getString(vekt1)+" "+ c.getString(rep2)+" "+ c.getString(vekt2)+" "+ c.getString(rep3)+" "+ c.getString(vekt3) +" "+ c.getString(rep4)+" "+ c.getString(vekt4)+" "+ c.getString(rep5)+" "+ c.getString(vekt5)+"\n");
			
		}
	
	
	return stringList;
}
	//-------------------------------------------------- funksjoner til å opplastning til database
	public ArrayList<String> getOId(){
		String[] columns = new String [] {KEY_TRENINGSID};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int treningsid = c.getColumnIndex(KEY_TRENINGSID);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(treningsid));
		}
	return stringList;
}
	public String getOTreningsID(String id){
		String[] columns = new String [] {KEY_TRENINGSID};
		aktivBruker = User.getName();	
		String whereClause = "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		String stringList="";
		int treningsid = c.getColumnIndex(KEY_TRENINGSID);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList =(c.getString(treningsid));
		}
	return stringList;
}
	public ArrayList<String> getOYoloID(String id){
		String[] columns = new String [] {KEY_TRENINGSID_YOLO};
		aktivBruker = User.getName();	
		String whereClause = "treningsid ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int treningsid = c.getColumnIndex(KEY_TRENINGSID_YOLO);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(treningsid));
		}
	return stringList;
}
	public ArrayList<String> getOname(){
		String[] columns = new String [] {KEY_ONAME};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int oname = c.getColumnIndex(KEY_ONAME);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(oname));
		}
	return stringList;
}
	public ArrayList<String> getSett(String id){
		String[] columns = new String [] {KEY_SETT};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'"+ " AND " + "treningsid ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int sett = c.getColumnIndex(KEY_SETT);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(sett));
		}
	return stringList;
}
	public ArrayList<String> getRep1(String id){
		String[] columns = new String [] {KEY_REP1};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int rep1 = c.getColumnIndex(KEY_REP1);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(rep1));
		}
	return stringList;
}
	public ArrayList<String> getRep2(String id){
		String[] columns = new String [] {KEY_REP2};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int rep1 = c.getColumnIndex(KEY_REP2);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(rep1));
		}
	return stringList;
}
	public ArrayList<String> getRep3(String id){
		String[] columns = new String [] {KEY_REP3};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int rep1 = c.getColumnIndex(KEY_REP3);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(rep1));
		}
	return stringList;
}
	public ArrayList<String> getRep4(String id){
		String[] columns = new String [] {KEY_REP4};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int rep1 = c.getColumnIndex(KEY_REP4);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(rep1));
		}
	return stringList;
}
	public ArrayList<String> getRep5(String id){
		String[] columns = new String [] {KEY_REP5};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int rep1 = c.getColumnIndex(KEY_REP5);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(rep1));
		}
	return stringList;
}
	public ArrayList<String> getVekt1(String id){
		String[] columns = new String [] {KEY_VEKT1};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int vekt1 = c.getColumnIndex(KEY_VEKT1);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(vekt1));
		}
	return stringList;
}
	public ArrayList<String> getVekt2(String id){
		String[] columns = new String [] {KEY_VEKT2};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int vekt1 = c.getColumnIndex(KEY_VEKT2);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(vekt1));
		}
	return stringList;
}
	public ArrayList<String> getVekt3(String id){
		String[] columns = new String [] {KEY_VEKT3};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int vekt1 = c.getColumnIndex(KEY_VEKT3);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(vekt1));
		}
	return stringList;
}
	public ArrayList<String> getVekt4(String id){
		String[] columns = new String [] {KEY_VEKT4};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int vekt1 = c.getColumnIndex(KEY_VEKT4);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(vekt1));
		}
	return stringList;
}
	public ArrayList<String> getVekt5(String id){
		String[] columns = new String [] {KEY_VEKT5};
		aktivBruker = User.getName();	
		String whereClause = "username ="+ "'"+aktivBruker+"'" + " AND " + "treningsid_yolo ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);	
		ArrayList<String> stringList = new ArrayList<String>();
		int vekt1 = c.getColumnIndex(KEY_VEKT5);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				stringList.add(c.getString(vekt1));
		}
	return stringList;
}
	
	public ArrayList<String> getAllOID(){

		String[] columns = new String [] {KEY_TRENINGSID_YOLO,KEY_TRENINGSID, KEY_USERNAME, KEY_ONAME, KEY_SETT, KEY_REP1, KEY_REP2, KEY_REP3, KEY_REP4, KEY_REP5, KEY_VEKT1, KEY_VEKT2, KEY_VEKT3, KEY_VEKT4, KEY_VEKT5};
		//String whereClause = "username ="+ "'"+aktivBruker+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, null, null, null, null, null);
		
		//ArrayList<String> stringList = new ArrayList<String>();
		//String results = "";
		ArrayList<String> stringList = new ArrayList<String>();
		int yoloID = c.getColumnIndex(KEY_TRENINGSID_YOLO);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			//stringList.add(c.getString(treningsid)+ " "+ c.getString(oname)+" "+ c.getString(sett)+" "+ c.getString(rep1)+" "+ c.getString(rep2)+" "+ c.getString(rep3)+" "+ c.getString(rep4)+" "+ c.getString(rep5)+" "+ c.getString(vekt1) +" "+ c.getString(vekt2)+" "+ c.getString(vekt3)+" "+ c.getString(vekt4)+" "+ c.getString(vekt5)+"\n");
			stringList.add(c.getString(yoloID));			
		}				
		return stringList;
	}
	
	//------------------------------------------------------
	
	public ArrayList<String> getODataNavn(String id){

		String[] columns = new String [] {KEY_TRENINGSID, KEY_ONAME, KEY_SETT, KEY_REP1, KEY_REP2, KEY_REP3, KEY_REP4, KEY_REP5, KEY_VEKT1, KEY_VEKT2, KEY_VEKT3, KEY_VEKT4, KEY_VEKT5};

		aktivBruker = User.getName();	
		String whereClause ="treningsid ="+ "'"+id+"'" +" AND "+"username ="+ "'"+aktivBruker+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);
		
		//ArrayList<String> stringList = new ArrayList<String>();
		//String results = "";
		ArrayList<String> stringList = new ArrayList<String>();
		int oname = c.getColumnIndex(KEY_ONAME);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			//stringList.add(c.getString(treningsid)+ " "+ c.getString(oname)+" "+ c.getString(sett)+" "+ c.getString(rep1)+" "+ c.getString(rep2)+" "+ c.getString(rep3)+" "+ c.getString(rep4)+" "+ c.getString(rep5)+" "+ c.getString(vekt1) +" "+ c.getString(vekt2)+" "+ c.getString(vekt3)+" "+ c.getString(vekt4)+" "+ c.getString(vekt5)+"\n");
			stringList.add(c.getString(oname)+"\n");			
		}				
		return stringList;
	}
	public ArrayList<String> getODataID(String id){

		String[] columns = new String [] {KEY_TRENINGSID_YOLO,KEY_TRENINGSID, KEY_ONAME, KEY_SETT, KEY_REP1, KEY_REP2, KEY_REP3, KEY_REP4, KEY_REP5, KEY_VEKT1, KEY_VEKT2, KEY_VEKT3, KEY_VEKT4, KEY_VEKT5};
		String whereClause ="treningsid ="+ "'"+id+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE_OVELSE, columns, whereClause, null, null, null, null);
		
		//ArrayList<String> stringList = new ArrayList<String>();
		//String results = "";
		ArrayList<String> stringList = new ArrayList<String>();
		int yoloID = c.getColumnIndex(KEY_TRENINGSID_YOLO);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			//stringList.add(c.getString(treningsid)+ " "+ c.getString(oname)+" "+ c.getString(sett)+" "+ c.getString(rep1)+" "+ c.getString(rep2)+" "+ c.getString(rep3)+" "+ c.getString(rep4)+" "+ c.getString(rep5)+" "+ c.getString(vekt1) +" "+ c.getString(vekt2)+" "+ c.getString(vekt3)+" "+ c.getString(vekt4)+" "+ c.getString(vekt5)+"\n");
			stringList.add(c.getString(yoloID)+"\n");			
		}				
		return stringList;
	}
	

	public String getData() {
		aktivBruker = User.getName();		
		String[] columns = new String [] {KEY_ROWID, KEY_USERNAME, KEY_NAME, KEY_DATE};
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, whereClause, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iUsername = c.getColumnIndex(KEY_USERNAME);
		int iName = c.getColumnIndex(KEY_NAME);
		int iDate = c.getColumnIndex(KEY_DATE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " " + c.getString(iUsername) + " " + c.getString(iName) + " " + c.getString(iDate) + "\n";
			
		}
		
		return result;
	}
	
	public ArrayList<String> getData2() {
		aktivBruker = User.getName();
		String[] columns = new String [] {KEY_ROWID, KEY_USERNAME, KEY_NAME, KEY_DATE};
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, whereClause, null, null, null, null); // Add filter, only fetch info about the logged in user
		ArrayList<String> stringList = new ArrayList<String>();
		int iUsername = c.getColumnIndex(KEY_USERNAME);
		int iName = c.getColumnIndex(KEY_NAME);
		int iDate = c.getColumnIndex(KEY_DATE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add("Laget av " + c.getString(iUsername) + ". Navn på treningsøkt " + c.getString(iName) + " Dato " + c.getString(iDate) + "\n");
			//result = result + c.getString(iRow) + " " + c.getString(iUsername) + " " + c.getString(iName) + " " + c.getString(iDate) + "\n";
			
		}
		
		return stringList;
	}
	public ArrayList<String> getData3() {
		aktivBruker = User.getName();
		String[] columns = new String [] {KEY_ROWID, KEY_USERNAME, KEY_NAME, KEY_DATE};
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, whereClause, null, null, null, null); // Add filter, only fetch info about the logged in user
		
		ArrayList<String> stringList = new ArrayList<String>();
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iUsername = c.getColumnIndex(KEY_USERNAME);
		int iName = c.getColumnIndex(KEY_NAME);
		int iDate = c.getColumnIndex(KEY_DATE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iRow) +" " + c.getString(iDate) + " " + c.getString(iName) + "\n");
			result = result + c.getString(iRow) + " " + c.getString(iUsername) + " " + c.getString(iName) + " " + c.getString(iDate) + "\n";
			
		}
		
		return stringList;
	}
	public ArrayList<String> getDataName() {
		aktivBruker = User.getName();
		String[] columns = new String [] {KEY_ROWID, KEY_USERNAME, KEY_NAME, KEY_DATE};
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, whereClause, null, null, null, null); // Add filter, only fetch info about the logged in user
		
		ArrayList<String> stringList = new ArrayList<String>();
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iUsername = c.getColumnIndex(KEY_USERNAME);
		int iName = c.getColumnIndex(KEY_NAME);
		int iDate = c.getColumnIndex(KEY_DATE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iName));  
			result = result + c.getString(iRow) + " " + c.getString(iUsername) + " " + c.getString(iName) + " " + c.getString(iDate) + "\n";
			
		}
		
		return stringList;
	}
	public ArrayList<String> getTName() {
		aktivBruker = User.getName();
		String[] columns = new String [] {KEY_ROWID, KEY_USERNAME, KEY_NAME, KEY_DATE};
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, whereClause, null, null, null, null); // Add filter, only fetch info about the logged in user
		
		ArrayList<String> stringList = new ArrayList<String>();
		//String result = "";
		int iDate = c.getColumnIndex(KEY_NAME);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iDate));
			//result = result + c.getString(iRow) + " " + c.getString(iUsername) + " " + c.getString(iName) + " " + c.getString(iDate) + "\n";
			
		}
		
		return stringList;
	}
	public ArrayList<String> getDate() {
		aktivBruker = User.getName();
		String[] columns = new String [] {KEY_ROWID, KEY_USERNAME, KEY_NAME, KEY_DATE};
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, whereClause, null, null, null, null); // Add filter, only fetch info about the logged in user
		
		ArrayList<String> stringList = new ArrayList<String>();
		//String result = "";
		int iDate = c.getColumnIndex(KEY_DATE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iDate));
			//result = result + c.getString(iRow) + " " + c.getString(iUsername) + " " + c.getString(iName) + " " + c.getString(iDate) + "\n";
			
		}
		
		return stringList;
	}
	
	public ArrayList<String> getBibData() {
		String[] columns = new String [] {KEY_BIBID, KEY_BIBNAVN, KEY_BIBKAT, KEY_BIBBESK};
		//String whereClause = "username ="+ "'"+aktivBruker+"'";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE_BIBLIOTEK, columns, null, null, null, null, null); // Add filter, only fetch info about the logged in user
		
		ArrayList<String> stringList = new ArrayList<String>();
		String result = "";
		int iID = c.getColumnIndex(KEY_BIBID);
		int iNavn = c.getColumnIndex(KEY_BIBNAVN);
		int iKat = c.getColumnIndex(KEY_BIBKAT);
		int iBesk = c.getColumnIndex(KEY_BIBBESK);
		//int iBilde = c.getColumnIndex(KEY_BIBBILDE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iID) +" " + c.getString(iNavn) + " " + c.getString(iKat) +" " + c.getString(iBesk) +  "\n");
			
		}
		
		return stringList;
	}
	public ArrayList<String> checkBibData(String name) {
		String[] columns = new String [] {KEY_BIBID, KEY_BIBNAVN, KEY_BIBKAT, KEY_BIBBESK};
		String whereClause = "bibnavn ="+ "'"+name+"'";
		System.out.println(whereClause);
		
		Cursor c = ourDatabase.query(DATABASE_TABLE_BIBLIOTEK, columns, whereClause, null, null, null, null); // Add filter, only fetch info about the logged in user
		System.out.println(c);
		ArrayList<String> stringList = new ArrayList<String>();
		String result = "";
		int iID = c.getColumnIndex(KEY_BIBID);
		int iNavn = c.getColumnIndex(KEY_BIBNAVN);
		int iKat = c.getColumnIndex(KEY_BIBKAT);
		int iBesk = c.getColumnIndex(KEY_BIBBESK);
		//int iBilde = c.getColumnIndex(KEY_BIBBILDE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iID) +" " + c.getString(iNavn) + " " + c.getString(iKat) +" " + c.getString(iBesk) +  "\n");
			
		}
		
		return stringList;
	}
	public ArrayList<String> checkBibDataID(String name) {
		String[] columns = new String [] {KEY_BIBID, KEY_BIBNAVN, KEY_BIBKAT, KEY_BIBBESK};
		String whereClause = "bibid ="+ "'"+name+"'";
		System.out.println(whereClause);
		
		Cursor c = ourDatabase.query(DATABASE_TABLE_BIBLIOTEK, columns, whereClause, null, null, null, null); // Add filter, only fetch info about the logged in user
		System.out.println(c);
		ArrayList<String> stringList = new ArrayList<String>();
		String result = "";
		int iID = c.getColumnIndex(KEY_BIBID);
		int iNavn = c.getColumnIndex(KEY_BIBNAVN);
		int iKat = c.getColumnIndex(KEY_BIBKAT);
		int iBesk = c.getColumnIndex(KEY_BIBBESK);
		//int iBilde = c.getColumnIndex(KEY_BIBBILDE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iNavn) + " " + c.getString(iKat) +" " + c.getString(iBesk) +  "\n");
			
		}
		
		return stringList;
	}


	public ArrayList<String> getBibOvelser() {
		String[] columns = new String [] {KEY_BIBNAVN};
		//String whereClause = "username ="+ "'"+aktivBruker+"'";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE_BIBLIOTEK, columns, null, null, null, null, null); // Add filter, only fetch info about the logged in user
		
		ArrayList<String> stringList = new ArrayList<String>();
		int iNavn = c.getColumnIndex(KEY_BIBNAVN);		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add( c.getString(iNavn) + "\n");		
		}
		
		return stringList;
	}
	
	public ArrayList<String> getIDArray() {
		aktivBruker = User.getName();
		String[] columns = new String [] {KEY_ROWID, KEY_USERNAME, KEY_NAME, KEY_DATE};
		String whereClause = "username ="+ "'"+aktivBruker+"'";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, whereClause, null, null, null, null); // Add filter, only fetch info about the logged in user
		
		ArrayList<String> stringList = new ArrayList<String>();
		int iRow = c.getColumnIndex(KEY_ROWID);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iRow));			
		}
		
		return stringList;
	}
	public ArrayList<String> getIDArrayBib() {
		aktivBruker = User.getName();
		String[] columns = new String [] {KEY_BIBID};
		//String whereClause = "username ="+ "'"+aktivBruker+"'";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE_BIBLIOTEK, columns, null, null, null, null, null); // Add filter, only fetch info about the logged in user
		
		ArrayList<String> stringList = new ArrayList<String>();
		int iRow = c.getColumnIndex(KEY_BIBID);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iRow));			
		}
		
		return stringList;
	}
	
	public String getUsername(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_USERNAME, KEY_NAME};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String username = c.getString(1);
			return username;
		}
		return null;
	}
	
	public String getID(String date, String username, String name) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_USERNAME, KEY_NAME, KEY_DATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_NAME + "=" + "'"+name+"'" + " AND " + KEY_USERNAME + "=" + "'"+username+"'" + " AND " + KEY_DATE + "=" + "'"+date+"'", null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String id = c.getString(0);
			return id;
		}
		return null;
	}

	public ArrayList<String> getName(String popo) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_USERNAME, KEY_NAME};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + popo, null, null, null, null);
		ArrayList<String> stringList = new ArrayList<String>();
		int iRow = c.getColumnIndex(KEY_NAME);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			stringList.add(c.getString(iRow));			
		}
		
		return stringList;
	}

	public void updateEntry(long lRow, String mUsername, String mName) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_USERNAME, mUsername);
		cvUpdate.put(KEY_NAME, mName);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
	}

	public void deleteEntry(String lRow1) throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
		
	}
	public void deleteEntryBib(String lRow1) throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE_BIBLIOTEK, KEY_BIBID + "=" + lRow1, null);
		
	}
	}




