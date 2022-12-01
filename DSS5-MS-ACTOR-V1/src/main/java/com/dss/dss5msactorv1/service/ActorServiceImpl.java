package com.dss.dss5msactorv1.service;

import com.dss.dss5msactorv1.dto.ActorDto;
import com.dss.dss5msactorv1.dto.DTOMapper;
import com.dss.dss5msactorv1.entity.Actor;
import com.dss.dss5msactorv1.exception.ActorNotFoundException;
import com.dss.dss5msactorv1.exception.CannotDeleteActorException;
import com.dss.dss5msactorv1.repository.ActorMovieRepository;
import com.dss.dss5msactorv1.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {


    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorMovieRepository actorMovieRepository;

    @Override
    public List<Actor> findAllActors() {
        List<Actor> actorList = null;
        actorList = actorRepository.findAll();
        if(!actorList.isEmpty()) {
            return actorList;
        }else{
            throw new ActorNotFoundException("No actors found.");
        }
    }

    @Override
    public List<Actor> findActorByFirstName(String firstName) {
        List<Actor> actorFound = null;
        actorFound = actorRepository.findByFirstName(firstName);
        if(!actorFound.isEmpty()){
            return actorFound;
        }else{
            throw new ActorNotFoundException("No actors found with first name: " + firstName + ".");
        }

    }

    @Override
    public List<Actor> findActorByLastName(String lastName) {
        List<Actor> actorFound = null;
        actorFound = actorRepository.findByLastName(lastName);
        if(!actorFound.isEmpty()){
            return actorFound;
        }else{
            throw new ActorNotFoundException("No actors found with last name: " + lastName + ".");
        }
    }

    @Override
    public Actor findActorById(int id) {
        Actor actorFound = null;
        Optional<Actor> actor = actorRepository.findById(id);
        if(actor.isPresent()) {
            actorFound = actor.get();
            return actorFound;
        }else{
            throw new ActorNotFoundException("No actors found with id: " + id + ".");
        }
    }

    @Override
    public String addActor(ActorDto actorDto){
        String responseMessage = null;
        DTOMapper dtoMapper = new DTOMapper();
        Actor actor = dtoMapper.mapActor(actorDto);
        actorRepository.save(actor);
        responseMessage = "Actor successfully added.";
        return responseMessage;

    }

    @Override
    public String updateActor(int id, ActorDto actorDto){
        Actor updateActor = null;
        String responseMessage = null;
        DTOMapper dtoMapper = new DTOMapper();
        Actor actor = dtoMapper.mapActor(actorDto);
        Optional<Actor> findActor = actorRepository.findById(id);
        if(findActor.isPresent()) {
            updateActor = findActor.get();
            updateActor.setFirstName(actor.getFirstName());
            updateActor.setLastName(actor.getLastName());
            updateActor.setAge(actor.getAge());
            updateActor.setGender(actor.getGender());
            actorRepository.save(updateActor);
            responseMessage = "Data successfully updated.";
            return responseMessage;
        }else{
            responseMessage = "No such data with id = " + id + ".";
            throw new ActorNotFoundException(responseMessage);
        }

    }

    @Override
    public String deleteActorById(int id){
        String responseMessage = null;
        Optional<Actor> findActor = actorRepository.findById(id);
        if(findActor.isPresent()){
            if(actorMovieRepository.findByActorId(id).isEmpty()) {
                actorRepository.deleteById(id);
                responseMessage = "Data successfully deleted.";
                return responseMessage;
            }else{
                responseMessage = "Cannot delete actor with existing movies.";
                throw new CannotDeleteActorException(responseMessage);
            }
        }else {
            responseMessage = "No such data with id = " + id + ".";
            throw new ActorNotFoundException(responseMessage);
        }

    }

}
