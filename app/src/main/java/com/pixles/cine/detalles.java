package com.pixles.cine;

public class detalles
{
	String Id,Titulo,Genero,Clasificaion,Formato,Resumen,Prota,Director,Funciones;
	public detalles(){
		
	}
	public detalles(String id,String titulo,String genero,String clasificacion,String formato,String resumen,String prota,String director,String funciones){
		
		this.Id=id;
		this.Titulo=titulo;
		this.Genero=genero;
		this.Clasificaion=clasificacion;
		this.Formato=formato;
		this.Resumen=resumen;
		this.Prota=prota;
		this.Director=director;
		this.Funciones=funciones;
		
		
	}
	public String getId(){
		return Id;
	}
	public String getTitulo(){
		return Titulo;
	}
	public String getGenero(){
		return Genero;
	}
	public String getClasificacion(){
		return Clasificaion;
	}
	public String getFormato(){
		return Formato;
	}
	public String getResumen(){
		return Resumen;
	}
	public String getProta(){
		return Prota;
	}
	public String getDirector(){
		return Director;
	}
	public String getFunciones(){
		return Funciones;
	}
	
}
