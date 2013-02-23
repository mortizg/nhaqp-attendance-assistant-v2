package com.nhaqp.attendance.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Alumno implements KvmSerializable {
	
	public int idAlumno;
	public String Nombre;
	
	public Alumno(){}
	
	public Alumno(int idAlumno, String nombre){
		
		this.idAlumno= idAlumno;
		this.Nombre=nombre;
		
	}
	
	
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		
        switch(arg0)
        {
        case 0:
            return idAlumno;
        case 1:
            return Nombre;
        }
        
		return null;
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idAlumno";
            break;
        case 1:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Nombre";
            break;
        default:break;
        }
	}

	public void setProperty(int index, Object value) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            idAlumno = Integer.parseInt(value.toString());
            break;
        case 1:
            Nombre = value.toString();
            break;

        default:
            break;
        }
	}

    
}