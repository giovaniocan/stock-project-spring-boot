package click.gestao.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations  {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Chega aqui messa bagaca sera ----------------------------");
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/login").permitAll(); // no login pode permitir o acesso sem o token
                    req.requestMatchers(HttpMethod.DELETE, "/transactions/**").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.DELETE, "/produtos/**").hasRole("ADMIN");
                    /*,.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll(); // esse ** é apra facilitar os subenderecos*/
                    req.anyRequest().authenticated(); // em qualquer  outra tem que estar autenticado
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // to falando, quero adicionar o meu filtro primeiro que o do spring, porque se o o Spring vir primeiro ele vai verificar se eu to logado, mas eu so faco iss nom meu filtro, então nunca vai me deixar ser logado
                .build();
    }

    // o @Bean serve para exportar uma classe para o spring, para que ele consiga fazer a sua injecao de denpendencia em outras classes
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
