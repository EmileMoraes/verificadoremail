package com.emailapp.demo.usecase;

import com.emailapp.demo.exception.UsuarioNotFoundException;
import com.emailapp.demo.registro.token.ConfirmacaoToken;
import com.emailapp.demo.registro.token.ConfirmacaoTokenService;
import com.emailapp.demo.usecase.model.UsuarioApp;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final static String USUARIO_NAO_ENCONTRADO_MSG = "usuario com o email %s não encontrado";
    private final static String EMAIL_EXISTE_MSG = "o email %s já existe";
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmacaoTokenService confirmacaoTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USUARIO_NAO_ENCONTRADO_MSG, email)));
    }

    public String singUpUsuario(UsuarioApp usuarioApp) {
        boolean usuarioExiste = usuarioRepository.findByEmail(usuarioApp.getEmail())
                .isPresent();
        if(usuarioExiste){
            throw new IllegalStateException(String.format(EMAIL_EXISTE_MSG, usuarioApp.getEmail()));
        }

        String senhaCripto = bCryptPasswordEncoder.encode(usuarioApp.getPassword());

        usuarioApp.setSenha(senhaCripto);

        usuarioRepository.save(usuarioApp);

        String token = UUID.randomUUID().toString();
        // TODO: ENVIAR CONFIMAÇÃO DO TOKEN
        ConfirmacaoToken confirmacaoToken = new ConfirmacaoToken(token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                usuarioApp);
        confirmacaoTokenService.saveConfirmacaoToken(confirmacaoToken);

        //TODO: Mandar o email
        return token;
    }

    public int mudarUsuarioApp(String email) {
        return usuarioRepository.mudarUsuarioApp(email);
    }


    public List<UsuarioApp> usuarioAppList(){
        return usuarioRepository.findAll();
    }

    public UsuarioApp findById(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new UsuarioNotFoundException("Usuario id=" + id + " não encontrado"));
    }
}
