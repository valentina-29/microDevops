package com.diarymedi.spring.diarymedispring.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Otros atributos específicos de paciente

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserLogin usuario;
    @Column(name = "numeroSeguro", unique = true)
    private String numeroSeguro;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "contactoEmergencia")
    private String contactoEmergencia;
    @Column(name = "alergias")
    private List<String> alergias;
    @Column(name = "gposan")
    private String gposan;

    public UserLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(UserLogin usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public String getGposan() {
        return gposan;
    }

    public void setGposan(String gposan) {
        this.gposan = gposan;
    }

   

    // Getters y setters
}
