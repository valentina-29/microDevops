package com.diarymedi.spring.diarymedispring.model;


import jakarta.persistence.*;

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserLogin usuario;
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "idMedico")
    private String idMedico;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public UserLogin getUsuario() {
        return usuario;
    }
    public void setUsuario(UserLogin usuario) {
        this.usuario = usuario;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public String getIdMedico() {
        return idMedico;
    }
    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }
    
}
