package com.diarymedi.spring.diarymedispring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diarymedi.spring.diarymedispring.model.Medico;

public interface MedicoRepo extends JpaRepository <Medico, Integer>{
    List<Medico> findByEspecialidad(String especialidad);
}
