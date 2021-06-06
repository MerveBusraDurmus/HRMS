package kodlamaio.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.*;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="job_positions")
@NoArgsConstructor
public class JobPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="create_date")
	private LocalDateTime createDate=LocalDateTime.now();
	
	@Column(name="active")
	private boolean active=true;

	public JobPosition(int id, String title, LocalDateTime createDate, boolean active) {
		super();
		this.id = id;
		this.title = title;
		this.createDate = createDate;
		this.active = active;
	}
	

	
	
}
