package com.example.AulaTeste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AulaTeste.model.UserModel;
import com.example.AulaTeste.repository.IUserRepository;
import com.example.AulaTeste.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private IUserRepository usuarioRepositoryData;

    @PostMapping("/criar")
    public ResponseEntity criarUsuario(@RequestBody UserModel UserModel) {
        var user = this.usuarioRepositoryData.findByEmail(UserModel.getEmail());
        
        // Caso não encontre um usuário ele retorna na requisição BAD_REQUEST
        if (user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        usuarioRepository.adicionarUsuario(UserModel);
        var userCreated = this.usuarioRepositoryData.save(UserModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

    }
 
    @GetMapping("/todos")
    public List<UserModel> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
        
    }

    @GetMapping("/todos2")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = usuarioRepositoryData.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/buscar")
    public ResponseEntity<UserModel> getUser(@RequestParam String email) {
        var user = usuarioRepositoryData.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody UserModel UserModel) {
        UserModel autenticado = usuarioRepository.autenticar(UserModel.getEmail(), UserModel.getSenha());
        if (autenticado != null) {
            return "Login realizado com sucesso!";
        } else {
            return "Email ou senha incorretos!";
        }
    }
}
