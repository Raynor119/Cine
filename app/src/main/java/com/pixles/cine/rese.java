package com.pixles.cine;

public class rese
{
	String Id,Cedula,Pelicula,Silla,Funcion;
	public rese(){
		
	}
	public rese(String id,String cedula,String pelicula,String silla,String funcion){
		this.Id=id;
		this.Cedula=cedula;
		this.Pelicula=pelicula;
		this.Silla=silla;
		this.Funcion=funcion;
		
	}
	
	public String getId(){
		return Id;
	}
	public String getCedula(){
		return Cedula;
	}
	public String getPelicula(){
		return Pelicula;
	}
	public String getSilla(){
		return Silla;
	}
	public String getFuncion(){
		return Funcion;
	}
}
