package com.nhaqp.attendance.entity;

public class ItemAlumno {

	protected long id;

	protected String nombre;
	protected String porcentaje;
	protected String cantidad;
	
	
	public ItemAlumno() {
		this.nombre = "";
		this.porcentaje = "";
		this.cantidad="";
	
	}
	
	public ItemAlumno(long id, String nombre, String porcentaje, String cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.porcentaje = porcentaje;
		this.cantidad=cantidad;
	
	}
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPorcentaje() {
		return porcentaje;
	}
	
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	
	
}