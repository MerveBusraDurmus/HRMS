package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.JobSeekerForRegisterDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	
	@PostMapping("/registeremployer")
	public ResponseEntity<Result> registerEmployer(@Valid @RequestBody EmployerForRegisterDto employerForRegisterDto) {
		
		Result result = authService.registerEmployer(employerForRegisterDto);
		
		if(!result.isSuccess())
			return new ResponseEntity<Result>(result,HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/registerjobseeker")
	public ResponseEntity<Result> registerJobSeeker(@Valid @RequestBody JobSeekerForRegisterDto jobSeekerForRegisterDto){
		Result result = authService.registerJobSeeker(jobSeekerForRegisterDto);
		
		if(!result.isSuccess())
			return new ResponseEntity<Result>(result,HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.ok(result);
	}
}
