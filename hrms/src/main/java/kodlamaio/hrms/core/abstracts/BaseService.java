package kodlamaio.hrms.core.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface BaseService<T,Id> {
	
	Result add(T entity);
	
	Result delete(T entity);
	
	Result update(T entity);
	
	DataResult<T> getById(Id id);
	
	DataResult<List<T>> getAll();
}
