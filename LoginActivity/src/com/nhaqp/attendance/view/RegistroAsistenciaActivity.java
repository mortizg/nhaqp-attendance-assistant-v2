package com.nhaqp.attendance.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.nhaqp.attendance.R;
import com.nhaqp.attendance.db.DBAsistencia;
import com.nhaqp.attendance.db.DBHelper;
import com.nhaqp.attendance.entity.AlumnoAsiste;
import com.nhaqp.attendance.entity.Asistencia;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class RegistroAsistenciaActivity extends ListActivity {

	private ListView mainListView = null;
	final String SETTING_TODOLIST = "todolist";
	private String[] listAsistencia ;
	private DBHelper dbh=null;
	private Cursor listAlumnosCursor=null;
	private Cursor listGeneralesCursor=null;
	
	private AlumnoAsiste[] listaAlumnosAsisten = null;
	private int idCurso, idProfesor, idGrupo, idMaxIdAsis;
	private String Curso=""; 
	private boolean estaEnHorario = true;
	
	//private ArrayList<String> selectedItems = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asistencia_layout);
		
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		
		String dia = Integer.toString(c.get(Calendar.DAY_OF_WEEK)-1);
		String hora= Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		String minutos= Integer.toString(c.get(Calendar.MINUTE));
		String horaactual=hora+":"+minutos;		
				
		Log.v("SAV", "cadena: "+df1.format(c.getTime()) + "  " + horaactual + "  "+dia);
		//getDatosDB("21-02-2013","21:25","4");
		getDatosDB(df1.format(c.getTime()), df2.format(c.getTime()), dia);
		
		TextView tituloCurso = (TextView)findViewById(R.id.txtCurso);
		tituloCurso.setText(Curso);
		
        this.mainListView = getListView();

		mainListView.setCacheColorHint(0);

		// Bind the data with the list
		mainListView.setAdapter(new ArrayAdapter<String>(RegistroAsistenciaActivity.this,
				android.R.layout.simple_list_item_multiple_choice, listAsistencia));

		mainListView.setItemsCanFocus(false);
		mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		LoadSelections();
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.asistencia_menu, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
		Log.v("ASIS","estaEnHorario: "+estaEnHorario);

	    if (!estaEnHorario){
	        menu.getItem(0).setEnabled(false);
	        menu.getItem(1).setEnabled(false);
	        menu.getItem(2).setEnabled(false);
	        
	    }else{
	    
		    if(FaltanGuardar()){
		    	menu.getItem(1).setEnabled(false);
		    }
		    if(!FaltanEnviar()){
		    	menu.getItem(0).setEnabled(false);
		    	menu.getItem(1).setEnabled(false);
		    	menu.getItem(2).setEnabled(false);
		    }
	    }
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
		switch (item.getItemId()) {
			case R.id.btnGuardar:
				SaveSelections();
				break;
			case R.id.btnEnviar:
				Send();
				break;
			case R.id.btnLimpiar:
				ClearSelections();
				break;
			default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}


	@Override
	protected void onPause() {
		// always handle the onPause to make sure selections are saved if user clicks back button
	
		super.onPause();
	}
	
	private boolean FaltanEnviar(){
		
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		
		String dia = Integer.toString(c.get(Calendar.DAY_OF_WEEK)-1);				
		
		String fecha = df1.format(c.getTime());
		String hora = df2.format(c.getTime());

		Log.v("ASIS","fecha: "+fecha+" - hora: "+hora+" - dia: "+dia);
		dbh= new DBHelper(this);
		int faltantesEnvio = 0;
		
        Cursor tmpCursor= dbh.getReadableDatabase().rawQuery("select COUNT(asi.idAsistencia), 'totalNoEnviados' from asistencia asi "+
        													 "inner join matricula m on asi.idAlumno = m.idAlumno "+ 
        													 "inner join grupoCurso gc on m.idGrupoCurso = gc.idGrupoCurso "+  
        													 "inner join horario h on gc.idHorario = h.idHorario "+   
        													 "and asi.idCurso = gc.idCurso and asi.idGrupo = gc.idGrupo "+ 
        													 "and substr(asi.fecha,1,10) = '"+fecha+"' "+ 
        													 "where time('"+hora+"') between time(h.horaInicio) and time(h.horaFin) "+  
        													 "and h.dia ="+dia+" and flgEnServidor = 'N'", null);
        tmpCursor.moveToFirst();
        faltantesEnvio = tmpCursor.getInt(0);
        tmpCursor.close();
        
        dbh.close();
        Log.v("ASIS","faltantesEnvio:"+faltantesEnvio);
        return faltantesEnvio>0;
	}
	
	private boolean FaltanGuardar(){
		
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		
		String dia = Integer.toString(c.get(Calendar.DAY_OF_WEEK)-1);				
		
		String fecha = df1.format(c.getTime());
		String hora = df2.format(c.getTime());

		Log.v("ASIS","fecha: "+fecha+" - hora: "+hora+" - dia: "+dia);
		dbh= new DBHelper(this);
		
		int totalAsistencia = 0;
		
        Cursor tmpCursor= dbh.getReadableDatabase().rawQuery("select COUNT(asi.idAsistencia), 'totalAsistencia' from asistencia asi "+
        													 "inner join matricula m on asi.idAlumno = m.idAlumno "+ 
        													 "inner join grupoCurso gc on m.idGrupoCurso = gc.idGrupoCurso "+  
        													 "inner join horario h on gc.idHorario = h.idHorario "+   
        													 "and asi.idCurso = gc.idCurso and asi.idGrupo = gc.idGrupo "+ 
        													 "and substr(asi.fecha,1,10) = '"+fecha+"' "+ 
        													 "where time('"+hora+"') between time(h.horaInicio) and time(h.horaFin) "+  
        													 "and h.dia ="+dia, null);
        tmpCursor.moveToFirst();
        totalAsistencia = tmpCursor.getInt(0);
        tmpCursor.close();
        
        dbh.close();
        Log.v("ASIS","TotalAsistencia: "+totalAsistencia);
        return totalAsistencia==0;
	
	}

	private void ClearSelections() {
		Log.v("ASIS","ClearSelections");

		// user has clicked clear button so uncheck all the items

		int count = this.mainListView.getAdapter().getCount();

		for (int i = 0; i < count; i++) {
			this.mainListView.setItemChecked(i, false);
		}

		// also clear the saved selections
	

	}

	private void LoadSelections() {
        // if the selections were previously saved load them
        
        for (int i = 0; i < listaAlumnosAsisten.length; i++) {

            if (listaAlumnosAsisten[i].tipoAsistencia.equals("a")) {
                this.mainListView.setItemChecked(i, true);
            }

        }
    }

	private void SaveSelections() {
		Log.v("ASIS", "SaveSelections");

        // save the selections in the shared preference in private mode for the user
        
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        String fecha = df1.format(c.getTime());
        String hora = df2.format(c.getTime());
        String dia = Integer.toString(c.get(Calendar.DAY_OF_WEEK)-1);
        
        dbh= new DBHelper(this);
		//int estaEnElHorario = 0;
        Log.v("ASIS","verificando horario: hora: "+hora+" - dia: "+dia+" - curso: "+ idCurso);
		
        Cursor tmpCursor= dbh.getReadableDatabase().rawQuery("select count(gc.idHorario) from horario h "+
        													 "inner join grupoCurso gc on gc.idHorario = h.idHorario "+
        													 "where time('"+hora+"') between time(h.horaInicio) and time(h.horaFin) "+  
        													 "and h.dia ="+dia+" and gc.idCurso ="+idCurso, null);
        tmpCursor.moveToFirst();
        estaEnHorario = tmpCursor.getInt(0)>0;
        Log.v("ASIS", "esta en el horario: "+tmpCursor.getInt(0));
        tmpCursor.close();
        dbh.close();
        
        if(estaEnHorario){

	        String fechaCompleta = fecha +" "+hora;
	        //String fechaCompleta = "07-02-2013 19:26:23";
	        DBAsistencia dbAsis = new DBAsistencia(this);
	        dbAsis.open();
	        String tipoAsistencia="i";
	        int nuevoIdAsis =0;
	        
	        for (int i = 0; i < listaAlumnosAsisten.length; i++) {
	            tipoAsistencia = (this.mainListView.isItemChecked(i))?"a":"i";
	            
	            if (((AlumnoAsiste)listaAlumnosAsisten[i]).idAsistencia ==0) {                
	                nuevoIdAsis= idMaxIdAsis+1;
	                Log.v("SAV"," :::insertAsistencia::: ");
	                long id = dbAsis.insertAsistencia(nuevoIdAsis, idProfesor, idCurso, listaAlumnosAsisten[i].idAlumno, idGrupo, fechaCompleta, tipoAsistencia, "N");
	                idMaxIdAsis += 1;
	                listaAlumnosAsisten[i].idAsistencia = (int)id;
	            }else{
	                Log.v("SAV"," :::actualizando::: ");
	                dbAsis.updateAsistencia(listaAlumnosAsisten[i].idAsistencia, idProfesor, idCurso, listaAlumnosAsisten[i].idAlumno, idGrupo, fechaCompleta, tipoAsistencia, "N");
	            }
	            listaAlumnosAsisten[i].fecha = fechaCompleta;
	            listaAlumnosAsisten[i].tipoAsistencia= tipoAsistencia;
	        }
	        
	        dbAsis.close();
	        Toast.makeText(getApplicationContext()," Se guardó la asistencia ", Toast.LENGTH_LONG).show();
        }else{
        	Toast.makeText(getApplicationContext()," No se puede guardar los datos fuera del horario ", Toast.LENGTH_LONG).show();
        }
    }

	
	private void getDatosDB(String fecha, String hora, String dia){
        dbh= new DBHelper(this);   
        
        
        Cursor tmpCursor= dbh.getReadableDatabase().rawQuery("select IFNULL(max(idAsistencia),0) from asistencia", null);
        tmpCursor.moveToFirst();
        idMaxIdAsis = tmpCursor.getInt(0);
        tmpCursor.close();
        
        listGeneralesCursor= dbh.getReadableDatabase().rawQuery("select gc.idCurso, gc.idGrupo, gc.idProfesor, c.Nombre "+
                                                          "from grupoCurso  gc "+
                                                          "inner join horario h on gc.idHorario = h.idHorario "+
                                                          "inner join curso c on gc.idCurso = c.idCurso "+
                                                          "where time('"+hora+"') between time(h.horaInicio) and time(h.horaFin) "+  
                                                          "and h.dia = "+dia, null);
        
        listGeneralesCursor.moveToFirst();        
        while (listGeneralesCursor.isAfterLast()==false)
        {
            idCurso =listGeneralesCursor.getInt(0);
            idGrupo =listGeneralesCursor.getInt(1);
            idProfesor =listGeneralesCursor.getInt(2);
            Curso = listGeneralesCursor.getString(3);
            
            listGeneralesCursor.moveToNext();
        }        
        listGeneralesCursor.close();
        
        
        listAlumnosCursor= dbh.getReadableDatabase().rawQuery("select a.*, IFNULL(asi.idAsistencia,0), IFNULL(asi.tipoAsistencia,'i')  from alumno a "+
                                                              "inner join matricula m on a.idAlumno = m.idAlumno "+
                                                              "inner join grupoCurso gc on m.idGrupoCurso = gc.idGrupoCurso "+ 
                                                              "inner join horario h on gc.idHorario = h.idHorario "+  
                                                              "left join asistencia asi on a.idAlumno = asi.idAlumno "+
                                                              "and asi.idCurso = gc.idCurso and asi.idGrupo = gc.idGrupo "+
                                                              "and substr(asi.fecha,1,10) = '"+fecha+"' "+
                                                              "where time('"+hora+"') between time(h.horaInicio) and time(h.horaFin) "+ 
                                                              "and h.dia ="+dia, null);
        
        listAlumnosCursor.moveToFirst();
        int i=0;
        listaAlumnosAsisten=new AlumnoAsiste [listAlumnosCursor.getCount()];
        listAsistencia = new String[listAlumnosCursor.getCount()];
        
        AlumnoAsiste tmp= null;
        while (listAlumnosCursor.isAfterLast()==false)
        {
            tmp = new AlumnoAsiste();
            tmp.idAlumno = Integer.parseInt(listAlumnosCursor.getString(0));
            tmp.Nombre = listAlumnosCursor.getString(1);
            tmp.idAsistencia = Integer.parseInt(listAlumnosCursor.getString(2));
            tmp.tipoAsistencia = listAlumnosCursor.getString(3);
            listAsistencia[i]=listAlumnosCursor.getString(1);
            listaAlumnosAsisten[i] = tmp;
            listAlumnosCursor.moveToNext();
            i++;
        }
        
        listAlumnosCursor.close();
        dbh.close();
    }

	public void Send(){
		Log.v("ASIS","Send");
		Calendar c = Calendar.getInstance();

        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        String fecha = df1.format(c.getTime());
        String hora = df2.format(c.getTime());
        String dia = Integer.toString(c.get(Calendar.DAY_OF_WEEK)-1);
        
        int estaEnElHorario = 0;
		
        Cursor tmpCursor= dbh.getReadableDatabase().rawQuery("select count(idHorario) from horario h "+
        													 "where time('"+hora+"') between time(h.horaInicio) and time(h.horaFin) "+  
        													 "and h.dia ="+dia, null);
        tmpCursor.moveToFirst();
        estaEnElHorario = tmpCursor.getInt(0);
        tmpCursor.close();
        dbh.close();
        
        if(estaEnElHorario >0){

			final String NAMESPACE = "http://localhost/";
			final String METHOD_NAME = "registroAsistencia2";
			final String SOAP_ACTION = "http://localhost/registroAsistencia2";
			final String URL="http://10.0.2.2:8090/Servicios.asmx";
	
			String respuesta = "";
	
			String dato_enviar="";
	
			for (int i = 0; i < listaAlumnosAsisten.length; i++) {
				dato_enviar = dato_enviar+idProfesor+"|";
				dato_enviar = dato_enviar+idCurso+"|";
				dato_enviar = dato_enviar+listaAlumnosAsisten[i].idAlumno+"|";
				dato_enviar = dato_enviar+idGrupo+"|";
				dato_enviar = dato_enviar+listaAlumnosAsisten[i].fecha+"|";
				dato_enviar = dato_enviar+listaAlumnosAsisten[i].tipoAsistencia;
				dato_enviar = dato_enviar +"#";
	
			}
	
			
	
			dato_enviar=dato_enviar.substring(0, dato_enviar.length()-1);
	
			Log.v("SAV", "candena: "+dato_enviar);
	
			
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	
	
			request.addProperty("listaAsistencia",dato_enviar);
	
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
	
			envelope.setOutputSoapObject(request);
	
			HttpTransportSE transporte = new HttpTransportSE(URL);
	
			try 
			{
				transporte.call(SOAP_ACTION, envelope);
				Log.v("SAV", "request: " + transporte.requestDump);
				Log.v("SAV", "response: " + transporte.responseDump);
	
				SoapObject resSoap =(SoapObject)envelope.getResponse();
	
				respuesta = resSoap.toString();
	
			} 
			catch (Exception e) 
			{
				Log.v("ASIS", e.getMessage());
			}
			
			
	
			if(respuesta.equals("")){
				DBAsistencia dbAsis = new DBAsistencia(this);
		        dbAsis.open();
				
				for (int i = 0; i < listaAlumnosAsisten.length; i++) {
					Log.v("SAV"," :::actualizando::: ");
	                dbAsis.updateAsistenciaEnviado(listaAlumnosAsisten[i].idAsistencia, "S");
				}
				dbAsis.close();
				
				Toast.makeText(getApplicationContext()," Se envió la asistencia ", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(),respuesta, Toast.LENGTH_SHORT).show();
			}
        }else{
        	Toast.makeText(getApplicationContext()," No se puede enviar los datos fuera del horario ", Toast.LENGTH_LONG).show();
        }

	}

	

}