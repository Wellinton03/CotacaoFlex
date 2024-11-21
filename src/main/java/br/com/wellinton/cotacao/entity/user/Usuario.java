package br.com.wellinton.cotacao.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "Usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)	
	@NotBlank(message = "O Username é obrigatório")
	@Size(min = 3, max = 10, message = "o username deve ter entre 3 e 10 caracteres")
	private String username;
	
	@Column(name = "email", unique = true, nullable = false)	
	@Email(message = "O Email deve ser válido")
	@NotBlank(message = "O Email é obrigatório")
	private String email;
	
	@Column(name = "password", nullable = false)
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	@NotBlank(message = "A Senha é obrigatória")
	private String password;
        
        @Column(name = "role", nullable = false)
        private UserRole role;
	
        
	public Usuario(String username, String password, UserRole role, String email) {
		this.username = username;
		this.password = password;
		this.role = role;
                this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
                else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
        
}
