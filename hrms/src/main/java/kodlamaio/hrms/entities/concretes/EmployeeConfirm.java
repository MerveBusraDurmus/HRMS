package kodlamaio.hrms.entities.concretes;


import java.time.LocalDateTime;


import javax.persistence.*;
//import javax.validation.constraints.AssertFalse;
//import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import kodlamaio.hrms.core.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "employee_confirms")
@Inheritance(strategy = InheritanceType.JOINED)
public class EmployeeConfirm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@NotNull
	@Column(name="is_confirmed")
//	@AssertFalse
	private boolean isConfirmed=false;
		
	@NotNull
	@PastOrPresent
	@Column(name="confirm_date")
	private LocalDateTime confirmDate;
	
	@NotNull
	@Column(name="create_date")
	private LocalDateTime createDate = LocalDateTime.now();
	
//	@AssertTrue
	@Column(name="is_active")
	private boolean isActive=true;

	@Builder
	public EmployeeConfirm(@NotNull final User user,@NotNull final boolean isConfirmed, 
			@NotNull @PastOrPresent final LocalDateTime confirmDate) {
		
		this.user = user;
		this.isConfirmed = isConfirmed;
		this.confirmDate = confirmDate;
	}
	
	
}
