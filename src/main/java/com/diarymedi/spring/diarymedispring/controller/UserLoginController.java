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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diarymedi.spring.diarymedispring.model.Medico;
import com.diarymedi.spring.diarymedispring.model.Paciente;
import com.diarymedi.spring.diarymedispring.model.UserLogin;
import com.diarymedi.spring.diarymedispring.repository.MedicoRepo;
import com.diarymedi.spring.diarymedispring.repository.PacienteRepo;
import com.diarymedi.spring.diarymedispring.repository.UserLoginRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/user")
public class UserLoginController {
    @Autowired
    private UserLoginRepo userLoginRepo;
    @Autowired
    private PacienteRepo pacienteRepositorio;
    @Autowired
    private MedicoRepo medicoRepo;
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/add")
    public ResponseEntity<String> guardarUsuarioYPaciente(@RequestBody UserLogin usuario,
            @RequestParam(required = false) String numse,
            @RequestParam(required = false) String dire,
            @RequestParam(required = false) String conEmer,
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) String idMedico,
            @RequestParam(required = false) String gposan,
            @RequestParam(required = false) List<String> alergias) {
        // Guardar el usuario primero

        if (usuario.getCargosUsuarios().equals("Administrador")) {
            userLoginRepo.save(usuario);
            // Crear un paciente y establecer la relación con el usuario
            return ResponseEntity.ok("Usuario Administrador guardado correctamente.");
        }
        if (usuario.getCargosUsuarios().equals("Paciente")) {
            userLoginRepo.save(usuario);
            // Crear un paciente y establecer la relación con el usuario
            Paciente paciente = new Paciente();
            paciente.setNumeroSeguro(numse);
            paciente.setDireccion(dire);
            paciente.setContactoEmergencia(conEmer);
            paciente.setAlergias(alergias);
            paciente.setGposan(gposan);
            paciente.setUsuario(usuario);
            // Establecer otros atributos del paciente
            pacienteRepositorio.save(paciente);
            return ResponseEntity.ok("Usuario Paciente guardado correctamente.");
        }
        if (usuario.getCargosUsuarios().equals("Medico")) {
            userLoginRepo.save(usuario);
            Medico medico = new Medico();
            medico.setEspecialidad(especialidad);
            medico.setIdMedico(idMedico);
            medico.setUsuario(usuario);
            medicoRepo.save(medico);
            return ResponseEntity.ok("Usuario Medico guardado correctamente.");
        }
        return ResponseEntity.ok("Debe agregar datos.");
    }

    @GetMapping("/usuarios-y-pacientes")
    public List<UserLogin> getUsuariosYPacientes() {
        // Realiza un INNER JOIN entre UserLogin y Paciente a través de la relación
        List<UserLogin> usuariosYPacientes = userLoginRepo.findAll();
        // List<UserLogin> user = userLoginRepo.findByCargo("Paciente");
        return usuariosYPacientes;
    }

    @GetMapping("/find/{idUser}")
    public UserLogin getUsuarioYPacienteById(@PathVariable String idUser) {
        // Realiza un INNER JOIN entre UserLogin y Paciente a través de la relación
        UserLogin usuariosYPacientes = userLoginRepo.findByIdUser(idUser);
        // List<UserLogin> user = userLoginRepo.findByCargo("Paciente");
        return usuariosYPacientes;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLogin> login(@RequestParam String user, @RequestParam String pass) {
        UserLogin usuario = userLoginRepo.findByUserAndPass(user, pass);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<UserLogin> updateUser(@PathVariable String idUser, @RequestBody UserLogin user) {
        // Valida los datos del usuario
        // ...

        // Recupera el usuario basado en su idUser
        UserLogin existingUser = userLoginRepo.findByIdUser(idUser);

        if (existingUser != null) {
            // Actualiza los detalles del usuario si han cambiado
            if (!user.getTipodoc().equals(existingUser.getTipodoc())) {
                existingUser.setTipodoc(user.getTipodoc());
            }
            if (!user.getGenero().equals(existingUser.getGenero())) {
                existingUser.setGenero(user.getGenero());
            }
            if (!user.getEstado().equals(existingUser.getEstado())) {
                existingUser.setEstado(user.getEstado());
            }
            if (!user.getUser().equals(existingUser.getUser())) {
                existingUser.setUser(user.getUser());
            }
            if (!user.getApellidos().equals(existingUser.getApellidos())) {
                existingUser.setApellidos(user.getApellidos());
            }
            if (!user.getNombres().equals(existingUser.getNombres())) {
                existingUser.setNombres(user.getNombres());
            }
            if (!user.getPass().equals(existingUser.getPass())) {
                existingUser.setPass(user.getPass());
            }
            if (!user.getEmail().equals(existingUser.getEmail())) {
                existingUser.setEmail(user.getEmail());
            }
            if (!user.getTelefono().equals(existingUser.getTelefono())) {
                existingUser.setTelefono(user.getTelefono());
            }
            if (!user.getCargosUsuarios().equals(existingUser.getCargosUsuarios())) {
                existingUser.setCargosUsuarios(user.getCargosUsuarios());
            }

            // Actualiza el usuario en la base de datos
            existingUser = userLoginRepo.save(existingUser);

            // Devuelve el usuario actualizado
            return ResponseEntity.ok(existingUser);
        } else {
            // User with the given identifier does not exist
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/por-especialidad")
    public ResponseEntity<List<Medico>> obtenerMedicosPorEspecialidad(@RequestParam String especialidad) {
        List<Medico> medicos = medicoRepo.findByEspecialidad(especialidad);

        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(medicos);
    }
}
