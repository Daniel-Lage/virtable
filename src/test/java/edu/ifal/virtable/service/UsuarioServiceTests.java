/*
package edu.ifal.virtable.service;

import edu.ifal.virtable.domain.Usuario;

public class UsuarioService extends CrudService<Usuario> {

    public UsuarioService() {
        super(Usuario::getId);
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario usuario : listar()) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }

        return null;
    }

    public boolean emailJaExiste(String email) {
        return buscarPorEmail(email) != null;
    }

    public boolean atualizarNome(long idUsuario, String novoNome) {
        Usuario usuario = buscarPorId(idUsuario);

        if (usuario == null) {
            return false;
        }

        usuario.setNome(novoNome);
        return true;
    }

    public boolean atualizarEmail(long idUsuario, String novoEmail) {
        Usuario usuario = buscarPorId(idUsuario);

        if (usuario == null) {
            return false;
        }

        if (emailJaExiste(novoEmail)) {
            return false;
        }

        usuario.setEmail(novoEmail);
        return true;
    }

    public boolean autenticar(String email, String senha) {
        Usuario usuario = buscarPorEmail(email);

        if (usuario == null) {
            return false;
        }

        return usuario.getSenha().equals(senha);
    }
}
*/