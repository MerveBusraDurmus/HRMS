package kodlamaio.hrms.business.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfigurations {

	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
