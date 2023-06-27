package com.kenzie.dynamodb.annotationsloadsave.videogames;

import com.kenzie.dynamodb.annotationsloadsave.utils.DynamoDBTestUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VideoGameTest {

    @Test
    public void dynamoDBTable_classShouldBeAnnotated_annotatedForVideoGamesTable() {
        Optional<DynamoDBTable> tableAnnotation = DynamoDBTestUtils.getAnnotation(VideoGame.class, DynamoDBTable.class);
        assertTrue(tableAnnotation.isPresent(), "Expected the VideoGame class to be annotated with DynamoDBTable.");
        assertEquals("DynamoDbAnnotationsLoadSave-VideoGames", tableAnnotation.get().tableName(),
                "Expected DynamoDbAnnotationsLoadSave-VideoGames to be the tableName for the " +
                        "DynamoDBTable annotation.");

    }

    @Test
    public void title_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        //GIVEN
        Optional<DynamoDBHashKey> hashKeyAnnotation = DynamoDBTestUtils.getAnnotation(VideoGame.class,
                "title", DynamoDBHashKey.class);
        assertTrue(hashKeyAnnotation.isPresent(), "Expected getTitle to be annotated with " +
                "DynamoDBHashKey.");
        assertEquals("title", hashKeyAnnotation.get().attributeName(),
                "Expected title as the title dynamoDB attribute name.");

    }

    @Test
    public void console_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBRangeKey> rangeKeyAnnotation = DynamoDBTestUtils.getAnnotation(VideoGame.class,
                "console", DynamoDBRangeKey.class);
        assertTrue(rangeKeyAnnotation.isPresent(), "Expected getConsole to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("console", rangeKeyAnnotation.get().attributeName(),
                "Expected console as the console DynamoDB attribute name.");
    }

    @Test
    public void releaseYear_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> directorAnnotation = DynamoDBTestUtils.getAnnotation(VideoGame.class,
                "releaseYear", DynamoDBAttribute.class);
        assertTrue(directorAnnotation.isPresent(), "Expected releaseYear to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("release_year", directorAnnotation.get().attributeName(),
                "Expected release_year as the releaseYear DynamoDB attribute name in the annotation.");
    }

    @Test
    public void hasSequel_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> yearReleasedAnnotation = DynamoDBTestUtils.getAnnotation(VideoGame.class,
                "hasSequel", DynamoDBAttribute.class);
        assertTrue(yearReleasedAnnotation.isPresent(), "Expected hasSequel to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("has_sequel", yearReleasedAnnotation.get().attributeName(),
                "Expected has_sequel as the hasSequel DynamoDB attribute name in the annotation.");
    }

    @Test
    public void description_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> yearReleasedAnnotation = DynamoDBTestUtils.getAnnotation(VideoGame.class,
                "description", DynamoDBAttribute.class);
        assertTrue(yearReleasedAnnotation.isPresent(), "Expected description to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("description", yearReleasedAnnotation.get().attributeName(),
                "Expected description as the description DynamoDB attribute name in the annotation.");
    }

    @Test
    public void numPlayers_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> yearReleasedAnnotation = DynamoDBTestUtils.getAnnotation(VideoGame.class,
                "numPlayers", DynamoDBAttribute.class);
        assertTrue(yearReleasedAnnotation.isPresent(), "Expected numPlayers to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("num_players", yearReleasedAnnotation.get().attributeName(),
                "Expected num_players as the numPlayers DynamoDB attribute name in the annotation.");
    }

}
