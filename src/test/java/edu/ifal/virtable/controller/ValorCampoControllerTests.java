package edu.ifal.virtable.controller;

import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import edu.ifal.virtable.helper.TipoValor;
import edu.ifal.virtable.model.ficha.CampoFicha;
import edu.ifal.virtable.model.ficha.ValorCampo;
import edu.ifal.virtable.service.ValorCampoService;

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
@DisplayName("ValorCampoController - Testes unitários")
class ValorCampoControllerTests {

        @Mock
        private ValorCampoService valorCampoService;

        private MockMvc mockMvc;

        private JsonMapper jsonMapper;

        private TipoValor tipoValorValido;

        @BeforeEach
        void setup() {
                JacksonJsonHttpMessageConverter converter = new JacksonJsonHttpMessageConverter();

                jsonMapper = converter.getMapper();

                tipoValorValido = TipoValor.values()[0];

                ValorCampoController controller = new ValorCampoController(valorCampoService);

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
                campoFicha.setNome("Pontos de vida");
                campoFicha.setTipoValor(tipoValorValido);

                return campoFicha;
        }

        private ValorCampo criarValorCampo(
                        Long id,
                        String valor) {
                ValorCampo valorCampo = new ValorCampo();

                valorCampo.setId(id);

                /*
                 * CampoFicha precisa ser definido antes do valor,
                 * pois setValor() acessa campoFicha.getTipoValor().
                 */
                valorCampo.setCampoFicha(criarCampoFicha());
                valorCampo.setValor(valor);

                return valorCampo;
        }

        private String criarCorpoJson(String valor) throws Exception {
                ObjectNode campoFicha = jsonMapper.createObjectNode();

                campoFicha.put("id", 1L);
                campoFicha.put("nome", "Pontos de vida");
                campoFicha.put(
                                "tipoValor",
                                valorJsonDoEnum(tipoValorValido));

                ObjectNode corpo = jsonMapper.createObjectNode();

                /*
                 * A ordem é importante por causa do método setValor().
                 */
                corpo.set("campoFicha", campoFicha);
                corpo.put("valor", valor);

                return jsonMapper.writeValueAsString(corpo);
        }

        @Test
        @DisplayName("Deve criar um valor de campo válido")
        void testCriarValorCampoValido() throws Exception {
                when(valorCampoService.create(any(ValorCampo.class)))
                                .thenAnswer(invocation -> {
                                        ValorCampo valorCampo = invocation.getArgument(0);

                                        valorCampo.setId(1L);

                                        return valorCampo;
                                });

                String corpo = criarCorpoJson("10");

                mockMvc.perform(
                                post("/valores-campo")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.valor").value("10"))
                                .andExpect(jsonPath("$.campoFicha.id").value(1));

                verify(valorCampoService)
                                .create(any(ValorCampo.class));
        }

        @Test
        @DisplayName("Não deve criar valor de campo sem valor")
        void testCriarValorCampoSemValor() throws Exception {
                ObjectNode campoFicha = jsonMapper.createObjectNode();

                campoFicha.put("id", 1L);
                campoFicha.put("nome", "Pontos de vida");
                campoFicha.put(
                                "tipoValor",
                                valorJsonDoEnum(tipoValorValido));

                ObjectNode corpo = jsonMapper.createObjectNode();

                corpo.set("campoFicha", campoFicha);

                mockMvc.perform(
                                post("/valores-campo")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(
                                                                jsonMapper.writeValueAsString(corpo)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Deve listar os valores de campo")
        void testListarValoresCampo() throws Exception {
                ValorCampo valor1 = criarValorCampo(1L, "10");

                ValorCampo valor2 = criarValorCampo(2L, "20");

                when(valorCampoService.list())
                                .thenReturn(List.of(valor1, valor2));

                mockMvc.perform(get("/valores-campo"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].valor").value("10"))
                                .andExpect(jsonPath("$[1].id").value(2))
                                .andExpect(jsonPath("$[1].valor").value("20"));

                verify(valorCampoService).list();
        }

        @Test
        @DisplayName("Deve buscar um valor de campo pelo ID")
        void testLerValorCampoValido() throws Exception {
                ValorCampo valorCampo = criarValorCampo(1L, "15");

                when(valorCampoService.read(1L))
                                .thenReturn(valorCampo);

                mockMvc.perform(
                                get("/valores-campo/{id}", 1L))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.valor").value("15"))
                                .andExpect(jsonPath("$.campoFicha.id").value(1));

                verify(valorCampoService).read(1L);
        }

        @Test
        @DisplayName("Deve atualizar um valor de campo")
        void testAtualizarValorCampoValido() throws Exception {
                when(
                                valorCampoService.update(
                                                eq(1L),
                                                any(ValorCampo.class)))
                                .thenAnswer(invocation -> {
                                        Long id = invocation.getArgument(0);
                                        ValorCampo valorCampo = invocation.getArgument(1);

                                        valorCampo.setId(id);

                                        return valorCampo;
                                });

                String corpo = criarCorpoJson("25");

                mockMvc.perform(
                                put("/valores-campo/{id}", 1L)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(corpo))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(
                                                MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.valor").value("25"))
                                .andExpect(jsonPath("$.campoFicha.id").value(1));

                verify(valorCampoService).update(
                                eq(1L),
                                any(ValorCampo.class));
        }

        @Test
        @DisplayName("Deve remover um valor de campo")
        void testRemoverValorCampoValido() throws Exception {
                when(valorCampoService.delete(1L))
                                .thenReturn(null);

                mockMvc.perform(
                                delete("/valores-campo/{id}", 1L))
                                .andExpect(status().isOk());

                verify(valorCampoService).delete(1L);
        }

        @Test
        @DisplayName("Deve retornar uma lista vazia")
        void testListarValoresCampoVazio() throws Exception {
                when(valorCampoService.list())
                                .thenReturn(List.of());

                mockMvc.perform(get("/valores-campo"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$").isArray())
                                .andExpect(jsonPath("$").isEmpty());

                verify(valorCampoService).list();
        }
}