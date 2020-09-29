package com.example.demo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
/**
 * ユーザー情報 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Page<User> findAll(Pageable pageable);
	//@Query("SELECT u FROM User u WHERE u.address Like %:name %")
	@Query(value = "SELECT * FROM jyusyoroku u WHERE u.address Like %:name%", nativeQuery=true)
	public Page<User> findSearch(@Param("name") String userName, Pageable pageable);






}