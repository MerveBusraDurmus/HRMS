package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kodlamaio.hrms.core.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@ToString
@EqualsAndHashCode
@Table(name="verification_codes")
@NoArgsConstructor
public class Verification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@JoinColumn(name = "user_id")
	@ManyToOne()
	private User user;
	
	@NotBlank
	@Size(max = 200)
	@Column(name="activation_code")
	private String activationCode;
	
	@NotBlank
	@Size(max = 100)
	@Column(name="email")
	private String email;
	
	@NotNull
	@Column(name="is_active", columnDefinition = "boolean default false")
	@AssertFalse
	private boolean isActive;
	
	@NotNull
	@Column(name="create_date", columnDefinition = "Date default CURRENT_DATE")
	private final LocalDateTime createDate = LocalDateTime.now();
	
	@NotNull
	@Column(name="expiration_date")
	private LocalDateTime expirationDate;
	
	@Column(name="activation_date")
	private LocalDateTime activationDate;

	@Builder
	public Verification(@NotNull User user, @NotBlank @Size(max = 200) String activationCode,
			@NotBlank @Size(max = 100) String email, @NotNull LocalDateTime expirationDate) {
		this.user = user;
		this.activationCode = activationCode;
		this.email = email;
		this.expirationDate = expirationDate;
	}
}
