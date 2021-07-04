package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertDao;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertForListDto;

@Service
public class JobAdvertManager implements JobAdvertService{

	private JobAdvertDao jobAdvertDao;
	
	
	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao) {
		this.jobAdvertDao = jobAdvertDao;
	}


	@Override
	public Result add(JobAdvert jobAdvert) {
		
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult();
	}


	@Override
	public Result delete(JobAdvert jobAdvert) {
		this.jobAdvertDao.delete(jobAdvert);
		return new SuccessResult();
	}


	@Override
	public Result update(JobAdvert jobAdvert) {
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult();
	}


	@Override
	public DataResult<JobAdvert> getById(Integer id) {
		return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.getById(id));
		
	}


	@Override
	public DataResult<List<JobAdvert>> getAll() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll());
	}


	@Override
	public DataResult<List<JobAdvert>> getAllByIsActive(boolean isActive) {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAllByIsActive(isActive),"Başarılı bir şekilde listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvertForListDto>> getAllByIsActiveForList(boolean isActive) {
		return new SuccessDataResult<List<JobAdvertForListDto>>(this.jobAdvertDao.findAllByIsActiveForList(isActive));
	}


	@Override
	public DataResult<List<JobAdvertForListDto>> getAllByIsActiveOrderByCreateDate(boolean isActive) {
		return new SuccessDataResult<List<JobAdvertForListDto>>(this.jobAdvertDao.findAllByIsActiveOrderByCreateDate(isActive));
	}


	@Override
	public DataResult<List<JobAdvertForListDto>> getAllByIsActiveAndCompanyName(boolean isActive, String companyName) {
		return new SuccessDataResult<List<JobAdvertForListDto>>(this.jobAdvertDao.findAllByIsActiveAndCompanyName(isActive, companyName));
	}


	@Override
	public Result updateIsActiveByEmployer(int jobAdvertId) {
		JobAdvert jobAdvert = this.jobAdvertDao.getById(jobAdvertId);
		jobAdvert.setActive(!jobAdvert.isActive());
		jobAdvert.setActive(false);
		this.jobAdvertDao.save(jobAdvert);
		
		return new SuccessResult("İş ilanı iş veren tarafından güncellendi");
	}


	
	
	
	

	

}
