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

import edu.ifal.virtable.model.dado.Modificador;
import edu.ifal.virtable.service.ModificadorService;

@ExtendWith(MockitoExtension.class)
@DisplayName("ModificadorController - Testes unitários")
class ModificadorControllerTests {

        @Mock
        private ModificadorService modificadorService;

        private MockMvc mockMvc;

        @BeforeEach
        void setup() {
                ModificadorController controller = new ModificadorController(modificadorService);

                mockMvc = MockMvcBuilders
                                .standaloneSetup(controller)
                                .build();
        }

        private Modificador criarModificador(
                        Long id,
                        int multiplicador) {
                Modificador modificador = new Modificador();

                modificador.setId(id);
                modificador.setMultiplicador(multiplicador);

                return modificador;
        }

        @Test
        @DisplayName("Deve criar um modificador")
        void testCriarModificadorValido() throws Exception {

                when(modificadorService.create(any(Modificador.class)))
                                .thenAnswer(invocation -> {
                                        Modificador modificador = invocation.getArgument(0);

                                        modificador.setId(1L);

                                        return modificador;
                                });

                String corpo = """
                                {
                                  "multiplicador": 2
                                }
                                """;

                mockMvc.perform(
                                post("/modificadores")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.multiplicador").value(2));

                verify(modificadorService)
                                .create(any(Modificador.class));
        }

        @Test
        @DisplayName("NÃo deve criar modificador com multiplicador nulo")
        void testCriarModificadorMultiplicadorNulo() throws Exception {

                String corpo = """
                                {
                                  "multiplicador": null
                                }
                                """;

                mockMvc.perform(
                                post("/modificadores")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deve listar os modificadores")
        void testListarModificadores() throws Exception {

                Modificador modificador1 = criarModificador(1L, 2);

                Modificador modificador2 = criarModificador(2L, 5);

                when(modificadorService.list())
                                .thenReturn(List.of(
                                                modificador1,
                                                modificador2));

                mockMvc.perform(get("/modificadores"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].multiplicador").value(2))
                                .andExpect(jsonPath("$[1].id").value(2))
                                .andExpect(jsonPath("$[1].multiplicador").value(5));

                verify(modificadorService).list();
        }

        @Test
        @DisplayName("Deve buscar um modificador pelo ID")
        void testLerModificadorValido() throws Exception {

                Modificador modificador = criarModificador(1L, 3);

                when(modificadorService.read(1L))
                                .thenReturn(modificador);

                mockMvc.perform(get("/modificadores/{id}", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.multiplicador").value(3));

                verify(modificadorService).read(1L);
        }

        @Test
        @DisplayName("Deve atualizar um modificador")
        void testAtualizarModificadorValido() throws Exception {

                when(
                                modificadorService.update(
                                                eq(1L),
                                                any(Modificador.class)))
                                .thenAnswer(invocation -> {
                                        Long id = invocation.getArgument(0);
                                        Modificador modificador = invocation.getArgument(1);

                                        modificador.setId(id);

                                        return modificador;
                                });

                String corpo = """
                                {
                                  "multiplicador": 4
                                }
                                """;

                mockMvc.perform(
                                put("/modificadores/{id}", 1L)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.multiplicador").value(4));

                verify(modificadorService).update(
                                eq(1L),
                                any(Modificador.class));
        }

        @Test
        @DisplayName("Deve remover um modificador")
        void testRemoverModificadorValido() throws Exception {

                when(modificadorService.delete(1L))
                                .thenReturn(null);

                mockMvc.perform(
                                delete("/modificadores/{id}", 1L))
                                .andExpect(status().isOk());

                verify(modificadorService).delete(1L);
        }

        @Test
        @DisplayName("Deve retornar lista vazia")
        void testListarModificadoresVazio() throws Exception {

                when(modificadorService.list())
                                .thenReturn(List.of());

                mockMvc.perform(get("/modificadores"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());

                verify(modificadorService).list();
        }
}
