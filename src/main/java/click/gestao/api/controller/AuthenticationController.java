package click.gestao.api.controller;

import click.gestao.api.domain.user.DadosAuthentication;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAuthentication data){
        //aqui não podemos apenas chamar a nossa service, e sim usar um metodo nativo do spring e eleque vai chamar a nosas service e autenticar

        //criando um objeto do tipo que o authentication manager precisa
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.senha());

       //quando isso é chamado, o spring chama o metodo loadUserByUsername da nossa classe AuthenticationService, e usa o repository para buscar o usuario
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
