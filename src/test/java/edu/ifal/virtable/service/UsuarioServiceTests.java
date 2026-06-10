package edu.ifal.virtable.service;

import edu.ifal.virtable.domain.Usuario;
import edu.ifal.virtable.dto.RegisterRequest;
import edu.ifal.virtable.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("UsuarioService - Testes Unitários")
public class UsuarioServiceTests {

        @Autowired
        private AuthService authService;

        @Autowired
        private UsuarioService usuarioService;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @BeforeEach
        void setup() {
                usuarioRepository.deleteAll();
        }

        private void cadastrar(String email) {
                RegisterRequest request = new RegisterRequest(
                                "Nome Exemplo",
                                email,
                                "senha123");

                authService.cadastrar(request);
        }

        @Test
        @DisplayName("Deve listar usuario cadastrado")
        void testListarValido() {
                cadastrar("user1@example.com");

                boolean included = false;

                for (final Usuario usuario : usuarioService.list().list()) {
                        if (usuario.getNome() == "João Silva") {
                                included = true;
                        }
                }

                assertTrue(included);
        }

        @Test
        @DisplayName("Deve retornar usuario cadastrado pelo id")
        void testLoginValido() {
                cadastrar("user2@example.com");

                usuarioService.read(null);
        }
}
