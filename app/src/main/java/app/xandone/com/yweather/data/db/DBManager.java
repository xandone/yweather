package app.xandone.com.yweather.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import app.xandone.com.yweather.R;


public class DBManager {
	    private final int BUFFER_SIZE = 1024;
	    public static final String DB_NAME = "city.s3db";
	    public static final String PACKAGE_NAME = "app.xandone.com.yweather";
	    public static final String DB_PATH = "/data"
	            + Environment.getDataDirectory().getAbsolutePath() + "/"+ PACKAGE_NAME;
	    private SQLiteDatabase database;
	    private Context context;
	    private File file=null;
	    
	    DBManager(Context context) {
	        this.context = context;
	    }
	 
	    public void openDatabase() {
	    	
	        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
	    }
	    public SQLiteDatabase getDatabase(){
	    	return this.database;
	    }
	 
	    private SQLiteDatabase openDatabase(String dbfile) {
	        try {
	        	file = new File(dbfile);
	            if (!file.exists()) {
	            	InputStream is = context.getResources().openRawResource(R.raw.city);
	            	if(is==null){
						return null;
	            	}
	            	FileOutputStream fos = new FileOutputStream(dbfile);
	                byte[] buffer = new byte[BUFFER_SIZE];
	                int count = 0;
	                while ((count =is.read(buffer)) > 0) {
	                    fos.write(buffer, 0, count);
	                	fos.flush();
	                }
	                fos.close();
	                is.close();
	            }
	            database = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
	            return database;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (Exception e){
	        }
	        return null;
	    }
	    public void closeDatabase() {
	    	if(this.database!=null)
	    		this.database.close();
	    }
	}
