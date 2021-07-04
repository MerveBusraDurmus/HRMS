package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import lombok.var;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult();
	}

	@Override
	public Result delete(User user) {
		this.userDao.delete(user);
		return new SuccessResult();
	}

	@Override
	public Result update(User user) {
		this.userDao.save(user);
		return new SuccessResult();
	}

	@Override
	public DataResult<User> getById(Integer id) {
		return new SuccessDataResult<User>(this.userDao.getById(id));
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll());
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		
		var user = this.userDao.findByEmail(email).getEmail();
		
		if(user.isEmpty()) {
			return new ErrorDataResult<User>("Kullanıcı bulunamadı");
		}
		return new SuccessDataResult<User>("Kullanıcı bulundu");
		
		
	
	}

	@Override
	public Result isEmailExist(final String email) {
	
		var result = this.userDao.findByEmail(email);
		if(result!=null)
			return new ErrorResult("Email kayıtlı");
		return new SuccessResult();
	}





}
