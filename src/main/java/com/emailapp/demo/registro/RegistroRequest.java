package com.emailapp.demo.registro;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistroRequest {

    private final String nome;
    private final String sobrenome;
    private final String senha;
    private final String email;
}
