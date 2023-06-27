package com.kenzie.dynamodb.annotationsloadsave.actormovie;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Data access object to retrieve ActorMovie items from DynamoDB.
 */
public class ActorMovieDao {

    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of ActorMovie objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public ActorMovieDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     *  Loads an item from the ActorMovies table and maps it to the ActorMovie Class.
     *
     * @param partitionKey is the value of the partition key being loaded
     * @param sortKey is the value of the sort key being loaded
     * @return actor returns the actor loaded from the database
     */
    public ActorMovie getActorMovie(String partitionKey, String sortKey) {
        //TODO
        return null;
    }

    /**
     * Saves the given `ActorMovie`.
     *
     * @param actorMovie is the item that is being saved to the table
     */
    public void saveActorMovie(ActorMovie actorMovie) {
        // PARTICIPANTS: Save an ActorMovie to the table
    }
}
