package com.dss.dss5msactorv1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACTOR_MOVIE")
@Entity
public class ActorMovie {
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Column(name = "actor_id")
    private int actorId;
    
}
