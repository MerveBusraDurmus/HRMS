package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertForListDto;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer>{
	
	List<JobAdvert> findAllByIsActive(boolean isActive);
	
	@Query("SELECT new kodlamaio.hrms.entities.dtos.JobAdvertForListDto"
			+ "(e.companyName , jp.title , j.numberOfOpenPositions , j.createDate, j.applicationDeadline) "
			+ "FROM JobAdvert j INNER JOIN j.employer e INNER JOIN j.jobPosition jp WHERE j.isActive=:isActive ")
	List<JobAdvertForListDto> findAllByIsActiveForList(@Param("isActive") boolean isActive);
	
	
	@Query("SELECT new kodlamaio.hrms.entities.dtos.JobAdvertForListDto"
			+ "(e.companyName , jp.title , j.numberOfOpenPositions , j.createDate, j.applicationDeadline) "
			+ "FROM JobAdvert j INNER JOIN j.employer e INNER JOIN j.jobPosition jp WHERE j.isActive=:isActive "
			+ "ORDER BY j.createDate")
	List<JobAdvertForListDto> findAllByIsActiveOrderByCreateDate(@Param("isActive") boolean isActive);
	
	@Query("SELECT new kodlamaio.hrms.entities.dtos.JobAdvertForListDto"
			+ "(e.companyName , jp.title , j.numberOfOpenPositions , j.createDate, j.applicationDeadline) "
			+ "FROM JobAdvert j INNER JOIN j.employer e INNER JOIN j.jobPosition jp WHERE j.isActive=:isActive AND e.companyName=:companyName")
	List<JobAdvertForListDto> findAllByIsActiveAndCompanyName(@Param("isActive") boolean isActive , @Param("companyName") String companyName);
	
	
	
}
