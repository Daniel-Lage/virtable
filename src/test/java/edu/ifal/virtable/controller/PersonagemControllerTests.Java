package edu.ifal.virtable.controller;

import edu.ifal.virtable.model.campanha.PersonagemCampanha;
import edu.ifal.virtable.model.ficha.ValorCampo;
import edu.ifal.virtable.model.personagem.Personagem;
import edu.ifal.virtable.model.sistema.SistemaRPG;
import edu.ifal.virtable.model.usuario.Usuario;
import edu.ifal.virtable.security.CustomUserDetails;
import edu.ifal.virtable.service.PersonagemCampanhaService;
import edu.ifal.virtable.service.PersonagemService;
import edu.ifal.virtable.service.ValorCampoService;
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
@DisplayName("PersonagemController - Testes unitários")
class PersonagemControllerTests {

    @Mock
    private PersonagemService personagemService;

    @Mock
    private ValorCampoService valorCampoService;

    @Mock
    private PersonagemCampanhaService personagemCampanhaService;

    private PersonagemController personagemController;

    private CustomUserDetails usuarioAutenticado;

    @BeforeEach
    void setup() {
        personagemController = new PersonagemController(
                personagemService,
                valorCampoService,
                personagemCampanhaService
        );

        usuarioAutenticado = new CustomUserDetails(
                10L,
                "jogador@email.com",
                "123",
                List.of()
        );
    }

    private Personagem criarPersonagem(Long id, String nome) {
        Usuario criador = new Usuario(10L, null, null, null);

        SistemaRPG sistemaRPG = new SistemaRPG();
        sistemaRPG.setId(1L);
        sistemaRPG.setNome("Dungeons & Dragons");

        Personagem personagem = new Personagem();
        personagem.setId(id);
        personagem.setNome(nome);
        personagem.setCriador(criador);
        personagem.setSistemaRPG(sistemaRPG);

        return personagem;
    }

    @Test
    @DisplayName("Deve criar um personagem")
    void deveCriarPersonagem() {
        Personagem personagem = criarPersonagem(null, "Arthas");
        Personagem personagemCriado = criarPersonagem(1L, "Arthas");

        when(personagemService.create(any(Personagem.class)))
                .thenReturn(personagemCriado);

        ResponseEntity<Personagem> resposta =
                personagemController.create(usuarioAutenticado, personagem);

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(1L, resposta.getBody().getId());
        assertEquals("Arthas", resposta.getBody().getNome());

        verify(personagemService).create(any(Personagem.class));
    }

    @Test
    @DisplayName("Deve atualizar um personagem")
    void deveAtualizarPersonagem() {
        Personagem personagem = criarPersonagem(null, "Arthas Atualizado");
        Personagem personagemAtualizado = criarPersonagem(1L, "Arthas Atualizado");

        when(personagemService.update(eq(1L), any(Personagem.class)))
                .thenReturn(personagemAtualizado);

        ResponseEntity<Personagem> resposta =
                personagemController.update(1L, usuarioAutenticado, personagem);

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(1L, resposta.getBody().getId());
        assertEquals("Arthas Atualizado", resposta.getBody().getNome());

        verify(personagemService).update(eq(1L), any(Personagem.class));
    }

    @Test
    @DisplayName("Deve listar personagens")
    void deveListarPersonagens() {
        Personagem personagem1 = criarPersonagem(1L, "Arthas");
        Personagem personagem2 = criarPersonagem(2L, "Jaina");

        when(personagemService.list())
                .thenReturn(List.of(personagem1, personagem2));

        ResponseEntity<List<Personagem>> resposta =
                personagemController.list();

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(2, resposta.getBody().size());

        verify(personagemService).list();
    }

    @Test
    @DisplayName("Deve buscar personagem por ID")
    void deveBuscarPersonagemPorId() {
        Personagem personagem = criarPersonagem(1L, "Arthas");

        when(personagemService.read(1L))
                .thenReturn(personagem);

        ResponseEntity<Personagem> resposta =
                personagemController.read(1L);

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(1L, resposta.getBody().getId());
        assertEquals("Arthas", resposta.getBody().getNome());

        verify(personagemService).read(1L);
    }

    @Test
    @DisplayName("Deve remover personagem")
    void deveRemoverPersonagem() {
        when(personagemService.delete(1L))
                .thenReturn(null);

        ResponseEntity<?> resposta =
                personagemController.delete(1L);

        assertEquals(200, resposta.getStatusCode().value());

        verify(personagemService).delete(1L);
    }

    @Test
    @DisplayName("Deve listar valores de campo do personagem")
    void deveListarValoresCampoDoPersonagem() {
        when(valorCampoService.listByIdPersonagem(1L))
                .thenReturn(List.of());

        ResponseEntity<List<ValorCampo>> resposta =
                personagemController.listValoresCampo(1L);

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(0, resposta.getBody().size());

        verify(valorCampoService).listByIdPersonagem(1L);
    }

    @Test
    @DisplayName("Deve listar campanhas participadas pelo personagem")
    void deveListarCampanhasDoPersonagem() {
        when(personagemCampanhaService.listByIdPersonagem(1L))
                .thenReturn(List.of());

        ResponseEntity<List<PersonagemCampanha>> resposta =
                personagemController.listCampanhasParticipadas(1L);

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(0, resposta.getBody().size());

        verify(personagemCampanhaService).listByIdPersonagem(1L);
    }
}