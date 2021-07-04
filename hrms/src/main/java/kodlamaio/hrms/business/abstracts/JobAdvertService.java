package kodlamaio.hrms.business.abstracts;


import java.util.*;

import kodlamaio.hrms.core.abstracts.BaseService;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertForListDto;
import kodlamaio.hrms.core.utilities.results.*;

public interface JobAdvertService extends BaseService<JobAdvert, Integer>{

	DataResult<List<JobAdvert>> getAllByIsActive(boolean isActive);
	DataResult<List<JobAdvertForListDto>> getAllByIsActiveForList(boolean isActive);
	DataResult<List<JobAdvertForListDto>> getAllByIsActiveOrderByCreateDate(boolean isActive);
	DataResult<List<JobAdvertForListDto>> getAllByIsActiveAndCompanyName(boolean isActive , String companyName);
	Result updateIsActiveByEmployer(int jobAdvertId);
}
