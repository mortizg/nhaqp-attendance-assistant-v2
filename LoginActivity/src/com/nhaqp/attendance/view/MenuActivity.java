package com.nhaqp.attendance.view;

import java.util.ArrayList;

import com.nhaqp.attendance.R;
import com.nhaqp.attendance.control.CargarDatosWS;
import com.nhaqp.attendance.db.DBBorrarTablas;
import com.nhaqp.attendance.db.DBHelper;
import com.nhaqp.attendance.db.DBLlenarTablas;
import com.nhaqp.attendance.entity.Alumno;
import com.nhaqp.attendance.entity.Asistencia;
import com.nhaqp.attendance.entity.Curso;
import com.nhaqp.attendance.entity.Grupo;
import com.nhaqp.attendance.entity.GrupoCurso;
import com.nhaqp.attendance.entity.Horario;
import com.nhaqp.attendance.entity.Matricula;
import com.nhaqp.attendance.view.listmenu.ListMenu;
import com.nhaqp.attendance.view.listmenu.ListMenuButton;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity  extends Activity {
	
	public ProgressDialog pd;
	public Button boton1;
	public Alumno[] listAlumnos=null;
	public Curso[] listCursos=null;
	public Grupo[] listGrupos=null;
	public Horario[] listHorarios=null;
	public GrupoCurso[] listGrupoCursos=null;
	public Matricula[] listMatriculas=null;
	public Asistencia[] listAsistencias=null;
	public Context context;
	public static int DATABASE_VERSION = 2;
	public DBHelper dbh=null;
	public String idProfesor;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idProfesor=getIntent().getStringExtra("idProfesor");
        ArrayList<ListMenuButton> botones = new ArrayList<ListMenuButton>();
        context=this;
        dbh=new DBHelper(this);
        ListMenuButton asistenciaButton = new ListMenuButton(
				this, "Asistencia", "Registrar asistencia de los alumnos",
				R.drawable.navigation_accept, RegistroAsistenciaActivity.class);
        
        ListMenuButton consultaButton = new ListMenuButton(
				this, "Consultas", "Consultas de cursos y alumnos",
				R.drawable.collections_view_as_list, ConsultasCursoActivity.class);        
        
        ListMenuButton sincronizarButton = new ListMenuButton(
				this, "Sincronizar", "Sincronizar datos",
				R.drawable.av_download, new OnClickListener(){

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						new SynWebservice().execute("");
						pd = ProgressDialog.show(context, "Sincronizando","Cargando datos", true, false);
					}} );
        
		
		botones.add(asistenciaButton);
		botones.add(consultaButton);
		botones.add(sincronizarButton);
		
        ListMenu menu =  new ListMenu(this, botones);
        setContentView(menu);
        dbh.close();
        
	}
	
	
	//Tarea en Background
		public class SynWebservice extends AsyncTask<String, Void, Object> {
			protected Integer doInBackground(String... args) {
				Log.v("ASIS", "SynWebservice");
				
				CargarDatosWS ws=new CargarDatosWS(Integer.parseInt(idProfesor));
				//Se invoca nuestro metodo
				listAlumnos=ws.syncAlumnos();
				listCursos=ws.syncCursos();
				listGrupos=ws.syncGrupos();
				listHorarios=ws.syncHorarios();
				listGrupoCursos=ws.syncGrupoCursos();
				listMatriculas=ws.syncMatriculas();
				listAsistencias=ws.syncAsistencias();
				
				//para probar sin server
				//generarDatosPrueba();
				
				DBLlenarTablas dbt= new DBLlenarTablas(MenuActivity.this);
				Log.v("ASIS", "LUEGO DE LLENAR TABLAS");
				dbt.TruncarTablas();
				Log.v("ASIS", "LUEGO DE TRUNCAR TABLAS");
				dbt.LlenarTblAlumno(listAlumnos);
				dbt.LLenarTblCurso(listCursos);
				dbt.LLenarTblGrupo(listGrupos);
				dbt.LLenarTblHorario(listHorarios);
				dbt.LLenarTblGrupoCurso(listGrupoCursos);
				dbt.LLenarTblMatricula(listMatriculas);
				dbt.LLenarTblAsistencia(listAsistencias);
				
				Log.v("ASIS", "LUEGO DE INSERTAR DATOS");
				return 1;
			}

			protected void onPostExecute(Object result) {
				//Se elimina la pantalla de por favor espere.
				pd.dismiss();
				//Se muestra mensaje con la respuesta del servicio web
		//		Toast.makeText(context,"DAto: "+ listAlumnos[0].Nombre +","+ listAlumnos[1].Nombre + "cursos:" + listCursos[0].Nombre+","+listCursos[1].Nombre,Toast.LENGTH_LONG).show();
				Toast.makeText(context,"Sincronización exitosa ",Toast.LENGTH_LONG).show();
				Intent consultascurso = new Intent().setClass(MenuActivity.this, MenuActivity.class);
				startActivity(consultascurso);

				super.onPostExecute(result);
				
			}
		}
		
		private void generarDatosPrueba(){
			
			listAlumnos = new Alumno[4];
			Alumno tmp = new Alumno();
			tmp.idAlumno = 1;
			tmp.Nombre = "alumno1";
			listAlumnos[0] = tmp;
			
			tmp = new Alumno();
			tmp.idAlumno = 2;
			tmp.Nombre = "alumno2";
			listAlumnos[1] = tmp;
			
			tmp = new Alumno();
			tmp.idAlumno = 3;
			tmp.Nombre = "alumno3";
			listAlumnos[2] = tmp;
			
			tmp = new Alumno();
			tmp.idAlumno = 4;
			tmp.Nombre = "alumno4";
			listAlumnos[3] = tmp;
			
			listCursos = new Curso[1];
			Curso tmpC = new Curso();
			tmpC.idCurso = 1;
			tmpC.Nombre = "Matematica";
			listCursos[0] = tmpC;
			
			listGrupos = new Grupo[1];
			Grupo tmpG = new Grupo();
			tmpG.idGrupo = 1;
			tmpG.nombre = "Grupo 1";
			listGrupos[0] = tmpG;
			
			listHorarios = new Horario[1];
			Horario tmpH= new Horario();
			tmpH.idHorario =1;
			tmpH.dia = "4";
			tmpH.horaInicio ="21:00";
			tmpH.horaFin = "24:00";
			listHorarios[0] = tmpH;
			
			listGrupoCursos= new GrupoCurso[1];
			GrupoCurso tmpGC = new GrupoCurso();
			tmpGC.idGrupoCurso = 1;
			tmpGC.idCurso = 1;
			tmpGC.idGrupo = 1;
			tmpGC.idHorario = 1;
			tmpGC.idProfesor = 1;
			tmpGC.Aula = "aula 203";
			listGrupoCursos[0] = tmpGC;
			
			listMatriculas = new Matricula[4];
			Matricula tmpM = new Matricula();
			tmpM.idMatricula = 1;
			tmpM.idAlumno = 1;
			tmpM.idGrupoCurso = 1;
			tmpM.horasAcademicas = 20;
			listMatriculas[0] = tmpM;
			
			tmpM = new Matricula();
			tmpM.idMatricula = 2;
			tmpM.idAlumno = 2;
			tmpM.idGrupoCurso = 1;
			tmpM.horasAcademicas = 20;
			listMatriculas[1] = tmpM;
			
			tmpM = new Matricula();
			tmpM.idMatricula = 3;
			tmpM.idAlumno = 3;
			tmpM.idGrupoCurso = 1;
			tmpM.horasAcademicas = 20;
			listMatriculas[2] = tmpM;
			
			tmpM = new Matricula();
			tmpM.idMatricula = 4;
			tmpM.idAlumno = 4;
			tmpM.idGrupoCurso = 1;
			tmpM.horasAcademicas = 20;
			listMatriculas[3] = tmpM;
			
			listAsistencias = new Asistencia[0];
			
			
		}
		
}
