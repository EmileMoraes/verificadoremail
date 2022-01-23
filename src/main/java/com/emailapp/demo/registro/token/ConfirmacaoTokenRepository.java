package com.emailapp.demo.registro.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmacaoTokenRepository extends JpaRepository<ConfirmacaoToken, Long> {
    Optional<ConfirmacaoToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmacaoToken c " +
            "SET c.confirmadoEm = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmadoEm(String token,
                           LocalDateTime confirmedAt);
}
