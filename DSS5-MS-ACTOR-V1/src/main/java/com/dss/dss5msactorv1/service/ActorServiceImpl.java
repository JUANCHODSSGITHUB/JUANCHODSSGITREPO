package com.dss.dss5msactorv1.service;

import com.dss.dss5msactorv1.entity.Actor;
import com.dss.dss5msactorv1.repository.ActorRepository;
import com.mysql.cj.exceptions.ExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {


    private static final Logger LOG =   LoggerFactory.getLogger(ExceptionFactory.class);

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Actor> findAllActors() {
        List<Actor> actorList = null;
        actorList = actorRepository.findAll();
        return actorList;
    }

    @Override
    public List<Actor> findActorByFirstName(String firstName) {
        List<Actor> actorFound = null;
        try{
            actorFound = actorRepository.findByFirstName(firstName);
            actorFound.size(); //for checking
        }catch (NoSuchElementException | NullPointerException e){
            LOG.error(e.getMessage());
        }
        return actorFound;
    }

    @Override
    public List<Actor> findActorByLastName(String lastName) {
        List<Actor> actorFound = null;
        try{
            actorFound = actorRepository.findByFirstName(lastName);
            actorFound.size(); //for checking
        }catch (NoSuchElementException | NullPointerException e){
            LOG.error(e.getMessage());
        }
        return actorFound;
    }

    @Override
    public Actor findActorById(int id) {
        Actor actorFound = null;

        try{
            Optional<Actor> booking = actorRepository.findById(id);
            actorFound = booking.get();
        }catch (NoSuchElementException e){
            LOG.error(e.getMessage());
        }

        return actorFound;
    }

    @Override
    public String addActor(Actor actor){
        String responseMessage = null;
        Actor savedActor = null;

        savedActor = actorRepository.save(actor);
        responseMessage = "Actor successfully added.";
        return responseMessage;

    }

    @Override
    public String updateActor(Actor actor){
        Actor savedActor = null;
        Actor updateActor = null;

        String responseMessage = null;
        try{
            Optional<Actor> findActor = actorRepository.findById(actor.getActorId());
            updateActor = findActor.get();
            updateActor.setFirstName(actor.getFirstName());
            updateActor.setLastName(actor.getLastName());
            updateActor.setAge(actor.getAge());
            updateActor.setGender(actor.getGender());
            savedActor = actorRepository.save(updateActor);
            responseMessage = "Data successfully updated.";

        }catch (NoSuchElementException | NullPointerException e){
            responseMessage = "No such data with id = " + actor.getActorId() + ".";
            LOG.error(e.getMessage());
        }

        return responseMessage;
    }

    @Override
    public String deleteActorById(int id){
        String responseMessage = null;
        try {
            actorRepository.deleteById(id);
            responseMessage = "Data successfully deleted.";
        }catch(EmptyResultDataAccessException e){
            responseMessage = "No such data with id = " + id + ".";
            LOG.error(e.getMessage());
        }
        return responseMessage;
    }

}
