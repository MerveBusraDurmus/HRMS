package kodlamaio.hrms.business.concretes;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.hrms.adapters.mernis.MernisValidation;
import kodlamaio.hrms.adapters.mernis.UserCheckService;
import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.EmployeeConfirmService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.EmployeeConfirm;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.JobSeekerForRegisterDto;
import lombok.var;

@Service
public class AuthManager implements AuthService{

	private EmployerService employerService;
	private JobSeekerService jobSeekerService;
	private UserService userService;
	private VerificationService verificationService;
	private EmployeeConfirmService employeeConfirmService;
	private UserCheckService userCheckService;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public AuthManager(EmployerService employerService, UserService userService,UserCheckService userCheckService
			,EmployeeConfirmService employeeConfirmService, JobSeekerService jobSeekerService,VerificationService verificationService
			,ModelMapper modelMapper
			){
		this.employerService = employerService;
		this.userService = userService;
		this.verificationService = verificationService;
		this.employeeConfirmService=employeeConfirmService;
		this.userCheckService=userCheckService;
		this.jobSeekerService=jobSeekerService;
		this.modelMapper=modelMapper;
	}


	@Override
	public Result registerEmployer(EmployerForRegisterDto employerForRegisterDto) {
		Result result = BusinessRules.run(
				userService.isEmailExist(employerForRegisterDto.getEmail()),
				checkIfCorporateEmail(employerForRegisterDto.getEmail(), employerForRegisterDto.getWebsite()),
				checkIfPasswordMatch(employerForRegisterDto.getPassword(), employerForRegisterDto.getConfirmPassword())
//				,checkIfNullEmployer(employerForRegisterDto)
				);
		
		if(result!=null)
			return result;
		
		Employer employer = Employer.childBuilder()
				.companyName(employerForRegisterDto.getCompanyName())
				.website(employerForRegisterDto.getWebsite())
				.phone(employerForRegisterDto.getPhone())
				.password(employerForRegisterDto.getPassword())
				.email(employerForRegisterDto.getEmail()).build();
				
		employerService.add(employer);
		//kullanıcı ve mail adresini veriyoruz.
		verificationService.createAndSendVerificationCode(employer, employer.getEmail());
		
		//register işleminden sonra bu bilgilerin employeeconfirms tablosuna eklenmesi gerekir. Çünkü oradan HRMS doğrulaması yapılacak.
		employeeConfirmService.add(EmployeeConfirm.builder().user(employer).build());
		
		return new SuccessResult("Kayıt gerçekleşti. Bu aşamadan sonra e posta doğrulaması yapıp Hrms onayını bekleyiniz.");
	}
	
	@Override
	public Result registerJobSeeker(JobSeekerForRegisterDto jobSeekerForRegisterDto) {
		
		var mernisValidation = this.modelMapper.map(jobSeekerForRegisterDto, MernisValidation.class);

		Result result = BusinessRules.run(
				userService.isEmailExist(jobSeekerForRegisterDto.getEmail()),
				checkIfIdentityNumberExist(jobSeekerForRegisterDto.getIdentityNumber()),
				checkIfPasswordMatch(jobSeekerForRegisterDto.getPassword(), jobSeekerForRegisterDto.getConfirmPassword()),
//				checkIfUserCheck(new MernisValidation(jobSeekerForRegisterDto.getIdentityNumber(),
//						jobSeekerForRegisterDto.getFirstName(),jobSeekerForRegisterDto.getLastName(),
//						jobSeekerForRegisterDto.getBirthYear()))
				checkIfUserCheck(mernisValidation)
//				,checkIfNullJobSeeker(jobSeekerForRegisterDto)
				);
//				checkIfMernisValidation(new MernisValidation(
//						jobSeekerForRegister.getIdentityNumber(),
//						jobSeekerForRegister.getFirstName(),
//						jobSeekerForRegister.getLastName(),
//						jobSeekerForRegister.getBirthYear())));
//				mernisCheckService.checkIfRealPerson(new MernisValidation(jobSeekerForRegister.getIdentityNumber(),
//						jobSeekerForRegister.getFirstName(),
//						jobSeekerForRegister.getLastName(),
//						jobSeekerForRegister.getBirthYear())));
		
		if(result!=null) {
			return result;
		}
			
		
		var jobSeeker = this.modelMapper.map(jobSeekerForRegisterDto, JobSeeker.class);
		
//		JobSeeker jobSeeker = JobSeeker.childBuilder()
//				.email(jobSeekerForRegisterDto.getEmail())
//				.password(jobSeekerForRegisterDto.getPassword())
//				.firstName(jobSeekerForRegisterDto.getFirstName())
//				.lastName(jobSeekerForRegisterDto.getLastName())
//				.identityNumber(jobSeekerForRegisterDto.getIdentityNumber())
//				.birthYear(jobSeekerForRegisterDto.getBirthYear())
//				.build();
		
		
		jobSeekerService.add(jobSeeker);
		verificationService.createAndSendVerificationCode(jobSeeker, jobSeeker.getEmail());
		
		return new SuccessResult("Kayıt gerçekleşti. Bu adımdam sonra email doğrulaması yapınız.");
		
	}

	
	
	private Result checkIfCorporateEmail(String email,String website) {
		return email.split("@")[1].equals(website) ? new SuccessResult("Email web sitesi ile aynı domaine sahip") : 
			new ErrorResult("Email web sitesi ile aynı domaine sahip değil");
	}

	
	private Result checkIfPasswordMatch(String password, String confirmPassword) {
		return password.equals(confirmPassword) ? new SuccessResult() : new ErrorResult("Parola hatalı");
	}

	
	private Result checkIfIdentityNumberExist(String identityNumber) {
		return this.jobSeekerService.getByIdentityNumber(identityNumber).isSuccess() ? new ErrorResult("Bu Tc no kayıtlı"):
			new SuccessResult();
	}
	
	private Result checkIfUserCheck(MernisValidation mernisValidation) {
		if(userCheckService.checkIfRealPerson(mernisValidation)) {
			return new SuccessResult();
		}
		
		return new ErrorResult("Mernis doğrulaması yapılamadı");
	}
	


//	private Result checkIfNullEmployer(EmployerForRegisterDto employerForRegisterDto) {
//		if(employerForRegisterDto.getCompanyName()==null && employerForRegisterDto.getWebsite() ==null &&
//				employerForRegisterDto.getEmail()==null && employerForRegisterDto.getPhone()==null &&
//				employerForRegisterDto.getPassword() == null && employerForRegisterDto.getConfirmPassword() ==null) {
//			return new ErrorResult("Tüm alanlar zorunludur");
//		}
//		
//		return new SuccessResult();
//	}
//	
//	
//	private Result checkIfNullJobSeeker(JobSeekerForRegisterDto jobSeekerForRegisterDto) {
//		if(jobSeekerForRegisterDto.getFirstName()==null && jobSeekerForRegisterDto.getLastName() ==null &&
//				jobSeekerForRegisterDto.getIdentityNumber()==null && jobSeekerForRegisterDto.getBirthYear()==null &&
//						jobSeekerForRegisterDto.getEmail() == null && jobSeekerForRegisterDto.getPassword()==null &&
//						jobSeekerForRegisterDto.getConfirmPassword() ==null) {
//			return new ErrorResult("Tüm alanlar zorunludur");
//		}
//		
//		return new SuccessResult();
//	}
	

}
