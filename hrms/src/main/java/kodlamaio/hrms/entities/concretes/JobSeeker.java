package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.*;

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
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "user_id")
public class JobSeeker extends User {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="user_id")
//	private int userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="identity_number")
	private String identityNumber;
	
	@Column(name="birth_year")
	private LocalDate birthYear;

	public JobSeeker(int id, String email, String password, String firstName, String lastName, String identityNumber,
			LocalDate birthYear) {
		super(id, email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.identityNumber = identityNumber;
		this.birthYear = birthYear;
	}

	}






