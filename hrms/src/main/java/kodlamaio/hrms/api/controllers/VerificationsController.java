package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.EmailVerificationCodeForVerifyDto;

@RestController
@RequestMapping("/api/verifications")
public class VerificationsController {
	private VerificationService verificationService;

	@Autowired
	public VerificationsController(VerificationService verificationService) {
		this.verificationService = verificationService;
	}
	
	@GetMapping("/verify")
	public ResponseEntity<Result> verify(@Valid final EmailVerificationCodeForVerifyDto emailVerificationCodeForVerifyDto) {
		final Result result = verificationService.verify(emailVerificationCodeForVerifyDto);
		
		if(!result.isSuccess())
			return new ResponseEntity<Result>(result,HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.ok(result);
	}
}
