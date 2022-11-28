package com.dss.dss5msactorv1.repository;

import com.dss.dss5msactorv1.entity.ActorMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorMovieRepository extends JpaRepository<ActorMovie, Integer> {

    List<ActorMovie> findByActorId(int actorId);
}
