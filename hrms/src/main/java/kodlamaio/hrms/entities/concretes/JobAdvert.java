package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="job_adverts")
@Builder
public class JobAdvert {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@JoinColumn(name="employer_id")
	@ManyToOne
	private Employer employer;
	
	@NotNull
	@JoinColumn(name="job_position_id")
	@ManyToOne
	private JobPosition jobPosition;
	
	@NotNull
	@JoinColumn(name="city_id") 
	@ManyToOne
	private City city;
	
	@NotBlank
	@Column(name="description")
	private String description;
	
	@PositiveOrZero
	@Column(name="min_salary")
	private int minSalary;
	
	@PositiveOrZero
	@Column(name="max_salary")
	private int maxSalary;
	
	@Positive
	@Column(name="number_of_open_positions")
	private int numberOfOpenPositions;
	
	@Future
	@Column(name="application_deadline")
	private LocalDateTime applicationDeadline;
	
	@PastOrPresent
	@Column(name="create_date")
	private final LocalDateTime createDate = LocalDateTime.now();
	
	@Column(name="is_active")
	private boolean isActive = true;
}
