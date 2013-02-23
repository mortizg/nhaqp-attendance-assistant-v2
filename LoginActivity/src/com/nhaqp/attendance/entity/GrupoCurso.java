package com.nhaqp.attendance.entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class GrupoCurso implements KvmSerializable {
	
	public int idGrupoCurso;
	public int idCurso;
	public int idGrupo;
	public int idHorario;
	public String Aula;
	public int idProfesor;
	
	public GrupoCurso(){}
	
	public GrupoCurso(int idGrupoCurso, int idCurso, int idGrupo,int idHorario, String aula, int idProfesor){
		
		this.idGrupoCurso= idGrupoCurso;
		this.idCurso=idCurso;
		this.idGrupo=idGrupo;
		this.idHorario=idHorario;
		this.Aula=aula;
		this.idProfesor=idProfesor;
		
	}
	
	
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		
        switch(arg0)
        {
        case 0:
            return idGrupoCurso;
        case 1:
            return idCurso;
        case 2:
        	return idGrupo;
        case 3:
        	return idHorario;
        case 4:
        	return Aula;
        case 5:
        	return idProfesor;
        }
        
		return null;
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idGrupoCurso";
            break;
        case 1:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idCurso";
            break;
        case 2:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idGrupo";
            break;
        case 3:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "idHorario";
            break;
        case 4:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Aula";
            break;
        case 5:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "idProfesor";
            break;
            
        default:break;
        }
	}

	public void setProperty(int index, Object value) {
		// TODO Auto-generated method stub
        switch(index)
        {
        case 0:
            idGrupoCurso = Integer.parseInt(value.toString());
            break;
        case 1:
            idCurso =Integer.parseInt(value.toString());
            break;
        case 2:
            idGrupo = Integer.parseInt(value.toString());
            break;
        case 3:
            idHorario = Integer.parseInt(value.toString());
            break;
            
        case 4:
            Aula = value.toString();
            break;
        case 5:
            idProfesor = Integer.parseInt(value.toString());
            break;

        default:
            break;
        }
	}

    
}