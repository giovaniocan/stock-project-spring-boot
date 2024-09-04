package click.gestao.api.infra.security;

import click.gestao.api.domain.user.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            //esse algoritmo é o que vai ser usado para criptografar o token, e esse secret é uma chave que a gente passa para garantir que o token é valido

            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API de teste da Click")
                    // aqui serve para guardar as informacoes do usuario no token, no caso só estamos guardando quem é o usuario
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId()) // aqui podemos armazenar mais informações, e podemos fazer isso quantas vezes forem necessarias, em uma estrutura de chave e valor
                    .withExpiresAt(dataExpiaracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao criar token", exception);    }
    }

    // aqui estamos criando e retornando quando o token vai expirar
    private Instant dataExpiaracao() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }

}
