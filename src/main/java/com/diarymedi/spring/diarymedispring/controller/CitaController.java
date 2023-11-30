package com.diarymedi.spring.diarymedispring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diarymedi.spring.diarymedispring.repository.CitaRepo;
import com.diarymedi.spring.diarymedispring.model.Cita;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/cita")
public class CitaController {

    @Autowired
    CitaRepo citaRepo;
    
    public CitaController(CitaRepo citaRepo) {
        this.citaRepo = citaRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<String> guardarCita(@RequestBody Cita cita) {
        citaRepo.save(cita);
        // Crear un paciente y establecer la relación con el usuario
        return ResponseEntity.ok("Cita Creada.");
    }

    @GetMapping("/consultarCita/{idMedico}")
    public ResponseEntity<List<Cita>> obtenerFechaByIdMedico(@PathVariable String idMedico) {
        List<Cita> citas = citaRepo.findFechaHoraConEstadoNuloByIdMedico(idMedico);
    
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
    
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/actualizarCita/{id}")
    public Cita actualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        Cita citaActualizada = citaRepo.findById(id).orElse(new Cita());
    
      // Actualizar los datos de la cita
      citaActualizada.setEstado(cita.getEstado());
      citaActualizada.setIdPaciente(cita.getIdPaciente());
    
      return citaRepo.save(citaActualizada);
    }
}
