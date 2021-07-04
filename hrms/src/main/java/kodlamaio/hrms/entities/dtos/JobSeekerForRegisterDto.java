package kodlamaio.hrms.entities.dtos;



import java.time.LocalDate;

import javax.validation.constraints.*;

import kodlamaio.hrms.core.entities.Dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class JobSeekerForRegisterDto implements Dto{

	@NotBlank
	@Email
	@Size(max = 100)
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;

	@NotBlank
	@Size(max = 100)
	private String confirmPassword;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	@Size(min = 11, max = 11)
	private String identityNumber;

	@NotNull
	@Past
	private LocalDate birthYear;


	
	
}
