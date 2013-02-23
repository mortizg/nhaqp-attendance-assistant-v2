package com.nhaqp.attendance.control;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.nhaqp.attendance.entity.Alumno;
import com.nhaqp.attendance.entity.Asistencia;
import com.nhaqp.attendance.entity.Curso;
import com.nhaqp.attendance.entity.Grupo;
import com.nhaqp.attendance.entity.GrupoCurso;
import com.nhaqp.attendance.entity.Horario;
import com.nhaqp.attendance.entity.Matricula;


public class CargarDatosWS {
	
	
	Alumno[] allAlumnos;
	Curso[] allCursos;
	Grupo[] allGrupos;
	GrupoCurso[] allGrupoCursos;
	Matricula[] allMatriculas;
	Horario[] allHorarios;
	Asistencia[] allAsistencias;
	
	int idProfesor;
	
	public CargarDatosWS(int idProfesor)
	{
		allAlumnos=null;
		allCursos=null;
		allGrupos=null;
		allGrupoCursos=null;
		allMatriculas=null;
		allHorarios=null;
		allAsistencias=null;
		this.idProfesor=idProfesor;
	
	}
	
	
	
	
	
	public Alumno[] syncAlumnos(){
		final String NAMESPACE = "http://localhost/";
		final String METHOD_NAME = "sincroAlumnos";
		final String SOAP_ACTION = "http://localhost/sincroAlumnos";
		final String URL="http://10.0.2.2:8090/Servicios.asmx";
		
	
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
		request.addProperty("idProfesor", idProfesor); 
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		
		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			transporte.call(SOAP_ACTION, envelope);

			SoapObject resSoap =(SoapObject)envelope.getResponse();
			
			allAlumnos = RetrieveFromSoap(resSoap);
			
		} 
		catch (Exception e) 
		{
			//txtResultado.setText("Error!");
		}
		return allAlumnos; 
		
		
	}
	
	
	public Curso[] syncCursos(){
		final String NAMESPACE = "http://localhost/";
		final String METHOD_NAME = "sincroCurso";
		final String SOAP_ACTION = "http://localhost/sincroCurso";
		final String URL="http://10.0.2.2:8090/Servicios.asmx";

		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
		request.addProperty("idProfesor", idProfesor); 
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		
		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			transporte.call(SOAP_ACTION, envelope);

			SoapObject resSoap =(SoapObject)envelope.getResponse();
			
			allCursos = RetrieveFromSoap2(resSoap);

		} 
		catch (Exception e) 
		{
			//txtResultado.setText("Error!");
		} 
		
		return allCursos;
		
	}

	public Grupo[] syncGrupos(){
		final String NAMESPACE = "http://localhost/";
		final String METHOD_NAME = "sincroGrupo";
		final String SOAP_ACTION = "http://localhost/sincroGrupo";
		final String URL="http://10.0.2.2:8090/Servicios.asmx";

		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
		request.addProperty("idProfesor", idProfesor); 
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		
		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			transporte.call(SOAP_ACTION, envelope);

			SoapObject resSoap =(SoapObject)envelope.getResponse();
			
			allGrupos = RetrieveFromSoap3(resSoap);

		} 
		catch (Exception e) 
		{
			//txtResultado.setText("Error!");
		} 
		
		return allGrupos;
		
	}

	
	public Horario[] syncHorarios(){
		final String NAMESPACE = "http://localhost/";
		final String METHOD_NAME = "sincroHorario";
		final String SOAP_ACTION = "http://localhost/sincroHorario";
		final String URL="http://10.0.2.2:8090/Servicios.asmx";

		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
		request.addProperty("idProfesor", idProfesor); 
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		
		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			transporte.call(SOAP_ACTION, envelope);

			SoapObject resSoap =(SoapObject)envelope.getResponse();
			
			allHorarios = RetrieveFromSoap4(resSoap);

		} 
		catch (Exception e) 
		{
			//txtResultado.setText("Error!");
		} 
		
		return allHorarios;
		
	}
	
	public GrupoCurso[] syncGrupoCursos(){
		final String NAMESPACE = "http://localhost/";
		final String METHOD_NAME = "sincroGrupoCurso";
		final String SOAP_ACTION = "http://localhost/sincroGrupoCurso";
		final String URL="http://10.0.2.2:8090/Servicios.asmx";

		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
		request.addProperty("idProfesor", idProfesor); 
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		
		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			transporte.call(SOAP_ACTION, envelope);

			SoapObject resSoap =(SoapObject)envelope.getResponse();
			
			allGrupoCursos = RetrieveFromSoap5(resSoap);

		} 
		catch (Exception e) 
		{
			//txtResultado.setText("Error!");
		} 
		
		return allGrupoCursos;
		
	}
	
	public Matricula[] syncMatriculas(){
		final String NAMESPACE = "http://localhost/";
		final String METHOD_NAME = "sincroMatriculas";
		final String SOAP_ACTION = "http://localhost/sincroMatriculas";
		final String URL="http://10.0.2.2:8090/Servicios.asmx";

		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
		request.addProperty("idProfesor", idProfesor); 
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		
		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			transporte.call(SOAP_ACTION, envelope);

			SoapObject resSoap =(SoapObject)envelope.getResponse();
			
			allMatriculas = RetrieveFromSoap6(resSoap);

		} 
		catch (Exception e) 
		{
			//txtResultado.setText("Error!");
		} 
		
		return allMatriculas;
		
	}
	
	public Asistencia[] syncAsistencias(){
		final String NAMESPACE = "http://localhost/";
		final String METHOD_NAME = "sincroAsistencia";
		final String SOAP_ACTION = "http://localhost/sincroAsistencia";
		final String URL="http://10.0.2.2:8090/Servicios.asmx";

		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
		request.addProperty("idProfesor", idProfesor); 
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		
		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			transporte.call(SOAP_ACTION, envelope);

			SoapObject resSoap =(SoapObject)envelope.getResponse();
			
			allAsistencias = RetrieveFromSoap7(resSoap);

		} 
		catch (Exception e) 
		{
			//txtResultado.setText("Error!");
		} 
		
		return allAsistencias;
		
	}
	
    //Metodo Helper para trabajar con array de objetos de tipo Alumno
    public static Alumno[] RetrieveFromSoap(SoapObject soap)
    {
        Alumno[] listaAlumnos = new Alumno[soap.getPropertyCount()];
        for (int i = 0; i < listaAlumnos.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Alumno alum = new Alumno();
            alum.idAlumno = Integer.parseInt(pii.getProperty(0).toString());
            alum.Nombre = pii.getProperty(1).toString();
            listaAlumnos[i] = alum;
        }
        return listaAlumnos;
    }

    
    //Metodo Helper para trabajar con array de objetos de tipo Curso
    public static Curso[] RetrieveFromSoap2(SoapObject soap)
    {
        Curso[] listaCursos = new Curso[soap.getPropertyCount()];
        for (int i = 0; i < listaCursos.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Curso cur = new Curso();
            cur.idCurso = Integer.parseInt(pii.getProperty(0).toString());
            cur.Nombre = pii.getProperty(1).toString();
            listaCursos[i] = cur;
        }
        return listaCursos;
    }

    
    //Metodo Helper para trabajar con array de objetos de tipo Grupo
    public static Grupo[] RetrieveFromSoap3(SoapObject soap)
    {
        Grupo[] listaGrupos = new Grupo[soap.getPropertyCount()];
        for (int i = 0; i < listaGrupos.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Grupo gru = new Grupo();
            gru.idGrupo = Integer.parseInt(pii.getProperty(0).toString());
            gru.nombre = pii.getProperty(1).toString();
            listaGrupos[i] = gru;
        }
        return listaGrupos;
    }

  //Metodo Helper para trabajar con array de objetos de tipo Horario
    public static Horario[] RetrieveFromSoap4(SoapObject soap)
    {
        Horario[] listaHorarios = new Horario[soap.getPropertyCount()];
        for (int i = 0; i < listaHorarios.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Horario hor = new Horario();
            hor.idHorario = Integer.parseInt(pii.getProperty(0).toString());
            hor.horaInicio = pii.getProperty(1).toString();
            hor.horaFin = pii.getProperty(2).toString();
            hor.dia = pii.getProperty(3).toString();
            
            listaHorarios[i] = hor;
        }
        return listaHorarios;
    }
   
    //Metodo Helper para trabajar con array de objetos de tipo GrupoCurso
    public static GrupoCurso[] RetrieveFromSoap5(SoapObject soap)
    {
        GrupoCurso[] listaGrupoCursos = new GrupoCurso[soap.getPropertyCount()];
        for (int i = 0; i < listaGrupoCursos.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            GrupoCurso gcu = new GrupoCurso();
            gcu.idGrupoCurso = Integer.parseInt(pii.getProperty(0).toString());
            gcu.idCurso = Integer.parseInt(pii.getProperty(1).toString());
            gcu.idGrupo = Integer.parseInt(pii.getProperty(2).toString());
            gcu.idHorario = Integer.parseInt(pii.getProperty(3).toString());
            gcu.Aula = pii.getProperty(4).toString();
            gcu.idProfesor = Integer.parseInt(pii.getProperty(5).toString());
            
            listaGrupoCursos[i] = gcu;
        }
        return listaGrupoCursos;
    }
    
    //Metodo Helper para trabajar con array de objetos de tipo Matricula
    public static Matricula[] RetrieveFromSoap6(SoapObject soap)
    {
        Matricula[] listaMatriculas = new Matricula[soap.getPropertyCount()];
        for (int i = 0; i < listaMatriculas.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Matricula mat = new Matricula();
            mat.idMatricula = Integer.parseInt(pii.getProperty(0).toString());
            mat.idGrupoCurso = Integer.parseInt(pii.getProperty(1).toString());
            mat.idAlumno = Integer.parseInt(pii.getProperty(2).toString());
            mat.horasAcademicas = Integer.parseInt(pii.getProperty(3).toString());
      
            
            listaMatriculas[i] = mat;
        }
        return listaMatriculas;
    }
    
    //Metodo Helper para trabajar con array de objetos de tipo Asistencia
    public static Asistencia[] RetrieveFromSoap7(SoapObject soap)
    {
        Asistencia[] listaMatriculas = new Asistencia[soap.getPropertyCount()];
        for (int i = 0; i < listaMatriculas.length; i++) {
            SoapObject pii = (SoapObject)soap.getProperty(i);
            Asistencia mat = new Asistencia();
            mat.idAsistencia = Integer.parseInt(pii.getProperty(0).toString());
            mat.idProfesor = Integer.parseInt(pii.getProperty(1).toString());
            mat.idCurso = Integer.parseInt(pii.getProperty(2).toString());
            mat.idAlumno = Integer.parseInt(pii.getProperty(3).toString());
            mat.idGrupo = Integer.parseInt(pii.getProperty(4).toString());
            mat.fecha = pii.getProperty(5).toString();
            mat.tipoAsistencia = pii.getProperty(6).toString();
            
            /*  gcu.idProfesor = Integer.parseInt(pii.getProperty(5).toString());*/
            
            listaMatriculas[i] = mat;
        }
        return listaMatriculas;
    }
    
}
	

