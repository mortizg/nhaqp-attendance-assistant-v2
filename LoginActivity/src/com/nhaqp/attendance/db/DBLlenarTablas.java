package com.nhaqp.attendance.db;

import com.nhaqp.attendance.entity.Alumno;
import com.nhaqp.attendance.entity.Asistencia;
import com.nhaqp.attendance.entity.Curso;
import com.nhaqp.attendance.entity.Grupo;
import com.nhaqp.attendance.entity.GrupoCurso;
import com.nhaqp.attendance.entity.Horario;
import com.nhaqp.attendance.entity.Matricula;

import android.content.Context;
import android.util.Log;

public class DBLlenarTablas {
	
	
	DBAlumno dbAlumno;
	DBCurso dbCurso;
	DBGrupo dbGrupo;
	DBHorario dbHorario;
	DBGrupoCurso dbGrupoCurso;
	DBMatricula dbMatricula;
	DBAsistencia dbAsistencia;
	
	public DBLlenarTablas(Context ctx)
	{
		//Context ctx;
		dbAlumno = new DBAlumno(ctx);
		dbCurso = new DBCurso(ctx);
		dbGrupo = new DBGrupo(ctx);
		dbHorario = new DBHorario(ctx);
		dbGrupoCurso = new DBGrupoCurso(ctx);
		dbMatricula = new DBMatricula(ctx);
		dbAsistencia = new DBAsistencia(ctx);
		
	}
	
	public void TruncarTablas(){
		try{
			
			dbAlumno.open();
			dbAlumno.truncateAlumno();
			dbAlumno.close();
			
			
			dbCurso.open();
			dbCurso.truncateCurso();
			dbCurso.close();
			
			
			dbGrupo.open();
			dbGrupo.truncateGrupo();
			dbGrupo.close();
			
			
			dbHorario.open();
			dbHorario.truncateHorario();
			dbHorario.close();
			
			
			dbGrupoCurso.open();
			dbGrupoCurso.truncateGrupoCurso();
			dbGrupoCurso.close();
			
			
			dbMatricula.open();
			dbMatricula.truncateMatricula();
			dbMatricula.close();
			
			
			dbAsistencia.open();
			dbAsistencia.truncateAsistencia();
			dbAsistencia.close();
		}catch(Exception ex){
			Log.v("ASIS",ex.getMessage());
		}
	}
	
	public void LlenarTblAlumno(Alumno[] alumno)
	{
		dbAlumno.open();        
        long id = -1;
 
        for(int i=0; i<alumno.length; i++)
        {
        	id = dbAlumno.insertAlumno(alumno[i].idAlumno, alumno[i].Nombre);
        }
        
        dbAlumno.close();
        
		
	}
	
	public void LLenarTblCurso(Curso[] curso)
	{
		dbCurso.open();
		long id= -1;
		for(int i=0; i<curso.length; i++)
        {
        	id = dbCurso.insertCurso(curso[i].idCurso, curso[i].Nombre);
        }
        
		dbCurso.close();
		
	}
	
	public void LLenarTblGrupo(Grupo[] grupo)
	{

		dbGrupo.open();
		long id= -1;
		for(int i=0; i<grupo.length; i++)
        {
        	id = dbGrupo.insertGrupo(grupo[i].idGrupo, grupo[i].nombre);
        }
        
		dbGrupo.close();

	}
	

	public void LLenarTblHorario(Horario[] horario)
	{

		dbHorario.open();
		long id= -1;
		for(int i=0; i<horario.length; i++)
        {
        	id = dbHorario.insertHorario(horario[i].idHorario, horario[i].horaInicio, horario[i].horaFin, horario[i].dia);
        }
        
		dbHorario.close();

	}

	
	public void LLenarTblGrupoCurso(GrupoCurso[] grupoCurso)
	{

		dbGrupoCurso.open();
		long id= -1;
		for(int i=0; i<grupoCurso.length; i++)
        {
        	id = dbGrupoCurso.insertGrupoCurso(grupoCurso[i].idGrupoCurso, grupoCurso[i].idCurso, grupoCurso[i].idGrupo, grupoCurso[i].idHorario,grupoCurso[i].Aula, grupoCurso[i].idProfesor );
        }
        
		dbGrupoCurso.close();

	}
	
	public void LLenarTblMatricula(Matricula[] matricula)
	{

		dbMatricula.open();
		long id= -1;
		for(int i=0; i<matricula.length; i++)
        {
        	id = dbMatricula.insertMatricula(matricula[i].idMatricula, matricula[i].idGrupoCurso, matricula[i].idAlumno, matricula[i].horasAcademicas);
        }
        
		dbMatricula.close();

	}
	
	
	public void LLenarTblAsistencia(Asistencia[] asistencia)
	{

		dbAsistencia.open();
		long id= -1;
		for(int i=0; i<asistencia.length; i++)
        {
        	id = dbAsistencia.insertAsistencia(asistencia[i].idAsistencia, asistencia[i].idProfesor, asistencia[i].idCurso, asistencia[i].idAlumno, asistencia[i].idGrupo, asistencia[i].fecha , asistencia[i].tipoAsistencia, "S");
        }
        
		dbAsistencia.close();

	}
	
	
	
}