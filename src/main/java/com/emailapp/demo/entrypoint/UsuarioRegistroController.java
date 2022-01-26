package com.emailapp.demo.entrypoint;

import com.emailapp.demo.exception.UsuarioNotFoundException;
import com.emailapp.demo.registro.RegistroRequest;
import com.emailapp.demo.registro.RegistroService;
import com.emailapp.demo.usecase.UsuarioService;
import com.emailapp.demo.usecase.model.UsuarioApp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/registros")
@AllArgsConstructor
public class UsuarioRegistroController {

    private RegistroService registroService;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping(produces="application/json", consumes="application/json")
    public String registro(@RequestBody RegistroRequest registroRequest) {
        return registroService.registro(registroRequest);
    }

    @GetMapping(path = "valida", produces="application/json")
    public String validaToken(@RequestParam("token") String token){
        return registroService.confirmaToken(token);
    }

    @GetMapping(path = "usuario", produces="application/json")
    public ResponseEntity<List<UsuarioApp>> usuariosApp(){
        if(usuarioService.usuarioAppList().isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarioService.usuarioAppList());
    }

    @GetMapping(path = "usuario/{id}", produces="application/json")
    public ResponseEntity<UsuarioApp> findById(@PathVariable Long id) throws UsuarioNotFoundException {
        if(usuarioService.findById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuarioService.findById(id));
    }


}
