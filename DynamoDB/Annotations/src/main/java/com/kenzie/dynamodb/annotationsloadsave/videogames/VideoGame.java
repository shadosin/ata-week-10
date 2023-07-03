package com.kenzie.dynamodb.annotationsloadsave.videogames;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "DynamoDbAnnotationsLoadSave-VideoGames")
public class VideoGame {
    private String title;
    private String console;

    // optional parameters
    private Integer releaseYear;
    private String description;
    private Boolean hasSequel;
    private Integer numPlayers;

    @DynamoDBHashKey(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @DynamoDBRangeKey(attributeName = "console")
    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }
    @DynamoDBAttribute(attributeName = "release_year")
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @DynamoDBAttribute(attributeName = "has_sequel")
    public Boolean getHasSequel() {
        return hasSequel;
    }

    public void setHasSequel(Boolean hasSequel) {
        this.hasSequel = hasSequel;
    }
    @DynamoDBAttribute(attributeName = "num_players")
    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "title='" + title + '\'' +
                ", console='" + console + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VideoGame videoGame = (VideoGame) o;
        return Objects.equals(title, videoGame.title) &&
                Objects.equals(console, videoGame.console) &&
                Objects.equals(releaseYear, videoGame.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, console, releaseYear);
    }
}
