package com.pixles.cine;

public class cartelera
{
	String Id,Titulo,Formato,Funcion,Imagen;
	public cartelera(){
		
	}
	public cartelera(String id,String titulo,String formato,String funcion,String imagen){
		this.Id=id;
		this.Titulo=titulo;
		this.Formato=formato;
		this.Funcion=funcion;
		this.Imagen=imagen;
	}
	public String getId(){
		return Id;
	}
	public String getTitulo(){
		return Titulo;
	}
	public String getFormato(){
		return Formato;
	}
	public String getFuncion(){
		return Funcion;
	}
	public String getImagen(){
		return Imagen;
	}
}
