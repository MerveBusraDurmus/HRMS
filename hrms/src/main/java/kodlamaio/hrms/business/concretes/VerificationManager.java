package kodlamaio.hrms.business.concretes;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.adapters.email.EmailService;
import kodlamaio.hrms.business.abstracts.VerificationService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.helpers.GenerateRandomVerificationCode;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.VerificationDao;
import kodlamaio.hrms.entities.concretes.Verification;
import kodlamaio.hrms.entities.dtos.EmailVerificationCodeForVerifyDto;

@Service
public class VerificationManager implements VerificationService {

	private VerificationDao verificationDao;
	private EmailService emailService;
	
	
	@Autowired
	public VerificationManager(VerificationDao verificationDao, EmailService emailService) {
		this.verificationDao = verificationDao;
		this.emailService = emailService;
	}

	@Override
	public Result add(Verification verification) {
		this.verificationDao.save(verification);
		return new SuccessResult();
	}

	@Override
	public Result delete(Verification verification) {
		this.verificationDao.delete(verification);
		return new SuccessResult();
	}

	@Override
	public Result update(Verification verification) {
		this.verificationDao.save(verification);
		return new SuccessResult();
	}

	@Override
	public DataResult<Verification> getById(Integer id) {
		return new SuccessDataResult<Verification>(this.verificationDao.getById(id)) ;
	}

	@Override
	public DataResult<List<Verification>> getAll() {
		return new SuccessDataResult<List<Verification>>(this.verificationDao.findAll());
	}

	@Override
	 public Result createAndSendVerificationCode(final User user, final String email) {
		
		// Rastgele do??rulama kodu olu??turuyoruz. E??er olu??turulan code daha ??nce varsa tekrar olu??turuyor.
//					String verificationCode = GenerateRandomVerificationCode.getRandomVerificationCode();
//					while(!checkIfVerificationCodeExist(verificationCode).isSuccess()) {
//						verificationCode = GenerateRandomVerificationCode.getRandomVerificationCode();
//					}

        
			//olu??turdu??umuz kodu ve kullan??c?? bilgisini verification tablosuna ekliyoruz.Eklememiz gerekiyor ????nk?? daha sonra 
			//elimizdeki tabloda bulunan do??rulama kodu ile kullan??c??n??n verdi??i kodu kar????la??t??r??p verify i??lemi yapaca????z.
					
            final Verification emailActivation = Verification.builder()
                    .user(user)
                    .email(email)
                    .activationCode(GenerateRandomVerificationCode.getRandomVerificationCode())
                    .expirationDate(LocalDateTime.now().plusMonths(1))
                    .build();
            add(emailActivation);
            emailService.send(email,
                    Messages.emailActivationVerifyEmailTitle,
                    String.format("%swww.localhost:8080/api/verifications/verify?activationCode=%s&email=%s",
                            Messages.emailActivationVerifyEmailBody,
                            emailActivation.getActivationCode(),
                            email));
        

        return new SuccessResult(Messages.emailActivationCreatedAndSent);
    }
	
	
	@Override
	public Result verify(EmailVerificationCodeForVerifyDto emailVerificationCodeForVerifyDto) {
		
		Optional<Verification> verification = verificationDao.findByEmailAndActivationCode(
				emailVerificationCodeForVerifyDto.getEmail(), 
				emailVerificationCodeForVerifyDto.getVerificationCode());
		
		if(!verification.isPresent())
			return new ErrorResult("Email do??rulanamad??");
		
		verification.get().setActive(true);
		verification.get().setActivationDate(LocalDateTime.now());
		add(verification.get());
		
		return new SuccessResult("Email do??ruland??");
	}
	
//	private Result checkIfVerificationCodeExist(String code) {
//		return this.verificationDao.findAll().stream().filter(vc->vc.getActivationCode().equals(code)).findFirst().isPresent() ?
//				new SuccessResult() : new ErrorResult();
//	}


}
