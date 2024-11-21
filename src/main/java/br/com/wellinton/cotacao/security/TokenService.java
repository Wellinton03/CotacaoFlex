/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.wellinton.cotacao.security;

import br.com.wellinton.cotacao.entity.user.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author welli
 */

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;
    
    public String generatedToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o Token", exception);
        }
    }
    
    public String validateToken(String token) {
        try {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        
        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
        }catch (JWTVerificationException exception){
                    return "";
             }
    }
    
    private Instant genExpirationDate() {
         return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of( "-03:00"));
    }
    
    
}
