package com.emailapp.demo.registro.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmacaoTokenService {

    private final ConfirmacaoTokenRepository confirmacaoTokenRepository;

    public void saveConfirmacaoToken(ConfirmacaoToken confirmacaoToken){
        confirmacaoTokenRepository.save(confirmacaoToken);
    }

    public Optional<ConfirmacaoToken> getToken(String token) {
        return confirmacaoTokenRepository.findByToken(token);
    }

    public int setConfirmadoEm(String token) {
        return confirmacaoTokenRepository.updateConfirmadoEm(
                token, LocalDateTime.now());
    }
}
