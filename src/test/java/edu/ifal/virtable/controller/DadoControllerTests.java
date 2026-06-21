package edu.ifal.virtable.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import edu.ifal.virtable.model.dado.Dado;
import edu.ifal.virtable.service.DadoService;
import edu.ifal.virtable.service.ModificadorService;

@ExtendWith(MockitoExtension.class)
@DisplayName("DadoController - Testes unitários")
class DadoControllerTests {

        @Mock
        private DadoService dadoService;

        @Mock
        private ModificadorService modificadorService;

        private MockMvc mockMvc;

        @BeforeEach
        void setup() {
                DadoController controller = new DadoController(
                                dadoService,
                                modificadorService);

                mockMvc = MockMvcBuilders
                                .standaloneSetup(controller)
                                .build();
        }

        private Dado criarDado(
                        Long id,
                        String nome,
                        int limite) {
                Dado dado = new Dado();

                dado.setId(id);
                dado.setNome(nome);
                dado.setLimite(limite);

                return dado;
        }

        @Test
        @DisplayName("Deve criar um dado válido")
        void testCriarDadoValido() throws Exception {

                when(dadoService.create(any(Dado.class)))
                                .thenAnswer(invocation -> {
                                        Dado dado = invocation.getArgument(0);
                                        dado.setId(1L);

                                        return dado;
                                });

                String corpo = """
                                {
                                  "nome": "d20",
                                  "limite": 20
                                }
                                """;

                mockMvc.perform(
                                post("/dados")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("d20"))
                                .andExpect(jsonPath("$.limite").value(20));

                verify(dadoService).create(any(Dado.class));
        }

        @Test
        @DisplayName("Não deve criar dado com nome em branco")
        void testCriarDadoNomeEmBranco() throws Exception {

                String corpo = """
                                {
                                  "nome": "",
                                  "limite": 20
                                }
                                """;

                mockMvc.perform(
                                post("/dados")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("NÃ£o deve criar dado com limite nulo")
        void testCriarDadoLimiteNulo() throws Exception {

                String corpo = """
                                {
                                  "nome": "d20",
                                  "limite": null
                                }
                                """;

                mockMvc.perform(
                                post("/dados")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deve listar os dados")
        void testListarDados() throws Exception {

                Dado dado1 = criarDado(1L, "d20", 20);
                Dado dado2 = criarDado(2L, "d6", 6);

                when(dadoService.list())
                                .thenReturn(List.of(dado1, dado2));

                mockMvc.perform(get("/dados"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].name").value("d20"))
                                .andExpect(jsonPath("$[0].limite").value(20))
                                .andExpect(jsonPath("$[1].id").value(2))
                                .andExpect(jsonPath("$[1].name").value("d6"))
                                .andExpect(jsonPath("$[1].limite").value(6));

                verify(dadoService).list();
        }

        @Test
        @DisplayName("Deve buscar um dado pelo ID")
        void testLerDadoValido() throws Exception {

                Dado dado = criarDado(1L, "d20", 20);

                when(dadoService.read(1L))
                                .thenReturn(dado);

                mockMvc.perform(get("/dados/{id}", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("d20"))
                                .andExpect(jsonPath("$.limite").value(20));

                verify(dadoService).read(1L);
        }

        @Test
        @DisplayName("Deve atualizar um dado")
        void testAtualizarDadoValido() throws Exception {

                when(dadoService.update(eq(1L), any(Dado.class)))
                                .thenAnswer(invocation -> {
                                        Long id = invocation.getArgument(0);
                                        Dado dado = invocation.getArgument(1);

                                        dado.setId(id);

                                        return dado;
                                });

                String corpo = """
                                {
                                  "nome": "d12",
                                  "limite": 12
                                }
                                """;

                mockMvc.perform(
                                put("/dados/{id}", 1L)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("d12"))
                                .andExpect(jsonPath("$.limite").value(12));

                verify(dadoService).update(
                                eq(1L),
                                any(Dado.class));
        }

        @Test
        @DisplayName("Deve remover um dado")
        void testRemoverDadoValido() throws Exception {

                when(dadoService.delete(1L))
                                .thenReturn(null);

                mockMvc.perform(delete("/dados/{id}", 1L))
                                .andExpect(status().isOk());

                verify(dadoService).delete(1L);
        }

        @Test
        @DisplayName("Deve listar os modificadores de um dado")
        void testListarModificadores() throws Exception {

                when(modificadorService.listByIdDado(1L))
                                .thenReturn(List.of());

                mockMvc.perform(get("/dados/{id}/modificadores", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());

                verify(modificadorService).listByIdDado(1L);
        }
}
