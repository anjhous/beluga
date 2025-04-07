package com.example.AulaTeste.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.AulaTeste.model.UserModel;

@Repository
public class UsuarioRepository {
    private final List<UserModel> usuarios = new ArrayList<UserModel>();

    public void adicionarUsuario(UserModel UserModel) {
        usuarios.add(UserModel);
    }

    public List<UserModel> listarUsuarios() {
        return usuarios;
    }

    public UserModel autenticar(String email, String senha) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equals(email) && usuario.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }
}