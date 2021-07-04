package kodlamaio.hrms.core.entities;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@NotBlank
	@Email
	@Size(max=100)
	@Column(name="email")
	private String email;
	
	@NotBlank
	@Size(max=100)
	@Column(name="password")
	private String password;
	
	@NotNull
	@Column(name="create_date")
	private LocalDateTime createDate = LocalDateTime.now();

	@NotNull
	@AssertTrue
	@Column(name="is_active")
	private boolean isActive;
	
	@Builder
	public User(final int id,@NotBlank @Email @Size(max = 100) final String email, 
			@NotBlank @Size(max = 100) final String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
}
