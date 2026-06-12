package edu.ifal.virtable.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.usuario.Usuario;
import edu.ifal.virtable.dto.AuthResponse;
import edu.ifal.virtable.dto.LoginRequest;
import edu.ifal.virtable.dto.RegisterRequest;
import edu.ifal.virtable.repository.UsuarioRepository;
import edu.ifal.virtable.security.JwtService;
import jakarta.validation.Valid;

@Service
@Validated
public class UsuarioService extends CrudService<Usuario> {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse cadastrar(@Valid RegisterRequest request) {
        final Usuario usuario = new Usuario(request.nome(), request.email(), passwordEncoder.encode(request.senha()));

        create(usuario);

        return new AuthResponse(jwtService.gerarToken(usuario));
    }

    public AuthResponse login(@Valid LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean senhaCorreta = passwordEncoder.matches(
                request.senha(),
                usuario.getSenha());

        if (!senhaCorreta) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.gerarToken(usuario);

        return new AuthResponse(token);
    }
}