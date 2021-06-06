package kodlamaio.hrms.core.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

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
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="create_date")
	private LocalDateTime createDate = LocalDateTime.now();

	@Column(name="active")
	private boolean active=true;
	
	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
}
