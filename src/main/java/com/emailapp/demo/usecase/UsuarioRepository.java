package com.emailapp.demo.usecase;


import com.emailapp.demo.usecase.model.UsuarioApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<UsuarioApp, Long> {
    Optional<UsuarioApp> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UsuarioApp a " +
            "SET a.liberado = TRUE WHERE a.email = ?1")
    public int mudarUsuarioApp(String email);
}
