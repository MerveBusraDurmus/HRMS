package kodlamaio.hrms.dataAccess.abstracts;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Verification;

public interface VerificationDao extends JpaRepository<Verification, Integer>{
	
	Optional<Verification> findByEmailAndActivationCode(String email, String verificationCode);
}
