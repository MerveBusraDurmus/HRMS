package kodlamaio.hrms.core.utilities.business;

import kodlamaio.hrms.core.utilities.results.Result;

public class BusinessRules {
	
	public static Result run (final Result... logics) {
		for(final Result logic:logics) 
			if(!logic.isSuccess()) 
				return logic;
				
		return null;
	}
}
