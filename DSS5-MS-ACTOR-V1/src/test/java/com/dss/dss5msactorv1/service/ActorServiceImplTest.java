package com.dss.dss5msactorv1.service;

import com.dss.dss5msactorv1.dto.ActorDto;
import com.dss.dss5msactorv1.entity.Actor;
import com.dss.dss5msactorv1.entity.ActorMovie;
import com.dss.dss5msactorv1.exception.ActorNotFoundException;
import com.dss.dss5msactorv1.exception.CannotDeleteActorException;
import com.dss.dss5msactorv1.repository.ActorMovieRepository;
import com.dss.dss5msactorv1.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ActorServiceImplTest {

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private ActorMovieRepository actorMovieRepository;

    @InjectMocks
    private ActorService actorService = new ActorServiceImpl();


    @Test
    void findAllActors() {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(mockActor());
        Mockito.when(actorRepository.findAll()).thenReturn(actorList);
        assertNotNull(actorService.findAllActors());
        Mockito.verify(actorRepository).findAll();
    }

    @Test
    void findAllActorsFail() {
        List<Actor> actorList = new ArrayList<>();
        Mockito.when(actorRepository.findAll()).thenReturn(actorList);
        assertThrows(ActorNotFoundException.class, () -> actorService.findAllActors());
    }

    @Test
    void findActorByFirstName() {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(mockActor());
        Mockito.when(actorRepository.findByFirstName("Tom")).thenReturn(actorList);
        assertNotNull(actorService.findActorByFirstName("Tom"));
        Mockito.verify(actorRepository).findByFirstName("Tom");
    }

    @Test
    void findActorByFirstNameFail() {
        List<Actor> actorList = new ArrayList<>();
        Mockito.when(actorRepository.findByFirstName("Tom")).thenReturn(actorList);
        assertThrows(ActorNotFoundException.class, () -> actorService.findActorByFirstName("Tom"));
    }

    @Test
    void findActorByLastName() {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(mockActor());
        Mockito.when(actorRepository.findByLastName("Holland")).thenReturn(actorList);
        assertNotNull(actorService.findActorByLastName("Holland"));
        Mockito.verify(actorRepository).findByLastName("Holland");
    }

    @Test
    void findActorByLastNameFail() {
        List<Actor> actorList = new ArrayList<>();
        Mockito.when(actorRepository.findByLastName("Holland")).thenReturn(actorList);
        assertThrows(ActorNotFoundException.class, () -> actorService.findActorByLastName("Holland"));
    }

    @Test
    void findActorById() {
        Optional<Actor> optionalActor = Optional.of(mockActor());
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        assertNotNull( actorService.findActorById(1));
        Mockito.verify(actorRepository).findById(1);

    }

    @Test
    void findActorByIdFail() {
        Optional<Actor> optionalActor = Optional.empty();
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        assertThrows(ActorNotFoundException.class, () -> actorService.findActorById(1));
    }

    @Test
    void addActor() {
        Mockito.when(actorRepository.save(any(Actor.class))).thenReturn(mockActor());
        assertEquals("Actor successfully added.", actorService.addActor(mockActorDto()));
    }

    @Test
    void updateActor() {
        Optional<Actor> optionalActor = Optional.of(mockActor());
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        assertEquals("Data successfully updated.", actorService.updateActor(1,mockActorDto()));
    }

    @Test
    void updateActorFail() {
        Optional<Actor> optionalActor = Optional.empty();
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        assertThrows(ActorNotFoundException.class, () -> actorService.updateActor(1,mockActorDto()));
    }

    @Test
    void deleteActorById(){
        Optional<Actor> optionalActor = Optional.of(mockActor());
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        List<ActorMovie> actorMovies = new ArrayList<>();
        Mockito.when(actorMovieRepository.findByActorId(1)).thenReturn(actorMovies);
        assertEquals("Data successfully deleted.", actorService.deleteActorById(1));
    }

    @Test
    void deleteActorByIdFail1(){
        Optional<Actor> optionalActor = Optional.of(mockActor());
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        List<ActorMovie> actorMovies = new ArrayList<>();
        actorMovies.add(mockActorMovie());
        Mockito.when(actorMovieRepository.findByActorId(1)).thenReturn(actorMovies);
        assertThrows(CannotDeleteActorException.class, () -> actorService.deleteActorById(1));
    }

    @Test
    void deleteActorByIdFail2(){
        Optional<Actor> optionalActor = Optional.empty();
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        assertThrows(ActorNotFoundException.class, () -> actorService.deleteActorById(1));
    }

    private Actor mockActor(){
        Actor actor = new Actor();
        actor.setActorId(1);
        actor.setFirstName("Tom");
        actor.setLastName("Holland");
        actor.setAge(26);
        actor.setGender('F');
        return actor;
    }

    private ActorMovie mockActorMovie(){
        ActorMovie actorMovie = new ActorMovie(1, 1);
        return actorMovie;
    }

    private ActorDto mockActorDto(){
        ActorDto actor = new ActorDto();
        actor.setFirstName("Tom");
        actor.setLastName("Holland");
        actor.setAge(26);
        actor.setGender('F');
        return actor;
    }
}