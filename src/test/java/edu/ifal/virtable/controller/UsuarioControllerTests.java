package edu.ifal.virtable.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("UsuarioController - Testes Unitários")
class UsuarioControllerTests {

        @Autowired
        private WebApplicationContext webApplicationContext;
        private MockMvc mockMvc;
        private final ObjectMapper objectMapper = new ObjectMapper();

        @BeforeEach
        public void setup() {
                mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders
                                .webAppContextSetup(webApplicationContext).apply(springSecurity())
                                .build();
        }

        private String cadastrar(String email) throws Exception {
                var cadastroBody = String.format(
                                "{\"nome\": \"Nome Exemplo\", \"email\": \"%s\", \"senha\": \"senha123\"}",
                                email);

                MvcResult result = mockMvc.perform(post("/auth/cadastro")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(cadastroBody))
                                .andExpect(status().isOk())
                                .andReturn();

                JsonNode responseBody = objectMapper.readTree(result.getResponse().getContentAsString());
                return responseBody.get("token").asText();
        }

        private Long retornaUsuario(String token, String email) throws Exception {
                MvcResult result = mockMvc.perform(get("/eu")
                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.email").value(email))
                                .andReturn();

                JsonNode responseBody = objectMapper.readTree(result.getResponse().getContentAsString());

                return responseBody.get("id").asLong();
        }

        @Test
        void testListarUsuariosValido() throws Exception {
                String email = "usuario1@example.com";
                String token = cadastrar(email);

                mockMvc.perform(get("/usuarios")
                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$[*].email", hasItem(email)));
        }

        @Test
        void testLerUsuarioValido() throws Exception {
                String email = "usuario2@example.com";
                String token = cadastrar(email);
                Long id = retornaUsuario(token, email);

                mockMvc.perform(get("/usuarios/{id}", id)
                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.email").value(email))
                                .andExpect(jsonPath("$.id").value(id));
        }

        @Test
        void testRemoverUsuarioValido() throws Exception {
                String email = "usuario4@example.com";
                String token = cadastrar(email);
                Long id = retornaUsuario(token, email);

                mockMvc.perform(delete("/usuarios/{id}", id)
                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.result").exists());
        }

        @Test
        @DisplayName("Ler usuário inexistente deve retornar 500")
        void testLerUsuarioInexistente() throws Exception {
                String email = "usuario5@example.com";
                String token = cadastrar(email);
                Long invalidId = 99999L;

                mockMvc.perform(get("/usuarios/{id}", invalidId)
                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isInternalServerError())
                                .andExpect(jsonPath("$.message").value("Item não encontrado"));
        }

        @Test
        @DisplayName("Atualizar usuário inexistente deve retornar 500")
        void testAtualizarUsuarioInexistente() throws Exception {
                String email = "usuario6@example.com";
                String token = cadastrar(email);
                Long invalidId = 99999L;
                var updateBody = String.format(
                                "{\"value\": {\"id\": %d, \"nome\": \"Atualizado\", \"email\": \"novo@test.com\", \"senha\": \"123\"}}",
                                invalidId);

                mockMvc.perform(put("/usuarios/{id}", invalidId)
                                .header("Authorization", "Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateBody))
                                .andExpect(status().isMethodNotAllowed())
                                .andExpect(jsonPath("$.error").value("Erro de método não suportado"));
        }

        @Test
        @DisplayName("Deletar usuário inexistente deve retornar 500")
        void testRemoverUsuarioInexistente() throws Exception {
                String email = "usuario7@example.com";
                String token = cadastrar(email);
                Long invalidId = 99999L;

                mockMvc.perform(delete("/usuarios/{id}", invalidId)
                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isInternalServerError())
                                .andExpect(jsonPath("$.message").value("Item não encontrado"));
        }

}
