package kodlamaio.hrms.entities.concretes;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employer extends User{
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="user_id")
//	private int userId;
	
	@NotBlank
	@Size(max=100)
	@Column(name="company_name")
	private String companyName;
	
	@NotBlank
	@Size(max=100)
	@Column(name="website")
	private String website;
	
	@NotBlank
	@Size(max=15)
	@Column(name="phone")
	private String phone;
	
	@Builder(builderMethodName = "childBuilder")
	public Employer(final int id,@NotBlank @Email @Size(max = 100) final String email, 
			@NotBlank @Size(max = 100) final String password, @NotBlank @Size(max = 100) final String companyName, 
			@NotBlank @Size(max = 100) final String website,@NotBlank @Size(max = 15) final String phone) {
		super(id, email, password);
		this.companyName = companyName;
		this.website = website;
		this.phone = phone;
	}

	
	
	
}
