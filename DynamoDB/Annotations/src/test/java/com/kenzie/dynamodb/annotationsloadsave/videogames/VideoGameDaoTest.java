package com.kenzie.dynamodb.annotationsloadsave.videogames;

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

public class VideoGameDaoTest {

    @Mock
    DynamoDBMapper mapper;

    @InjectMocks
    VideoGameDao videoGameDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getVideoGame_VideoGameExists_returnsMarioKartDeluxe() {
        //GIVEN
        VideoGame expectedVideoGame = new VideoGame();
        expectedVideoGame.setTitle("Mario Kart 8 Deluxe");
        expectedVideoGame.setConsole("Nintendo Switch");
        expectedVideoGame.setNumPlayers(2);
        expectedVideoGame.setDescription("");
        expectedVideoGame.setReleaseYear(2013);
        expectedVideoGame.setHasSequel(false);

        when(mapper.load(VideoGame.class, "Mario Kart 8 Deluxe", "Nintendo Switch")).thenReturn(expectedVideoGame);

        //WHEN
        VideoGame actualVideoGame = videoGameDao.getMarioKartDeluxe();

        //THEN
        assertEquals(expectedVideoGame.getTitle(), actualVideoGame.getTitle(), "VideoGame names don't match!");
        assertEquals(expectedVideoGame.getConsole(), actualVideoGame.getConsole(), "VideoGame consoles don't match!");
    }

    @Test
    public void saveVideoGame_withVideoGame_savesItemToDynamoDB() {
        //GIVEN
        VideoGame expectedVideoGame = new VideoGame();
        expectedVideoGame.setTitle("Mario Kart 8 Deluxe");
        expectedVideoGame.setConsole("Nintendo Switch");
        expectedVideoGame.setNumPlayers(2);
        expectedVideoGame.setDescription("");
        expectedVideoGame.setReleaseYear(2013);
        expectedVideoGame.setHasSequel(false);


        // WHEN
        videoGameDao.storeGameDetails(expectedVideoGame);

        // THEN
        verify(mapper, times(1)).save(expectedVideoGame);
    }

}
