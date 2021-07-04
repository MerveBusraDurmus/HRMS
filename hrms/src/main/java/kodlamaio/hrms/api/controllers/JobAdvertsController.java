package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertForListDto;

@RestController
@RequestMapping("/api/jobadverts")
public class JobAdvertsController {

	private JobAdvertService jobAdvertService;

	@Autowired
	public JobAdvertsController(JobAdvertService jobAdvertService) {
		this.jobAdvertService = jobAdvertService;
	}
	
	
	@GetMapping("/getallbyisactive")
	public ResponseEntity<DataResult<List<JobAdvert>>> getAllByIsActive(boolean isActive){
		
		DataResult<List<JobAdvert>> result = this.jobAdvertService.getAllByIsActive(isActive);
		
		if(!result.isSuccess())
			return new ResponseEntity<DataResult<List<JobAdvert>>>(result,HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(result);
	
	}
	
	
	@GetMapping("/getAllByIsActiveForList")
	public ResponseEntity<DataResult<List<JobAdvertForListDto>>> getAllByIsActiveForList(boolean isActive){
		
		DataResult<List<JobAdvertForListDto>> result = this.jobAdvertService.getAllByIsActiveForList(isActive);
		
		if(!result.isSuccess())
			return new ResponseEntity<DataResult<List<JobAdvertForListDto>>>(result,HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(result);	
	}
	
	
	@GetMapping("/getAllByIsActiveOrderByCreateDate")
	public ResponseEntity<DataResult<List<JobAdvertForListDto>>> getAllByIsActiveOrderByCreateDate(boolean isActive){
		DataResult<List<JobAdvertForListDto>> result = this.jobAdvertService.getAllByIsActiveOrderByCreateDate(isActive);
		
		if(!result.isSuccess())
			return new ResponseEntity<DataResult<List<JobAdvertForListDto>>>(result,HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getAllByIsActiveAndCompanyName")
	public ResponseEntity<DataResult<List<JobAdvertForListDto>>> getAllByIsActiveAndCompanyName(boolean isActive, String companyName){
		DataResult<List<JobAdvertForListDto>> result = this.jobAdvertService.getAllByIsActiveAndCompanyName(isActive, companyName);
		
		if(!result.isSuccess())
			return new ResponseEntity<DataResult<List<JobAdvertForListDto>>>(result,HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/updateIsActiveByEmployer")
	public ResponseEntity<Result> updateIsActiveByEmployer(int jobAdvertId) {
		
		Result result = this.jobAdvertService.updateIsActiveByEmployer(jobAdvertId);
		
		if(!result.isSuccess())
			return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(result);
	}
}
