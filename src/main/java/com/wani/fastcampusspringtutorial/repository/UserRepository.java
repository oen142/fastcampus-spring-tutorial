package com.wani.fastcampusspringtutorial.repository;

import com.wani.fastcampusspringtutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
