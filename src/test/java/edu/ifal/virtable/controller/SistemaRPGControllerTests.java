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

import edu.ifal.virtable.model.sistema.SistemaRPG;
import edu.ifal.virtable.service.CampanhaService;
import edu.ifal.virtable.service.CampoFichaService;
import edu.ifal.virtable.service.DadoService;
import edu.ifal.virtable.service.PersonagemService;
import edu.ifal.virtable.service.SistemaRPGService;

@ExtendWith(MockitoExtension.class)
@DisplayName("SistemaRPGController - Testes unitários")
class SistemaRPGControllerTests {

        @Mock
        private SistemaRPGService sistemaRPGService;

        @Mock
        private CampanhaService campanhaService;

        @Mock
        private CampoFichaService campoFichaService;

        @Mock
        private DadoService dadoService;

        @Mock
        private PersonagemService personagemService;

        private MockMvc mockMvc;

        @BeforeEach
        void setup() {
                SistemaRPGController controller = new SistemaRPGController(
                                sistemaRPGService,
                                campanhaService,
                                campoFichaService,
                                dadoService,
                                personagemService

                );

                mockMvc = MockMvcBuilders
                                .standaloneSetup(controller)
                                .build();
        }

        private SistemaRPG criarSistema(Long id, String nome) {
                SistemaRPG sistemaRPG = new SistemaRPG();

                sistemaRPG.setId(id);
                sistemaRPG.setNome(nome);

                return sistemaRPG;
        }

        @Test
        @DisplayName("Deve criar um sistema RPG")
        void testCriarSistemaRPGValido() throws Exception {
                when(sistemaRPGService.create(any(SistemaRPG.class)))
                                .thenAnswer(invocation -> {
                                        SistemaRPG sistema = invocation.getArgument(0);
                                        sistema.setId(1L);

                                        return sistema;
                                });

                String corpo = """
                                {
                                  "nome": "Dungeons & Dragons 5e"
                                }
                                """;

                mockMvc.perform(
                                post("/sistemas-rpg")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.nome")
                                                .value("Dungeons & Dragons 5e"));

                verify(sistemaRPGService)
                                .create(any(SistemaRPG.class));
        }

        @Test
        @DisplayName("Não deve criar sistema com nome em branco")
        void testCriarSistemaRPGNomeEmBranco() throws Exception {
                String corpo = """
                                {
                                  "nome": ""
                                }
                                """;

                mockMvc.perform(
                                post("/sistemas-rpg")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Não deve criar sistema com nome menor que dois caracteres")
        void testCriarSistemaRPGNomeMuitoCurto() throws Exception {
                String corpo = """
                                {
                                  "nome": "D"
                                }
                                """;

                mockMvc.perform(
                                post("/sistemas-rpg")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deve listar os sistemas RPG")
        void testListarSistemasRPG() throws Exception {
                SistemaRPG sistema1 = criarSistema(
                                1L,
                                "Dungeons & Dragons 5e");

                SistemaRPG sistema2 = criarSistema(
                                2L,
                                "Pathfinder 2e");

                when(sistemaRPGService.list())
                                .thenReturn(List.of(sistema1, sistema2));

                mockMvc.perform(get("/sistemas-rpg"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].nome")
                                                .value("Dungeons & Dragons 5e"))
                                .andExpect(jsonPath("$[1].id").value(2))
                                .andExpect(jsonPath("$[1].nome")
                                                .value("Pathfinder 2e"));

                verify(sistemaRPGService).list();
        }

        @Test
        @DisplayName("Deve buscar um sistema RPG pelo ID")
        void testLerSistemaRPGValido() throws Exception {
                SistemaRPG sistema = criarSistema(
                                1L,
                                "Dungeons & Dragons 5e");

                when(sistemaRPGService.read(1L))
                                .thenReturn(sistema);

                mockMvc.perform(get("/sistemas-rpg/{id}", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.nome")
                                                .value("Dungeons & Dragons 5e"));

                verify(sistemaRPGService).read(1L);
        }

        @Test
        @DisplayName("Deve atualizar um sistema RPG")
        void testAtualizarSistemaRPGValido() throws Exception {
                when(
                                sistemaRPGService.update(
                                                eq(1L),
                                                any(SistemaRPG.class)))
                                .thenAnswer(invocation -> {
                                        Long id = invocation.getArgument(0);
                                        SistemaRPG sistema = invocation.getArgument(1);

                                        sistema.setId(id);

                                        return sistema;
                                });

                String corpo = """
                                {
                                  "nome": "Pathfinder 2e"
                                }
                                """;

                mockMvc.perform(
                                put("/sistemas-rpg/{id}", 1L)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.nome")
                                                .value("Pathfinder 2e"));

                verify(sistemaRPGService).update(
                                eq(1L),
                                any(SistemaRPG.class));
        }

        @Test
        @DisplayName("Deve remover um sistema RPG")
        void testRemoverSistemaRPGValido() throws Exception {
                when(sistemaRPGService.delete(1L))
                                .thenReturn(null);

                mockMvc.perform(
                                delete("/sistemas-rpg/{id}", 1L))
                                .andExpect(status().isOk());

                verify(sistemaRPGService).delete(1L);
        }

        @Test
        @DisplayName("Deve listar as campanhas de um sistema RPG")
        void testListarCampanhasDoSistema() throws Exception {
                when(campanhaService.listByIdSistemaRPG(1L))
                                .thenReturn(List.of());

                mockMvc.perform(
                                get("/sistemas-rpg/{id}/campanhas", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());

                verify(campanhaService)
                                .listByIdSistemaRPG(1L);
        }

        @Test
        @DisplayName("Deve listar os campos de ficha de um sistema RPG")
        void testListarCamposFichaDoSistema() throws Exception {
                when(campoFichaService.listByIdSistemaRPG(1L))
                                .thenReturn(List.of());

                mockMvc.perform(
                                get("/sistemas-rpg/{id}/campos_ficha", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());

                verify(campoFichaService)
                                .listByIdSistemaRPG(1L);
        }

        @Test
        @DisplayName("Deve listar os dados de um sistema RPG")
        void testListarDadosDoSistema() throws Exception {
                when(dadoService.listByIdSistemaRPG(1L))
                                .thenReturn(List.of());

                mockMvc.perform(
                                get("/sistemas-rpg/{id}/dados", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());

                verify(dadoService)
                                .listByIdSistemaRPG(1L);
        }

        @Test
        @DisplayName("Deve listar os personagens de um sistema RPG")
        void testListarPersonagensDoSistema() throws Exception {
                when(personagemService.listByIdSistemaRPG(1L))
                                .thenReturn(List.of());

                mockMvc.perform(
                                get("/sistemas-rpg/{id}/personagens", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());

                verify(personagemService)
                                .listByIdSistemaRPG(1L);
        }
}