package com.pixles.cine;

public class numef
{
	int N;
	String funciones;
	public numef(){
		
	}
	public numef(int n,String funcion){
		this.N=n;
		this.funciones=funcion;
		
	}
	public int getNumero(){
		return N;
	}
	public String getFunciones(){
		return funciones;
	}
}
