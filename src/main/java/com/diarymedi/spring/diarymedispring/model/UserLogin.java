package com.diarymedi.spring.diarymedispring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")

public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_user", unique = true)
    private String idUser;  // Debe ser único y coincidir con la cédula del usuario
    @Column(name = "user", unique = true)
    private String user;
    @Column(name = "tipodoc"   )
    private String tipodoc;
    @Column(name = "pass"   )
    private String pass;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "cargosUsuarios")
    private String cargosUsuarios;
    @Column(name = "genero")
    private String genero;
    @Column(name = "estado")
    private String estado;

 
    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getTipodoc() {
        return tipodoc;
    }
    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }
    public String getCargosUsuarios() {
        return cargosUsuarios;
    }
    public void setCargosUsuarios(String cargosUsuarios) {
        this.cargosUsuarios = cargosUsuarios;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    // Getters y setters
}

