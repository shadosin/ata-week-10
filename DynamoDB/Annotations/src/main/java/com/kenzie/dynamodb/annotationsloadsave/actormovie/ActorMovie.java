package com.kenzie.dynamodb.annotationsloadsave.actormovie;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * ActorMovie POJO which maps to the ActorMovies table.
 */
@DynamoDBTable(tableName = "DynamoDbAnnotationsLoadSave-ActorMovies")
public class ActorMovie {
    private String actor;
    private String movieTitle;
    private String director;
    private Integer yearReleased;
    private Boolean watched;

    @DynamoDBHashKey(attributeName = "actor")
    public String getActor() {
        return actor;
    }

    public void setActor(final String actor) {
        this.actor = actor;
    }

    @DynamoDBRangeKey(attributeName = "movie_title")
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(final String movieTitle) {
        this.movieTitle = movieTitle;
    }

    @DynamoDBAttribute(attributeName = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @DynamoDBAttribute(attributeName = "year_released")
    public Integer getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(Integer yearReleased) {
        this.yearReleased = yearReleased;
    }

    @DynamoDBAttribute(attributeName = "have_watched")
    public Boolean isWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }

    @Override
    public String toString() {
        return "actor: " + actor + " movie title: " + movieTitle + " director: " + director + " year released: " +
                yearReleased + " watched? " + watched;
    }

}
