package edu.ifal.virtable.security;

import edu.ifal.virtable.domain.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceTests {

    private final String SECRET = "minha-chave-secreta-minha-chave-secreta-123456";

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("id", usuario.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getChave())
                .compact();
    }

    private SecretKey getChave() {
        byte[] keyBytes = SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}