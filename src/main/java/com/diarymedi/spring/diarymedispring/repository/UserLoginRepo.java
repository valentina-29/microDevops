package com.diarymedi.spring.diarymedispring.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.diarymedi.spring.diarymedispring.model.UserLogin;

public interface UserLoginRepo extends JpaRepository<UserLogin, Integer>{
    
    List<UserLogin> findByCargosUsuarios(String cargosUsuarios);
    
    UserLogin findByUserAndPass(String username, String password);
    
    UserLogin findByIdUser(String idUser);

    UserLogin findCargosUsuariosByUser(String user);
}
