package edu.ifal.virtable.controller;

import edu.ifal.virtable.model.campanha.Campanha;
import edu.ifal.virtable.model.campanha.PersonagemCampanha;
import edu.ifal.virtable.model.campanha.UsuarioCampanha;
import edu.ifal.virtable.model.sistema.SistemaRPG;
import edu.ifal.virtable.model.usuario.Usuario;
import edu.ifal.virtable.security.CustomUserDetails;
import edu.ifal.virtable.service.CampanhaService;
import edu.ifal.virtable.service.PersonagemCampanhaService;
import edu.ifal.virtable.service.UsuarioCampanhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CampanhaController - Testes unitários")
class CampanhaControllerTests {

        @Mock
        private CampanhaService campanhaService;

        @Mock
        private UsuarioCampanhaService usuarioCampanhaService;

        @Mock
        private PersonagemCampanhaService personagemCampanhaService;

        private CampanhaController campanhaController;

        private CustomUserDetails usuarioAutenticado;

        @BeforeEach
        void setup() {
                campanhaController = new CampanhaController(
                                campanhaService,
                                usuarioCampanhaService,
                                personagemCampanhaService);

                usuarioAutenticado = new CustomUserDetails(
                                10L,
                                "mestre@email.com",
                                "123",
                                List.of());
        }

        private Campanha criarCampanha(Long id, String nome) {
                Usuario mestre = new Usuario(10L, null, null, null);

                SistemaRPG sistemaRPG = new SistemaRPG();
                sistemaRPG.setId(1L);
                sistemaRPG.setNome("Dungeons & Dragons");

                Campanha campanha = new Campanha();
                campanha.setId(id);
                campanha.setNome(nome);
                campanha.setConteudo("Conteúdo da campanha");
                campanha.setMestre(mestre);
                campanha.setSistemaRPG(sistemaRPG);

                return campanha;
        }

        @Test
        @DisplayName("Deve criar uma campanha")
        void testCriarCampanhaValido() {
                Campanha campanha = criarCampanha(null, "Aventura RPG");
                Campanha campanhaCriada = criarCampanha(1L, "Aventura RPG");

                when(campanhaService.create(any(Campanha.class)))
                                .thenReturn(campanhaCriada);

                ResponseEntity<Campanha> resposta = campanhaController.create(usuarioAutenticado, campanha);

                assertEquals(200, resposta.getStatusCode().value());
                assertNotNull(resposta.getBody());
                assertEquals(1L, resposta.getBody().getId());
                assertEquals("Aventura RPG", resposta.getBody().getNome());

                verify(campanhaService).create(any(Campanha.class));
        }

        @Test
        @DisplayName("Deve atualizar uma campanha")
        void testAtualizarCampanhaValido() {
                Campanha campanha = criarCampanha(null, "Campanha Atualizada");
                Campanha campanhaAtualizada = criarCampanha(1L, "Campanha Atualizada");

                when(campanhaService.update(eq(1L), any(Campanha.class)))
                                .thenReturn(campanhaAtualizada);

                ResponseEntity<Campanha> resposta = campanhaController.update(1L, usuarioAutenticado, campanha);

                assertEquals(200, resposta.getStatusCode().value());
                assertNotNull(resposta.getBody());
                assertEquals(1L, resposta.getBody().getId());
                assertEquals("Campanha Atualizada", resposta.getBody().getNome());

                verify(campanhaService).update(eq(1L), any(Campanha.class));
        }

        @Test
        @DisplayName("Deve listar campanhas")
        void testListarCampanhasValido() {
                Campanha campanha1 = criarCampanha(1L, "Campanha 1");
                Campanha campanha2 = criarCampanha(2L, "Campanha 2");

                when(campanhaService.list())
                                .thenReturn(List.of(campanha1, campanha2));

                ResponseEntity<List<Campanha>> resposta = campanhaController.list();

                assertEquals(200, resposta.getStatusCode().value());
                assertNotNull(resposta.getBody());
                assertEquals(2, resposta.getBody().size());

                verify(campanhaService).list();
        }

        @Test
        @DisplayName("Deve buscar campanha por ID")
        void testBuscarCampanhaPorIdValido() {
                Campanha campanha = criarCampanha(1L, "Campanha Encontrada");

                when(campanhaService.read(1L))
                                .thenReturn(campanha);

                ResponseEntity<Campanha> resposta = campanhaController.read(1L);

                assertEquals(200, resposta.getStatusCode().value());
                assertNotNull(resposta.getBody());
                assertEquals(1L, resposta.getBody().getId());
                assertEquals("Campanha Encontrada", resposta.getBody().getNome());

                verify(campanhaService).read(1L);
        }

        @Test
        @DisplayName("Deve listar usuários da campanha")
        void testListarUsuariosDaCampanhaValido() {
                when(usuarioCampanhaService.listByIdCampanha(1L))
                                .thenReturn(List.of());

                ResponseEntity<List<UsuarioCampanha>> resposta = campanhaController.listUsuarios(1L);

                assertEquals(200, resposta.getStatusCode().value());
                assertNotNull(resposta.getBody());
                assertEquals(0, resposta.getBody().size());

                verify(usuarioCampanhaService).listByIdCampanha(1L);
        }

        @Test
        @DisplayName("Deve listar personagens da campanha")
        void testListarPersonagensDaCampanhaValido() {
                when(personagemCampanhaService.listByIdCampanha(1L))
                                .thenReturn(List.of());

                ResponseEntity<List<PersonagemCampanha>> resposta = campanhaController.listPersonagens(1L);

                assertEquals(200, resposta.getStatusCode().value());
                assertNotNull(resposta.getBody());
                assertEquals(0, resposta.getBody().size());

                verify(personagemCampanhaService).listByIdCampanha(1L);
        }
}
