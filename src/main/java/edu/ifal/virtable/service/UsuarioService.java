package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.usuario.Usuario;
import edu.ifal.virtable.repository.UsuarioRepository;

@Service
@Validated
public class UsuarioService extends CrudService<Usuario> {

    public UsuarioService(
            UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}