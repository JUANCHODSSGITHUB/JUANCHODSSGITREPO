package com.dss.dss5msactorv1.service;

import com.dss.dss5msactorv1.dto.ActorDto;
import com.dss.dss5msactorv1.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAllActors();
    List<Actor> findActorByFirstName(String firstName);
    List<Actor> findActorByLastName(String lastName);
    Actor findActorById(int id);
    String addActor(ActorDto actorDto);

    String updateActor(int id, ActorDto actorDto);
    String deleteActorById(int id);
}
