package com.nhaqp.attendance.db;



import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBBorrarTablas {
	
    final Context context;

    DBHelper dbHelper;
    SQLiteDatabase db;
    

	
	public DBBorrarTablas(Context ctx)
    {
        this.context = ctx;
        dbHelper = new DBHelper(context);
    }
	
	    //---opens the database---
	public DBBorrarTablas open() throws SQLException 
	{
	    db = dbHelper.getWritableDatabase();
	    return this;
	}
	
	//---closes the database---
	public void close() 
	{
		dbHelper.close();
	}
	
    public void dropTables(){
    	
        db.execSQL("DROP TABLE IF EXISTS alumno");
        db.execSQL("DROP TABLE IF EXISTS curso");
        db.execSQL("DROP TABLE IF EXISTS grupo");
        db.execSQL("DROP TABLE IF EXISTS grupoCurso");
        db.execSQL("DROP TABLE IF EXISTS horario");
        db.execSQL("DROP TABLE IF EXISTS matricula");
        db.execSQL("DROP TABLE IF EXISTS asistencia");
    	
       
    }
		
}