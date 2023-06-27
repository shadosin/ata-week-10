package com.kenzie.discussion.dynamodb;

import java.util.Objects;

/**
 * A topic in the Discussion app, which may have many TopicMessages associated
 * with it.
 */
public class Topic {
    private static final int MAX_NAME_LENGTH = 50;

    private String name;
    private String description;
    private Boolean isArchived;

    /**
     * Constructs a name/description-less Topic.
     * This should only be used by DynamoDBMapper when loading an item.
     */
    public Topic() {
    }

    /**
     * Constructs a Topic with the given name and description.
     * @param name the topic name
     * @param description the topic description
     */
    public Topic(String name, String description) {
        setName(name);
        setDescription(description);
        setArchived(false);
    }

    public String getName() {
        return name;
    }

    /**
     * Sets the name, validating topic name isn't longer than MAX_NAME_LENGTH.
     * @param name Topic's new name
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Topic name must not be null!");
        } else if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Topic name cannot be longer than " + MAX_NAME_LENGTH);
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        return Objects.equals(name, topic.name) &&
               Objects.equals(description, topic.description) &&
               Objects.equals(isArchived, topic.isArchived);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, isArchived);
    }
}
