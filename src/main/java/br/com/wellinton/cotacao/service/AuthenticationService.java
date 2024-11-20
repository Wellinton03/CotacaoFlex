package br.com.wellinton.cotacao.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.wellinton.cotacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthenticationService implements UserDetailsService {

            @Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByUsername(username);
                
	}

}