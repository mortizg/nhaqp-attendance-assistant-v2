package com.nhaqp.attendance.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhaqp.attendance.R;
import com.nhaqp.attendance.db.DBHelper;
import com.nhaqp.attendance.entity.ItemAlumno;
import com.nhaqp.attendance.entity.ItemAlumnoAdapter;

public class ConsultasAlumnoActivity extends Activity {
    private int listaId;
    private int[] idAlumno;
    private int[] ids;
    private Cursor tempCursor=null;
    private Cursor asistenciaCursor=null;
    private DBHelper db1=null;
    private DBHelper db2=null;
    private ListView lv=null;
    private static final int RESULT = 0;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.lista_alumno_layout);
    	
    	listaId = getIntent().getIntExtra("idGrupoCurso", 0);
    	ArrayList<ItemAlumno> itemsAsistencia = new ArrayList<ItemAlumno>();
    	
    	
    	db1=new DBHelper(this);
        tempCursor= db1.getReadableDatabase().rawQuery("select gc.idCurso, gc.idGrupo from  grupoCurso gc where gc.idGrupoCurso="+ listaId, null);

   		tempCursor.moveToFirst();

   		ids= new int[2];
   		ids[0]=tempCursor.getInt(0);
        ids[1]=tempCursor.getInt(1);
        
        
        tempCursor.close();
        db1.close();
    	db2=new DBHelper(this);
       
        
        asistenciaCursor=db2.getReadableDatabase().rawQuery("select  alum.idAlumno, alum.Nombre, sum(Inasistencias)*100/count(alum.idAlumno) as porcentajeInasistencia, sum(Inasistencias) as cantidadInasistencia "+
        													"from alumno alum inner join "+
        													"(select idAlumno, "+
        													"case tipoAsistencia " +
        													"	when  'i' "+
        													"		then  1 "+
        													"	else   0 "+
        													"end as Inasistencias "+
        													"from asistencia "+
        													"where  idCurso="+ids[0]+" and  idGrupo="+ids[1]+" ) as  temp "+

        													"on alum.idAlumno= temp.idAlumno "+
        													"group by alum.idAlumno", null);
        
        asistenciaCursor.moveToFirst();
        idAlumno=new int[asistenciaCursor.getCount()];
        int m=0;
        while (asistenciaCursor.isAfterLast() == false) {
        	
        	idAlumno[m]= asistenciaCursor.getInt(0);
        	asistenciaCursor.moveToNext();	
        	m++;
        }

        asistenciaCursor.moveToFirst(); 
        
        ArrayList<ItemAlumno> itemsAlumno= obtenerItems();
        
        lv = (ListView)findViewById(R.id.listView);
        
        ItemAlumnoAdapter adapter = new ItemAlumnoAdapter(this, itemsAlumno);
        lv.setAdapter(adapter);
        
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	 
     	
        	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
    			// TODO Auto-generated method stub
        		Cursor nombreCursor=null;
        		Cursor fechasCursor=null;
        		int n=0;
        		String nom;
        		String [] listfecha;
        		
        		nombreCursor=db2.getReadableDatabase().rawQuery("select alum.Nombre from alumno alum where alum.idAlumno="+idAlumno[position], null);
        		nombreCursor.moveToFirst();
        		nom= nombreCursor.getString(0);	
        		fechasCursor=db2.getReadableDatabase().rawQuery("select fecha from asistencia where idAlumno="+idAlumno[position] + " and tipoAsistencia='i'", null);
        		fechasCursor.moveToFirst();
        		
        		if(fechasCursor.getCount()==0)
        		{
        			listfecha = new String[1];
        			listfecha[0] = "No tiene inasistencias"; 
        		}
        		else 
        		{
		        		listfecha=new String[fechasCursor.getCount()];
		        		
		        		while(fechasCursor.isAfterLast()==false)
		        		{
		        			listfecha[n]=fechasCursor.getString(0);
		        			fechasCursor.moveToNext();
		        			n++;
		        		}
        		}
        		
        		ShowMessage(nom, listfecha);
                
            }
        });
   

    }
    
    
 	private ArrayList<ItemAlumno> obtenerItems() {
		ArrayList<ItemAlumno> items = new ArrayList<ItemAlumno>();
			
		
        int i=1;
        while (asistenciaCursor.isAfterLast() == false) {
        	
        	items.add(new ItemAlumno(i, asistenciaCursor.getString(1), asistenciaCursor.getString(2), asistenciaCursor.getString(3)));
        	asistenciaCursor.moveToNext();	
        	i++;
        }
        
        asistenciaCursor.close();
        db2.close();
		return items;
	 	
 	}
 	
    /*
    final CharSequence[] items = {"Android OS", "iOS", "Windows Phone", "Meego"};
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("Tu OS móvil preferido?");
	builder.setItems(items, new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int item) {
	        Toast toast = Toast.makeText(getApplicationContext(), "Haz elegido la opcion: " + items[item] , Toast.LENGTH_SHORT);
	        toast.show();
	        dialog.cancel();
	    }
	});
	AlertDialog alert = builder.create();
	alert.show();       * */

    public void ShowMessage(String n,String[] lf){
    
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);        
   //     dialogo.setMessage(msg); 
    	builder.setTitle(n);       
    	builder.setItems(lf, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
    	builder.setNeutralButton("OK", null);
        
        AlertDialog alert = builder.create();
        alert.show();
    
    }	
    
    
}