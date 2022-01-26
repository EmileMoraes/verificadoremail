package com.emailapp.demo.usecase.model;

import com.emailapp.demo.usecase.enums.TipoUsuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UsuarioApp implements UserDetails {

    @ApiModelProperty(value = "Código do usuario")
    @Id
    @SequenceGenerator(name = "cliente_id", sequenceName = "cliente_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id")
    private Long id;
    @ApiModelProperty(value = "Nome do usuario")
    private String nome;
    @ApiModelProperty(value = "Sobrenome do usuario")
    private String sobrenome;
    @ApiModelProperty(value = "Email do usuario")
    private String email;
    @ApiModelProperty(value = "Senha do usuario")
    private String senha;
    @ApiModelProperty(value = "Tipo de usuario")
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    private Boolean bloqueado = false;
    private Boolean liberado = false;

    public UsuarioApp(String nome, String sobrenome, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(tipoUsuario.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !bloqueado;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return liberado;
    }

}
