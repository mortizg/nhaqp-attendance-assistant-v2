package com.nhaqp.attendance.db;

import android.content.Context;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
   
    static final String DATABASE_NAME = "bd_asistencia";
    public static int DATABASE_VERSION = 4;
  
  //  static final int DATABASE_VERSION = 1;
    static final String TAG = "DBAdapter";

    static final String DB_CREATE_TABLA_ALUMNO = "create table alumno (idAlumno integer primary key, Nombre text not null);";
    static final String DB_CREATE_TABLA_CURSO = "create table curso (idCurso integer primary key, Nombre text not null);";
    static final String DB_CREATE_TABLA_GRUPO = "create table grupo (idGrupo integer primary key, Nombre text not null);";	
    static final String DB_CREATE_TABLA_HORARIO = "create table horario (idHorario integer primary key,horaInicio text , horaFin text , dia text not null);";
    static final String DB_CREATE_TABLA_MATRICULA ="create table matricula (idMatricula integer primary key, idGrupoCurso integer not null, idAlumno integer not null, horasAcademicas integer not null);";
    static final String DB_CREATE_TABLA_GRUPOCURSO ="create table grupoCurso (idGrupoCurso integer primary key, idCurso integer not null, idGrupo integer not null, idHorario integer not null, Aula text not null, idProfesor integer not null);";
    static final String DB_CREATE_TABLA_ASISTENCIA ="create table asistencia (idAsistencia integer primary key, idProfesor integer not null, idCurso integer not null, idAlumno integer not null, idGrupo integer not null, fecha text not null,tipoAsistencia text not null, flgEnServidor text not null);";
    
  
        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DB_CREATE_TABLA_ALUMNO);
                db.execSQL(DB_CREATE_TABLA_CURSO);
                db.execSQL(DB_CREATE_TABLA_GRUPO);
                db.execSQL(DB_CREATE_TABLA_HORARIO);
                db.execSQL(DB_CREATE_TABLA_MATRICULA);
                db.execSQL(DB_CREATE_TABLA_GRUPOCURSO);
                db.execSQL(DB_CREATE_TABLA_ASISTENCIA);
                  
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS alumno");
            db.execSQL("DROP TABLE IF EXISTS curso");
            db.execSQL("DROP TABLE IF EXISTS grupo");
            db.execSQL("DROP TABLE IF EXISTS grupoCurso");
            db.execSQL("DROP TABLE IF EXISTS horario");
            db.execSQL("DROP TABLE IF EXISTS matricula");
            db.execSQL("DROP TABLE IF EXISTS asistencia");
            onCreate(db);
        }

        
        
        
}
