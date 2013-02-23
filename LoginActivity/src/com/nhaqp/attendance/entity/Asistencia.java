package com.nhaqp.attendance.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Asistencia implements KvmSerializable {
	
	public int idAsistencia;
	public int idProfesor;
	public int idCurso;
	public int idAlumno;
	public int idGrupo;
	public String fecha;
	public String tipoAsistencia;
	
	public Asistencia(){}
	
	public Asistencia(int idAsistencia, int idProfesor, int idCurso, int idAlumno,int idGrupo, String fecha, String tipoAsistencia){
		
		this.idAsistencia=idAsistencia;
		this.idProfesor=idProfesor;
		this.idCurso=idCurso;
		this.idAlumno=idAlumno;
		this.idGrupo=idGrupo;
		this.fecha=fecha;
		this.tipoAsistencia=tipoAsistencia;
		
	}
	
	
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		
        switch(arg0)
        {
        case 0:
            return idAsistencia;
        case 1:
        	return idProfesor;
        case 2:
            return idCurso;
        case 3:
        	return idAlumno;
        case 4:
            return idGrupo;
        case 5:
        	return fecha;
        case 6:
        	return tipoAsistencia;
        }
        
		return null;
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idAsistencia";
            break;
        case 1:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idProfesor";
            break;
         case 2:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idCurso";
            break;
        case 3:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idAlumno";
            break;
        case 4:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idGrupo";
            break;
        case 5:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "fecha";
            break;
        case 6:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "tipoAsistencia";
            break;
            
        default:break;
        }
	}

	public void setProperty(int index, Object value) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            idAsistencia = Integer.parseInt(value.toString());
            break;
        case 1:
            idProfesor =Integer.parseInt(value.toString());
            break;

        case 2:
            idCurso = Integer.parseInt(value.toString());
            break;
        case 3:
            idAlumno =Integer.parseInt(value.toString());
            break;
        case 4:
            idGrupo = Integer.parseInt(value.toString());
            break;
        case 5:
            fecha = value.toString();
            break;
            
        case 6:
            tipoAsistencia = value.toString();
            break;

        default:
            break;
        }
	}

    
}