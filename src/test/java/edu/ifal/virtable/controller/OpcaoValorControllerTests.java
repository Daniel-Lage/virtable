package edu.ifal.virtable.controller;

import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import edu.ifal.virtable.helper.TipoValor;
import edu.ifal.virtable.model.ficha.CampoFicha;
import edu.ifal.virtable.model.ficha.OpcaoValor;
import edu.ifal.virtable.service.OpcaoValorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("OpcaoValorController - Testes unitários")
class OpcaoValorControllerTests {

        @Mock
        private OpcaoValorService opcaoValorService;

        private MockMvc mockMvc;

        private JsonMapper jsonMapper;

        private TipoValor tipoValorValido;

        @BeforeEach
        void setup() {
                JacksonJsonHttpMessageConverter converter = new JacksonJsonHttpMessageConverter();

                jsonMapper = converter.getMapper();

                tipoValorValido = TipoValor.values()[0];

                OpcaoValorController controller = new OpcaoValorController(opcaoValorService);

                mockMvc = MockMvcBuilders
                                .standaloneSetup(controller)
                                .setMessageConverters(converter)
                                .build();
        }

        private String valorJsonDoEnum(Enum<?> valor) {
                return jsonMapper
                                .valueToTree(valor)
                                .asString();
        }

        private CampoFicha criarCampoFicha() {
                CampoFicha campoFicha = new CampoFicha();

                campoFicha.setId(1L);
                campoFicha.setNome("Campo de teste");
                campoFicha.setTipoValor(tipoValorValido);

                return campoFicha;
        }

        private OpcaoValor criarOpcao(
                        Long id,
                        String valor) {
                return new OpcaoValor(
                                id,
                                criarCampoFicha(),
                                null,
                                valor);
        }

        private String criarCorpoJson(String valor) throws Exception {
                ObjectNode campoFicha = jsonMapper.createObjectNode();

                campoFicha.put("id", 1L);
                campoFicha.put("nome", "Campo de teste");
                campoFicha.put(
                                "tipoValor",
                                valorJsonDoEnum(tipoValorValido));

                ObjectNode corpo = jsonMapper.createObjectNode();

                /*
                 * CampoFicha vem antes de valor porque setValor()
                 * acessa campoFicha.getTipoValor().
                 */
                corpo.set("campoFicha", campoFicha);
                corpo.put("valor", valor);

                return jsonMapper.writeValueAsString(corpo);
        }

        @Test
        @DisplayName("Deve criar uma opção de valor válida")
        void testCriarOpcaoValorValida() throws Exception {
                when(opcaoValorService.create(any(OpcaoValor.class)))
                                .thenAnswer(invocation -> {
                                        OpcaoValor opcao = invocation.getArgument(0);

                                        opcao.setId(1L);

                                        return opcao;
                                });

                String corpo = criarCorpoJson("10");

                mockMvc.perform(
                                post("/opcoes-valor")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.valor").value("10"))
                                .andExpect(jsonPath("$.campoFicha.id").value(1));

                verify(opcaoValorService)
                                .create(any(OpcaoValor.class));
        }

        @Test
        @DisplayName("Não deve criar opção sem valor")
        void testCriarOpcaoSemValor() throws Exception {
                ObjectNode campoFicha = jsonMapper.createObjectNode();

                campoFicha.put("id", 1L);
                campoFicha.put("nome", "Campo de teste");
                campoFicha.put(
                                "tipoValor",
                                valorJsonDoEnum(tipoValorValido));

                ObjectNode corpo = jsonMapper.createObjectNode();

                corpo.set("campoFicha", campoFicha);

                mockMvc.perform(
                                post("/opcoes-valor")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(
                                                                jsonMapper.writeValueAsString(corpo)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deve listar as opções de valor")
        void testListarOpcoesValor() throws Exception {
                OpcaoValor opcao1 = criarOpcao(1L, "10");
                OpcaoValor opcao2 = criarOpcao(2L, "20");

                when(opcaoValorService.list())
                                .thenReturn(List.of(opcao1, opcao2));

                mockMvc.perform(get("/opcoes-valor"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].valor").value("10"))
                                .andExpect(jsonPath("$[1].id").value(2))
                                .andExpect(jsonPath("$[1].valor").value("20"));

                verify(opcaoValorService).list();
        }

        @Test
        @DisplayName("Deve buscar uma opção pelo ID")
        void testLerOpcaoValorValida() throws Exception {
                OpcaoValor opcao = criarOpcao(1L, "10");

                when(opcaoValorService.read(1L))
                                .thenReturn(opcao);

                mockMvc.perform(
                                get("/opcoes-valor/{id}", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.valor").value("10"))
                                .andExpect(jsonPath("$.campoFicha.id").value(1));

                verify(opcaoValorService).read(1L);
        }

        @Test
        @DisplayName("Deve atualizar uma opção de valor")
        void testAtualizarOpcaoValorValida() throws Exception {
                when(
                                opcaoValorService.update(
                                                eq(1L),
                                                any(OpcaoValor.class)))
                                .thenAnswer(invocation -> {
                                        Long id = invocation.getArgument(0);
                                        OpcaoValor opcao = invocation.getArgument(1);

                                        opcao.setId(id);

                                        return opcao;
                                });

                String corpo = criarCorpoJson("20");

                mockMvc.perform(
                                put("/opcoes-valor/{id}", 1L)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.valor").value("20"))
                                .andExpect(jsonPath("$.campoFicha.id").value(1));

                verify(opcaoValorService).update(
                                eq(1L),
                                any(OpcaoValor.class));
        }

        @Test
        @DisplayName("Deve remover uma opção de valor")
        void testRemoverOpcaoValorValida() throws Exception {
                when(opcaoValorService.delete(1L))
                                .thenReturn(null);

                mockMvc.perform(
                                delete("/opcoes-valor/{id}", 1L))
                                .andExpect(status().isOk());

                verify(opcaoValorService).delete(1L);
        }

        @Test
        @DisplayName("Deve retornar uma lista vazia")
        void testListarOpcoesValorVazia() throws Exception {
                when(opcaoValorService.list())
                                .thenReturn(List.of());

                mockMvc.perform(get("/opcoes-valor"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());

                verify(opcaoValorService).list();
        }
}