package service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import entity.Usuario;
import repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository userRepository;
	private final PasswordEncoder passEncoder;
	
	public UsuarioService (UsuarioRepository userRepository, PasswordEncoder passEncoder) {
		this.userRepository = userRepository;
		this.passEncoder = passEncoder;
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		
		usuario.setSenha(passEncoder.encode(usuario.getSenha()));
		
		return userRepository.save(usuario);
	}
}
