package com.nhaqp.attendance.entity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhaqp.attendance.R;

public class ItemAlumnoAdapter extends BaseAdapter{

	protected Activity activity;
	protected ArrayList<ItemAlumno> items;
	
	
	public ItemAlumnoAdapter(Activity activity, ArrayList<ItemAlumno> items){
		this.activity=activity;
		this.items=items;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return items.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi=convertView;
		
        if(convertView == null) {
        	LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	vi = inflater.inflate(R.layout.item_alumno_layout, null);
        }
            
        ItemAlumno item = items.get(position);
        
        
        TextView nombre = (TextView) vi.findViewById(R.id.nombre);
        nombre.setText(item.getNombre());
        
        TextView porcentaje = (TextView) vi.findViewById(R.id.porcentaje);
        porcentaje.setText(item.getPorcentaje());

        TextView cantidad = (TextView) vi.findViewById(R.id.cantidad);
        cantidad.setText(item.getCantidad());

        return vi;	
        
	}
	
	
	
}