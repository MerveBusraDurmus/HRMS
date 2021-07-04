package kodlamaio.hrms.entities.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kodlamaio.hrms.core.entities.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployerForRegisterDto implements Dto{

	@NotBlank
	@Size(max=100)
	private String companyName;
	
	@NotBlank
	@Size(max=100)
	private String website;
	
	@NotBlank
	@Size(max=100)
	@Email
	private String email;
	
	@NotBlank
	@Size(max=15)
	private String phone;
	
	@NotBlank
	@Size(max=100)
	private String password;
	
	@NotBlank
	@Size(max=100)
	private String confirmPassword;
}
