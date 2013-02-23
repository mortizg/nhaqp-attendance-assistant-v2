package com.nhaqp.attendance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBAlumno {
    static final String KEY_ROWID = "idAlumno";
    static final String KEY_NAME = "Nombre";
    static final String TAG = "DBAlumnoAdapter";
    static final String DATABASE_TABLE = "alumno";


 
    final Context context;

    DBHelper dbHelper;
    SQLiteDatabase db;
    
    public DBAlumno(Context ctx)
    {
        this.context = ctx;
        dbHelper = new DBHelper(context);
    }

 	    //---opens the database---
    public DBAlumno open() throws SQLException 
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
    public long insertAlumno(int idAlumno, String nombre) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, idAlumno);
        initialValues.put(KEY_NAME, nombre);
        
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteAlumno(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllAlumnos()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, 
        		KEY_NAME}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getAlumno(long rowId) throws SQLException 
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
    public boolean updateAlumno(long rowId, String nombre) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, nombre);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean truncateAlumno(){
    	return db.delete(DATABASE_TABLE, null, null) > 0;
    }
}