package com.nhaqp.attendance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBCurso {
    static final String KEY_ROWID = "idCurso";
    static final String KEY_NAME = "Nombre";
    static final String DATABASE_TABLE = "curso";
    static final String TAG = "DBCursoAdapter";
//  static final int DATABASE_VERSION = 1;
	  
 
    final Context context;

    DBHelper dbHelper;
    SQLiteDatabase db;
    
    public DBCurso(Context ctx)
    {
        this.context = ctx;
        dbHelper = new DBHelper(context);
    }

 	    //---opens the database---
    public DBCurso open() throws SQLException 
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() 
    {
    	dbHelper.close();
    }

    //---insert a contact into the database---
    public long insertCurso(int idCurso, String nombre) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, idCurso);
        initialValues.put(KEY_NAME, nombre);
        
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteCurso(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllCursos()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, 
        		KEY_NAME}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getCurso(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_NAME}, KEY_ROWID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateCurso(long rowId, String nombre) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, nombre);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public boolean truncateCurso(){
    	return db.delete(DATABASE_TABLE, null, null) > 0;
    }

}