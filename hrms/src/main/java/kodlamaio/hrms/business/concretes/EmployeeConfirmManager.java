package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeConfirmService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeConfirmDao;
import kodlamaio.hrms.entities.concretes.EmployeeConfirm;


@Service
public class EmployeeConfirmManager implements EmployeeConfirmService{

	private EmployeeConfirmDao employeeConfirmDao;
	
	@Autowired
	public EmployeeConfirmManager(EmployeeConfirmDao employeeConfirmDao) {
		this.employeeConfirmDao = employeeConfirmDao;
	}

	@Override
	public Result add(EmployeeConfirm employeeConfirm) {
		this.employeeConfirmDao.save(employeeConfirm);
		return new SuccessResult();
	}

	@Override
	public Result delete(EmployeeConfirm employeeConfirm) {
		this.employeeConfirmDao.delete(employeeConfirm);
		return new SuccessResult();
	}

	@Override
	public Result update(EmployeeConfirm employeeConfirm) {
		this.employeeConfirmDao.save(employeeConfirm);
		return new SuccessResult();
	}

	@Override
	public DataResult<EmployeeConfirm> getById(Integer id) {
		return new SuccessDataResult<EmployeeConfirm>(this.employeeConfirmDao.getById(id));
	}

	@Override
	public DataResult<List<EmployeeConfirm>> getAll() {
		return new SuccessDataResult<List<EmployeeConfirm>>(this.employeeConfirmDao.findAll());
	}

	@Override
	public DataResult<EmployeeConfirm> getByUserId(int userId) {
		return new SuccessDataResult<EmployeeConfirm>(this.employeeConfirmDao.findByUser_Id(userId));
	}

	@Override
	public Result verify(int userId) {
		
		DataResult<EmployeeConfirm> employeeConfirm = getByUserId(userId);
		
		if(!employeeConfirm.isSuccess())
			return new ErrorResult();
		
		employeeConfirm.getData().setConfirmed(true);
		employeeConfirm.getData().setConfirmDate(LocalDateTime.now());
		add(employeeConfirm.getData());
		
		return new SuccessResult("Kullanıcı HRMS tarafından doğrulandı");
	}

}
