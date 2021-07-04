package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{

	private JobPositionDao jobPositionDao;
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		this.jobPositionDao = jobPositionDao;
	}
	@Override
	public DataResult<JobPosition> getByTitle(String title) {
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.findByTitle(title));
	}
	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll());
	}
	@Override
	public Result add(JobPosition jobPosition) {
		Result result = BusinessRules.run(checkIfJobPositionExist(jobPosition.getTitle()));
		if(result!=null)
		{
			return new ErrorResult("Pozisyon tekrar edemez.");
		}
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult("iş pozisyonu eklendi");
	}
	@Override
	public Result delete(JobPosition jobPosition) {
		this.jobPositionDao.delete(jobPosition);
		return new SuccessResult();
	}
	@Override
	public Result update(JobPosition jobPosition) {
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult();
	}
	@Override
	public DataResult<JobPosition> getById(Integer id) {
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.getById(id));   
	}
	
	public Result checkIfJobPositionExist(String title) {
	
		//TODO:buranın düzeltilmesi gerekli
		return jobPositionDao.findAll().stream().filter(j->j.getTitle()==title).collect(Collectors.toList()).isEmpty()? 
				new ErrorResult() : new SuccessResult();
	}
	
	
	
}
