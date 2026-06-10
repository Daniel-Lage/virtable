package edu.ifal.virtable.service;

import edu.ifal.virtable.domain.Usuario;
import edu.ifal.virtable.repository.UsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UsuarioService extends CrudService<Usuario> {

    public UsuarioService(
            UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}