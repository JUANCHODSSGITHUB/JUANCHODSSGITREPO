package com.dss.dss5msactorv1.dto;

import com.dss.dss5msactorv1.entity.Actor;

public class DTOMapper {

    public Actor mapActor(ActorDto actorDto){
        return new Actor(actorDto.getFirstName(), actorDto.getLastName(), actorDto.getAge(), actorDto.getGender());
    }
}
