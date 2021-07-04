package kodlamaio.hrms.entities.concretes;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kodlamaio.hrms.core.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User{
	
//	@Id
//	@Column(name="user_id")
//	private int userId;
	
	@NotBlank 
	@Size(max = 50) 
	@Column(name="first_name")
	private String firstName;
	
	@NotBlank 
	@Size(max = 50)
	@Column(name="last_name")
	private String lastName;

	public Employee(final int id, String email,@NotBlank @Size(max = 100) final String password,
			@NotBlank @Size(max = 50) final String firstName,@NotBlank @Size(max = 50) final String lastName) {
		super(id, email, password);
		this.firstName = firstName;
		this.lastName = lastName;
	}



	
}
