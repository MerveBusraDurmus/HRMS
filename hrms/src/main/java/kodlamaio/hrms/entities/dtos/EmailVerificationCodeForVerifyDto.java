package kodlamaio.hrms.entities.dtos;

import javax.validation.constraints.*;

import kodlamaio.hrms.core.entities.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EmailVerificationCodeForVerifyDto implements Dto{
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String verificationCode;
}
