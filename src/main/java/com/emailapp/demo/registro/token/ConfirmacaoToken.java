package com.emailapp.demo.registro.token;

import com.emailapp.demo.usecase.model.UsuarioApp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmacaoToken {

    @Id
    @SequenceGenerator(name = "confirmacao_token_id", sequenceName = "confirmacao_token_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmacao_token_id")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private LocalDateTime expiradoEm;

    private LocalDateTime confirmadoEm;

    @ManyToOne
    @JoinColumn(nullable = false, name = "usuario_app_id")
    private UsuarioApp usuarioApp;

    public ConfirmacaoToken(String token, LocalDateTime criadoEm, LocalDateTime expiradoEm,
                            UsuarioApp usuarioApp) {
        this.token = token;
        this.criadoEm = criadoEm;
        this.expiradoEm = expiradoEm;
        this.usuarioApp = usuarioApp;
    }
}
