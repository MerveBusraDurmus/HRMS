package kodlamaio.hrms.business.abstracts;




import kodlamaio.hrms.core.abstracts.BaseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobPosition;

public interface JobPositionService extends BaseService<JobPosition,Integer> {
	
	DataResult<JobPosition> getByTitle(String title);
}
