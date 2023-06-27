package com.kenzie.dynamodb.annotationsloadsave.videogames;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Data access object to retrieve VideoGame items from DynamoDB.
 */
public class VideoGameDao {

    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of VideoGame objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public VideoGameDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     *  Loads an item from the ActorMovies table and maps it to the ActorMovie Class.
     *
     * @param partitionKey is the value of the partition key being loaded
     * @param sortKey is the value of the sort key being loaded
     * @return returns the video game loaded from the database
     */
    public VideoGame getVideoGame(String partitionKey, String sortKey) {
        return this.mapper.load(VideoGame.class, partitionKey, sortKey);
    }

    /**
     * Saves the given `VideoGame`.
     *
     * @param videoGame is the item that is being saved to the table
     */
    public void storeGameDetails(VideoGame videoGame) {
    }

    /**
     * Returns the specific Mario Game.
     *
     */
    public VideoGame getMarioKartDeluxe() {
        // PARTICIPANTS: return the correct game
        return new VideoGame();
    }
}
