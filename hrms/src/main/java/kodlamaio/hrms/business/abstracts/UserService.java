package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.abstracts.BaseService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface UserService extends BaseService<User ,Integer>{
	DataResult<User> getByEmail(String email);
	Result isEmailExist(final String email);
}
