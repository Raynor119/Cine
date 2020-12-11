package com.pixles.cine;

public class usuario
{
	public String Cedula,Nombre,Telefono,Email;
	public usuario(){
		
	}
	public usuario(String cedula,String nombre,String telefono,String email){
		this.Cedula=cedula;
		this.Nombre=nombre;
		this.Telefono=telefono;
		this.Email=email;
	} 
	public String getCedula(){
		return Cedula;
	}
	public String getNombre(){
		return Nombre;
	}
	public String getTelefono(){
		return Telefono;
	}
	public String getEmail(){
		return Email;
	}
}
