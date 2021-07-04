package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployeeConfirmService;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/employeeconfirms")
public class EmployeeConfirmsController {

	private EmployeeConfirmService employeeConfirmService;

	
	@Autowired
	public EmployeeConfirmsController(EmployeeConfirmService employeeConfirmService) {
		this.employeeConfirmService = employeeConfirmService;
	}
	
	@GetMapping("/verify")
	public ResponseEntity<Result> verify(@RequestParam final  int userId){
		
		final Result result = employeeConfirmService.verify(userId);
		
		if(!result.isSuccess())
			return new ResponseEntity<Result>(result,HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(result);
	}
}
