package com.diarymedi.spring.diarymedispring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.diarymedi.spring.diarymedispring.model.Cita;


public interface CitaRepo extends JpaRepository<Cita, Long> {

        @Query("SELECT c FROM Cita c WHERE c.idMedico = :idMedico AND c.estado IS NULL")
        public List<Cita> findFechaHoraConEstadoNuloByIdMedico(@Param("idMedico") String idMedico);

}
