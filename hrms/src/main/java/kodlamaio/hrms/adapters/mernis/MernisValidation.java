package kodlamaio.hrms.adapters.mernis;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MernisValidation {
	
	@NotNull
	@Size(min = 11, max = 11)
	private String IdentityNumber;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Past
	private LocalDate birthYear;
	
}
