package edu.ifal.virtable.service;

import edu.ifal.virtable.domain.usuario.Usuario;
import edu.ifal.virtable.dto.AuthResponse;
import edu.ifal.virtable.dto.LoginRequest;
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
        private UsuarioService usuarioService;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @BeforeEach
        void setup() {
                usuarioRepository.deleteAll();
        }

        // ====== TESTES DE CADASTRO COM VALIDAÇÃO CORRETA ======

        @Test
        @DisplayName("Deve aceitar cadastro válido e retornar token")
        void testCadastroValido() {
                RegisterRequest request = new RegisterRequest(
                                "João Silva",
                                "user1@example.com",
                                "senha123");

                AuthResponse response = usuarioService.cadastrar(request);

                assertNotNull(response);
                assertInstanceOf(AuthResponse.class, response);
                AuthResponse successResponse = (AuthResponse) response;
                assertNotNull(successResponse.token());
                assertFalse(successResponse.token().isEmpty());
        }

        @Test
        @DisplayName("Deve aceitar login válido com email e senha corretos")
        void testLoginValido() {
                RegisterRequest registerRequest = new RegisterRequest(
                                "Maria Santos",
                                "user2@example.com",
                                "senha456");
                usuarioService.cadastrar(registerRequest);

                LoginRequest loginRequest = new LoginRequest(
                                "user2@example.com",
                                "senha456");

                AuthResponse response = usuarioService.login(loginRequest);

                assertNotNull(response);
                assertInstanceOf(AuthResponse.class, response);
                AuthResponse successResponse = (AuthResponse) response;
                assertNotNull(successResponse.token());
                assertFalse(successResponse.token().isEmpty());
        }

        // ====== TESTES DE VALIDAÇÃO COM CAMPOS INVÁLIDOS ======

        @Test
        @DisplayName("Deve rejeitar cadastro com nome vazio")
        void testCadastroComNomeVazio() {
                RegisterRequest request = new RegisterRequest(
                                "",
                                "user3@example.com",
                                "senha789");

                Exception exception = assertThrows(Exception.class, () -> usuarioService.cadastrar(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar cadastro com email vazio")
        void testCadastroComEmailVazio() {
                RegisterRequest request = new RegisterRequest(
                                "Pedro Costa",
                                "",
                                "senha789");

                Exception exception = assertThrows(Exception.class, () -> usuarioService.cadastrar(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar cadastro com email inválido")
        void testCadastroComEmailInvalido() {
                RegisterRequest request = new RegisterRequest(
                                "Lucas Oliveira",
                                "email-invalido",
                                "senha789");

                Exception exception = assertThrows(Exception.class, () -> usuarioService.cadastrar(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar cadastro com senha vazia")
        void testCadastroComSenhaVazia() {
                RegisterRequest request = new RegisterRequest(
                                "Ana Silva",
                                "user4@example.com",
                                "");

                Exception exception = assertThrows(Exception.class, () -> usuarioService.cadastrar(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar login com email vazio")
        void testLoginComEmailVazio() {
                LoginRequest request = new LoginRequest(
                                "",
                                "senhaQualquer");

                Exception exception = assertThrows(Exception.class, () -> usuarioService.login(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar login com email inválido")
        void testLoginComEmailInvalido() {
                LoginRequest request = new LoginRequest(
                                "nao-eh-email",
                                "senhaQualquer");

                Exception exception = assertThrows(Exception.class, () -> usuarioService.login(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar login com senha vazia")
        void testLoginComSenhaVazia() {
                LoginRequest request = new LoginRequest(
                                "user5@example.com",
                                "");

                Exception exception = assertThrows(Exception.class, () -> usuarioService.login(request));
                assertNotNull(exception);
        }

        // ====== TESTES DE EMAIL DUPLICADO OU NÃO CADASTRADO ======

        @Test
        @DisplayName("Deve rejeitar cadastro com email duplicado")
        void testCadastroComEmailDuplicado() {
                RegisterRequest request1 = new RegisterRequest(
                                "Usuario Um",
                                "user7@example.com",
                                "senha123");

                usuarioService.cadastrar(request1);

                RegisterRequest request2 = new RegisterRequest(
                                "Usuario Dois",
                                "user7@example.com",
                                "outraSenha456");

                assertThrows(RuntimeException.class,
                                () -> usuarioService.cadastrar(request2));
        }

        @Test
        @DisplayName("Deve rejeitar login com email não cadastrado")
        void testLoginComEmailNaoCadastrado() {
                LoginRequest request = new LoginRequest(
                                "user8@example.com",
                                "senhaQualquer");

                assertThrows(RuntimeException.class, () -> usuarioService.login(request));
        }

        @Test
        @DisplayName("Deve rejeitar login com senha incorreta")
        void testLoginComSenhaIncorreta() {
                RegisterRequest registerRequest = new RegisterRequest(
                                "Usuario Teste",
                                "user9@example.com",
                                "senhaCorreta123");
                usuarioService.cadastrar(registerRequest);

                LoginRequest loginRequest = new LoginRequest(
                                "user9@example.com",
                                "senhaIncorreta999");

                assertThrows(RuntimeException.class,
                                () -> usuarioService.login(loginRequest));
        }

        @Test
        @DisplayName("Deve listar usuario cadastrado")
        void testListarValido() {
                RegisterRequest request = new RegisterRequest(
                                "João Silva",
                                "user10@example.com",
                                "senha123");

                usuarioService.cadastrar(request);

                boolean included = false;

                for (final Usuario usuario : usuarioService.list()) {
                        if (usuario.getEmail() == "user10@example.com") {
                                included = true;
                        }
                }

                assertTrue(included);
        }

        @Test
        @DisplayName("Deve retornar usuario cadastrado pelo id")
        void testLerValido() {
                RegisterRequest request = new RegisterRequest(
                                "João Silva",
                                "user11@example.com",
                                "senha123");

                usuarioService.cadastrar(request);

                usuarioService.read(Long.valueOf(0));
        }

        @Test
        @DisplayName("Deve retornar usuario cadastrado pelo id")
        void testAtualizarValido() {
                RegisterRequest request = new RegisterRequest(
                                "João Silva",
                                "user12@example.com",
                                "senha123");

                usuarioService.cadastrar(request);

                usuarioService.update(Long.valueOf(1), new Usuario("Novo Nome Exemplo", "user12@example.com",
                                "senha123"));
        }

        @Test
        @DisplayName("Deve retornar usuario cadastrado pelo id")
        void testRemoverValido() {
                RegisterRequest request = new RegisterRequest(
                                "João Silva",
                                "user13@example.com",
                                "senha123");

                usuarioService.cadastrar(request);

                usuarioService.delete(Long.valueOf(1));
        }
}
