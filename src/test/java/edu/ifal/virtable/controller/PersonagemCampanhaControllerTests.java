package edu.ifal.virtable.controller;

import edu.ifal.virtable.model.campanha.Campanha;
import edu.ifal.virtable.model.campanha.PersonagemCampanha;
import edu.ifal.virtable.model.personagem.Personagem;
import edu.ifal.virtable.service.PersonagemCampanhaService;
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
@DisplayName("PersonagemCampanhaController - Testes unitários")
class PersonagemCampanhaControllerTests {

    @Mock
    private PersonagemCampanhaService personagemCampanhaService;

    private PersonagemCampanhaController personagemCampanhaController;

    @BeforeEach
    void setup() {
        personagemCampanhaController =
                new PersonagemCampanhaController(personagemCampanhaService);
    }

    private PersonagemCampanha criarPersonagemCampanha(Long id) {
        Personagem personagem = new Personagem();
        personagem.setId(1L);
        personagem.setNome("Arthas");

        Campanha campanha = new Campanha();
        campanha.setId(2L);
        campanha.setNome("A Maldição do Dragão");

        PersonagemCampanha personagemCampanha = new PersonagemCampanha();
        personagemCampanha.setId(id);
        personagemCampanha.setPersonagem(personagem);
        personagemCampanha.setCampanha(campanha);

        return personagemCampanha;
    }

    @Test
    @DisplayName("Deve criar vínculo entre personagem e campanha")
    void deveCriarPersonagemCampanha() {
        PersonagemCampanha request = criarPersonagemCampanha(null);
        PersonagemCampanha criado = criarPersonagemCampanha(1L);

        when(personagemCampanhaService.create(any(PersonagemCampanha.class)))
                .thenReturn(criado);

        ResponseEntity<PersonagemCampanha> resposta =
                personagemCampanhaController.create(request);

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(1L, resposta.getBody().getId());
        assertEquals(1L, resposta.getBody().getPersonagem().getId());
        assertEquals(2L, resposta.getBody().getCampanha().getId());

        verify(personagemCampanhaService)
                .create(any(PersonagemCampanha.class));
    }

    @Test
    @DisplayName("Deve listar vínculos entre personagens e campanhas")
    void deveListarPersonagensCampanhas() {
        PersonagemCampanha vinculo1 = criarPersonagemCampanha(1L);
        PersonagemCampanha vinculo2 = criarPersonagemCampanha(2L);

        when(personagemCampanhaService.list())
                .thenReturn(List.of(vinculo1, vinculo2));

        ResponseEntity<List<PersonagemCampanha>> resposta =
                personagemCampanhaController.list();

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(2, resposta.getBody().size());

        verify(personagemCampanhaService).list();
    }

    @Test
    @DisplayName("Deve buscar vínculo pelo ID")
    void deveBuscarPersonagemCampanhaPorId() {
        PersonagemCampanha vinculo = criarPersonagemCampanha(1L);

        when(personagemCampanhaService.read(1L))
                .thenReturn(vinculo);

        ResponseEntity<PersonagemCampanha> resposta =
                personagemCampanhaController.read(1L);

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(1L, resposta.getBody().getId());

        verify(personagemCampanhaService).read(1L);
    }

    @Test
    @DisplayName("Deve atualizar vínculo entre personagem e campanha")
    void deveAtualizarPersonagemCampanha() {
        PersonagemCampanha request = criarPersonagemCampanha(null);
        PersonagemCampanha atualizado = criarPersonagemCampanha(1L);

        when(personagemCampanhaService.update(eq(1L), any(PersonagemCampanha.class)))
                .thenReturn(atualizado);

        ResponseEntity<PersonagemCampanha> resposta =
                personagemCampanhaController.update(1L, request);

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertEquals(1L, resposta.getBody().getId());

        verify(personagemCampanhaService)
                .update(eq(1L), any(PersonagemCampanha.class));
    }

    @Test
    @DisplayName("Deve remover vínculo entre personagem e campanha")
    void deveRemoverPersonagemCampanha() {
        when(personagemCampanhaService.delete(1L))
                .thenReturn(null);

        ResponseEntity<?> resposta =
                personagemCampanhaController.delete(1L);

        assertEquals(200, resposta.getStatusCode().value());

        verify(personagemCampanhaService).delete(1L);
    }
}