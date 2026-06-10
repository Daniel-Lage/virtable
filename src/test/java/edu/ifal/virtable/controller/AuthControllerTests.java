package edu.ifal.virtable.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.BeforeEach;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("AuthController - Testes Unitários")
class AuthControllerTests {

        @Autowired
        private WebApplicationContext webApplicationContext;
        private MockMvc mockMvc;

        @BeforeEach
        public void setup() {
                mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders
                                .webAppContextSetup(webApplicationContext)
                                .build();
        }

        @Test
        void testCadastroValido() throws Exception {
                var cadastroBody = "{\"nome\": \"Nome Exemplo\", \"email\": \"usuario1@example.com\", \"senha\": \"senha123\"}";

                mockMvc.perform(post("/auth/cadastro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(cadastroBody))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").isString());
        }

        @Test
        void testLoginValido() throws Exception {
                var cadastroBody = "{\"nome\": \"Nome Exemplo\", \"email\": \"usuario2@example.com\", \"senha\": \"senha123\"}";
                var loginBody = "{\"email\": \"usuario2@example.com\", \"senha\": \"senha123\"}";

                mockMvc.perform(post("/auth/cadastro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(cadastroBody));

                mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginBody))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").isString());
        }

        @Test
        void testCadastroRepetido() throws Exception {
                var cadastroBody = "{\"nome\": \"Nome Exemplo\", \"email\": \"usuario3@example.com\", \"senha\": \"senha123\"}";

                mockMvc.perform(post("/auth/cadastro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(cadastroBody));

                mockMvc.perform(post("/auth/cadastro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(cadastroBody))
                                .andExpect(status().isInternalServerError())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.error").value("Erro de Tempo de Execução"))
                                .andExpect(jsonPath("$.message").value("Email já cadastrado"));
        }

        @Test
        void testCadastroInvalido() throws Exception {
                var cadastroBody = "{\"nome\": \"Nome Exemplo\", \"email\": \"usuario4@example.com\", \"senha\": \"senha123\"}";

                mockMvc.perform(post("/auth/cadastro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(cadastroBody))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").isString());
        }

        @Test
        void testLoginCamposEmBranco() throws Exception {
                var loginBody = "{\"email\":\"\",\"senha\":\"\"}";

                mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginBody))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.error").value("Erro de Validação"))
                                .andExpect(jsonPath("$.fields.email", hasItem("Não deve estar em branco")))
                                .andExpect(jsonPath("$.fields.senha", hasItem("Não deve estar em branco")));
        }

        @Test
        void testLoginCamposNulos() throws Exception {
                var loginBody = "{\"email\":null,\"senha\":null}";

                mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginBody))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.error").value("Erro de Validação"))
                                .andExpect(jsonPath("$.fields.email", hasItem("Não deve ser nulo")))
                                .andExpect(jsonPath("$.fields.senha", hasItem("Não deve ser nulo")));
        }

        @Test
        void testLoginEmailInvalido() throws Exception {
                var loginBody = "{\"email\":\"invalid-email\",\"senha\":\"senha123\"}";

                mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginBody))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.error").value("Erro de Validação"))
                                .andExpect(jsonPath("$.fields.email", hasItem("Deve ser um email válido")));
        }
}
