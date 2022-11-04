package com.dss.dss5msactorv1.service;

import com.dss.dss5msactorv1.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAllActors();
    List<Actor> findActorByFirstName(String firstName);
    List<Actor> findActorByLastName(String lastName);
    Actor findActorById(int id);
    String addActor(Actor actor);

    String updateActor(Actor actor);
    String deleteActorById(int id);
}
