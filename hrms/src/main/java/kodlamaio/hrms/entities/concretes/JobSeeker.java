package kodlamaio.hrms.entities.concretes;


import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;

import kodlamaio.hrms.core.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "job_seekers")
@PrimaryKeyJoinColumn(name = "user_id")
public class JobSeeker extends User {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="user_id")
//	private int userId;
	
	@NotBlank()
	@Size(max = 50)
	@Column(name="first_name")
	private String firstName;
	
	@NotBlank
	@Size(max = 50)
	@Column(name="last_name")
	private String lastName;
	
	@NotBlank
	@Size(min = 11, max = 11)
	@Column(name="identity_number")
	private String identityNumber;
	
	@NotNull
	@Past
	@Column(name="birth_year")
	private LocalDate birthYear;

	@Builder(builderMethodName = "childBuilder")
	public JobSeeker(final int id, @NotBlank @Email @Size(max = 100) final String email, 
			@NotBlank @Size(max = 100) final String password,@NotBlank @Size(max = 50) final String firstName, 
			@NotBlank @Size(max = 50) final String lastName,@NotBlank @Size(min = 11, max = 11) final String identityNumber,
		    @NotNull @Past final LocalDate birthYear
			) {
		super(id, email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.identityNumber = identityNumber;
		this.birthYear = birthYear;
	}

}






