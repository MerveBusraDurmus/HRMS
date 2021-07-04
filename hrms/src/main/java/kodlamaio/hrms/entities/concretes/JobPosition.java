package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@ToString
@EqualsAndHashCode
@Table(name="job_positions")
@NoArgsConstructor
public class JobPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotBlank()
	@Size(max = 50)
	@Column(name="title")
	private String title;
	
	@NotNull
	@Column(name="create_date")
	private LocalDateTime createDate=LocalDateTime.now();
	
	@NotNull
	@AssertTrue
	@Column(name="is_active")
	private boolean isActive;

	public JobPosition(@NotBlank @Size(max = 50) final String title, @NotNull final boolean isActive) {
		this.title = title;
		this.isActive = isActive;
	}
	

	
	
}
