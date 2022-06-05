package com.mvc.fraudmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mvc.fraudmanagement.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

}
