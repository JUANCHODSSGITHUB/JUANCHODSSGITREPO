package com.dss.dss5msactorv1.entity;


import javax.persistence.*;


@Table(name = "ACTOR_MOVIE")
@Entity
public class ActorMovie {
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Column(name = "actor_id")
    private int actorId;

    public ActorMovie() {
    }

    public ActorMovie(int movieId, int actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }
}
