package kodlamaio.hrms.business.abstracts;



import kodlamaio.hrms.core.abstracts.BaseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService extends BaseService<JobSeeker,Integer>{
	
	DataResult<JobSeeker> getByIdentityNumber(String identityNumber);
	DataResult<JobSeeker>  getByEmail (String email);
	
	
}
