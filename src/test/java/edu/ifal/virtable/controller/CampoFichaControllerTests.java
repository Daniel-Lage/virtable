package edu.ifal.virtable.controller;

import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import edu.ifal.virtable.helper.TipoCampo;
import edu.ifal.virtable.helper.TipoValor;
import edu.ifal.virtable.model.ficha.CampoFicha;
import edu.ifal.virtable.service.CampoFichaService;
import edu.ifal.virtable.service.ModificadorService;
import edu.ifal.virtable.service.OpcaoValorService;
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
@DisplayName("CampoFichaController - Testes unitários")
class CampoFichaControllerTests {

    @Mock
    private CampoFichaService campoFichaService;

    @Mock
    private ValorCampoService valorCampoService;

    @Mock
    private OpcaoValorService opcaoValorService;

    @Mock
    private ModificadorService modificadorService;

    private MockMvc mockMvc;

    private JsonMapper jsonMapper;

    private TipoCampo tipoCampoValido;

    private TipoValor tipoValorValido;

    @BeforeEach
    void setup() {
        JacksonJsonHttpMessageConverter converter =
                new JacksonJsonHttpMessageConverter();

        jsonMapper = converter.getMapper();

        tipoCampoValido = TipoCampo.values()[0];
        tipoValorValido = TipoValor.values()[0];

        CampoFichaController controller =
                new CampoFichaController(
                        campoFichaService,
                        valorCampoService,
                        opcaoValorService,
                        modificadorService
                );

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(converter)
                .build();
    }

    private CampoFicha criarCampo(
            Long id,
            String nome
    ) {
        CampoFicha campo = new CampoFicha();

        campo.setId(id);
        campo.setNome(nome);
        campo.setTipoCampo(tipoCampoValido);
        campo.setTipoValor(tipoValorValido);

        return campo;
    }

    private String valorJsonDoEnum(Enum<?> valor) {
        return jsonMapper
                .valueToTree(valor)
                .asText();
    }

    private String criarCorpoJson(String nome) throws Exception {
        CampoFicha campo = criarCampo(null, nome);

        return jsonMapper.writeValueAsString(campo);
    }

