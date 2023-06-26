package com.example.davicio;

import java.io.Serializable;


public class Personas implements  Serializable{

    private Integer id;
    private String nombre;
    private String apellido;
    private String mail;
    private String contraseña;

    public Personas(Integer id, String nombre, String apellido,String mail,String contraseña ) {
        this.setId(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contraseña = contraseña;
    }

    public Personas(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String apellido) {
        this.apellido = apellido;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String nombre) {
        this.contraseña = contraseña;
    }




}
