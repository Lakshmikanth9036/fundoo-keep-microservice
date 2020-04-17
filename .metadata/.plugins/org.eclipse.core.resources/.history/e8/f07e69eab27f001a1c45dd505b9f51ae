package com.bridgelabz.fundookeep.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundookeep.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Modifying
	@Transactional
	@Query("update User u set u.isUserVerified=1,u.updateUser=:time where u.userId=:id")
	int updateUserVerificationStatus(@Param("id") Long id,@Param("time") LocalDateTime time);

	Optional<User> findByEmailAddressOrMobile(String email,Long mobile);

	Optional<User> findByEmailAddress(String emailAddress);
	
	Optional<User> findByMobile(Long mobile);

	@Modifying
	@Transactional
	@Query("update User u set u.password=:newPassword,u.updateUser=:time where u.userId=:id")
	int updatePassword(@Param("id") Long id,@Param("newPassword") String newPassword,@Param("time") LocalDateTime time);

}
