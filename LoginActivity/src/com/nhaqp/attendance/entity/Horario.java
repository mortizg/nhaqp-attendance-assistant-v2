package com.nhaqp.attendance.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Horario implements KvmSerializable {
	
	public int idHorario;
	public String horaInicio;
	public String horaFin;
	public String dia;
	
	public Horario(){}
	
	public Horario(int idHorario, String horaInicio,String horaFin, String dia){
		
		this.idHorario= idHorario;
		this.horaInicio=horaInicio;
		this.horaFin=horaFin;
		this.dia=dia;
		
	}
	
	
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		
        switch(arg0)
        {
        case 0:
            return idHorario;
        case 1:
            return horaInicio;
        case 2:
        	return horaFin;
        case 3:
        	return dia;
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
            info.name = "idHorario";
            break;
        case 1:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "horaInicio";
            break;
        case 2:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "horaFin";
            break;
        case 3:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "dia";
            break;
            
        default:break;
        }
	}

	public void setProperty(int index, Object value) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            idHorario = Integer.parseInt(value.toString());
            break;
        case 1:
            horaInicio = value.toString();
            break;
        case 2:
            horaFin = value.toString();
            break;
        case 3:
            dia = value.toString();
            break;

        default:
            break;
        }
	}

    
}