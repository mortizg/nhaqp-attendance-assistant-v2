package com.nhaqp.attendance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBHorario {
    static final String KEY_ROWID = "idHorario";
    static final String KEY_HINICIO = "horaInicio";
    static final String KEY_HFIN = "horaFin";
    static final String KEY_DIA = "dia";
    
    static final String DATABASE_TABLE = "horario";
    static final String TAG = "DBHorarioAdapter";
    
 
    final Context context;

    DBHelper dbHelper;
    SQLiteDatabase db;
    
    public DBHorario(Context ctx)
    {
        this.context = ctx;
        dbHelper = new DBHelper(context);
    }

 	    //---opens the database---
    public DBHorario open() throws SQLException 
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
    public long insertHorario(int idHorario, String hInicio, String hFin, String dia) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, idHorario);
        initialValues.put(KEY_HINICIO, hInicio);
        initialValues.put(KEY_HFIN, hFin);
        initialValues.put(KEY_DIA, dia);
        
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteHorario(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllHorarios()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, 
        		KEY_HINICIO, KEY_HFIN, KEY_DIA }, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getHorario(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_HINICIO, KEY_HFIN, KEY_DIA}, KEY_ROWID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateHorario(long rowId, String hInicio, String hFin, String dia) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_HINICIO, hInicio);
        args.put(KEY_HFIN, hFin);
        args.put(KEY_DIA, dia);
        	        
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    
    }
    
    public boolean truncateHorario(){
    	return db.delete(DATABASE_TABLE, null, null) > 0;
    }

}