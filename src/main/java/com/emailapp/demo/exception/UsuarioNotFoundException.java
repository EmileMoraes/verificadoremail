package com.emailapp.demo.exception;

public class UsuarioNotFoundException extends Exception{

    private static final long serialVersionUID = -2586209354700102349L;

    public UsuarioNotFoundException(){
        super();
    }

    public UsuarioNotFoundException(String msg){
        super(msg);
    }

    public UsuarioNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
