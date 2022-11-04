package com.dss.dss5msactorv1.repository;

import com.dss.dss5msactorv1.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    List<Actor> findByFirstName(String firstName);
    List<Actor> findByLastName(String lastName);

}
