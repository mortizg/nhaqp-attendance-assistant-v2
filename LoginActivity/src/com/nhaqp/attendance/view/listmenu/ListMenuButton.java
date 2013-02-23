package com.nhaqp.attendance.view.listmenu;

import android.content.Context;
import android.content.Intent;
import android.view.View.OnClickListener;

public class ListMenuButton {
	private String titulo;
	private String descripcion;
	private int icono = -1;
	private OnClickListener onClickListener;
	private Intent intent;
	private Class<?> clase;
	Context context;
	
	public ListMenuButton(Context context, String titulo,
			String descripcion, int icono, OnClickListener onClickListener) {
		//super(context);
		this.context = context;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.icono = icono;
		this.onClickListener = onClickListener;
		
	}
	
	public ListMenuButton(Context context, String titulo,
			String descripcion, int icono, Intent intent) {
		//super(context);
		this.context = context;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.icono = icono;
		this.intent = intent;		
	}
	
	public ListMenuButton(Context context, String titulo,
			String descripcion, int icono, Class<?> clase) {
		//super(context);
		this.context = context;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.icono = icono;
		this.clase = clase;		
	}
	
	public ListMenuButton(Context context, String titulo, String descripcion, int icono) {
		//super(context);
		this.context = context;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.icono = icono;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setIcono(int icono) {
		this.icono = icono;
	}

	public int getIcono() {
		return icono;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	//@Override
	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}
	
	
	public OnClickListener getOnClickListener() {
		return onClickListener;
	}
	
	public Intent getIntent() {
		return intent;
	}
	
	public Class<?> getClase() {
		return clase;
	}
}