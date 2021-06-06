package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_confirm_employers")
@PrimaryKeyJoinColumn(name = "employee_confirm_id")
public class EmployeeConfirmEmployer extends EmployeeConfirm{

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="employee_confirm_id")
//	private int employeeConfirmId;
	
	@Column(name="employer_id")
	private int employerId;

	public EmployeeConfirmEmployer(int id, int employeeId, boolean isConfirmed, LocalDateTime createDate,
		LocalDate confirmDate, int employerId) {
	super(id, employeeId, isConfirmed, createDate, confirmDate);
	this.employerId = employerId;
}

	
}
