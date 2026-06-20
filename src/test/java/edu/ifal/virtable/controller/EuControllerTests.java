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
@DisplayName("EuController - Testes Unitários")
class EuControllerTests {

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
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").isString())
                                .andReturn();

                JsonNode responseBody = objectMapper.readTree(result.getResponse().getContentAsString());
                return responseBody.get("token").asText();
        }

        @Test
        void testLerEuValido() throws Exception {
                String email = "usuario2@example.com";
                String token = cadastrar(email);

                mockMvc.perform(get("/eu")
                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.nome").value("Nome Exemplo"))
                                .andExpect(jsonPath("$.email").value(email));
        }

        @Test
        void testAtualizarEuValido() throws Exception {
                String email = "usuario3@example.com";
                String token = cadastrar(email);

                var updateBody = String.format(
                                "{\"nome\": \"Outro Nome Exemplo\", \"email\": \"%s\", \"senha\": \"senha123\"}",
                                email);

                mockMvc.perform(put("/eu")
                                .header("Authorization", "Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateBody))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.nome").value("Outro Nome Exemplo"))
                                .andExpect(jsonPath("$.email").value(email));
        }

        @Test
        @DisplayName("Deve aceitar remover usuário atual")
        void testRemoverEuValido() throws Exception {
                String email = "usuario4@example.com";
                String token = cadastrar(email);

                mockMvc.perform(delete("/eu")
                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.result").exists());
        }

        @Test
        @DisplayName("Ler usuário atual sem token deve retornar 401")
        void testLerEuDesautorizado() throws Exception {

                mockMvc.perform(get("/eu"))
                                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Atualizar usuário atual sem token deve retornar 401")
        void testAtualizarEuDesautorizado() throws Exception {
                Long invalidId = 99999L;
                var updateBody = String.format(
                                "{\"value\": {\"id\": %d, \"nome\": \"Atualizado\", \"email\": \"novo@test.com\", \"senha\": \"123\"}}",
                                invalidId);
                mockMvc.perform(put("/eu")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateBody))
                                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Deletar usuário atual sem token deve retornar 401")
        void testRemoverEuDesautorizado() throws Exception {

                mockMvc.perform(delete("/eu"))
                                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Atualizar usuário com request body inválido deve retornar 400")
        void testAtualizarEuCamposNulos() throws Exception {
                String email = "usuario8@example.com";
                String token = cadastrar(email);

                var invalidUpdateBody = "{\"value\": null}";

                mockMvc.perform(put("/eu")
                                .header("Authorization", "Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalidUpdateBody))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Atualizar usuário com request body vazio deve retornar 400")
        void testAtualizarEuCorpoNulo() throws Exception {
                String email = "usuario9@example.com";
                String token = cadastrar(email);

                mockMvc.perform(put("/eu")
                                .header("Authorization", "Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(""))
                                .andExpect(status().isInternalServerError());
        }
}
