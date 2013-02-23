package com.nhaqp.attendance.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBAsistencia {
    static final String KEY_ROWID = "idAsistencia";
    static final String KEY_IDPROFESOR = "idProfesor";
    static final String KEY_IDCURSO = "idCurso";
    static final String KEY_IDALUMNO = "idAlumno";
    static final String KEY_IDGRUPO = "idGrupo";
    static final String KEY_FECHA = "fecha";
    static final String KEY_TIPOASIST = "tipoAsistencia";
    static final String KEY_FLGENSERVIDOR = "flgEnServidor";


    static final String DATABASE_TABLE = "asistencia";
//    static final int DATABASE_VERSION = 1;
    static final String TAG = "DBAsistenciaAdapter";
    
 
    final Context context;

    DBHelper dbHelper;
    SQLiteDatabase db;
    
    public DBAsistencia(Context ctx)
    {
        this.context = ctx;
        dbHelper = new DBHelper(context);
    }

 	    //---opens the database---
    public DBAsistencia open() throws SQLException 
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
    public long insertAsistencia(int idAsistencia,int idProfesor, int idCurso, int idAlumno, int idGrupo, String fecha, String tipoAsistencia, String flgEnServidor ) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, idAsistencia);
        initialValues.put(KEY_IDPROFESOR, idProfesor);
        initialValues.put(KEY_IDCURSO, idCurso);
        initialValues.put(KEY_IDALUMNO, idAlumno);
        initialValues.put(KEY_IDGRUPO, idGrupo);
        initialValues.put(KEY_FECHA, fecha);
        initialValues.put(KEY_TIPOASIST, tipoAsistencia);
        initialValues.put(KEY_FLGENSERVIDOR, flgEnServidor);
        
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteAsistencia(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllAsistencias()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, 
        		KEY_IDPROFESOR, KEY_IDCURSO, KEY_IDALUMNO, KEY_IDGRUPO, KEY_FECHA, KEY_TIPOASIST, KEY_FLGENSERVIDOR}, null, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getAsistencia(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_IDPROFESOR, KEY_IDCURSO, KEY_IDALUMNO, KEY_IDGRUPO, KEY_FECHA, KEY_TIPOASIST, KEY_FLGENSERVIDOR}, KEY_ROWID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateAsistencia(long rowId, int idProfesor, int idCurso, int idAlumno, int idGrupo, String fecha, String tipoAsistencia, String flgEnServidor) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_IDPROFESOR, idProfesor);
        args.put(KEY_IDCURSO, idCurso);
        args.put(KEY_IDALUMNO, idAlumno);
        args.put(KEY_IDGRUPO, idGrupo);
        args.put(KEY_FECHA, fecha);
        args.put(KEY_TIPOASIST, tipoAsistencia);
        args.put(KEY_FLGENSERVIDOR, flgEnServidor);
        
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public boolean updateAsistenciaEnviado(long rowId, String flgEnServidor) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_FLGENSERVIDOR, flgEnServidor);
        
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public boolean truncateAsistencia(){
    	return db.delete(DATABASE_TABLE, null, null) > 0;
    }

}