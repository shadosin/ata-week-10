package com.kenzie.discussion.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * A Discussion app member.
 */
@DynamoDBTable(tableName = "GroupWork-Members")
public class Member {
    private static final Integer STARTING_KARMA_POINTS = 1000;

    private String username;
    private Boolean active;
    private Integer karmaPointsAvailable;

    /**
     * Constructs username-less member.
     */
    public Member() {
        this(null);
    }

    /**
     * Constructs a member with the given username.
     * @param username Member's username
     */
    public Member(String username) {
        this.username = username;
        this.active = true;
        this.karmaPointsAvailable = STARTING_KARMA_POINTS;
    }

    /**
     * User-facing displayable representation of a member.
     * @return member representation
     */
    public String display() {
        return String.format("%s(%d)", username, karmaPointsAvailable);
    }

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "isActive")
    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean isActive) {
        this.active = isActive;
    }

    @DynamoDBAttribute(attributeName = "karmaPointsAvailable")
    public Integer getKarmaPointsAvailable() {
        return karmaPointsAvailable;
    }

    public void setKarmaPointsAvailable(Integer karmaPointsAvailable) {
        this.karmaPointsAvailable = karmaPointsAvailable;
    }

    @Override
    public String toString() {
        return "Member{" +
               "username='" + username + '\'' +
               ", isActive=" + active +
               ", karmaPointsAvailable=" + karmaPointsAvailable +
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
        Member member = (Member) o;
        return Objects.equals(username, member.username) &&
               Objects.equals(active, member.active) &&
               Objects.equals(karmaPointsAvailable, member.karmaPointsAvailable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, active, karmaPointsAvailable);
    }
}