    @Test
    @DisplayName("Deve criar um campo de ficha válido")
    void testCriarCampoFichaValido() throws Exception {
        when(campoFichaService.create(any(CampoFicha.class)))
                .thenAnswer(invocation -> {
                    CampoFicha campo = invocation.getArgument(0);
                    campo.setId(1L);

                    return campo;
                });

        String corpo = criarCorpoJson("Força");

        mockMvc.perform(
                        post("/campos-ficha")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(corpo)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Força"))
                .andExpect(jsonPath("$.tipoCampo").value(
                        valorJsonDoEnum(tipoCampoValido)
                ))
                .andExpect(jsonPath("$.tipoValor").value(
                        valorJsonDoEnum(tipoValorValido)
                ));

        verify(campoFichaService)
                .create(any(CampoFicha.class));
    }

    @Test
    @DisplayName("Não deve criar campo com nome em branco")
    void testCriarCampoFichaNomeEmBranco() throws Exception {
        String corpo = criarCorpoJson("");

        mockMvc.perform(
                        post("/campos-ficha")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(corpo)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Não deve criar campo sem tipo de campo")
    void testCriarCampoFichaSemTipoCampo() throws Exception {
        ObjectNode corpo = jsonMapper.createObjectNode();

        corpo.put("nome", "Força");
        corpo.put(
                "tipoValor",
                valorJsonDoEnum(tipoValorValido)
        );

        mockMvc.perform(
                        post("/campos-ficha")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        jsonMapper.writeValueAsString(corpo)
                                )
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Não deve criar campo sem tipo de valor")
    void testCriarCampoFichaSemTipoValor() throws Exception {
        ObjectNode corpo = jsonMapper.createObjectNode();

        corpo.put("nome", "Força");
        corpo.put(
                "tipoCampo",
                valorJsonDoEnum(tipoCampoValido)
        );

        mockMvc.perform(
                        post("/campos-ficha")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        jsonMapper.writeValueAsString(corpo)
                                )
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve listar os campos de ficha")
    void testListarCamposFicha() throws Exception {
        CampoFicha campo1 = criarCampo(
                1L,
                "Força"
        );

        CampoFicha campo2 = criarCampo(
                2L,
                "Destreza"
        );

        when(campoFichaService.list())
                .thenReturn(List.of(campo1, campo2));

        mockMvc.perform(get("/campos-ficha"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Força"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Destreza"));

        verify(campoFichaService).list();
    }

    @Test
    @DisplayName("Deve buscar um campo de ficha pelo ID")
    void testLerCampoFichaValido() throws Exception {
        CampoFicha campo = criarCampo(
                1L,
                "Constituição"
        );

        when(campoFichaService.read(1L))
                .thenReturn(campo);

        mockMvc.perform(
                        get("/campos-ficha/{id}", 1L)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome")
                        .value("Constituição"))
                .andExpect(jsonPath("$.tipoCampo").value(
                        valorJsonDoEnum(tipoCampoValido)
                ))
                .andExpect(jsonPath("$.tipoValor").value(
                        valorJsonDoEnum(tipoValorValido)
                ));

        verify(campoFichaService).read(1L);
    }

    @Test
    @DisplayName("Deve atualizar um campo de ficha")
    void testAtualizarCampoFichaValido() throws Exception {
        when(
                campoFichaService.update(
                        eq(1L),
                        any(CampoFicha.class)
                )
        ).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            CampoFicha campo = invocation.getArgument(1);

            campo.setId(id);

            return campo;
        });

        String corpo = criarCorpoJson("Sabedoria");

        mockMvc.perform(
                        put("/campos-ficha/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(corpo)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome")
                        .value("Sabedoria"))
                .andExpect(jsonPath("$.tipoCampo").value(
                        valorJsonDoEnum(tipoCampoValido)
                ))
                .andExpect(jsonPath("$.tipoValor").value(
                        valorJsonDoEnum(tipoValorValido)
                ));

        verify(campoFichaService).update(
                eq(1L),
                any(CampoFicha.class)
        );
    }

    @Test
    @DisplayName("Deve remover um campo de ficha")
    void testRemoverCampoFichaValido() throws Exception {
        when(campoFichaService.delete(1L))
                .thenReturn(null);

        mockMvc.perform(
                        delete("/campos-ficha/{id}", 1L)
                )
                .andExpect(status().isOk());

        verify(campoFichaService).delete(1L);
    }

    @Test
    @DisplayName("Deve listar os valores de um campo de ficha")
    void testListarValoresCampo() throws Exception {
        when(valorCampoService.listByIdCampoFicha(1L))
                .thenReturn(List.of());

        mockMvc.perform(
                        get(
                                "/campos-ficha/{id}/valores-campo",
                                1L
                        )
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(valorCampoService)
                .listByIdCampoFicha(1L);
    }

    @Test
    @DisplayName("Deve listar as opções de valor de um campo")
    void testListarOpcoesValor() throws Exception {
        when(opcaoValorService.listByIdCampoFicha(1L))
                .thenReturn(List.of());

        mockMvc.perform(
                        get(
                                "/campos-ficha/{id}/opcoes-valor",
                                1L
                        )
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(opcaoValorService)
                .listByIdCampoFicha(1L);
    }

    @Test
    @DisplayName("Deve listar os modificadores de um campo")
    void testListarModificadores() throws Exception {
        when(modificadorService.listByIdCampoFicha(1L))
                .thenReturn(List.of());

        mockMvc.perform(
                        get(
                                "/campos-ficha/{id}/modificadores",
                                1L
                        )
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(modificadorService)
                .listByIdCampoFicha(1L);
    }
}