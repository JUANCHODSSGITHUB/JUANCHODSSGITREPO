package com.dss.dss5msactorv1.controller;

import com.dss.dss5msactorv1.dto.ActorDto;
import com.dss.dss5msactorv1.entity.Actor;
import com.dss.dss5msactorv1.service.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ActorControllerTest.class)
class ActorControllerTest {

    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorController actorController;

    @Test
    void getActorById() {
        Mockito.when(actorService.findActorById(1)).thenReturn(mockActor());
        ResponseEntity<Actor> response = actorController.getActorById(1);
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getByFirstName() {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(mockActor());
        Mockito.when(actorService.findActorByFirstName("Tom")).thenReturn(actorList);
        ResponseEntity<List<Actor>> response = actorController.getByFirstName("Tom");
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getByLastName() {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(mockActor());
        Mockito.when(actorService.findActorByLastName("Holland")).thenReturn(actorList);
        ResponseEntity<List<Actor>> response = actorController.getByLastName("Holland");
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void getActors() {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(mockActor());
        Mockito.when(actorService.findAllActors()).thenReturn(actorList);
        ResponseEntity<List<Actor>> response = actorController.getActors();
        Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    void deleteActor() {
        Mockito.when(actorService.deleteActorById(1)).thenReturn("Data successfully deleted.");
        ResponseEntity<String> response = actorController.deleteActor(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addActor() {
        Mockito.when(actorService.addActor(mockActorDto())).thenReturn("Actor successfully added.");
        ResponseEntity<String> response = actorController.addActor(mockActorDto());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateActor() {
        Mockito.when(actorService.updateActor(1, mockActorDto())).thenReturn("Data successfully updated.");
        ResponseEntity<String> response = actorController.updateActor(1, mockActorDto());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private Actor mockActor(){
        Actor actor = new Actor();
        actor.setFirstName("Tom");
        actor.setLastName("Holland");
        actor.setAge(26);
        actor.setGender('F');
        return actor;
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