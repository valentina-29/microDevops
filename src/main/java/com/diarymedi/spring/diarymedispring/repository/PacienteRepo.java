package com.diarymedi.spring.diarymedispring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.diarymedi.spring.diarymedispring.model.Paciente;

public interface PacienteRepo extends JpaRepository<Paciente, Integer>{

    
    
}
