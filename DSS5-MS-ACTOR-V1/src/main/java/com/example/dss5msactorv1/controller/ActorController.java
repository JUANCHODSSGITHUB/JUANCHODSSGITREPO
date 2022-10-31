package com.example.dss5msactorv1.controller;


import com.example.dss5msactorv1.model.Actor;
import com.example.dss5msactorv1.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dss/api")
public class ActorController {
    @Autowired
    private ActorService actorService;


    @GetMapping("/actor/{id}")
    public Actor get(@PathVariable int id){
        Actor actor = null;
        actor = actorService.findActorById(id);
        return actor;
    }

    @GetMapping("/actor")
    public List<Actor> get(){
        List actorList = actorService.findAllActors();
        return actorList;
    }

    @DeleteMapping("/actor/{id}")
    public String delete(@PathVariable int id){
        return actorService.deleteActorById(id);
    }


    @PostMapping("/actor")
    public String insert(@RequestBody Actor actor){
        return actorService.addActor(actor);
    }
}

