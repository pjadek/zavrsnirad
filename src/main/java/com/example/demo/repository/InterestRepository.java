package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Interest;
import com.example.demo.model.InterestId;

public interface InterestRepository extends JpaRepository<Interest, InterestId> {

	List<Interest> findByInterestIdUser(long id);

}
