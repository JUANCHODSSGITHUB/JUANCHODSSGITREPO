package com.dss.dss5msactorv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {
    private int actorId;

    private String firstName;

    private String lastName;

    private int age;

    private char gender;

}
