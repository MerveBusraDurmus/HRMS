package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.EmployeeConfirmEmployer;

public interface EmployeeConfirmEmployerDao extends JpaRepository<EmployeeConfirmEmployer, Integer> {

}
