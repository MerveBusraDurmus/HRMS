package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.abstracts.BaseService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Verification;
import kodlamaio.hrms.entities.dtos.EmailVerificationCodeForVerifyDto;

public interface VerificationService extends BaseService<Verification,Integer>{
	Result createAndSendVerificationCode(User user, String email);
	Result verify(EmailVerificationCodeForVerifyDto emailVerificationCodeForVerifyDto);
}
