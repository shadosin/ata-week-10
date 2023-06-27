package com.kenzie.dynamodb.annotationsloadsave.actormovie;

import com.kenzie.dynamodb.annotationsloadsave.DynamoDBTestUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActorMovieTest {

    @Test
    public void dynamoDBTable_classShouldBeAnnotated_annotatedForActorMoviesTable() {
        Optional<DynamoDBTable> tableAnnotation = DynamoDBTestUtils.getAnnotation(ActorMovie.class, DynamoDBTable.class);
        assertTrue(tableAnnotation.isPresent(), "Expected the ActorMovie class to be annoted with DynamoDBTable.");
        assertEquals("DynamoDbAnnotationsLoadSave-ActorMovies", tableAnnotation.get().tableName(),
                     "Expected DynamoDbAnnotationsLoadSave-ActorMovies to be the tableName for the " +
                         "DynamoDBTable annotation.");

    }

    @Test
    public void actor_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        //GIVEN
        Optional<DynamoDBHashKey> hashKeyAnnotation = DynamoDBTestUtils.getAnnotation(ActorMovie.class,
                                                                                      "actor", DynamoDBHashKey.class);
        assertTrue(hashKeyAnnotation.isPresent(), "Expected getActor to be annotated with " +
                "DynamoDBHashKey.");
        assertEquals("actor", hashKeyAnnotation.get().attributeName(),
                "Expected actor as the actor dynamoDB attribute name.");

    }

    @Test
    public void movieTitle_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBRangeKey> movieTitleAnnotation = DynamoDBTestUtils.getAnnotation(ActorMovie.class,
                                                                                          "movieTitle", DynamoDBRangeKey.class);
        assertTrue(movieTitleAnnotation.isPresent(), "Expected getMovieTitle to be annotated with " +
                "DynamoDBRangeKey.");
        assertEquals("movie_title", movieTitleAnnotation.get().attributeName(),
                "Expected movie_title as the movieTitle DynamoDB attribute name.");
    }

    @Test
    public void director_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> directorAnnotation = DynamoDBTestUtils.getAnnotation(ActorMovie.class,
                                                                                         "director", DynamoDBAttribute.class);
        assertTrue(directorAnnotation.isPresent(), "Expected getDirector to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("director", directorAnnotation.get().attributeName(),
                "Expected director as the director DynamoDB attribute name in the annotation.");
    }

    @Test
    public void yearReleased_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> yearReleasedAnnotation = DynamoDBTestUtils.getAnnotation(ActorMovie.class,
                                                                                             "yearReleased", DynamoDBAttribute.class);
        assertTrue(yearReleasedAnnotation.isPresent(), "Expected getYearReleased to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("year_released", yearReleasedAnnotation.get().attributeName(),
                "Expected year_released as the yearReleased DynamoDB attribute name in the annotation.");
    }

    @Test
    public void watched_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> yearReleasedAnnotation = DynamoDBTestUtils.getAnnotation(ActorMovie.class,
                                                                                             "watched", DynamoDBAttribute.class);
        assertTrue(yearReleasedAnnotation.isPresent(), "Expected getWatched to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("have_watched", yearReleasedAnnotation.get().attributeName(),
                "Expected have_watched as the watched DynamoDB attribute name in the annotation.");
    }

}
