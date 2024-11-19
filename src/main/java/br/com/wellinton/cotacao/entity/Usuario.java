package entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)	
	@NotBlank(message = "O Username é obrigatório")
	@Size(min = 5, max = 10, message = "o username deve ter entre 5 e 10 caracteres")
	private String username;
	
	@Column(name = "email", unique = true, nullable = false)	
	@Email(message = "O Email deve ser válido")
	@NotBlank(message = "O Email é obrigatório")
	private String email;
	
	@Column(name = "senha", unique = true, nullable = false)
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	@NotBlank(message = "A Senha é obrigatória")
	private String senha;
	
	public Usuario() {}
	
	public Usuario(String username, String email, String senha) {
		this.username = username;
		this.email = email;
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, senha, username);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(senha, other.senha)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", senha=" + senha + "]";
	}
	
	
	
	
	
	
}
