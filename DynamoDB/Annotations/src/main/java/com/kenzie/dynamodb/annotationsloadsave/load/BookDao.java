package com.kenzie.dynamodb.annotationsloadsave.load;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Data access object to retrieve Book items from DynamoDB.
 */
public class BookDao {

    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Book objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public BookDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /** Loads an item from the Books table and maps it to the Book Class.
     *
     * @param key is the value of the key being loaded
     * @return book returns the book loaded from the database
     */
    public Book getBook(String key) {
        //TODO: Implement
        return null;
    }
}
