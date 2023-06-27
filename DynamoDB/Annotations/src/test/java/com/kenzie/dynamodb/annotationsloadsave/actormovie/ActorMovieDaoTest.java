package com.kenzie.dynamodb.annotationsloadsave.actormovie;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ActorMovieDaoTest {

    @Mock
    DynamoDBMapper mapper;

    @InjectMocks
    ActorMovieDao actorMovieDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getActorMovie_actorMovieExists_returnsPopulatedActorMovie() {
        //GIVEN
        String actorName = "Chadwick Boseman";
        String movieTitle = "42";
        ActorMovie expectedActorMovie = new ActorMovie();
        expectedActorMovie.setActor(actorName);
        expectedActorMovie.setMovieTitle(movieTitle);
        expectedActorMovie.setDirector("Brian Helgeland");
        expectedActorMovie.setWatched(false);
        expectedActorMovie.setYearReleased(2013);
        when(mapper.load(ActorMovie.class, actorName, movieTitle)).thenReturn(expectedActorMovie);

        //WHEN
        ActorMovie actualActorMovie = actorMovieDao.getActorMovie(actorName, movieTitle);

        //THEN
        assertEquals(expectedActorMovie.getActor(), actualActorMovie.getActor(), "ActorMovie names don't match!");
        assertEquals(expectedActorMovie.getMovieTitle(), actualActorMovie.getMovieTitle(), "Movie titles don't match!");
        assertEquals(expectedActorMovie.getDirector(), actualActorMovie.getDirector(), "Directors don't match!");
        assertEquals(expectedActorMovie.isWatched(), actualActorMovie.isWatched(), "Watch status doesn't match!");
        assertEquals(expectedActorMovie.getYearReleased(), actualActorMovie.getYearReleased(), "Years released don't match!");
    }

    @Test
    public void saveActorMovie_withActorMovie_savesItemToDynamoDB() {
        //GIVEN
        String actorName = "Chadwick Boseman";
        String movieTitle = "42";
        ActorMovie actorMovie = new ActorMovie();
        actorMovie.setActor(actorName);
        actorMovie.setMovieTitle(movieTitle);
        actorMovie.setDirector("Brian Helgeland");
        actorMovie.setWatched(false);
        actorMovie.setYearReleased(2013);

        // WHEN
        actorMovieDao.saveActorMovie(actorMovie);

        // THEN
        verify(mapper, times(1)).save(actorMovie);
    }

}
