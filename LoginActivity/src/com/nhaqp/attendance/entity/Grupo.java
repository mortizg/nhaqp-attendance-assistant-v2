package com.nhaqp.attendance.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Grupo implements KvmSerializable {
	
	public int idGrupo;
	public String nombre;
	
	public Grupo(){}
	
	public Grupo(int idGrupo, String nombre){
		
		this.idGrupo= idGrupo;
		this.nombre=nombre;
		
	}
	
	
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		
        switch(arg0)
        {
        case 0:
            return idGrupo;
        case 1:
            return nombre;
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
            info.name = "idGrupo";
            break;
        case 1:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "nombre";
            break;
        default:break;
        }
	}

	public void setProperty(int index, Object value) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            idGrupo = Integer.parseInt(value.toString());
            break;
        case 1:
            nombre = value.toString();
            break;

        default:
            break;
        }
	}

    
}