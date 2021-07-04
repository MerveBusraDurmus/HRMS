package kodlamaio.hrms.entities.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import kodlamaio.hrms.core.entities.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class JobAdvertForListDto implements Dto{
	
	@NotBlank
	@Size(max=100)
	private String companyName;
	
	@NotBlank
	@Size(max=50)
	private String title;
	
	@Positive
	private int numberOfOpenPositions;
	
	@PastOrPresent
	private LocalDateTime createDate;
	
	@Future
	private LocalDateTime applicationDeadline;
	
	
}
