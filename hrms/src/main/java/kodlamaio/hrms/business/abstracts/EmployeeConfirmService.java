package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.abstracts.BaseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmployeeConfirm;

public interface EmployeeConfirmService extends BaseService<EmployeeConfirm,Integer>{
	DataResult<EmployeeConfirm> getByUserId(int userId);
	Result verify(int userId);
}
