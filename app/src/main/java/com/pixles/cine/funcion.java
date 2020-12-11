package com.pixles.cine;

public class funcion
{
	public String Dia,Hora,Sala;
	public funcion(){
		
	}
	public funcion(String dia,String hora,String sala){
		this.Dia=dia;
		this.Hora=hora;
		this.Sala=sala;
	}
	public String getDia(){
		return Dia;
	}
	public String getHora(){
		return Hora;
	}
	public String getSala(){
		return Sala;
	}
}
