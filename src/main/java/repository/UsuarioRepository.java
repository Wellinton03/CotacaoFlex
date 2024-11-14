package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findUsername(String username);
}
