package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.JobSeekerForRegisterDto;

public interface AuthService {

	Result registerEmployer(EmployerForRegisterDto employerForRegister);
	Result registerJobSeeker(JobSeekerForRegisterDto jobSeekerForRegister);
}
