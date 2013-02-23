package com.nhaqp.attendance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBGrupoCurso {
	
	static final String DATABASE_TABLE = "grupoCurso";
	static final String KEY_ROWID = "idGrupoCurso";
    static final String KEY_IDCURSO = "idCurso";
    static final String KEY_IDGRUPO = "idGrupo";
    static final String KEY_IDHORARIO = "idHorario";
    static final String KEY_AULA = "Aula";
    static final String KEY_IDPROFESOR = "idProfesor";

//    static final int DATABASE_VERSION = 1;
    static final String TAG = "DBGrupoCursoAdapter";
    
 
    final Context context;

    DBHelper dbHelper;
    SQLiteDatabase db;
    
    public DBGrupoCurso(Context ctx)
    {
        this.context = ctx;
        dbHelper = new DBHelper(context);
    }

 	    //---opens the database---
    public DBGrupoCurso open() throws SQLException 
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
    public long insertGrupoCurso(int idGrupoCurso, int idCurso, int idGrupo, int idHorario, String aula, int idProfesor) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, idGrupoCurso);
        initialValues.put(KEY_IDCURSO, idCurso);
        initialValues.put(KEY_IDGRUPO, idGrupo);
        initialValues.put(KEY_IDHORARIO, idHorario);
        initialValues.put(KEY_AULA, aula);
        initialValues.put(KEY_IDPROFESOR, idProfesor);
                
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteGrupoCurso(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllGrupoCurso()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, 
        		KEY_IDCURSO, KEY_IDGRUPO, KEY_IDHORARIO, KEY_AULA, KEY_IDPROFESOR}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getGrupoCurso(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_IDCURSO, KEY_IDGRUPO, KEY_IDHORARIO, KEY_AULA, KEY_IDPROFESOR}, KEY_ROWID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateGrupoCurso(long rowId, String nombre) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_IDCURSO, nombre);
        args.put(KEY_IDGRUPO, nombre);
        args.put(KEY_IDHORARIO, nombre);
        args.put(KEY_AULA, nombre);
        args.put(KEY_IDPROFESOR, nombre);
        
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public boolean truncateGrupoCurso(){
    	return db.delete(DATABASE_TABLE, null, null) > 0;
    }

}
