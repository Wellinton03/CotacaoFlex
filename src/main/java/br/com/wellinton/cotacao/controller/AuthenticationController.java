package br.com.wellinton.cotacao.controller;


import br.com.wellinton.cotacao.repository.UsuarioRepository;
import br.com.wellinton.cotacao.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wellinton.cotacao.entity.user.Usuario;
import jakarta.validation.Valid;
import br.com.wellinton.cotacao.entity.user.AuthenticationDTO;
import br.com.wellinton.cotacao.entity.user.LoginResponseDTO;
import br.com.wellinton.cotacao.entity.user.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
        @Autowired
	private TokenService tokenService;
        
        @Autowired
        private AuthenticationManager authenticationManager;
	
        @Autowired
        private UsuarioRepository repository;
        
	@PostMapping("/register")
	public ResponseEntity register(@Valid @RequestBody RegisterDTO data) {
		if(this.repository.findByUsername(data.username()) != null)
                    return ResponseEntity.badRequest().build();
                
                String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
                Usuario usuario = new Usuario(data.username(), encryptedPassword, data.role(), data.email());
                
                this.repository.save(usuario);
                
                return ResponseEntity.ok().build();
        
        }
        @PostMapping("/login")
        public ResponseEntity login(@Valid @RequestBody AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
                var auth = authenticationManager.authenticate(usernamePassword);
                
                var token = tokenService.generatedToken((Usuario) auth.getPrincipal());
                
                return ResponseEntity.ok(new LoginResponseDTO(token));
	}
}
