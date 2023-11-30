package com.diarymedi.spring.diarymedispring.model;
import jakarta.persistence.*;


@Entity
public class PdfDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre_Archivo;
    private String idPaciente;
    private String idMedico;
    @Lob
    private byte[] pdf_data;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre_Archivo() {
        return nombre_Archivo;
    }
    public void setNombre_Archivo(String nombre_Archivo) {
        this.nombre_Archivo = nombre_Archivo;
    }
    public String getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getIdMedico() {
        return idMedico;
    }
    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }
    public byte[] getPdf_data() {
        return pdf_data;
    }
    public void setPdf_data(byte[] pdf_data) {
        this.pdf_data = pdf_data;
    }


}