package com.nhaqp.attendance.view;

import com.nhaqp.attendance.R;
import com.nhaqp.attendance.db.DBHelper;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ConsultasCursoActivity extends ListActivity {
    /** Called when the activity is first created. */
	
	private DBHelper db=null;
	private Cursor cursosCursor=null;

	private String[] listcursos ;
	private int[] listIdGrupoCurso;
//	private Cursor alumnosCursor=null;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        
        
        db= new DBHelper(this);
        cursosCursor= db.getReadableDatabase().rawQuery("select distinct gc.idGrupoCurso, c.nombre, g.nombre "+ 
        												"from curso c "+ 
        												"inner join grupocurso gc "+ 
        												"on c.idcurso = gc.idcurso "+
        												"inner join grupo g "+
        												"on g.idgrupo = gc.idgrupo", null);
        
        cursosCursor.moveToFirst();
        
   
        
        int i=0;
        listcursos= new String[cursosCursor.getCount()];
        listIdGrupoCurso= new int[cursosCursor.getCount()];
        
        while (cursosCursor.isAfterLast() == false) {
        	
        	listIdGrupoCurso[i]=cursosCursor.getInt(0);
        	listcursos[i]=cursosCursor.getString(1)+ " - " +cursosCursor.getString(2);;
           	
           	cursosCursor.moveToNext();	
        	i++;

        }
        
        cursosCursor.close();
        
        db.close();
        
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listcursos));
		
	    setContentView(R.layout.lista_curso_layout);
	    
    }

	public void onListItemClick(ListView parent, View v, int position, long id) {

		//seleccion.setText(items[position]);
		//Toast.makeText(this, "Se ha seleccionado " + listcursos[position] + "nro de position:" + position,
		//		Toast.LENGTH_SHORT).show();
	
		Intent result= new Intent();
		result.setClass(this, ConsultasAlumnoActivity.class);
		result.putExtra("idGrupoCurso", listIdGrupoCurso[position]);
		startActivityForResult(result, position);
		
	}
    
}