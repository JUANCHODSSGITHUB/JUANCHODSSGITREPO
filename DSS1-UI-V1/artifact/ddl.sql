create table users(
                      user_id VARCHAR ( 50 ) PRIMARY KEY,
                      password VARCHAR ( 50 ) NOT NULL,
                      email VARCHAR ( 255 ) UNIQUE NOT NULL,
                      first_name VARCHAR ( 255 ) NOT NULL,
                      last_name VARCHAR ( 255 ) NOT NULL,
                      phone_number VARCHAR (11) NOT NULL,
);

create table actors(
                       actor_id INT PRIMARY KEY,
                       first_name VARCHAR ( 255 ) NOT NULL,
                       last_name VARCHAR ( 255 ) NOT NULL,
                       age INT NOT NULL,
                       gender CHAR NOT NULL
);

create table movies(
                       movie_id INT PRIMARY KEY,
                       movie_title VARCHAR ( 255 ) NOT NULL,
                       movie_image TEXT NOT NULL,
                       movie_cost INT NOT NULL,
                       movie_year INT NOT NULL,
                       CONSTRAINT FK_Actor FOREIGN KEY(actor_id) REFERENCES actors(actor_id) ON DELETE SET NULL
);

create table reviews(
                        review_id INT PRIMARY KEY,
                        movie_id INT NOT NULL,
                        description TEXT NOT NULL,
                        date_posted DATE NOT NULL,
                        rating INT NOT NULL,
                        CONSTRAINT FK_Movie FOREIGN KEY(movie_id) REFERENCES movies(movie_id) ON DELETE SET NULL
);


CREATE TABLE actor_movie(
                        actor_id int NOT NULL,
                        movie_id int NOT NULL,
                        CONSTRAINT FK_actor FOREIGN KEY (actor_id) REFERENCES actors(actor_id),
                        CONSTRAINT FK_movie FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);