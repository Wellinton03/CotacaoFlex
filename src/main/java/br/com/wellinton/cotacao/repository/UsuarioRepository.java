package br.com.wellinton.cotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wellinton.cotacao.user.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	   UserDetails findByUsername(String username);
	
}
