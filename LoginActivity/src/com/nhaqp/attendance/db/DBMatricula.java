package com.nhaqp.attendance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBMatricula {
    static final String KEY_ROWID = "idMatricula";
    static final String KEY_IDGRUPOCURSO = "idGrupoCurso";
    static final String KEY_IDALUMNO = "idAlumno";
    static final String KEY_HORASACADEMICAS = "horasAcademicas";
    static final String DATABASE_TABLE = "matricula";
//    static final int DATABASE_VERSION = 1;
    static final String TAG = "DBMatriculaAdapter";

 
    final Context context;

    DBHelper dbHelper;
    SQLiteDatabase db;
    
    public DBMatricula(Context ctx)
    {
        this.context = ctx;
        dbHelper = new DBHelper(context);
    }

 	    //---opens the database---
    public DBMatricula open() throws SQLException 
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
    public long insertMatricula(int idMatricula, int idGrupoCurso, int idAlumno, int horasAcademicas) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, idMatricula);
        initialValues.put(KEY_IDGRUPOCURSO, idGrupoCurso);
        initialValues.put(KEY_IDALUMNO, idAlumno);
        initialValues.put(KEY_HORASACADEMICAS, horasAcademicas);
        
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteMatricula(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllMatriculas()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, 
        		KEY_IDGRUPOCURSO, KEY_IDALUMNO, KEY_HORASACADEMICAS}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getMatricula(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_IDGRUPOCURSO, KEY_IDALUMNO, KEY_HORASACADEMICAS}, KEY_ROWID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateMatricula(long rowId, int idGrupoCurso, int idAlumno, int horasAcademicas) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_IDGRUPOCURSO, idGrupoCurso);
        args.put(KEY_IDALUMNO, idAlumno);
        args.put(KEY_HORASACADEMICAS, horasAcademicas);
        
        
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public boolean truncateMatricula(){
    	return db.delete(DATABASE_TABLE, null, null) > 0;
    }

}