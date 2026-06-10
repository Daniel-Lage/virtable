package edu.ifal.virtable.service;

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
@DisplayName("AuthService - Testes Unitários")
public class AuthServiceTests {

        @Autowired
        private AuthService authService;

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
                                "joao@example.com",
                                "senha123");

                AuthResponse response = authService.cadastrar(request);

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
                                "maria@example.com",
                                "senha456");
                authService.cadastrar(registerRequest);

                LoginRequest loginRequest = new LoginRequest(
                                "maria@example.com",
                                "senha456");

                AuthResponse response = authService.login(loginRequest);

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
                                "teste@example.com",
                                "senha789");

                Exception exception = assertThrows(Exception.class, () -> authService.cadastrar(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar cadastro com email vazio")
        void testCadastroComEmailVazio() {
                RegisterRequest request = new RegisterRequest(
                                "Pedro Costa",
                                "",
                                "senha789");

                Exception exception = assertThrows(Exception.class, () -> authService.cadastrar(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar cadastro com email inválido")
        void testCadastroComEmailInvalido() {
                RegisterRequest request = new RegisterRequest(
                                "Lucas Oliveira",
                                "email-invalido",
                                "senha789");

                Exception exception = assertThrows(Exception.class, () -> authService.cadastrar(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar cadastro com senha vazia")
        void testCadastroComSenhaVazia() {
                RegisterRequest request = new RegisterRequest(
                                "Ana Silva",
                                "ana@example.com",
                                "");

                Exception exception = assertThrows(Exception.class, () -> authService.cadastrar(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar login com email vazio")
        void testLoginComEmailVazio() {
                LoginRequest request = new LoginRequest(
                                "",
                                "senhaQualquer");

                Exception exception = assertThrows(Exception.class, () -> authService.login(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar login com email inválido")
        void testLoginComEmailInvalido() {
                LoginRequest request = new LoginRequest(
                                "nao-eh-email",
                                "senhaQualquer");

                Exception exception = assertThrows(Exception.class, () -> authService.login(request));
                assertNotNull(exception);
        }

        @Test
        @DisplayName("Deve rejeitar login com senha vazia")
        void testLoginComSenhaVazia() {
                LoginRequest request = new LoginRequest(
                                "usuario@example.com",
                                "");

                Exception exception = assertThrows(Exception.class, () -> authService.login(request));
                assertNotNull(exception);
        }

        // ====== TESTES DE EMAIL DUPLICADO OU NÃO CADASTRADO ======

        @Test
        @DisplayName("Deve rejeitar cadastro com email duplicado")
        void testCadastroComEmailDuplicado() {
                RegisterRequest request1 = new RegisterRequest(
                                "Usuario Um",
                                "duplicado@example.com",
                                "senha123");

                authService.cadastrar(request1);

                RegisterRequest request2 = new RegisterRequest(
                                "Usuario Dois",
                                "duplicado@example.com",
                                "outraSenha456");

                RuntimeException exception = assertThrows(RuntimeException.class,
                                () -> authService.cadastrar(request2));
                assertEquals("Email já cadastrado", exception.getMessage());
        }

        @Test
        @DisplayName("Deve rejeitar login com email não cadastrado")
        void testLoginComEmailNaoCadastrado() {
                LoginRequest request = new LoginRequest(
                                "nuncaficadastrado@example.com",
                                "senhaQualquer");

                RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(request));
                assertEquals("Usuário não encontrado", exception.getMessage());
        }

        @Test
        @DisplayName("Deve rejeitar login com senha incorreta")
        void testLoginComSenhaIncorreta() {
                RegisterRequest registerRequest = new RegisterRequest(
                                "Usuario Teste",
                                "usuario@example.com",
                                "senhaCorreta123");
                authService.cadastrar(registerRequest);

                LoginRequest loginRequest = new LoginRequest(
                                "usuario@example.com",
                                "senhaIncorreta999");

                RuntimeException exception = assertThrows(RuntimeException.class,
                                () -> authService.login(loginRequest));
                assertEquals("Senha inválida", exception.getMessage());
        }
}
