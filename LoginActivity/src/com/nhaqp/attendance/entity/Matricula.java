package com.nhaqp.attendance.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Matricula implements KvmSerializable {
	public int idMatricula;
	public int idGrupoCurso;
	public int idAlumno;
	public int horasAcademicas;
	
	public Matricula(){}
	
	public Matricula(int idMatricula, int idGrupoCurso, int idAlumno, int horasAcademicas){
		this.idMatricula=idMatricula;
		this.idGrupoCurso= idGrupoCurso;
		this.idAlumno=idAlumno;
		this.horasAcademicas=horasAcademicas;
		
	}
	
	
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		
        switch(arg0)
        {
        case 0:
            return idMatricula;
        case 1:
            return idGrupoCurso;
        case 2:
        	return idAlumno;
        case 3:
        	return horasAcademicas;
        
        }
        
		return null;
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idMatricula";
            break;
        case 1:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idGrupoCurso";
            break;
        case 2:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idAlumno";
            break;
        case 3:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "horasAcademicas";
            break;
        default:break;
        }
	}

	public void setProperty(int index, Object value) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            idMatricula = Integer.parseInt(value.toString());
            break;
        case 1:
            idGrupoCurso = Integer.parseInt(value.toString());
            break;
        case 2:
            idAlumno = Integer.parseInt(value.toString());
            break;
        case 3:
            horasAcademicas = Integer.parseInt(value.toString());
            break;

        default:
            break;
        }
	}

    
}