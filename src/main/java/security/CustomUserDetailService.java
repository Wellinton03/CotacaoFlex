package security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import entity.Usuario;
import repository.UsuarioRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;
	
	public CustomUserDetailService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findUsername(username);
		
		return null;
	}

}
