package br.com.cotiinformatica.controllers;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("criar")
	public ResponseEntity<String> criar(@RequestBody @Valid CriarUsuarioRequestDto dto) {

		try {

			// criando um objeto da classe de entidade
			Usuario usuario = new Usuario();

			usuario.setId(UUID.randomUUID());
			usuario.setNome(dto.getNome());
			usuario.setEmail(dto.getEmail());
			usuario.setSenha(dto.getSenha());
			usuario.setDataHoraCadastro(new Date());

			// gravando no banco de dados
			usuarioRepository.save(usuario);

			// HTTP 201 (CREATED)
			return ResponseEntity.status(201).body("Usuário cadastrado com sucesso.");
		} catch (Exception e) {
			// HTTP 500 (INTERNAL SERVER ERROR)
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@PostMapping("autenticar")
	public void autenticar() {
		// TODO
	}

}
