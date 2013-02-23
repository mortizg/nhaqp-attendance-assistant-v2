package com.nhaqp.attendance.view.listmenu;

import java.util.ArrayList;
import java.util.List;

import com.nhaqp.attendance.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListMenuAdapter extends ArrayAdapter<ListMenuButton> {
	
	private final List<ListMenuButton> list;
	private final Context context;
	
	public ListMenuAdapter(Context context, ArrayList<ListMenuButton> list) {
		super(context, R.layout.list_menu_layout, list);
		this.context=context;
		this.list=list;
	}
	
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_menu_layout, null);
        }

        ListMenuButton item = list.get(position);
        
        if (item != null) {             
        	TextView txtTitulo = (TextView)v.findViewById(R.id.nextel_list_menu_button_titulo);
        	TextView txtDescripcion = (TextView)v.findViewById(R.id.list_menu_button_descripcion);
        	ImageView imgIcono = (ImageView)v.findViewById(R.id.list_menu_button_icono);
        	if (item.getTitulo() != null && !item.getTitulo().equals("")){
        		txtTitulo.setText(item.getTitulo());
        	}
        	else{
        		txtTitulo.setVisibility(View.GONE);
        	}
        		
        	if (item.getDescripcion() != null && !item.getDescripcion().equals("")){
        		txtDescripcion.setText(item.getDescripcion());
        	}
        	else{
        		txtDescripcion.setVisibility(View.GONE);
        	}
        	
        	if (item.getIcono() != -1){
        		imgIcono.setImageResource(item.getIcono());
        	}
        	else{
        		imgIcono.setVisibility(View.GONE);
        	}
        	
        	if (item.getOnClickListener() != null){
        		v.setOnClickListener(item.getOnClickListener());
        	}
        	else if (item.getIntent() != null || item.getClase() != null){
        		v.setOnClickListener(new ListMenuButtonListener(context, item));
        	}        	
         }

        return v;
    }
}