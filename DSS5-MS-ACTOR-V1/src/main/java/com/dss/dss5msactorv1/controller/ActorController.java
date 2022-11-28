package com.dss.dss5msactorv1.controller;


import com.dss.dss5msactorv1.dto.ActorDto;
import com.dss.dss5msactorv1.entity.Actor;
import com.dss.dss5msactorv1.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dss/api")
public class ActorController {
    @Autowired
    private ActorService actorService;


    @GetMapping("/actor/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable int id){
        return new ResponseEntity<>(actorService.findActorById(id), HttpStatus.FOUND);
    }

    @GetMapping("/actor/first-name/{firstName}")
    public ResponseEntity<List<Actor>> getByFirstName(@PathVariable String firstName){
        return new ResponseEntity<>(actorService.findActorByFirstName(firstName), HttpStatus.FOUND);
    }

    @GetMapping("/actor/last-name/{lastName}")
    public ResponseEntity<List<Actor>> getByLastName(@PathVariable String lastName){
        return new ResponseEntity<>(actorService.findActorByLastName(lastName), HttpStatus.FOUND);
    }

    @GetMapping("/actor")
    public ResponseEntity<List<Actor>> getActors(){
        return new ResponseEntity<>(actorService.findAllActors(), HttpStatus.FOUND);
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable int id){
        return new ResponseEntity<>(actorService.deleteActorById(id), HttpStatus.OK);
    }


    @PostMapping("/actor")
    public ResponseEntity<String> addActor(@RequestBody ActorDto actorDto){
        return new ResponseEntity<>(actorService.addActor(actorDto), HttpStatus.CREATED);
    }

    @PutMapping("/actor/{id}")
    public ResponseEntity<String> updateActor(@PathVariable int id, @RequestBody ActorDto actorDto){
        return new ResponseEntity<>(actorService.updateActor(id, actorDto), HttpStatus.OK);
    }
}

