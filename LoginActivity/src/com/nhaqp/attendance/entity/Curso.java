package com.nhaqp.attendance.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Curso implements KvmSerializable {
	
	public int idCurso;
	public String Nombre;
	
	public Curso(){}
	
	public Curso(int idCurso, String nombre){
		
		this.idCurso= idCurso;
		this.Nombre=nombre;
		
	}
	
	
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		
        switch(arg0)
        {
        case 0:
            return idCurso;
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
            info.name = "idCurso";
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
            idCurso = Integer.parseInt(value.toString());
            break;
        case 1:
            Nombre = value.toString();
            break;

        default:
            break;
        }
	}

    
}