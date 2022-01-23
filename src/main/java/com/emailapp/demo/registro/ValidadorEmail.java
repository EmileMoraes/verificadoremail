package com.emailapp.demo.registro;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class ValidadorEmail implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // TODO: Usar o Regex para validar o email
        return true;
    }
}
